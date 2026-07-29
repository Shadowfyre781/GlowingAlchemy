package nox.shadowfyre.glowingalchemy.mechanic.gmoak;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.JsonCodecProvider;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

import java.util.concurrent.CompletableFuture;

public class GMOakMetadataProvider extends JsonCodecProvider<IntegrationMetadata> {

    public GMOakMetadataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
                output,
                PackOutput.Target.DATA_PACK,
                "gmoak_metadata",
                IntegrationMetadata.CODEC,
                lookupProvider,
                GlowingAlchemy.MODID
        );
    }

    @Override
    protected void gather() {
        add("iron_block", new IntegrationMetadata("minecraft", "iron_block", "iron_ingot", "iron_nugget", "ore", 0xD8D8D8));
        add("gold_block", new IntegrationMetadata("minecraft", "gold_block", "gold_ingot", "gold_nugget", "ore", 0xFCEE4B));
        add("copper_block", new IntegrationMetadata("minecraft", "copper_block", "copper_ingot", "copper_nugget", "ore", 0xB87333));
        add("netherite_block", new IntegrationMetadata("minecraft", "netherite_block", "netherite_ingot", "netherite_nugget", "ore", 0x444444));
    }

    private void add(String path, IntegrationMetadata metadata) {
        unconditional(Identifier.fromNamespaceAndPath(GlowingAlchemy.MODID, path), metadata);
    }
}