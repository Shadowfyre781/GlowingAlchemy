package nox.shadowfyre.glowingalchemy.mechanic.groundcover;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GroundcoverModelProvider implements DataProvider {
    private final PackOutput.PathProvider blockModels;
    private final PackOutput.PathProvider blockStates;
    private final PackOutput.PathProvider itemModels;

    public GroundcoverModelProvider(PackOutput output) {
        this.blockModels = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/block");
        this.blockStates = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.itemModels = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/item");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
/*
        for (var def : GroundcoverDefinitions.ALL) {
            BlockShapeTemplate archetype = def.archetype();

            for (String shape : archetype.getAssociatedShapes()) {
                if (shape.equals("block")) {
                    futures.add(saveBlockModel(output, def));
                    futures.add(saveBlockState(output, def));
                    futures.add(saveItemModel(output, def, "block"));
                } else {
                    futures.add(saveComplexShapeModel(output, def, shape));
                    futures.add(saveComplexShapeState(output, def, shape));
                    futures.add(saveItemModel(output, def, shape));
                }
            }


        }
*/
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));


    }

    private CompletableFuture<?> saveBlockModel(CachedOutput output, GroundcoverDefinitions.GroundcoverDefinition def) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/cube_bottom_top");

        JsonObject textures = new JsonObject();
        textures.addProperty("top", def.topTexture());
        textures.addProperty("side", def.topTexture());
        textures.addProperty("bottom", def.bottomTexture());

        root.add("textures", textures);

        Path path = blockModels.json(Identifier.fromNamespaceAndPath(def.namespace(), def.name()));
        return DataProvider.saveStable(output, root, path);
    }

    private CompletableFuture<?> saveBlockState(CachedOutput output, GroundcoverDefinitions.GroundcoverDefinition def) {
        JsonObject root = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject normal = new JsonObject();

        normal.addProperty("model", def.namespace() + ":block/" + def.name());
        variants.add("", normal);
        root.add("variants", variants);

        Path path = blockStates.json(Identifier.fromNamespaceAndPath(def.namespace(), def.name()));
        return DataProvider.saveStable(output, root, path);
    }

    private CompletableFuture<?> saveComplexShapeModel(CachedOutput output, GroundcoverDefinitions.GroundcoverDefinition def, String shape) {
        JsonObject root = new JsonObject();

        String parentTemplate = "minecraft:block/" + shape;
        if (shape.equals("fence")) parentTemplate = "minecraft:block/fence_side";
        if (shape.equals("fence_gate")) parentTemplate = "minecraft:block/fence_gate_closed";

        root.addProperty("parent", parentTemplate);

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", def.namespace() + ":block/" + def.name());
        textures.addProperty("bottom", def.bottomTexture());
        textures.addProperty("top", def.topTexture());
        textures.addProperty("side", def.topTexture());
        root.add("textures", textures);

        Path path = blockModels.json(Identifier.fromNamespaceAndPath(def.namespace(), def.name() + "_" + shape));
        return DataProvider.saveStable(output, root, path);
    }

    private CompletableFuture<?> saveComplexShapeState(CachedOutput output, GroundcoverDefinitions.GroundcoverDefinition def, String shape) {
        JsonObject root = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject normal = new JsonObject();

        normal.addProperty("model", def.namespace() + ":block/" + def.name() + "_" + shape);
        variants.add("", normal);
        root.add("variants", variants);

        Path path = blockStates.json(Identifier.fromNamespaceAndPath(def.namespace(), def.name() + "_" + shape));
        return DataProvider.saveStable(output, root, path);
    }

    private CompletableFuture<?> saveItemModel(CachedOutput output, GroundcoverDefinitions.GroundcoverDefinition def, String shape) {
        JsonObject root = new JsonObject();
        String modelPath = shape.equals("block") ? def.name() : def.name() + "_" + shape;
        root.addProperty("parent", def.namespace() + ":block/" + modelPath);

        Path path = itemModels.json(Identifier.fromNamespaceAndPath(def.namespace(), modelPath));
        return DataProvider.saveStable(output, root, path);
    }

    @Override
    public String getName() {
        return "Glowing Alchemy Groundcover Models";
    }
}