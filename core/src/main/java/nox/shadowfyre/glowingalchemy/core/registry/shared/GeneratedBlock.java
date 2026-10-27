package nox.shadowfyre.glowingalchemy.core.registry.shared;

import net.minecraft.resources.Identifier;
import nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColor;
import nox.shadowfyre.glowingalchemy.core.shapebin.BlockShape;


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
            return definition.namingTemplate().format(definition, color, shape);}
        public String namespace() {
            return definition.namespace();}
        public Identifier id()  {
            return Identifier.parse(namespace() + ":" + name());}






        //generatedBlock.blockName();
        //generatedBlock.texture();

        //generatedBlock.modelType();
        // TODO

        //public TextureDefinition texture() {
        //    return definition.texture();
        // }


    }

