package nox.shadowfyre.glowingalchemy.registry;

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
            //
             /
            new BlockDefinition("Limestone", "standard_base",  List.of("limestone"), BlockShape.DEEP_MASONRY_SET, "glowingalchemy", "limestone");
            new BlockDefinition("Limestone mossy",  List.of("mossy_limestone"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowingalchemy", "limestone_mossy");

/*            new BlockDefinition("Marble", "standard_base",  List.of("marble"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowingalchemy", "marble"),
            new BlockDefinition("Marble", "mossy",  List.of("mossy_marble"), BlockShapeTemplate.DEEP_MASONRY_SET, "glowingalchemy", "marble_mossy"),
            new BlockDefinition("Maple", "leaves", "red,green,bold_purple,pink,pastel_purple", List.of("maple_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "{color}_maple_leaves"),
            new BlockDefinition("Maple", "log",  List.of("maple_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "maple_log"),
            new BlockDefinition("Maple", "plank",  List.of("maple_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "maple_plank"),
            new BlockDefinition("Chestnut", "leaves",  List.of("chestnut_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "chestnut_leaves"),
            new BlockDefinition("Chestnut", "log",  List.of("chestnut_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "chestnut_log"),
            new BlockDefinition("Chestnut", "plank",  List.of("chestnut_plank"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "chestnut_plank"),
            new BlockDefinition("Evergreen", "leaves",  List.of("evergreen_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "evergreen_leaves"),
            new BlockDefinition("Evergreen", "log",  List.of("evergreen_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "evergreen_log"),
            new BlockDefinition("Evergreen", "plank",  List.of("evergreen_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "evergreen_plank"),
            new BlockDefinition("Palm", "leaves",  List.of("palm_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "palm_leaves"),
            new BlockDefinition("Palm", "log",  List.of("palm_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "palm_log"),
            new BlockDefinition("Palm", "plank",  List.of("palm_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "palm_plank"),
            new BlockDefinition("Willow", "leaves",  List.of("willow_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "willow_leaves"),
            new BlockDefinition("Willow", "canes",  List.of("willow_canes"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "willow_canes"),
            new BlockDefinition("Willow", "log",  List.of("willow_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "willow_log"),
            new BlockDefinition("Willow", "plank",  List.of("willow_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "willow_plank"),
            new BlockDefinition("Redwood", "canes",  List.of("redwood_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "redwood_plank"),
            new BlockDefinition("Redwood", "log",  List.of("redwood_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "redwood_log"),
            new BlockDefinition("Redwood", "plank",  List.of("redwood_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "willow_plank"),
            new BlockDefinition("Aspen", "canes",  List.of("aspen_leaves"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "aspen_leaves"),
            new BlockDefinition("Aspen", "log",  List.of("aspen_log"), BlockShapeTemplate.MASONRY_SET, "glowingalchemy", "aspen_log"),
            new BlockDefinition("Aspen", "plank",  List.of("aspen_planks"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "aspen_plank"),
            new BlockDefinition("Aspen", "roots",  List.of("aspen_roots"), BlockShapeTemplate.WOOD_PLANK_SET, "glowingalchemy", "aspen_plank"),


            // ==========================================
            // 3. NOT A BLOCK SHAPE (Fluids, Crops, Panes, Dusts, etc.)
            // ==========================================

            // -- Glowing Alchemy Non-Blocks --
            new BlockDefinition("Dust", "standard",  List.of("dust"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "dust"),
            new BlockDefinition("Ash", "standard",  List.of("ash"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "ash"),
            new BlockDefinition("Netherdust", "standard",  List.of("dust"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "netherdust"),
            new BlockDefinition("Ashdust", "standard",  List.of("ashdust"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "ashdust"),
            new BlockDefinition("Biofilm", "standard",  List.of("dust"), BlockShapeTemplate.SINGLE_ENTRY, "glowingalchemy", "biofilm"),
            new BlockDefinition("Clover", "carpet", List.of("clover_carpet"), BlockShapeTemplate.SINGLE_ENTRY,"glowingalchemy", "clover_carpet"),
            new BlockDefinition("Clover", "spreading", List.of("clover_spread"), BlockShapeTemplate.SINGLE_ENTRY,"glowingalchemy", "clover_spread"),
            new BlockDefinition("Clover", "growth", List.of("clover_growth"), BlockShapeTemplate.SINGLE_ENTRY,"glowingalchemy", "clover_growth"),
            new BlockDefinition("Clover", "flowers", List.of("clover_flowers"), BlockShapeTemplate.SINGLE_ENTRY,"glowingalchemy", "clover_flowers")
*/



    );}