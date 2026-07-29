package nox.shadowfyre.glowingalchemy.datagen;

import nox.shadowfyre.glowingalchemy.blocks.BlockShape;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColor;
import nox.shadowfyre.glowingalchemy.registry.BlockDefinition;

public final class BlockGenerator {

    public void generate(BlockDefinition def) {

        if (def.isColored()) {
            generateColored(def);
        } else {
            generatePlain(def);
        }}

    private void generateColored(BlockDefinition def) {

        for (GlowColor color : def.glowSet().colors()) {

            for (BlockShape shape : def.shapeTemplate().shapes()) {

                    generateOne(def, color, shape);

            }
        }
    }

        private void generatePlain(BlockDefinition def) {

            for (BlockShape shape : def.shapeTemplate().shapes()) {

                generateOne(def, null, shape);

            }
}
    private void generateOne(
            BlockDefinition def,
            GlowColor color,
            BlockShape shape) {

        // TODO
    }
}