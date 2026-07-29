package nox.shadowfyre.glowingalchemy.registry;

import java.util.ArrayList;
import java.util.List;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColor;

    public final class FamilyExpander {

        private FamilyExpander() {}

        public static List<GeneratedBlock> expand(BlockDefinition definition) {

            List<GeneratedBlock> results = new ArrayList<>();

            BlockShapes template = definition.shapeTemplate();

            for (GlowColor color : definition.glowSet().colors()) {//cant resolve colors in blockdefinition
                List<GlowColor> colors = definition.isColored()
                        ? definition.glowSet().colors()
                        : List.of((GlowColor) null);
                for (BlockShape shape : template.shapes()) {

                    results.add(
                            new GeneratedBlock(//can't resolve symbol generatedblock
                                    definition,
                                    color,
                                    shape
                            )
                    );

                }
            }

            return results;
        }
}
/**
 * Expands one BlockDefinition into every concrete block
 * that should exist.
 *
 * Example:
 *
 * Glass
 *  ×
 * GlowPalette
 *  ×
 * MASONRY_SET
 *
 * =
 *
 * Red Glass
 * Red Glass Slab
 * Red Glass Stairs
 * ...
 */