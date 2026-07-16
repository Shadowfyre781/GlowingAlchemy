package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import nox.shadowfyre.glowingalchemy.glowing_things.GlowColor;
import nox.shadowfyre.glowingalchemy.glowing_things.GlowPalette;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Expands BlockDefinitions.ALL into real registered blocks, looping over the
 * matching GlowColor palette for each definition's colorGroup.
 *
 * NOTE: currently every block registers as a plain Block regardless of archetype
 * (i.e. only the "block" shape). Stairs/slabs/walls/fences/etc. for MASONRY_SET,
 * DEEP_MASONRY_SET and WOOD_PLANK_SET are NOT yet generated -- that's the next
 * follow-up piece, alongside the model/blockstate datagen provider.
 */
public class BlockFamilyRegistry {

    // Keyed by resolved block id. Used later by the datagen provider and the
    // client-side BlockColor tint handler.
    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();
    public static final Map<String, GlowColor> BLOCK_TINTS = new HashMap<>();
    public static final Map<String, BlockDefinition> REGISTERED_DEFS = new HashMap<>();

    public static void registerAll() {
        for (BlockDefinition def : BlockDefinitions.ALL) {
            List<GlowColor> colors = colorsFor(def.colorGroup());

            if (colors.isEmpty()) {
                registerOne(def, null);
            } else {
                for (GlowColor color : colors) {
                    registerOne(def, color);
                }
            }
        }
    }

    private static void registerOne(BlockDefinition def, GlowColor color) {
        String id = def.resolveId(color);

        if (REGISTERED_BLOCKS.containsKey(id)) {
            throw new IllegalStateException("Duplicate block id generated: " + id
                    + " (family=" + def.family() + ", variant=" + def.variant() + ")");
        }

        MapColor mapColor = color != null ? color.mapColor() : MapColor.STONE; // TODO: per-family map color for unique/none entries
        int light = def.lightLevel() != null ? def.lightLevel() : 0;

        var register = def.namespace().equals("glowingalchemy")
                ? BlockRegistry.BLOCKS
                : BlockRegistry.GLOWING_THINGS_BLOCKS;
        var itemRegister = def.namespace().equals("glowingalchemy")
                ? BlockRegistry.ITEMS
                : BlockRegistry.GLOWING_THINGS_ITEMS;

        DeferredBlock<Block> block = register.registerBlock(id, properties -> new Block(properties
                .mapColor(mapColor)
                .strength(1.5F) // TODO: per-family strength/sound once those are added to the CSV
                .lightLevel(state -> light)
        ));

        DeferredItem<BlockItem> item = itemRegister.registerSimpleBlockItem(block);

        REGISTERED_BLOCKS.put(id, block);
        REGISTERED_DEFS.put(id, def);
        if (color != null) {
            BLOCK_TINTS.put(id, color);
        }
    }

    private static List<GlowColor> colorsFor(String colorGroup) {
        return switch (colorGroup) {
            case "full16" -> GlowPalette.FULL_16;
            case "pastel" -> GlowPalette.PASTELS;
            case "bold" -> GlowPalette.BOLDS;
            case "monochrome" -> GlowPalette.MONOCHROME;
            case "rainbow" -> GlowPalette.COLORS.stream()
                    .filter(c -> c.name().equals("rainbow"))
                    .toList();
            case "unique", "none" -> List.of(); // registered once, no palette color
            default -> resolveExplicitColorList(colorGroup);
        };
    }

    /** Handles comma-separated explicit color names, e.g. "red,green,bold_purple,pink,pastel_purple". */
    private static List<GlowColor> resolveExplicitColorList(String colorGroup) {
        List<GlowColor> result = new java.util.ArrayList<>();
        for (String rawName : colorGroup.split(",")) {
            String name = rawName.trim();
            GlowColor match = GlowPalette.COLORS.stream()
                    .filter(c -> c.name().equals(name))
                    .findFirst()
                    .orElse(null);
            if (match == null) {
                throw new IllegalStateException("Unknown color name '" + name
                        + "' in explicit color list \"" + colorGroup + "\" -- check GlowPalette.COLORS for the exact name");
            }
            result.add(match);
        }
        return result;
    }
}
