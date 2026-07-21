package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.blocks.BlockArchetype;
import java.util.List;

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
public class BlockDefinitions {
    public static final List<BlockDefinition> ALL = List.of(

            // ==========================================
            // 1. GLOWING ALCHEMY (Standard Block Shapes)
            // ==========================================
            new BlockDefinition("Limestone", "standard_base", null, "unique", List.of("limestone"), BlockArchetype.DEEP_MASONRY_SET, "glowingalchemy", "limestone"),
            new BlockDefinition("Limestone", "mossy", null, "unique", List.of("mossy_limestone"), BlockArchetype.DEEP_MASONRY_SET, "glowingalchemy", "limestone_mossy"),
            new BlockDefinition("Marble", "standard_base", null, "unique", List.of("marble"), BlockArchetype.DEEP_MASONRY_SET, "glowingalchemy", "marble"),
            new BlockDefinition("Marble", "mossy", null, "unique", List.of("mossy_marble"), BlockArchetype.DEEP_MASONRY_SET, "glowingalchemy", "marble_mossy"),
            new BlockDefinition("Maple", "leaves", null, "red,green,bold_purple,pink,pastel_purple", List.of("maple_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "{color}_maple_leaves"),
            new BlockDefinition("Maple", "log", null, "unique", List.of("maple_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "maple_log"),
            new BlockDefinition("Maple", "plank", null, "unique", List.of("maple_planks"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "maple_plank"),
            new BlockDefinition("Chestnut", "leaves", null, "unique", List.of("chestnut_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "chestnut_leaves"),
            new BlockDefinition("Chestnut", "log", null, "unique", List.of("chestnut_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "chestnut_log"),
            new BlockDefinition("Chestnut", "plank", null, "unique", List.of("chestnut_plank"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "chestnut_plank"),
            new BlockDefinition("Evergreen", "leaves", null, "unique", List.of("evergreen_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "evergreen_leaves"),
            new BlockDefinition("Evergreen", "log", null, "unique", List.of("evergreen_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "evergreen_log"),
            new BlockDefinition("Evergreen", "plank", null, "unique", List.of("evergreen_planks"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "evergreen_plank"),
            new BlockDefinition("Palm", "leaves", null, "unique", List.of("palm_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "palm_leaves"),
            new BlockDefinition("Palm", "log", null, "unique", List.of("palm_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "palm_log"),
            new BlockDefinition("Palm", "plank", null, "unique", List.of("palm_planks"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "palm_plank"),
            new BlockDefinition("Willow", "leaves", null, "unique", List.of("willow_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "willow_leaves"),
            new BlockDefinition("Willow", "canes", null, "unique", List.of("willow_canes"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "willow_canes"),
            new BlockDefinition("Willow", "log", null, "unique", List.of("willow_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "willow_log"),
            new BlockDefinition("Willow", "plank", null, "unique", List.of("willow_planks"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "willow_plank"),
            new BlockDefinition("Redwood", "canes", null, "unique", List.of("redwood_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "redwood_plank"),
            new BlockDefinition("Redwood", "log", null, "unique", List.of("redwood_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "redwood_log"),
            new BlockDefinition("Redwood", "plank", null, "unique", List.of("redwood_planks"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "willow_plank"),
            new BlockDefinition("Aspen", "canes", null, "unique", List.of("aspen_leaves"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "aspen_leaves"),
            new BlockDefinition("Aspen", "log", null, "unique", List.of("aspen_log"), BlockArchetype.MASONRY_SET, "glowingalchemy", "aspen_log"),
            new BlockDefinition("Aspen", "plank", null, "unique", List.of("aspen_planks"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "aspen_plank"),
            new BlockDefinition("Aspen", "roots", null, "unique", List.of("aspen_roots"), BlockArchetype.WOOD_PLANK_SET, "glowingalchemy", "aspen_plank"),

            // ==========================================
            // 2. GLOWING THINGS (Standard Block Shapes)
            // ==========================================
            new BlockDefinition("GlowDirt", "dirt", null, "full16", List.of("dirt"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowoak_leaves"),
            new BlockDefinition("GlowOak", "leaves", 15, "rainbow", List.of("pale_leaves"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowoak_leaves"),
            new BlockDefinition("GlowOak", "log", 10, "full16", List.of("pale_log_top", "oak"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glowoak_log"),
            new BlockDefinition("GlowOak", "plank", 15, "full16", List.of("pale_planks"), BlockArchetype.WOOD_PLANK_SET, "glowing_things", "{color}_glowoak_plank"),
            new BlockDefinition("RainbOak", "leaves", null, "full16", List.of("pale_leaves"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_rainboak_leaves"),
            new BlockDefinition("RainbOak", "leaves", null, "rainbow", List.of("pale_leaves"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_rainboak_leaves"),
            new BlockDefinition("RainbOak", "log", null, "full16", List.of("paleoak_log_top", "paleoak_log"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_rainboak_log"),
            new BlockDefinition("RainbOak", "plank", null, "full16", List.of("pale_planks"), BlockArchetype.WOOD_PLANK_SET, "glowing_things", "{color}_rainboak_plank"),
            new BlockDefinition("Asphalt", "standard", null, "full16", List.of("asphalt"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_asphalt"),
            new BlockDefinition("Glow Asphalt", "glowing", 8, "full16", List.of("asphalt"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glow_asphalt"),
            new BlockDefinition("Concrete", "standard", null, "full16", List.of("concrete"), BlockArchetype.ADD_ON_MASONRY_SET, "glowing_things", "{color}_concrete"),
            new BlockDefinition("Glow Crete", "glowing", 8, "full16", List.of("concrete"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glowcrete"),
            new BlockDefinition("Colored Stone", "standard", null, "full16", List.of("stone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_stone"),
            new BlockDefinition("Colored Stone", "standard", null, "pastel", List.of("stone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_stone"),
            new BlockDefinition("Colored Stone", "standard", null, "bold", List.of("stone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_stone"),
            new BlockDefinition("Glowing Stone", "glowing", 8, "full16", List.of("stone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone"),
            new BlockDefinition("Glowing Stone", "glowing", 8, "pastel", List.of("stone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone"),
            new BlockDefinition("Glowing Stone", "glowing", 8, "bold", List.of("stone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone"),
            new BlockDefinition("Colored Stone Brick", "standard", null, "full16", List.of("stone_brick"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_stone_brick"),
            new BlockDefinition("Colored Stone Brick", "standard", null, "pastel", List.of("stone_brick"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_stone_brick"),
            new BlockDefinition("Colored Stone Brick", "standard", null, "bold", List.of("stone_brick"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_stone_brick"),
            new BlockDefinition("Glowing Stone Brick", "glowing", 8, "full16", List.of("stone_brick"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone_brick"),
            new BlockDefinition("Glowing Stone Brick", "glowing", 8, "pastel", List.of("stone_brick"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone_brick"),
            new BlockDefinition("Glowing Stone Brick", "glowing", 8, "bold", List.of("stone_brick"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_stone_brick"),
            new BlockDefinition("Colored Cobble", "standard", null, "full16", List.of("cobblestone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_cobblestone"),
            new BlockDefinition("Colored Cobble", "standard", null, "pastel", List.of("cobblestone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_cobblestone"),
            new BlockDefinition("Colored Cobble", "standard", null, "bold", List.of("cobblestone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_cobblestone"),
            new BlockDefinition("Glowing Cobble", "glowing", 8, "full16", List.of("cobblestone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowcobble"),
            new BlockDefinition("Glowing Cobble", "glowing", 8, "pastel", List.of("cobblestone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowcobble"),
            new BlockDefinition("Glowing Cobble", "glowing", 8, "bold", List.of("cobblestone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowcobble"),
            new BlockDefinition("Plastic", "standard", null, "full16", List.of("plastic"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_plastic"),
            new BlockDefinition("Plastic", "standard", null, "pastel", List.of("plastic"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_plastic"),
            new BlockDefinition("Plastic", "standard", null, "bold", List.of("plastic"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_plastic"),
            new BlockDefinition("Glowplastic", "glowing", 15, "full16", List.of("plastic"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowplastic"),
            new BlockDefinition("Glowplastic", "glowing", 15, "pastel", List.of("plastic"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowplastic"),
            new BlockDefinition("Glowplastic", "glowing", 15, "bold", List.of("plastic"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowplastic"),
            new BlockDefinition("Colored Sand", "standard", null, "full16", List.of("sand"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_sand"),
            new BlockDefinition("Colored Sand", "standard", null, "pastel", List.of("sand"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_sand"),
            new BlockDefinition("Colored Sand", "standard", null, "bold", List.of("sand"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_sand"),
            new BlockDefinition("Glowsand", "glowing", 8, "full16", List.of("sand"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowsand"),
            new BlockDefinition("Glowsand", "glowing", 8, "pastel", List.of("sand"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowsand"),
            new BlockDefinition("Glowsand", "glowing", 8, "bold", List.of("sand"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowsand"),
            new BlockDefinition("Colored Sandstone", "standard", null, "full16", List.of("sandstone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_sandstone"),
            new BlockDefinition("Colored Sandstone", "standard", null, "pastel", List.of("sandstone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_sandstone"),
            new BlockDefinition("Colored Sandstone", "standard", null, "bold", List.of("sandstone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_sandstone"),
            new BlockDefinition("Glowing Sandstone", "glowing", 8, "full16", List.of("sandstone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_sandstone"),
            new BlockDefinition("Glowing Sandstone", "glowing", 8, "pastel", List.of("sandstone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_sandstone"),
            new BlockDefinition("Glowing Sandstone", "glowing", 8, "bold", List.of("sandstone"), BlockArchetype.DEEP_MASONRY_SET, "glowing_things", "{color}_glowing_sandstone"),
            new BlockDefinition("Colored Glass", "standard", null, "full16", List.of("glass"), BlockArchetype.ADD_ON_MASONRY_SET, "glowing_things", "{color}_glass"),
            new BlockDefinition("Illumiglass", "block", 15, "full16", List.of("glass"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_illumiglass"),
            new BlockDefinition("Illumiglass", "clear_block", 15, "unique", List.of("glass"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "illumiglass"),
            new BlockDefinition("Clover", "block", null, "full16", List.of("clover_block"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_clover"),
            new BlockDefinition("Clay", "block", null, "full16", List.of("minecraft:clay"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_clay"),
            new BlockDefinition("GlowClay", "block", null, "full16", List.of("minecraft:clay"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glow_clay"),
            new BlockDefinition("Citrine", "ore", null, "unique", List.of("citrine_ore"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "citrine_ore"),
            new BlockDefinition("Citrine", "block", 15, "full16", List.of("citrine_block"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_citrine"),
            new BlockDefinition("Glowmist", "standard", 15, "unique", List.of("fog"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "glowmist"),
            new BlockDefinition("GlowIce", "standard", 5, "pastel", List.of("minecraft:ice"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glow_ice"),
            new BlockDefinition("GlowIce", "standard", 5, "monochrome", List.of("minecraft:ice"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glow_ice"),
            new BlockDefinition("GlowingPackedIce", "standard", 10, "full16", List.of("minecraft:packed_ice"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glow_packed_ice"),
            new BlockDefinition("GlowingPermafrost", "standard", 15, "bold", List.of("minecraft:blue_ice"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glow_permafrost"),
            new BlockDefinition("GlowingPermafrost", "standard", 15, "monochrome", List.of("minecraft:blue_ice"), BlockArchetype.MASONRY_SET, "glowing_things", "{color}_glow_permafrost"),

            // ==========================================
            // 3. NOT A BLOCK SHAPE (Fluids, Crops, Panes, Dusts, etc.)
            // ==========================================

            // -- Glowing Alchemy Non-Blocks --
            new BlockDefinition("Dust", "standard", null, "unique", List.of("dust"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "dust"),
            new BlockDefinition("Ash", "standard", null, "unique", List.of("ash"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "ash"),
            new BlockDefinition("Netherdust", "standard", null, "unique", List.of("dust"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "netherdust"),
            new BlockDefinition("Ashdust", "standard", null, "unique", List.of("ashdust"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "ashdust"),
            new BlockDefinition("Biofilm", "standard", null, "unique", List.of("dust"), BlockArchetype.SINGLE_ENTRY, "glowingalchemy", "biofilm"),
            new BlockDefinition("Clover", "carpet",null, "unique", List.of("clover_carpet"),BlockArchetype.SINGLE_ENTRY,"glowingalchemy", "clover_carpet"),
            new BlockDefinition("Clover", "spreading",null, "unique", List.of("clover_spread"),BlockArchetype.SINGLE_ENTRY,"glowingalchemy", "clover_spread"),
            new BlockDefinition("Clover", "growth",null, "unique", List.of("clover_growth"),BlockArchetype.SINGLE_ENTRY,"glowingalchemy", "clover_growth"),
            new BlockDefinition("Clover", "flowers",null, "unique", List.of("clover_flowers"),BlockArchetype.SINGLE_ENTRY,"glowingalchemy", "clover_flowers"),



            // -- Glowing Things Non-Blocks --
            new BlockDefinition("Illumiglass", "pane", 15, "full16", List.of("glass_pane"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_illumiglass_pane"),
            new BlockDefinition("Illumiglass", "clear_pane", 15, "unique", List.of("glass_pane"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "illumiglass_pane"),
            new BlockDefinition("Clover", "carpet", null, "full16", List.of("clover_carpet"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_clover_carpet"),
            new BlockDefinition("Clover", "growth", null, "full16", List.of("clover_growth"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_clover_carpet"),
            new BlockDefinition("Clover", "flowers", null, "full16", List.of("clover_flowers"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_clover_carpet"),
            new BlockDefinition("Citrine", "bud", null, "full16", List.of("citrine_bud"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_citrine_bud"),
            new BlockDefinition("Citrine", "bud1", null, "full16", List.of("citrine_bud_1"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_citrine_bud1"),
            new BlockDefinition("Citrine", "bud2", null, "full16", List.of("citrine_bud_2"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_citrine_bud2"),
            new BlockDefinition("Citrine", "crystal", 15, "full16", List.of("citrine_crystal"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_citrine_crystal"),
            new BlockDefinition("Citrine", "raw_crystal", null, "unique", List.of("citrine_crystal"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "raw_citrine_crystal"),
            new BlockDefinition("Citrine", "raw_bud", null, "unique", List.of("citrine_bud"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "raw_citrine_bud"),
            new BlockDefinition("Citrine", "raw_bud1", null, "unique", List.of("citrine_bud_1"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "raw_citrine_bud1"),
            new BlockDefinition("Citrine", "raw_bud2", null, "unique", List.of("citrine_bud_2"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "raw_citrine_bud2"),
            new BlockDefinition("Citrine", "crystal", 15, "rainbow", List.of("citrine_crystal"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_citrine_crystal"),
            new BlockDefinition("Lanternfruit", "bud", 5, "full16", List.of("glowlantern_0"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_lanternfruit_bud"),
            new BlockDefinition("Lanternfruit", "bud1", 5, "full16", List.of("glowlantern_1"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_lanternfruit_bud1"),
            new BlockDefinition("Lanternfruit", "bud2", 5, "full16", List.of("glowlantern_2"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_lanternfruit_bud2"),
            new BlockDefinition("Glow Lanternfruit", "bud", 10, "full16", List.of("glowlantern_0"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glow_lanternfruit_bud"),
            new BlockDefinition("Glow Lanternfruit", "bud1", 10, "full16", List.of("glowlantern_1"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glow_lanternfruit_bud1"),
            new BlockDefinition("Glow Lanternfruit", "bud2", 10, "full16", List.of("glowlantern_2"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glow_lanternfruit_bud2"),
            new BlockDefinition("Glowwater", "source", 15, "full16", List.of("minecraft:water_source"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowwater_source"),
            new BlockDefinition("Glowwater", "flowing", 15, "full16", List.of("minecraft:water_flowing"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_glowwater_flowing"),
            new BlockDefinition("TinyGlowShrooms", "standard", 15, "full16", List.of("minecraft:mushroom"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_tiny_glowshroom"),
            new BlockDefinition("SmallGlowShroom", "standard", 12, "full16", List.of("small_mushroom"), BlockArchetype.SINGLE_ENTRY, "glowing_things", "{color}_small_glowshroom")
    );
}