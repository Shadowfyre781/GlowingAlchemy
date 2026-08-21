package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowSets;

import java.util.List;

public class BlockDefinitions {

    private static final NamingTemplate DEFAULT_NAMING = new NamingTemplate();

    // ==========================================
    // SKIPPED — needs special handling later:
    //   - GlowCoral / NecroCoral (nested type x shape x color loop)
    //   - Citrine raw_budding (composite texture, needs compositing step)
    //   - Lanternfruit seeds / Glow Lanternfruit seeds (custom crop/seed Item class)
    //   - GlowshroomCap, GlowingMycelium, ColoredMoss, GlowingMoss (no texture yet)
    //   - All "rainbow" palette entries (animated texture, deferred)
    //   - Light level field (deferred — add to BlockDefinition + Registration Engine later)
    // ==========================================

    public static final List<BlockDefinition> ALL = List.of(

            // ==========================================
            // STONE / MASONRY
            // ==========================================

            new BlockDefinition("limestone", "base",
                    BlockShapes.MASONRY_SET, "limestone", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("limestone", "mossy",
                    BlockShapes.MASONRY_SET, "mossy_limestone", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("marble", "base",
                    BlockShapes.MASONRY_SET, "marble", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("marble", "mossy",
                    BlockShapes.MASONRY_SET, "mossy_marble", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            // ==========================================
            // TREES — LOGS
            // ==========================================

            new BlockDefinition("maple", "log",
                    BlockShapes.MASONRY_SET, "maple_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("chestnut", "log",
                    BlockShapes.MASONRY_SET, "chestnut_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("evergreen", "log",
                    BlockShapes.MASONRY_SET, "evergreen_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("palm", "log",
                    BlockShapes.MASONRY_SET, "palm_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("willow", "log",
                    BlockShapes.MASONRY_SET, "willow_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("redwood", "log",
                    BlockShapes.MASONRY_SET, "redwood_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("aspen", "log",
                    BlockShapes.MASONRY_SET, "aspen_log", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            // ==========================================
            // TREES — PLANKS
            // ==========================================

            new BlockDefinition("maple", "plank",
                    BlockShapes.WOOD_PLANK_SET, "maple_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("chestnut", "plank",
                    BlockShapes.WOOD_PLANK_SET, "chestnut_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("evergreen", "plank",
                    BlockShapes.WOOD_PLANK_SET, "evergreen_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("palm", "plank",
                    BlockShapes.WOOD_PLANK_SET, "palm_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("willow", "plank",
                    BlockShapes.WOOD_PLANK_SET, "willow_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("redwood", "plank",
                    BlockShapes.WOOD_PLANK_SET, "redwood_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("aspen", "plank",
                    BlockShapes.WOOD_PLANK_SET, "aspen_planks", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            // ==========================================
            // TREES — LEAVES
            // ==========================================

            // Maple leaves use the MAPLE palette (red, green, bold_purple, pink, pastel_purple)
            new BlockDefinition("maple", "leaves",
                    BlockShapes.SINGLE_ENTRY, "maple_leaves", "glowingalchemy",
                    GlowSets.MAPLE, DEFAULT_NAMING),

            new BlockDefinition("chestnut", "leaves",
                    BlockShapes.SINGLE_ENTRY, "chestnut_leaves", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("evergreen", "leaves",
                    BlockShapes.SINGLE_ENTRY, "evergreen_leaves", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("palm", "leaves",
                    BlockShapes.SINGLE_ENTRY, "palm_leaves", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("willow", "leaves",
                    BlockShapes.SINGLE_ENTRY, "willow_leaves", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("redwood", "leaves",
                    BlockShapes.SINGLE_ENTRY, "redwood_leaves", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("aspen", "leaves",
                    BlockShapes.SINGLE_ENTRY, "aspen_leaves", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            // ==========================================
            // TREES — OTHER
            // ==========================================

            new BlockDefinition("willow", "canes",
                    BlockShapes.SINGLE_ENTRY, "willow_canes", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("aspen", "roots",
                    BlockShapes.SINGLE_ENTRY, "aspen_roots", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            // ==========================================
            // GROUNDCOVER / PARTICLES
            // ==========================================

            new BlockDefinition("dust", "standard",
                    BlockShapes.SINGLE_ENTRY, "dust", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("ash", "standard",
                    BlockShapes.SINGLE_ENTRY, "ash", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("netherdust", "standard",
                    BlockShapes.SINGLE_ENTRY, "netherdust", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("ashdust", "standard",
                    BlockShapes.SINGLE_ENTRY, "ashdust", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("biofilm", "standard",
                    BlockShapes.SINGLE_ENTRY, "biofilm", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            // ==========================================
            // CLOVER
            // ==========================================

            new BlockDefinition("clover", "carpet",
                    BlockShapes.SINGLE_ENTRY, "clover_carpet", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("clover", "spreading",
                    BlockShapes.SINGLE_ENTRY, "clover_spread", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("clover", "growth",
                    BlockShapes.SINGLE_ENTRY, "clover_growth", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING),

            new BlockDefinition("clover", "flowers",
                    BlockShapes.SINGLE_ENTRY, "clover_flowers", "glowingalchemy",
                    GlowSets.NONE, DEFAULT_NAMING)

    );

    public static void registerAll() {
        for (BlockDefinition def : ALL) {
            BlockDefinitionRegistry.register(def);
        }
    }
}