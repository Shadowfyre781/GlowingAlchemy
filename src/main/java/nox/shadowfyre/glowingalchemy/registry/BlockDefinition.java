package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowSet;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowSets;

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
        glowSet = (glowSet == null) ? GlowSets.NONE : glowSet;
    }

    public boolean isColored() {
        return glowSet.hasColors();
    }
}
