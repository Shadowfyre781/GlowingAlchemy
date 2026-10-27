package nox.shadowfyre.glowingalchemy.core.registry.shared;

import nox.shadowfyre.glowingalchemy.core.crayonbin.GlowSet;
import nox.shadowfyre.glowingalchemy.core.crayonbin.Core_Palette;
import nox.shadowfyre.glowingalchemy.core.shapebin.BlockShapes;

public record BlockDefinition(
        String familyId,       // e.g. "birch", "oak", "limestone"
        String blockId,        // e.g. "log", "plank", "base"
        BlockShapes shapeTemplate,
        String texture,
        String namespace,
        GlowSet glowSet,
        NamingTemplate namingTemplate
) {
    public BlockDefinition {
        glowSet = (glowSet == null) ? Core_Palette.NONE : glowSet;
    }

    public boolean isColored() {
        return glowSet.hasColors();
    }
}
