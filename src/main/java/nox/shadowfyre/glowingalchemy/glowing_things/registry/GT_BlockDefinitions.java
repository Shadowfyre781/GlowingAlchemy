package nox.shadowfyre.glowingalchemy.glowing_things.registry;

import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowSets;
import nox.shadowfyre.glowingalchemy.registry.BlockDefinition;
import nox.shadowfyre.glowingalchemy.registry.BlockDefinitionRegistry;
import nox.shadowfyre.glowingalchemy.registry.BlockShapes;
import nox.shadowfyre.glowingalchemy.registry.NamingTemplate;
import java.util.List;

    public class GT_BlockDefinitions {

        private static final NamingTemplate DEFAULT_NAMING = new NamingTemplate();

        // ==========================================
        // SKIPPED — needs special handling later:
        //   - GlowCoral / NecroCoral (nested type x shape x color loop)
        //   - Glowwater source/flowing (fluid, not a block)
        //   - All "rainbow" palette entries (animated texture, deferred)
        //   - GlowshroomCap, GlowingMycelium, ColoredMoss, GlowingMoss (no texture yet)
        //   - Light level field (deferred — add to BlockDefinition + Registration Engine later)
        // ==========================================

        public static final List<BlockDefinition> ALL = List.of(

                // ==========================================
                // DIRT / SOIL
                // ==========================================

                new BlockDefinition("glow", "dirt", BlockShapes.SINGLE_ENTRY, "dirt", "glowing_things",                        GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow", "moss", BlockShapes.SINGLE_ENTRY, "moss", "glowing_things",                        GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("colored", "moss", BlockShapes.SINGLE_ENTRY, "moss", "glowing_things",                        GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow", "mycelium", BlockShapes.SINGLE_ENTRY, "mycelium", "glowing_things",                        GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("colored", "mycelium", BlockShapes.SINGLE_ENTRY, "mycelium", "glowing_things",                        GlowSets.ALL_18, DEFAULT_NAMING),

                // ==========================================
                // GLOWOAK / RAINBOAK TREES
                // ==========================================

                new BlockDefinition("glowoak", "log",
                        BlockShapes.MASONRY_SET, "pale_log", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowoak", "plank",
                        BlockShapes.WOOD_PLANK_SET, "pale_planks", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowoak", "leaves",
                        BlockShapes.SINGLE_ENTRY, "pale_leaves", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("rainboak", "log",
                        BlockShapes.MASONRY_SET, "pale_log", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("rainboak", "plank",
                        BlockShapes.WOOD_PLANK_SET, "pale_planks", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("rainboak", "leaves",
                        BlockShapes.SINGLE_ENTRY, "pale_leaves", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                // ==========================================
                // ASPHALT
                // ==========================================

                new BlockDefinition("asphalt", "standard",
                        BlockShapes.MASONRY_SET, "asphalt", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glow_asphalt", "standard",
                        BlockShapes.MASONRY_SET, "asphalt", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                // ==========================================
                // CONCRETE
                // ==========================================

                new BlockDefinition("concrete", "standard",
                        BlockShapes.ADD_ON_MASONRY_SET, "concrete", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowcrete", "standard",
                        BlockShapes.MASONRY_SET, "concrete", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                // ==========================================
                // COLORED STONE
                // ==========================================

                new BlockDefinition("colored_stone", "standard",
                        BlockShapes.MASONRY_SET, "stone", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("colored_stone", "pastel",
                        BlockShapes.MASONRY_SET, "stone", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("colored_stone", "bold",
                        BlockShapes.MASONRY_SET, "stone", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                new BlockDefinition("colored_gravel", "standard",
                        BlockShapes.SINGLE_ENTRY, "gravel", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("colored_gravel", "pastel",
                        BlockShapes.SINGLE_ENTRY, "gravel", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("colored_gravel", "bold",
                        BlockShapes.SINGLE_ENTRY, "gravel", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                new BlockDefinition("glowing_stone", "standard",
                        BlockShapes.MASONRY_SET, "stone", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowing_stone", "pastel",
                        BlockShapes.MASONRY_SET, "stone", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("glowing_stone", "bold",
                        BlockShapes.MASONRY_SET, "stone", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),
                new BlockDefinition("glowing_gravel", "standard",
                        BlockShapes.SINGLE_ENTRY, "gravel", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowing_gravel", "pastel",
                        BlockShapes.SINGLE_ENTRY, "gravel", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("glowing_gravel", "bold",
                        BlockShapes.SINGLE_ENTRY, "gravel", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                // ==========================================
                // STONE BRICK
                // ==========================================

                new BlockDefinition("colored_stone_brick", "standard",
                        BlockShapes.MASONRY_SET, "stone_bricks", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("colored_stone_brick", "pastel",
                        BlockShapes.MASONRY_SET, "stone_bricks", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("colored_stone_brick", "bold",
                        BlockShapes.MASONRY_SET, "stone_bricks", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                new BlockDefinition("glowing_stone_brick", "standard",
                        BlockShapes.MASONRY_SET, "stone_bricks", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowing_stone_brick", "pastel",
                        BlockShapes.MASONRY_SET, "stone_bricks", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("glowing_stone_brick", "bold",
                        BlockShapes.MASONRY_SET, "stone_bricks", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                // ==========================================
                // COBBLESTONE
                // ==========================================

                new BlockDefinition("colored_cobble", "standard",
                        BlockShapes.MASONRY_SET, "cobblestone", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("colored_cobble", "pastel",
                        BlockShapes.MASONRY_SET, "cobblestone", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("colored_cobble", "bold",
                        BlockShapes.MASONRY_SET, "cobblestone", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                new BlockDefinition("glowing_cobble", "standard",
                        BlockShapes.MASONRY_SET, "cobblestone", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("glowing_cobble", "pastel",
                        BlockShapes.MASONRY_SET, "cobblestone", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("glowing_cobble", "bold",
                        BlockShapes.MASONRY_SET, "cobblestone", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),

                // ==========================================
                // PLASTIC
                // ==========================================

                new BlockDefinition("plastic", "standard",
                        BlockShapes.MASONRY_SET, "plastic", "glowing_things",
                        GlowSets.ALL_18, DEFAULT_NAMING),

                new BlockDefinition("plastic", "pastel",
                        BlockShapes.MASONRY_SET, "plastic", "glowing_things",
                        GlowSets.PASTEL, DEFAULT_NAMING),

                new BlockDefinition("plastic", "bold",
                        BlockShapes.MASONRY_SET, "plastic", "glowing_things",
                        GlowSets.BOLD, DEFAULT_NAMING),
                new BlockDefinition("glowplastic", "standard", BlockShapes.MASONRY_SET, "plastic", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glowplastic", "pastel", BlockShapes.MASONRY_SET, "plastic", "glowing_things", GlowSets.PASTEL, DEFAULT_NAMING),
                new BlockDefinition("glowplastic", "bold", BlockShapes.MASONRY_SET, "plastic", "glowing_things", GlowSets.BOLD, DEFAULT_NAMING),
                // ==========================================
                // SAND
                // ==========================================
                new BlockDefinition("colored_sand", "standard", BlockShapes.SINGLE_ENTRY, "sand", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("colored_sand", "pastel", BlockShapes.SINGLE_ENTRY, "sand", "glowing_things", GlowSets.PASTEL, DEFAULT_NAMING),
                new BlockDefinition("colored_sand", "bold", BlockShapes.SINGLE_ENTRY, "sand", "glowing_things", GlowSets.BOLD, DEFAULT_NAMING),
                new BlockDefinition("glowsand", "standard", BlockShapes.SINGLE_ENTRY, "sand", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glowsand", "pastel",BlockShapes.SINGLE_ENTRY, "sand", "glowing_things", GlowSets.PASTEL, DEFAULT_NAMING),
                new BlockDefinition("glowsand", "bold", BlockShapes.SINGLE_ENTRY, "sand", "glowing_things", GlowSets.BOLD, DEFAULT_NAMING),
                // ==========================================
                // SANDSTONE
                // ==========================================
                new BlockDefinition("colored_sandstone", "standard", BlockShapes.MASONRY_SET, "sandstone", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("colored_sandstone", "pastel", BlockShapes.MASONRY_SET, "sandstone", "glowing_things", GlowSets.PASTEL, DEFAULT_NAMING),
                new BlockDefinition("colored_sandstone", "bold", BlockShapes.MASONRY_SET, "sandstone", "glowing_things", GlowSets.BOLD, DEFAULT_NAMING),
                new BlockDefinition("glowing_sandstone", "standard", BlockShapes.MASONRY_SET, "sandstone", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glowing_sandstone", "pastel",BlockShapes.MASONRY_SET, "sandstone", "glowing_things", GlowSets.PASTEL, DEFAULT_NAMING),
                new BlockDefinition("glowing_sandstone", "bold", BlockShapes.MASONRY_SET, "sandstone", "glowing_things", GlowSets.BOLD, DEFAULT_NAMING),
                // ==========================================
                // GLASS
                // =========================================
                new BlockDefinition("colored_glass", "standard", BlockShapes.ADD_ON_MASONRY_SET, "glass", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("illumiglass", "block", BlockShapes.MASONRY_SET, "glass", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("illumiglass", "clear", BlockShapes.SINGLE_ENTRY, "glass", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                new BlockDefinition("illumiglass", "pane", BlockShapes.SINGLE_ENTRY, "glass_pane", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("illumiglass", "clear_pane", BlockShapes.SINGLE_ENTRY, "glass_pane", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                // ==========================================
                // ICE
                // ==========================================
                new BlockDefinition("glow_ice", "pastel", BlockShapes.MASONRY_SET, "ice", "glowing_things", GlowSets.PASTEL, DEFAULT_NAMING),
                new BlockDefinition("glow_ice", "monochrome", BlockShapes.MASONRY_SET, "ice", "glowing_things", GlowSets.MONOCHROME, DEFAULT_NAMING),
                new BlockDefinition("glow_packed_ice", "standard", BlockShapes.MASONRY_SET, "packed_ice", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow_permafrost", "bold", BlockShapes.MASONRY_SET, "blue_ice", "glowing_things", GlowSets.BOLD, DEFAULT_NAMING),
                new BlockDefinition("glow_permafrost", "monochrome", BlockShapes.MASONRY_SET, "blue_ice", "glowing_things", GlowSets.MONOCHROME, DEFAULT_NAMING),
                // ==========================================
                // CLAY
                // ==========================================
                new BlockDefinition("clay", "colored", BlockShapes.SINGLE_ENTRY, "clay", "glowing_things",GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow_clay", "standard", BlockShapes.SINGLE_ENTRY, "clay", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                // ==========================================
                // CITRINE
                // ==========================================
                new BlockDefinition("citrine", "ore", BlockShapes.SINGLE_ENTRY, "citrine_ore", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                new BlockDefinition("citrine", "block", BlockShapes.SINGLE_ENTRY, "citrine_block", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("citrine", "bud", BlockShapes.SINGLE_ENTRY, "citrine_bud", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("citrine", "bud1", BlockShapes.SINGLE_ENTRY, "citrine_bud_1", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("citrine", "bud2", BlockShapes.SINGLE_ENTRY, "citrine_bud_2", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("citrine", "crystal", BlockShapes.SINGLE_ENTRY, "citrine_crystal", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("citrine", "raw_crystal", BlockShapes.SINGLE_ENTRY, "citrine_crystal", "glowing_things",GlowSets.NONE, DEFAULT_NAMING),
                new BlockDefinition("citrine", "raw_bud", BlockShapes.SINGLE_ENTRY, "citrine_bud", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                new BlockDefinition("citrine", "raw_bud1", BlockShapes.SINGLE_ENTRY, "citrine_bud_1", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                new BlockDefinition("citrine", "raw_bud2", BlockShapes.SINGLE_ENTRY, "citrine_bud_2", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                // ==========================================
                // LANTERNFRUIT
                // ==========================================
                new BlockDefinition("lanternfruit", "bud", BlockShapes.SINGLE_ENTRY, "glowlantern_0", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("lanternfruit", "bud1", BlockShapes.SINGLE_ENTRY, "glowlantern_1", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("lanternfruit", "bud2", BlockShapes.SINGLE_ENTRY, "glowlantern_2", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow_lanternfruit", "bud", BlockShapes.SINGLE_ENTRY, "glowlantern_0", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow_lanternfruit", "bud1", BlockShapes.SINGLE_ENTRY, "glowlantern_1", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("glow_lanternfruit", "bud2", BlockShapes.SINGLE_ENTRY, "glowlantern_2", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                // ==========================================
                // CLOVER
                // ==========================================
                new BlockDefinition("clover", "block", BlockShapes.SINGLE_ENTRY, "clover_block", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("clover", "carpet", BlockShapes.SINGLE_ENTRY, "clover_carpet", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("clover", "growth", BlockShapes.SINGLE_ENTRY, "clover_growth", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("clover", "flowers", BlockShapes.SINGLE_ENTRY, "clover_flowers", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                // ==========================================
                // MISC
                // ==========================================
                new BlockDefinition("glowmist", "standard", BlockShapes.SINGLE_ENTRY, "fog", "glowing_things", GlowSets.NONE, DEFAULT_NAMING),
                new BlockDefinition("tiny_glowshroom", "standard", BlockShapes.SINGLE_ENTRY, "mushroom", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING),
                new BlockDefinition("small_glowshroom", "standard", BlockShapes.SINGLE_ENTRY, "small_mushroom", "glowing_things", GlowSets.ALL_18, DEFAULT_NAMING)
        );

        public static void registerAll() {
            for (BlockDefinition def : ALL) {BlockDefinitionRegistry.register(def);
            }
        }
    }
