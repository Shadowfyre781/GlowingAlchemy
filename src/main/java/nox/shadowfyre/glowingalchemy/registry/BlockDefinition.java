package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.blocks.BlockArchetype;
import nox.shadowfyre.glowingalchemy.glowing_things.GlowColor;

import java.util.List;

/**
 * One row from block_definitions_v2.csv. "color" is resolved separately per-block
 * at registration time (see BlockFamilyRegistry) since a single row can expand
 * into 1 (unique/none) up to 16 (full16) actual registered blocks.
 */
public record BlockDefinition(
        String family,
        String variant,
        Integer lightLevel,
        String colorGroup,
        List<String> textures,
        BlockArchetype archetype,
        String namespace,
        String blockIdPattern
) {
    /** Resolves the final registry id. Pass null for unique/none color groups. */
    public String resolveId(GlowColor color) {
        if (color == null) {
            return blockIdPattern;
        }
        return blockIdPattern.replace("{color}", color.name());
    }
}
