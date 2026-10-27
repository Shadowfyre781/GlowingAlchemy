package nox.shadowfyre.glowingalchemy.core.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

public class Core_BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(GlowingAlchemy.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(GlowingAlchemy.MODID);


    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);


    }
}