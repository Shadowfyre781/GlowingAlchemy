package nox.shadowfyre.glowingalchemy.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;
;

public class BlockRegistry {

    // This is the "Inventory Shelf" for your blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GlowingAlchemy.MODID);

    // Example of a hard-coded block (your "base stock")
    public static final DeferredBlock<Block> GLOW_ASPHALT = BLOCKS.registerSimpleBlock(
            "glow_asphalt",
            () -> Block.Properties.of() // Pass as a Supplier
    );

    // We call this in your main class to activate the registration
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        // If you have Items, register them here too!
       // ITEMS.register(modEventBus);
    }
}