package nox.shadowfyre.glowingalchemy.datagen;
/*
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.*;
        import  net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import  net.minecraft.client.data.models.model.ModelTemplate;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import nox.shadowfyre.glowingalchemy.registry.BlockDefinition;
import nox.shadowfyre.glowingalchemy.registry.BlockFamilyRegistry;
import net.minecraft.world.level.block.Block;

public class GlowingBlockStateProvider_Old extends BlockStateProvider {

    public GlowingBlockStateProvider_Old(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "glowingalchemy", existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (BlockDefinition definition : BlockFamilyRegistry.getDefinitions()) {
            for (String shape : definition.BlockShape.shapes()) {
                Block block = BlockFamilyRegistry.getBlock(definition, shape);
                if (block == null) continue;

                boolean tinted = BlockFamilyRegistry.isTinted(block);
                Identifier texture = resolveTexture(definition, shape, tinted);
                String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath();

                if (tinted) {
                    int tintColor = BlockFamilyRegistry.getTintColor(block);
                    generateTintedAssets(block, blockName, shape, texture, tintColor);
                } else {
                    generateStandardAssets(block, blockName, shape, texture);
                }
            }
        }
        ModBlockRegistry.forEachDefinition(def -> {
            if (def.isTinted()) {
                generateTintedFamily(def);
            } else {
                generateUntintedFamily(def);
            }
        });
    }
    private void generateTintedFamily(BlockDefinition def) {
        for (String colorName : def.shapes()) {
            int tint = def.usesVanillaTintTable()
                    ? resolveVanillaTintColor(def.familyId(), colorName)
                    : resolvePaletteTintColor(def.tintSourceId(), colorName);

            generateTintedShape(def, colorName, tint);
        }
    }
    private void generateStandardAssets(Block block, String name, String shape, Identifier texture) {
        switch (shape) {
            case "block" -> {
                if (block instanceof RotatedPillarBlock) {
                    modelBlock(block, cubeColumnModel(name, texture));
                } else {
                    modelBlock(block, cubeAllModel(name, texture));
                }
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "slab" -> {
                ModelTemplate bottom = slabModel(name, texture, "");
                ModelTemplate top = slabModel(name, texture, "_top");
                slabBlock((SlabBlock) block, bottom, top, bottom);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "stairs" -> {
                ModelTemplate stairs = stairsModel(name, texture, "");
                ModelTemplate stairsInner = stairsModel(name, texture, "_inner");
                ModelTemplate stairsOuter = stairsModel(name, texture, "_outer");
                stairsBlock((StairBlock) block, stairs, stairsInner, stairsOuter);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "wall" -> {
                ModelTemplate post = wallModel(name, texture, "_post", "post");
                ModelTemplate side = wallModel(name, texture, "_side", "side");
                ModelTemplate sideTall = wallModel(name, texture, "_side_tall", "side_tall");
                wallBlock((WallBlock) block, post, side, sideTall);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "fence" -> {
                ModelTemplate post = fenceModel(name, texture, "_post", true);
                ModelTemplate side = fenceModel(name, texture, "_side", false);
                fenceBlock((FenceBlock) block, post, side);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "fence_gate" -> {
                ModelTemplate gate = fenceGateModel(name, texture, "");
                ModelTemplate open = fenceGateModel(name, texture, "_open");
                ModelTemplate wall = fenceGateModel(name, texture, "_wall");
                ModelTemplate wallOpen = fenceGateModel(name, texture, "_wall_open");
                fenceGateBlock((FenceGateBlock) block, gate, open, wall, wallOpen);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "button" -> {
                ModelTemplate button = buttonModel(name, texture, "");
                ModelTemplate buttonPressed = buttonModel(name, texture, "_pressed");
                buttonBlock((ButtonBlock) block, button, buttonPressed);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "pressure_plate" -> {
                ModelTemplate up = pressurePlateModel(name, texture, "");
                ModelTemplate down = pressurePlateModel(name, texture, "_down");
                pressurePlateBlock((PressurePlateBlock) block, up, down);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "door" -> {
                ModelTemplate bottom = doorModel(name, texture, "_bottom");
                ModelTemplate bottomHinge = doorModel(name, texture, "_bottom_hinge");
                ModelTemplate top = doorModel(name, texture, "_top");
                ModelTemplate topHinge = doorModel(name, texture, "_top_hinge");
                doorBlock((DoorBlock) block, bottom, bottomHinge, top, topHinge);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            case "trapdoor" -> {
                ModelTemplate bottom = trapdoorModel(name, texture, "_bottom");
                ModelTemplate top = trapdoorModel(name, texture, "_top");
                ModelTemplate open = trapdoorModel(name, texture, "_open");
                trapdoorBlock((TrapDoorBlock) block, bottom, top, open, true);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }

            default -> {
                modelBlock(block, cubeAllModel(name, texture));
                itemModels().withExistingParent(name, modLoc("block/" + name));
            }
        }
    }

    private void generateTintedAssets(Block block, String name, String shape, Identifier texture, int tintColor) {
        String hex = String.format("%06X", tintColor & 0xFFFFFF);

        switch (shape) {
            case "block" -> {
                ModelTemplate model = cubeAllTintedModel(name, texture, hex);
                modelBlock(block, model);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "slab" -> {
                ModelTemplate bottom = slabTintedModel(name, texture, "", hex);
                ModelTemplate top = slabTintedModel(name, texture, "_top", hex);
                slabBlock((SlabBlock) block, bottom, top, bottom);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "stairs" -> {
                ModelTemplate stairs = stairsTintedModel(name, texture, "", hex);
                ModelTemplate inner = stairsTintedModel(name, texture, "_inner", hex);
                ModelTemplate outer = stairsTintedModel(name, texture, "_outer", hex);
                stairsBlock((StairBlock) block, stairs, inner, outer);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "wall" -> {
                ModelTemplate post = wallTintedModel(name, texture, "_post", hex, "post");
                ModelTemplate side = wallTintedModel(name, texture, "_side", hex, "side");
                ModelTemplate sideTall = wallTintedModel(name, texture, "_side_tall", hex, "side_tall");
                wallBlock((WallBlock) block, post, side, sideTall);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "fence" -> {
                ModelTemplate post = fenceTintedModel(name, texture, "_post", hex, true);
                ModelTemplate side = fenceTintedModel(name, texture, "_side", hex, false);
                fenceBlock((FenceBlock) block, post, side);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "fence_gate" -> {
                ModelTemplate gate = fenceGateTintedModel(name, texture, "", hex);
                ModelTemplate open = fenceGateTintedModel(name, texture, "_open", hex);
                ModelTemplate wall = fenceGateTintedModel(name, texture, "_wall", hex);
                ModelTemplate wallOpen = fenceGateTintedModel(name, texture, "_wall_open", hex);
                fenceGateBlock((FenceGateBlock) block, gate, open, wall, wallOpen);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "button" -> {
                ModelTemplate button = buttonTintedModel(name, texture, "", hex);
                ModelTemplate buttonPressed = buttonTintedModel(name, texture, "_pressed", hex);
                buttonBlock((ButtonBlock) block, button, buttonPressed);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "pressure_plate" -> {
                ModelTemplate up = pressurePlateTintedModel(name, texture, "", hex);
                ModelTemplate down = pressurePlateTintedModel(name, texture, "_down", hex);
                pressurePlateBlock((PressurePlateBlock) block, up, down);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "door" -> {
                ModelTemplate bottom = doorTintedModel(name, texture, "_bottom", hex);
                ModelTemplate bottomHinge = doorTintedModel(name, texture, "_bottom_hinge", hex);
                ModelTemplate top = doorTintedModel(name, texture, "_top", hex);
                ModelTemplate topHinge = doorTintedModel(name, texture, "_top_hinge", hex);
                doorBlock((DoorBlock) block, bottom, bottomHinge, top, topHinge);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            case "trapdoor" -> {
                ModelTemplate bottom = trapdoorTintedModel(name, texture, "_bottom", hex);
                ModelTemplate top = trapdoorTintedModel(name, texture, "_top", hex);
                ModelTemplate open = trapdoorTintedModel(name, texture, "_open", hex);
                trapdoorBlock((TrapDoorBlock) block, bottom, top, open, true);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }

            default -> {
                ModelTemplate model = cubeAllTintedModel(name, texture, hex);
                modelBlock(block, model);
                tintedItemModel(name, texture, hex, modLoc("block/" + name));
            }
        }
    }

    private void tintedItemModel(String name, Identifier texture, String hex, Identifier parent) {
        var item = itemModels().withExistingParent(name, parent);
        item.texture("layer0", texture);

        JsonObject tint = new JsonObject();
        tint.addProperty("type", "constant");
        tint.addProperty("value", "#" + hex);
        item.model.add("tint_source", tint);
    }

    private ModelTemplate cubeAllModel(String name, Identifier texture) {
        return models().withExistingParent(name, mcLoc("block/cube_all"))
                .texture("all", texture);
    }

    private ModelTemplate cubeColumnModel(String name, Identifier texture) {
        return models().withExistingParent(name, mcLoc("block/cube_column"))
                .texture("end", texture)
                .texture("side", texture);
    }

    private ModelTemplate cubeAllTintedModel(String name, Identifier texture, String hex) {
        Identifier parent = modLoc("block/template_tinted_cube_all");
        return models().withExistingParent(name, parent)
                .texture("all", texture);
    }

    private ModelTemplate slabModel(String name, Identifier texture, String suffix) {
        return models().withExistingParent(name + suffix, mcLoc("block/slab"))
                .texture("bottom", texture)
                .texture("top", texture)
                .texture("side", texture);
    }

    private ModelTemplate slabTintedModel(String name, Identifier texture, String suffix, String hex) {
        Identifier parent = modLoc("block/template_tinted_slab");
        return models().withExistingParent(name + suffix, parent)
                .texture("bottom", texture)
                .texture("top", texture)
                .texture("side", texture);
    }

    private ModelTemplate stairsModel(String name, Identifier texture, String suffix) {
        return models().withExistingParent(name + suffix, mcLoc("block/stairs"))
                .texture("bottom", texture)
                .texture("top", texture)
                .texture("side", texture);
    }

    private ModelTemplate stairsTintedModel(String name, Identifier texture, String suffix, String hex) {
        Identifier parent = modLoc("block/template_tinted_stairs");
        return models().withExistingParent(name + suffix, parent)
                .texture("bottom", texture)
                .texture("top", texture)
                .texture("side", texture);
    }

    private ModelTemplate wallModel(String name, Identifier texture, String suffix, String variant) {
        Identifier parent = switch (variant) {
            case "post" -> mcLoc("block/template_wall_post");
            case "side" -> mcLoc("block/template_wall_side");
            case "side_tall" -> mcLoc("block/template_wall_side_tall");
            default -> throw new IllegalStateException("Unknown wall variant: " + variant);
        };
        return models().withExistingParent(name + suffix, parent).texture("wall", texture);
    }

    private ModelTemplate wallTintedModel(String name, Identifier texture, String suffix, String hex, String variant) {
        Identifier parent = switch (variant) {
            case "post" -> modLoc("block/template_tinted_wall_post");
            case "side" -> modLoc("block/template_tinted_wall_side");
            case "side_tall" -> modLoc("block/template_tinted_wall_side");
            default -> throw new IllegalStateException("Unknown wall variant: " + variant);
        };
        return models().withExistingParent(name + suffix, parent).texture("wall", texture);
    }

    private ModelTemplate fenceModel(String name, Identifier texture, String suffix, boolean post) {
        Identifier parent = post ? mcLoc("block/fence_post") : mcLoc("block/fence_side");
        return models().withExistingParent(name + suffix, parent).texture("texture", texture);
    }

    private ModelTemplate fenceTintedModel(String name, Identifier texture, String suffix, String hex, boolean post) {
        Identifier parent = post ? modLoc("block/template_tinted_fence_post") : modLoc("block/template_tinted_fence_side");
        return models().withExistingParent(name + suffix, parent).texture("texture", texture);
    }

    private ModelTemplate fenceGateModel(String name, Identifier texture, String suffix) {
        return models().withExistingParent(name + suffix, mcLoc("block/template_fence_gate"))
                .texture("texture", texture);
    }

    private ModelTemplate fenceGateTintedModel(String name, Identifier texture, String suffix, String hex) {
        return models().withExistingParent(name + suffix, modLoc("block/template_tinted_fence_gate"))
                .texture("texture", texture);
    }

    private ModelTemplate buttonModel(String name, Identifier texture, String suffix) {
        return models().withExistingParent(name + suffix, mcLoc("block/button"))
                .texture("texture", texture);
    }

    private ModelTemplate buttonTintedModel(String name, Identifier texture, String suffix, String hex) {
        return models().withExistingParent(name + suffix, modLoc("block/template_tinted_button"))
                .texture("texture", texture);
    }

    private ModelTemplate pressurePlateModel(String name, Identifier texture, String suffix) {
        return models().withExistingParent(name + suffix, mcLoc("block/pressure_plate_up"))
                .texture("texture", texture);
    }

    private ModelTemplate pressurePlateTintedModel(String name, Identifier texture, String suffix, String hex) {
        return models().withExistingParent(name + suffix, modLoc("block/template_tinted_pressure_plate"))
                .texture("texture", texture);
    }

    private ModelTemplate doorModel(String name, Identifier texture, String suffix) {
        Identifier parent = suffix.contains("top") ? mcLoc("block/door_top") : mcLoc("block/door_bottom");
        return models().withExistingParent(name + suffix, parent)
                .texture("top", texture)
                .texture("bottom", texture);
    }

    private ModelTemplate doorTintedModel(String name, Identifier texture, String suffix, String hex) {
        Identifier parent = suffix.contains("top") ? modLoc("block/template_tinted_door_top") : modLoc("block/template_tinted_door_bottom");
        return models().withExistingParent(name + suffix, parent)
                .texture("top", texture)
                .texture("bottom", texture);
    }

    private ModelTemplate trapdoorModel(String name, Identifier texture, String suffix) {
        return models().withExistingParent(name + suffix, mcLoc("block/template_trapdoor_" + suffix.substring(1)))
                .texture("texture", texture);
    }

    private ModelTemplate trapdoorTintedModel(String name, Identifier texture, String suffix, String hex) {
        return models().withExistingParent(name + suffix, modLoc("block/template_tinted_trapdoor"))
                .texture("texture", texture);
    }

    private Identifier resolveTexture(BlockDefinition definition, String shape, boolean tinted) {
        String textureName = definition.textureName();

        if (!tinted && BlockFamilyRegistry.VANILLA_SOURCED.containsKey(textureName)) {
            return BlockFamilyRegistry.VANILLA_SOURCED.get(textureName);
        }

        return Identifier.fromNamespaceAndPath("glowingalchemy", "block/" + textureName);
    }

    private void modelBlock(Block block, ModelTemplate model) {
        simpleBlock(block, model);
    }

    private Identifier modLoc(String path) {
        return Identifier.fromNamespaceAndPath("glowingalchemy", path);
    }
}
*/