package nox.shadowfyre.glowingalchemy.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
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
        CompletableFuture<?>[] futures = GroundcoverDefinitions.ALL.stream()
                .flatMap(def -> java.util.stream.Stream.of(
                        saveBlockModel(output, def),
                        saveBlockState(output, def),
                        saveItemModel(output, def)
                ))
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures);
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

    private CompletableFuture<?> saveItemModel(CachedOutput output, GroundcoverDefinitions.GroundcoverDefinition def) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", def.namespace() + ":block/" + def.name());

        Path path = itemModels.json(Identifier.fromNamespaceAndPath(def.namespace(), def.name()));
        return DataProvider.saveStable(output, root, path);
    }

    @Override
    public String getName() {
        return "Glowing Alchemy Groundcover Models";
    }
}