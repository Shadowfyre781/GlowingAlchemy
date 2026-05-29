package nox.shadowfyre.glowingalchemy.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record MaterialMetadata(String name, float hardness, int lightLevel) {

    // This Codec tells the game how to turn your JSON into this record
    public static final Codec<MaterialMetadata> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(MaterialMetadata::name),
            Codec.FLOAT.fieldOf("hardness").forGetter(MaterialMetadata::hardness),
            Codec.INT.fieldOf("lightLevel").forGetter(MaterialMetadata::lightLevel)
    ).apply(instance, MaterialMetadata::new));
}