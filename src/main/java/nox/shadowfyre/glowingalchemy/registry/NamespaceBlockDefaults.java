package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.Map;
import java.util.Set;

public record NamespaceBlockDefaults(
        String namespace,
        Map<String, Identifier> vanillaSourcedTextures,
        Set<String> skipTextures,
        Set<String> nonCubeShapes,
        Set<String> woodSetShapesUsingPlanksRule,
        Set<String> tintedShapes,
        Set<String> specialModelShapes,
        List<String> defaultColorOrder
) {
    public Identifier resolveVanillaTexture(String textureKey) {
        return vanillaSourcedTextures.get(textureKey);
    }

    public boolean shouldSkipTexture(String textureKey) {
        return skipTextures.contains(textureKey);
    }

    public boolean isNonCubeShape(String shapeId) {
        return nonCubeShapes.contains(shapeId);
    }

    public boolean usesPlanksSuffixRule(String shapeId) {
        return woodSetShapesUsingPlanksRule.contains(shapeId);
    }

    public boolean isTintedShape(String shapeId) {
        return tintedShapes.contains(shapeId);
    }

    public boolean usesSpecialModel(String shapeId) {
        return specialModelShapes.contains(shapeId);
    }

    public boolean hasColorOrder() {
        return defaultColorOrder != null && !defaultColorOrder.isEmpty();
    }
}