package nox.shadowfyre.glowingalchemy.datagen;

import nox.shadowfyre.glowingalchemy.registry.BlockShape;
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

                createGeneratedBlock(def, color, shape);

            }
        }
    }

        private void generatePlain(BlockDefinition def) {

            for (BlockShape shape : def.shapeTemplate().shapes()) {

                createGeneratedBlock(def, null, shape);

            }
}
    private GeneratedBlock createGeneratedBlock(
            BlockDefinition def,
            GlowColor color,
            BlockShape shape) {
        return new GeneratedBlock(def, color, shape);
    }
    public record GeneratedBlock(
            BlockDefinition definition,
            GlowColor color,
            BlockShape shape
           // Identifier textureId(),
           // Identifier modelId()
            //boolean isColored()
            // boolean isGlowing()
    ) {

        public String name() {
            return definition.namingTemplate()
                    .format(definition, color, shape);
        }



        //generatedBlock.blockName();
        //generatedBlock.texture();
        //generatedBlock.namespace();
        //generatedBlock.modelType();
        // TODO

        //public TextureDefinition texture() {
        //    return definition.texture();
       // }

        public String namespace() {
            return definition.namespace();
        }

        public BlockShape shape() {
            return shape;
        }

        public GlowColor color() {
            return color;
        }

    }}