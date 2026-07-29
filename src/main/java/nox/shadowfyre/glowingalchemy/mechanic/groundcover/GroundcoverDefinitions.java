package nox.shadowfyre.glowingalchemy.mechanic.groundcover;

import nox.shadowfyre.glowingalchemy.registry.BlockShapes;

import java.util.List;

public final class GroundcoverDefinitions {
    private GroundcoverDefinitions() {
    }

    public record GroundcoverDefinition(
            String namespace,
            String name,
            String topTexture,
            String bottomTexture,
            String spreadFamily
    ) {
        // Dynamically supplies the BlockShape to the model provider
        public BlockShapes archetype() {
            // All current definitions here are standalone custom blocks
            return BlockShapes.SINGLE_ENTRY;
        }
    }

    public static final List<GroundcoverDefinition> ALL = List.of(
// Glowgrass Variants
            new GroundcoverDefinition("glowing_things", "white_glowgrass", "glowing_things:block/white_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "light_gray_glowgrass", "glowing_things:block/light_gray_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "gray_glowgrass", "glowing_things:block/gray_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "black_glowgrass", "glowing_things:block/black_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "brown_glowgrass", "glowing_things:block/brown_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "red_glowgrass", "glowing_things:block/red_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "orange_glowgrass", "glowing_things:block/orange_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "yellow_glowgrass", "glowing_things:block/yellow_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "lime_glowgrass", "glowing_things:block/lime_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "green_glowgrass", "glowing_things:block/green_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "cyan_glowgrass", "glowing_things:block/cyan_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "light_blue_glowgrass", "glowing_things:block/light_blue_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "blue_glowgrass", "glowing_things:block/blue_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "purple_glowgrass", "glowing_things:block/purple_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "magenta_glowgrass", "glowing_things:block/magenta_glowgrass_top", "minecraft:block/dirt", "glowgrass"),
            new GroundcoverDefinition("glowing_things", "pink_glowgrass", "glowing_things:block/pink_glowgrass_top", "minecraft:block/dirt", "glowgrass"),

// Glowmycelium Variants
            new GroundcoverDefinition("glowing_things", "white_glowmycelium", "glowing_things:block/white_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "light_gray_glowmycelium", "glowing_things:block/light_gray_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "gray_glowmycelium", "glowing_things:block/gray_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "black_glowmycelium", "glowing_things:block/black_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "brown_glowmycelium", "glowing_things:block/brown_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "red_glowmycelium", "glowing_things:block/red_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "orange_glowmycelium", "glowing_things:block/orange_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "yellow_glowmycelium", "glowing_things:block/yellow_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "lime_glowmycelium", "glowing_things:block/lime_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "green_glowmycelium", "glowing_things:block/green_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "cyan_glowmycelium", "glowing_things:block/cyan_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "light_blue_glowmycelium", "glowing_things:block/light_blue_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "blue_glowmycelium", "glowing_things:block/blue_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "purple_glowmycelium", "glowing_things:block/purple_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "magenta_glowmycelium", "glowing_things:block/magenta_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),
            new GroundcoverDefinition("glowing_things", "pink_glowmycelium", "glowing_things:block/pink_glowmycelium_top", "minecraft:block/dirt", "glowmycelium"),

// Clover Variant
            new GroundcoverDefinition("glowing_things", "clover", "glowing_things:block/clover_top", "minecraft:block/dirt", "clover")
    );
}
