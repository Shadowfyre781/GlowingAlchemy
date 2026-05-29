package nox.shadowfyre.glowingalchemy.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

// IntegrationMetadata.java
public record IntegrationMetadata(
        String modId,
        String blockId,
        String ingotId,
        String nuggetId,
        String category,
        int tintColor
) {
    public static final Codec<IntegrationMetadata> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("modId").forGetter(IntegrationMetadata::modId),
            Codec.STRING.fieldOf("blockId").forGetter(IntegrationMetadata::blockId),
            Codec.STRING.fieldOf("ingotId").forGetter(IntegrationMetadata::ingotId),
            Codec.STRING.fieldOf("nuggetId").forGetter(IntegrationMetadata::nuggetId),
            Codec.STRING.fieldOf("category").forGetter(IntegrationMetadata::category),
            Codec.STRING.fieldOf("tintColor").xmap(
                    s -> Integer.decode(s.startsWith("#") ? s : "#" + s),
                    i -> String.format("#%06X", i)
            ).forGetter(IntegrationMetadata::tintColor)
    ).apply(instance, IntegrationMetadata::new));
}