package nox.shadowfyre.glowingalchemy.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;


import nox.shadowfyre.glowingalchemy.GlowingAlchemy;
import nox.shadowfyre.glowingalchemy.registry.IntegrationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GMOakMetadataProvider implements DataProvider {
    private final PackOutput output;

    public GMOakMetadataProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        var pathProvider = this.output.createPathProvider(PackOutput.Target.DATA_PACK, "glowing_oak_metadata");
        List<CompletableFuture<?>> futures = new ArrayList<>();

        // Replace this with your actual list of metadata
        List<IntegrationMetadata> myMetadataList = new ArrayList<>();

        for (IntegrationMetadata meta : myMetadataList) {
            var path = pathProvider.json(ResourceLocation.fromNamespaceAndPath(GlowingAlchemy.MODID, meta.modId()));
            futures.add(DataProvider.saveStable(output, IntegrationMetadata.CODEC, meta, path));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "GMOak Metadata Generator";
    }
}