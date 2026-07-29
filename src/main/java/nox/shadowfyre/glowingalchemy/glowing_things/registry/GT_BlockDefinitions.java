package nox.shadowfyre.glowingalchemy.glowing_things.registry;

import nox.shadowfyre.glowingalchemy.blocks.BlockShapeTemplate;

import java.util.List;
import nox.shadowfyre.glowingalchemy.registry.BlockDefinition;


// AUTO-GENERATED from block_definitions_v2.csv. Do not hand-edit -- re-run gen_blockdefs.py instead.
//
// SKIPPED ROWS (need follow-up before they can generate):
//   - GlowCoral (brain,horn,fire,tube,bubble) - coral: needs nested type x shape x color loop, handled separately
//   - NecroCoral (brain,horn,fire,tube,bubble) - coral: needs nested type x shape x color loop, handled separately
//   - Citrine raw_budding - composite texture (block+overlay), needs compositing step like ores
//   - Lanternfruit seeds - likely needs a custom crop/seed Item class, not a plain Block
//   - Glow Lanternfruit seeds - likely needs a custom crop/seed Item class, not a plain Block
//   - GlowshroomCap - no texture reference specified yet
//   - GlowingMycelium - no texture reference specified yet
//   - ColoredMoss - no texture reference specified yet
//   - GlowingMoss - no texture reference specified yet
public class GT_BlockDefinitions {
    public static final List<BlockDefinition> ALL = List.of(

            new BlockDefinition("GlowDirt", "dirt", null, "full16", List.of("dirt"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowoak_leaves"),
        new BlockDefinition("GlowOak", "leaves", 15, "rainbow", List.of("pale_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowoak_leaves"),
        new BlockDefinition("GlowOak", "log", 10, "full16", List.of("pale_log_top", "oak"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glowoak_log"),
        new BlockDefinition("GlowOak", "plank", 15, "full16", List.of("pale_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowing_things", "{color}_glowoak_plank"),
        new BlockDefinition("RainbOak", "leaves", null, "full16", List.of("pale_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_rainboak_leaves"),
        new BlockDefinition("RainbOak", "leaves", null, "rainbow", List.of("pale_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_rainboak_leaves"),
        new BlockDefinition("RainbOak", "log", null, "full16", List.of("paleoak_log_top", "paleoak_log"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_rainboak_log"),
        new BlockDefinition("RainbOak", "plank", null, "full16", List.of("pale_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowing_things", "{color}_rainboak_plank"),
        new BlockDefinition("Asphalt", "standard", null, "full16", List.of("asphalt"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_asphalt"),
        new BlockDefinition("Glow Asphalt", "glowing", 8, "full16", List.of("asphalt"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glow_asphalt"),
        new BlockDefinition("Concrete", "standard", null, "full16", List.of("concrete"), BlockShapeTemplate.ADD_ON_MASONRY_SET, "glowing_things", "{color}_concrete"),
        new BlockDefinition("Glow Crete", "glowing", 8, "full16", List.of("concrete"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glowcrete"),
        new BlockDefinition("Colored Stone", "standard", null, "full16", List.of("stone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_stone"),
        new BlockDefinition("Colored Stone", "standard", null, "pastel", List.of("stone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_stone"),
        new BlockDefinition("Colored Stone", "standard", null, "bold", List.of("stone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_stone"),
        new BlockDefinition("Glowing Stone", "glowing", 8, "full16", List.of("stone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone"),
        new BlockDefinition("Glowing Stone", "glowing", 8, "pastel", List.of("stone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone"),
        new BlockDefinition("Glowing Stone", "glowing", 8, "bold", List.of("stone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone"),
        new BlockDefinition("Colored Stone Brick", "standard", null, "full16", List.of("stone_brick"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_stone_brick"),
        new BlockDefinition("Colored Stone Brick", "standard", null, "pastel", List.of("stone_brick"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_stone_brick"),
        new BlockDefinition("Colored Stone Brick", "standard", null, "bold", List.of("stone_brick"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_stone_brick"),
        new BlockDefinition("Glowing Stone Brick", "glowing", 8, "full16", List.of("stone_brick"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone_brick"),
        new BlockDefinition("Glowing Stone Brick", "glowing", 8, "pastel", List.of("stone_brick"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone_brick"),
        new BlockDefinition("Glowing Stone Brick", "glowing", 8, "bold", List.of("stone_brick"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone_brick"),
        new BlockDefinition("Colored Cobble", "standard", null, "full16", List.of("cobblestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_cobblestone"),
        new BlockDefinition("Colored Cobble", "standard", null, "pastel", List.of("cobblestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_cobblestone"),
        new BlockDefinition("Colored Cobble", "standard", null, "bold", List.of("cobblestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_cobblestone"),
        new BlockDefinition("Glowing Cobble", "glowing", 8, "full16", List.of("cobblestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowcobble"),
        new BlockDefinition("Glowing Cobble", "glowing", 8, "pastel", List.of("cobblestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowcobble"),
        new BlockDefinition("Glowing Cobble", "glowing", 8, "bold", List.of("cobblestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowcobble"),
        new BlockDefinition("Plastic", "standard", null, "full16", List.of("plastic"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_plastic"),
        new BlockDefinition("Plastic", "standard", null, "pastel", List.of("plastic"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_plastic"),
        new BlockDefinition("Plastic", "standard", null, "bold", List.of("plastic"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_plastic"),
        new BlockDefinition("Glowplastic", "glowing", 15, "full16", List.of("plastic"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowplastic"),
        new BlockDefinition("Glowplastic", "glowing", 15, "pastel", List.of("plastic"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowplastic"),
        new BlockDefinition("Glowplastic", "glowing", 15, "bold", List.of("plastic"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowplastic"),
        new BlockDefinition("Colored Sand", "standard", null, "full16", List.of("sand"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_sand"),
        new BlockDefinition("Colored Sand", "standard", null, "pastel", List.of("sand"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_sand"),
        new BlockDefinition("Colored Sand", "standard", null, "bold", List.of("sand"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_sand"),
        new BlockDefinition("Glowsand", "glowing", 8, "full16", List.of("sand"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowsand"),
        new BlockDefinition("Glowsand", "glowing", 8, "pastel", List.of("sand"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowsand"),
        new BlockDefinition("Glowsand", "glowing", 8, "bold", List.of("sand"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowsand"),
        new BlockDefinition("Colored Sandstone", "standard", null, "full16", List.of("sandstone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_sandstone"),
        new BlockDefinition("Colored Sandstone", "standard", null, "pastel", List.of("sandstone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_sandstone"),
        new BlockDefinition("Colored Sandstone", "standard", null, "bold", List.of("sandstone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_sandstone"),
        new BlockDefinition("Glowing Sandstone", "glowing", 8, "full16", List.of("sandstone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_sandstone"),
        new BlockDefinition("Glowing Sandstone", "glowing", 8, "pastel", List.of("sandstone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_sandstone"),
        new BlockDefinition("Glowing Sandstone", "glowing", 8, "bold", List.of("sandstone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_sandstone"),
        new BlockDefinition("Colored Glass", "standard", null, "full16", List.of("glass"), BlockShapeTemplate.ADD_ON_MASONRY_SET, "glowing_things", "{color}_glass"),
        new BlockDefinition("Illumiglass", "block", 15, "full16", List.of("glass"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_illumiglass"),
        new BlockDefinition("Illumiglass", "clear_block", 15, "unique", List.of("glass"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "illumiglass"),
        new BlockDefinition("Clover", "block", null, "full16", List.of("clover_block"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_clover"),
        new BlockDefinition("Clay", "block", null, "full16", List.of("minecraft:clay"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_clay"),
        new BlockDefinition("GlowClay", "block", null, "full16", List.of("minecraft:clay"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glow_clay"),
        new BlockDefinition("Citrine", "ore", null, "unique", List.of("citrine_ore"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "citrine_ore"),
        new BlockDefinition("Citrine", "block", 15, "full16", List.of("citrine_block"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_citrine"),
        new BlockDefinition("Glowmist", "standard", 15, "unique", List.of("fog"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "glowmist"),
        new BlockDefinition("GlowIce", "standard", 5, "pastel", List.of("minecraft:ice"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glow_ice"),
        new BlockDefinition("GlowIce", "standard", 5, "monochrome", List.of("minecraft:ice"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glow_ice"),
        new BlockDefinition("GlowingPackedIce", "standard", 10, "full16", List.of("minecraft:packed_ice"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glow_packed_ice"),
        new BlockDefinition("GlowingPermafrost", "standard", 15, "bold", List.of("minecraft:blue_ice"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glow_permafrost"),
        new BlockDefinition("GlowingPermafrost", "standard", 15, "monochrome", List.of("minecraft:blue_ice"), BlockShapeTemplate.MASONRY_SET, "glowing_things", "{color}_glow_permafrost"),

        // -- Glowing Things Non-Blocks --
        new BlockDefinition("Illumiglass", "pane", 15, "full16", List.of("glass_pane"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_illumiglass_pane"),
        new BlockDefinition("Illumiglass", "clear_pane", 15, "unique", List.of("glass_pane"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "illumiglass_pane"),
        new BlockDefinition("Clover", "carpet", null, "full16", List.of("clover_carpet"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_clover_carpet"),
        new BlockDefinition("Clover", "growth", null, "full16", List.of("clover_growth"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_clover_carpet"),
        new BlockDefinition("Clover", "flowers", null, "full16", List.of("clover_flowers"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_clover_carpet"),
        new BlockDefinition("Citrine", "bud", null, "full16", List.of("citrine_bud"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_citrine_bud"),
        new BlockDefinition("Citrine", "bud1", null, "full16", List.of("citrine_bud_1"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_citrine_bud1"),
        new BlockDefinition("Citrine", "bud2", null, "full16", List.of("citrine_bud_2"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_citrine_bud2"),
        new BlockDefinition("Citrine", "crystal", 15, "full16", List.of("citrine_crystal"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_citrine_crystal"),
        new BlockDefinition("Citrine", "raw_crystal", null, "unique", List.of("citrine_crystal"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "raw_citrine_crystal"),
        new BlockDefinition("Citrine", "raw_bud", null, "unique", List.of("citrine_bud"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "raw_citrine_bud"),
        new BlockDefinition("Citrine", "raw_bud1", null, "unique", List.of("citrine_bud_1"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "raw_citrine_bud1"),
        new BlockDefinition("Citrine", "raw_bud2", null, "unique", List.of("citrine_bud_2"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "raw_citrine_bud2"),
        new BlockDefinition("Citrine", "crystal", 15, "rainbow", List.of("citrine_crystal"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_citrine_crystal"),
        new BlockDefinition("Lanternfruit", "bud", 5, "full16", List.of("glowlantern_0"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_lanternfruit_bud"),
        new BlockDefinition("Lanternfruit", "bud1", 5, "full16", List.of("glowlantern_1"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_lanternfruit_bud1"),
        new BlockDefinition("Lanternfruit", "bud2", 5, "full16", List.of("glowlantern_2"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_lanternfruit_bud2"),
        new BlockDefinition("Glow Lanternfruit", "bud", 10, "full16", List.of("glowlantern_0"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glow_lanternfruit_bud"),
        new BlockDefinition("Glow Lanternfruit", "bud1", 10, "full16", List.of("glowlantern_1"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glow_lanternfruit_bud1"),
        new BlockDefinition("Glow Lanternfruit", "bud2", 10, "full16", List.of("glowlantern_2"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glow_lanternfruit_bud2"),
        new BlockDefinition("Glowwater", "source", 15, "full16", List.of("minecraft:water_source"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowwater_source"),
        new BlockDefinition("Glowwater", "flowing", 15, "full16", List.of("minecraft:water_flowing"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_glowwater_flowing"),
        new BlockDefinition("TinyGlowShrooms", "standard", 15, "full16", List.of("minecraft:mushroom"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_tiny_glowshroom"),
        new BlockDefinition("SmallGlowShroom", "standard", 12, "full16", List.of("small_mushroom"), BlockShapeTemplate.SINGLE_ENTRY, "glowing_things", "{color}_small_glowshroom")
        );}