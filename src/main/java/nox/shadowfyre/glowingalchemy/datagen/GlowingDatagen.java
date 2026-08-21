package nox.shadowfyre.glowingalchemy.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import nox.shadowfyre.glowingalchemy.debug.BlockListWriter;

public class GlowingDatagen {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        BlockListWriter.write(event.getGenerator().getPackOutput().getOutputFolder());
    }
}