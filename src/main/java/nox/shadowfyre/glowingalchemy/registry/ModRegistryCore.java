package nox.shadowfyre.glowingalchemy.registry;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import nox.shadowfyre.glowingalchemy.datagen.GMOakMetadataProvider;

public class ModRegistryCore {
    public static void register(net.neoforged.bus.api.IEventBus modEventBus) {
        BlockRegistry.register(modEventBus);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true,
                new GMOakMetadataProvider(event.getGenerator().getPackOutput()));
    }
}