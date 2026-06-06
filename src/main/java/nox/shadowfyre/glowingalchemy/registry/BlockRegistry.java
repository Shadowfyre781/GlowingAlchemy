package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(GlowingAlchemy.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(GlowingAlchemy.MODID);

    public static final DeferredRegister.Blocks GLOWING_THINGS_BLOCKS =
            DeferredRegister.createBlocks(GlowingAlchemy.GLOWING_THINGS);

    public static final DeferredRegister.Items GLOWING_THINGS_ITEMS =
            DeferredRegister.createItems(GlowingAlchemy.GLOWING_THINGS);

    public static final DeferredBlock<Block> GLOW_ASPHALT = BLOCKS.registerSimpleBlock(
            "glow_asphalt",
            () -> Block.Properties.of()
    );

    public static final DeferredBlock<Block> WHITE_GLOWGRASS = GLOWING_THINGS_BLOCKS.registerBlock(
            "white_glowgrass",
            properties -> new Block(properties
                    .mapColor(MapColor.GRASS)
                    .strength(0.6F)
                    .sound(SoundType.GRASS)
                    .randomTicks()
                    .lightLevel(state -> 15)
            )
    );

    public static final DeferredItem<BlockItem> WHITE_GLOWGRASS_ITEM =
            GLOWING_THINGS_ITEMS.registerSimpleBlockItem(WHITE_GLOWGRASS);

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        GLOWING_THINGS_BLOCKS.register(modEventBus);
        GLOWING_THINGS_ITEMS.register(modEventBus);
    }
}