package nox.shadowfyre.glowingalchemy.core.registry;

import nox.shadowfyre.glowingalchemy.core.crayonbin.Core_Palette;
import nox.shadowfyre.glowingalchemy.core.registry.shared.BlockDefinition;
import nox.shadowfyre.glowingalchemy.core.registry.shared.NamingTemplate;
import nox.shadowfyre.glowingalchemy.core.shapebin.BlockShapes;

import java.util.List;

public class Core_BlockDefinitions {
    private static final NamingTemplate DEFAULT_NAMING = new NamingTemplate();

    public static final List<BlockDefinition> ALL = List.of(


// ==========================================
// STONE / MASONRY
// ==========================================

     new BlockDefinition("limestone", "base",
             BlockShapes.MASONRY_SET, "limestone", "glowingalchemy",
             Core_Palette.NONE, DEFAULT_NAMING),
       new BlockDefinition("limestone", "mossy",
              BlockShapes.MASONRY_SET, "mossy_limestone", "glowingalchemy",
              Core_Palette.NONE, DEFAULT_NAMING),

// ==========================================
// GROUNDCOVER / PARTICLES
// ==========================================

            new BlockDefinition("dust", "standard",
                                BlockShapes.SINGLE_ENTRY, "dust", "glowingalchemy",
                                Core_Palette.NONE, DEFAULT_NAMING),

            new BlockDefinition("ash", "standard",
                                BlockShapes.SINGLE_ENTRY, "ash", "glowingalchemy",
                                Core_Palette.NONE, DEFAULT_NAMING),

            new BlockDefinition("netherdust", "standard",
                                BlockShapes.SINGLE_ENTRY, "netherdust", "glowingalchemy",
                                Core_Palette.NONE, DEFAULT_NAMING),

            new BlockDefinition("ashdust", "standard",
                                BlockShapes.SINGLE_ENTRY, "ashdust", "glowingalchemy",
                                Core_Palette.NONE, DEFAULT_NAMING),

            new BlockDefinition("silt", "standard",
                                BlockShapes.SINGLE_ENTRY, "silt", "glowingalchemy",
                                Core_Palette.NONE, DEFAULT_NAMING)
    );
    public static List<BlockDefinition> definitions() {
        return ALL;
    }

    }