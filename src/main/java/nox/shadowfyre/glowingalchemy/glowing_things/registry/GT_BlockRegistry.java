package nox.shadowfyre.glowingalchemy.glowing_things.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;



public final class GT_BlockRegistry {
    private static final String MOD_ID = "glowing_things";

    public static final DeferredRegister.Blocks GLOWING_THINGS_BLOCKS =
            DeferredRegister.createBlocks(MOD_ID);

    public static final DeferredRegister.Items GLOWING_THINGS_ITEMS =
            DeferredRegister.createItems(MOD_ID);

    public static void register(IEventBus modEventBus) {

        GLOWING_THINGS_BLOCKS.register(modEventBus);
        GLOWING_THINGS_ITEMS.register(modEventBus);
    }

}


