package nox.shadowfyre.glowingalchemy.datagen;
//
//import nox.shadowfyre.glowingalchemy.core.shapebin.BlockShape;
//import nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColor;
//import nox.shadowfyre.glowingalchemy.core.registry.shared.BlockDefinition;
//import nox.shadowfyre.glowingalchemy.core.registry.shared.GeneratedBlock;
//
//public final class BlockGenerator {
//
//    public void generate(BlockDefinition definition) {
//
//        if (definition.isColored()) {
//            generateColored(definition);
//        } else {
//            generatePlain(definition);
//        }}
//
//    private void generateColored(BlockDefinition definition) {
//
//        for (GlowColor color : definition.glowSet().colors()) {
//
//            for (BlockShape shape : definition.shapeTemplate().shapes()) {
//
//                GeneratedBlock(definition, color, shape);
//
//            }
//        }
//    }
//
//        private void generatePlain(BlockDefinition definition) {
//
//            for (BlockShape shape : definition.shapeTemplate().shapes()) {
//
//                GeneratedBlock(definition, null, shape);
//
//            }
//}
//
//
//
//        //generatedBlock.blockName();
//        //generatedBlock.texture();
//        //generatedBlock.namespace();
//        //generatedBlock.modelType();
//        // TODO
//
//        //public TextureDefinition texture() {
//        //    return definition.texture();
//       // }
//
//        public String namespace() {
//            return definition.namespace();
//        }
//
//        public BlockShape shape() {
//            return shape;
//        }
//
//        public GlowColor color() {
//            return color;
//        }
//
//    }}