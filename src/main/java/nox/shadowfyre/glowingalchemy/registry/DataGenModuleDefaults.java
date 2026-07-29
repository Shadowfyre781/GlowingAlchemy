package nox.shadowfyre.glowingalchemy.registry;

/*
public record DataGenModuleDefaults(
        String namespace,
        Map<TextureDefinition, Identifier> vanillaSourcedTextures,
        Set<String> skipTextures,
        Set<String> nonCubeShapes,
        Set<BlockShape> woodSetShapesUsingPlanksRule,
        Set<BlockShape> tintedShapes,
        Set<BlockShape> specialModelShapes,
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

 */