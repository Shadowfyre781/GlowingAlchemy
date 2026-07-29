package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.resources.Identifier;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColor;




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

