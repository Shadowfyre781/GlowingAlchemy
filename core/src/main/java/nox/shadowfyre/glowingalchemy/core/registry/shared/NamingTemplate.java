package nox.shadowfyre.glowingalchemy.core.registry.shared;

import nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColor;
import nox.shadowfyre.glowingalchemy.core.shapebin.BlockShape;

public class NamingTemplate {

    // Produces names like:
    //   birch_log
    //   birch_log_slab
    //   oak_plank_stairs
    //   red_glowstone
    //   red_glowstone_stairs
    public String format(
            BlockDefinition definition,
            GlowColor color,
            BlockShape shape) {

        StringBuilder name = new StringBuilder();

        // color prefix (only for colored families)
        if (color != null) {
            name.append(color.name()).append("_");
        }

        // family + block type
        name.append(definition.familyId())
                .append("_")
                .append(definition.blockId());

        // shape suffix (skipped for base BLOCK shape)
        if (!shape.isBaseBlock()) {
            name.append("_").append(shape.nameSuffix());
        }

        return name.toString();
    }
}