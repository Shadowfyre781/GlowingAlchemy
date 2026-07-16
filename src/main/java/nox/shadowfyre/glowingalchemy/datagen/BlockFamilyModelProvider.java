package nox.shadowfyre.glowingalchemy.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import nox.shadowfyre.glowingalchemy.registry.BlockDefinition;
import nox.shadowfyre.glowingalchemy.registry.BlockFamilyRegistry;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Generates blockstates/models/item-models for everything in BlockFamilyRegistry.
 *
 * LIMITATION: only generates the "block" shape right now, matching what
 * BlockFamilyRegistry currently registers. Stairs/slabs/walls/etc. from
 * MASONRY_SET / DEEP_MASONRY_SET / WOOD_PLANK_SET are not yet generated.
 *
 * Textures with no ':' are resolved relative to the block's own namespace
 * (e.g. "stone" under a glowing_things block -> glowing_things:block/stone)
 * UNLESS they appear in VANILLA_SOURCED, in which case they resolve against
 * minecraft: instead (per Mike's call to source stone/cobblestone/etc. directly
 * from vanilla rather than keeping local copies).
 */
public class BlockFamilyModelProvider implements DataProvider {

    // texture name (as written in the CSV) -> actual vanilla texture id
    private static final Map<String, String> VANILLA_SOURCED = Map.of(
            "stone", "stone",
            "cobblestone", "cobblestone",
            "stone_brick", "stone_bricks",
            "sand", "sand",
            "sandstone", "sandstone",
            "glass", "glass",
            "asphalt", "light_gray_concrete_powder",
            "glowwool", "wool",
            "illumiglass", "glass",
            "glowcrete", "concrete"
    );

    // textures that need special non-cube handling and aren't generated yet
    private static final List<String> SKIP_TEXTURES = List.of(
            "minecraft:water_source", "minecraft:water_flowing", "minecraft:mushroom", "small_mushroom",
            "clover_base", "clover_main", "citrine_bud_1", "citrine_bud_2", "citrine_bud_lg", "citrine_crystal"
    );

    private final PackOutput.PathProvider blockModels;
    private final PackOutput.PathProvider blockStates;
    private final PackOutput.PathProvider clientItems;

    public BlockFamilyModelProvider(PackOutput output) {
        this.blockModels = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/block");
        this.blockStates = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.clientItems = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        // Shared tinted templates -- written for both namespaces so models in either can resolve them
        futures.add(saveTintedCubeAllTemplate(output, "glowingalchemy"));
        futures.add(saveTintedCubeAllTemplate(output, "glowing_things"));
        futures.add(saveTintedCubeColumnTemplate(output, "glowingalchemy"));
        futures.add(saveTintedCubeColumnTemplate(output, "glowing_things"));

        for (var entry : BlockFamilyRegistry.REGISTERED_DEFS.entrySet()) {
            String id = entry.getKey();
            BlockDefinition def = entry.getValue();

            if (def.textures().stream().anyMatch(SKIP_TEXTURES::contains)) {
                continue; // liquids / ambiguous vanilla refs -- needs manual handling, not auto-gen
            }

            boolean tinted = BlockFamilyRegistry.BLOCK_TINTS.containsKey(id);
            futures.add(saveBlockModel(output, def, id, tinted));
            futures.add(saveBlockState(output, def, id));
            Integer tintColor = tinted ? BlockFamilyRegistry.BLOCK_TINTS.get(id).colorHex() : null;
            futures.add(saveClientItem(output, def, id, tintColor));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    /**
     * Helper to resolve where a texture should point.
     * If the block is tinted/glowing, we bypass VANILLA_SOURCED so it grabs your local asset.
     */
    private String resolveTexture(String raw, String namespace, boolean tinted) {
        if (raw.contains(":")) {
            // already a full reference like "minecraft:clay" -- normalize to block/ path
            String[] parts = raw.split(":", 2);
            String path = parts[1].startsWith("block/") ? parts[1] : "block/" + parts[1];
            return parts[0] + ":" + path;
        }
        String normalized = raw.trim().toLowerCase().replace(" ", "_");

        // If it is NOT tinted and matches a vanilla block, source it from minecraft
        if (!tinted && VANILLA_SOURCED.containsKey(normalized)) {
            return "minecraft:block/" + VANILLA_SOURCED.get(normalized);
        }

        // Default to block's native namespace
        return namespace + ":block/" + normalized;
    }

    private CompletableFuture<?> saveBlockModel(CachedOutput output, BlockDefinition def, String id, boolean tinted) {
        JsonObject root = new JsonObject();
        JsonObject textures = new JsonObject();
        List<String> tex = def.textures();

        if (tex.size() >= 2) {
            // [top/bottom, side] -- cube_column style
            root.addProperty("parent", tinted
                    ? def.namespace() + ":block/template_tinted_cube_column"
                    : "minecraft:block/cube_column");
            textures.addProperty("end", resolveTexture(tex.get(0), def.namespace(), tinted));
            textures.addProperty("side", resolveTexture(tex.get(1), def.namespace(), tinted));
        } else {
            root.addProperty("parent", tinted
                    ? def.namespace() + ":block/template_tinted_cube_all"
                    : "minecraft:block/cube_all");
            textures.addProperty("all", resolveTexture(tex.get(0), def.namespace(), tinted));
        }

        // Tell NeoForge's model loader to render this on the translucent pass!
        if (def.family().equals("Illumiglass")) {
            root.addProperty("render_type", "minecraft:translucent");
        }

        root.add("textures", textures);
        Path path = blockModels.json(Identifier.fromNamespaceAndPath(def.namespace(), id));
        return DataProvider.saveStable(output, root, path);
    }

    private CompletableFuture<?> saveBlockState(CachedOutput output, BlockDefinition def, String id) {
        JsonObject root = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject normal = new JsonObject();
        normal.addProperty("model", def.namespace() + ":block/" + id);
        variants.add("", normal);
        root.add("variants", variants);

        Path path = blockStates.json(Identifier.fromNamespaceAndPath(def.namespace(), id));
        return DataProvider.saveStable(output, root, path);
    }

    /**
     * Writes the "Client Item" file this NeoForge version actually reads
     * (assets/<namespace>/items/<id>.json), pointing straight at the block
     * model. Tinted blocks get a "minecraft:constant" tint source baked in
     * for tintindex 0 -- no separate ItemTintSource event needed for a
     * fixed color.
     */
    private CompletableFuture<?> saveClientItem(CachedOutput output, BlockDefinition def, String id, Integer tintColor) {
        JsonObject root = new JsonObject();
        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:model");
        model.addProperty("model", def.namespace() + ":block/" + id);

        if (tintColor != null) {
            com.google.gson.JsonArray tints = new com.google.gson.JsonArray();
            JsonObject constant = new JsonObject();
            constant.addProperty("type", "minecraft:constant");
            constant.addProperty("value", tintColor);
            tints.add(constant);
            model.add("tints", tints);
        }

        root.add("model", model);
        Path path = clientItems.json(Identifier.fromNamespaceAndPath(def.namespace(), id));
        return DataProvider.saveStable(output, root, path);
    }

    // --- shared tint templates, written once per datagen run ---

    private CompletableFuture<?> saveTintedCubeAllTemplate(CachedOutput output, String namespace) {
        JsonObject root = new JsonObject();
        root.addProperty("ambientocclusion", true);
        JsonObject textures = new JsonObject();
        textures.addProperty("particle", "#all");
        root.add("textures", textures);

        com.google.gson.JsonArray elements = new com.google.gson.JsonArray();
        JsonObject element = new JsonObject();
        com.google.gson.JsonArray from = new com.google.gson.JsonArray();
        from.add(0); from.add(0); from.add(0);
        com.google.gson.JsonArray to = new com.google.gson.JsonArray();
        to.add(16); to.add(16); to.add(16);
        element.add("from", from);
        element.add("to", to);

        JsonObject faces = new JsonObject();
        for (String face : new String[]{"down", "up", "north", "south", "west", "east"}) {
            JsonObject faceObj = new JsonObject();
            faceObj.addProperty("texture", "#all");
            faceObj.addProperty("tintindex", 0);
            faces.add(face, faceObj);
        }
        element.add("faces", faces);
        elements.add(element);
        root.add("elements", elements);

        Path path = blockModels.json(Identifier.fromNamespaceAndPath(namespace, "template_tinted_cube_all"));
        return DataProvider.saveStable(output, root, path);
    }

    private CompletableFuture<?> saveTintedCubeColumnTemplate(CachedOutput output, String namespace) {
        JsonObject root = new JsonObject();
        root.addProperty("ambientocclusion", true);
        JsonObject textures = new JsonObject();
        textures.addProperty("particle", "#side");
        root.add("textures", textures);

        com.google.gson.JsonArray elements = new com.google.gson.JsonArray();
        JsonObject element = new JsonObject();
        com.google.gson.JsonArray from = new com.google.gson.JsonArray();
        from.add(0); from.add(0); from.add(0);
        com.google.gson.JsonArray to = new com.google.gson.JsonArray();
        to.add(16); to.add(16); to.add(16);
        element.add("from", from);
        element.add("to", to);

        JsonObject faces = new JsonObject();
        for (String face : new String[]{"down", "up"}) {
            JsonObject faceObj = new JsonObject();
            faceObj.addProperty("texture", "#end");
            faceObj.addProperty("tintindex", 0);
            faces.add(face, faceObj);
        }
        for (String face : new String[]{"north", "south", "west", "east"}) {
            JsonObject faceObj = new JsonObject();
            faceObj.addProperty("texture", "#side");
            faceObj.addProperty("tintindex", 0);
            faces.add(face, faceObj);
        }
        element.add("faces", faces);
        elements.add(element);
        root.add("elements", elements);

        Path path = blockModels.json(Identifier.fromNamespaceAndPath(namespace, "template_tinted_cube_column"));
        return DataProvider.saveStable(output, root, path);
    }

    @Override
    public String getName() {
        return "Glowing Alchemy Block Family Models";
    }
}