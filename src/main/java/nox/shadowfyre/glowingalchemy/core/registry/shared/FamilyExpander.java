package nox.shadowfyre.glowingalchemy.core.registry.shared;

import java.util.ArrayList;
import java.util.List;
import nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColor;
import nox.shadowfyre.glowingalchemy.core.shapebin.BlockShape;

public final class FamilyExpander {

    private FamilyExpander() {}

    public static List<GeneratedBlock> expand(BlockDefinition definition) {

        List<GeneratedBlock> results = new ArrayList<>();

        if (definition.isColored()) {
            for (GlowColor color : definition.glowSet().colors()) {
                for (BlockShape shape : definition.shapeTemplate().shapes()) {
                    results.add(new GeneratedBlock(definition, color, shape));
                }
            }
        } else {
            for (BlockShape shape : definition.shapeTemplate().shapes()) {
                results.add(new GeneratedBlock(definition, null, shape));
            }
        }

        return results;
    }
}