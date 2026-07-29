package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.blocks.BlockShapeTemplate;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowSet;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowSets;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColors;

public record BlockDefinition(
        String familyId,
        String blockId,
        BlockShapeTemplate shapeTemplate,
        TextureDefinition texture,
        String namespace,
        GlowSet glowSet,
        NamingTemplate namingTemplate
) {
    public BlockDefinition {
        glowSet = (glowSet == null)
                ? GlowSets.NONE
                : glowSet;
    }
    public boolean isColored() {
        return glowSet.hasColors();
    }}


//// 2) Central resolver for tint behavior.
//
//// 3) Datagen: route all three families through the same provider path.
//public final class GlowingBlockStateProvider extends BlockStateProvider {
//    public GlowingBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
//        super(output, GlowingAlchemy.MOD_ID, exFileHelper);
//    }
//
//    @Override
//    protected void registerStatesAndModels() {
//        ModBlockRegistry.forEachDefinition(def -> {
//            if (def.useVanillaTint()) {
//                generateVanillaTintFamily(def);
//            } else if (def.isTinted()) {
//                generatePaletteTintFamily(def);
//            } else {
//                generateUntintedFamily(def);
//            }
//        });
//    }
//////!
//    private void generateVanillaTintFamily(BlockDefinition def) {
//        for (String colorName : def.ShapeTemplate().shapes()) {
//            int tint = TintResolvers.resolveTintColor(def, colorName);
//            generateTintedShape(def, colorName, tint);
//        }
//    }
//
//    private void generatePaletteTintFamily(BlockDefinition def) {
//        for (String colorName : def.ShapeTemplate().shapes()) {
//            int tint = TintResolvers.resolveTintColor(def, colorName);
//            generateTintedShape(def, shapeTemplate()colorName, tint);
//        }
//    }
//
//    private void generateUntintedFamily(BlockDefinition def) {
//        for (String shape : def.ShapeTemplate().shapes()) {
//            generateShape(def, shape);
//        }
//    }
//
//    private void generateTintedShape(BlockDefinition def, String colorName, int tint) {
//        String blockName = def.familyID() + "_" + colorName;
//        // build models using your tinted templates here
//        // include item model with constant tint source baked in
//    }
//
//    private void generateShape(BlockDefinition def, String shape) {
//        String blockName = def.familyID() + "_" + shape;
//        // build standard cube/stairs/slab/wall/etc. models here
//    }
//    !//
