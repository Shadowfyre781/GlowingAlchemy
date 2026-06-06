package nox.shadowfyre.glowingalchemy.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class GroundcoverBlockTagProvider implements DataProvider {
    private final PackOutput.PathProvider blockTags;

    public GroundcoverBlockTagProvider(PackOutput output) {
        this.blockTags = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/block");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        Map<String, List<GroundcoverDefinitions.GroundcoverDefinition>> byFamily =
                GroundcoverDefinitions.ALL.stream()
                        .collect(Collectors.groupingBy(GroundcoverDefinitions.GroundcoverDefinition::spreadFamily));

        CompletableFuture<?> sideSpreaders = saveTag(
                output,
                Identifier.fromNamespaceAndPath("glowingalchemy", "side_spreaders"),
                GroundcoverDefinitions.ALL
        );

        CompletableFuture<?>[] familyTags = byFamily.entrySet().stream()
                .map(entry -> saveTag(
                        output,
                        Identifier.fromNamespaceAndPath("glowingalchemy", "spread_sources/" + entry.getKey()),
                        entry.getValue()
                ))
                .toArray(CompletableFuture[]::new);

        CompletableFuture<?>[] all = new CompletableFuture[familyTags.length + 1];
        all[0] = sideSpreaders;
        System.arraycopy(familyTags, 0, all, 1, familyTags.length);

        return CompletableFuture.allOf(all);
    }

    private CompletableFuture<?> saveTag(
            CachedOutput output,
            Identifier tagId,
            List<GroundcoverDefinitions.GroundcoverDefinition> definitions
    ) {
        JsonObject root = new JsonObject();
        root.addProperty("replace", false);

        JsonArray values = new JsonArray();

        for (GroundcoverDefinitions.GroundcoverDefinition def : definitions) {
            values.add(def.namespace() + ":" + def.name());
        }

        root.add("values", values);

        Path path = blockTags.json(tagId);
        return DataProvider.saveStable(output, root, path);
    }

    @Override
    public String getName() {
        return "Glowing Alchemy Groundcover Block Tags";
    }
}