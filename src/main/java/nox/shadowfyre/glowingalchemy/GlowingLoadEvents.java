package nox.shadowfyre.glowingalchemy;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import nox.shadowfyre.glowingalchemy.debug.BlockListWriter;

import java.nio.file.Path;

public class GlowingLoadEvents {

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        BlockListWriter.write(Path.of("debug"));
        // writes to debug/nox.shadowfyre.glowingalchemy/dev/BlockList.txt
        // in your run folder
    }
}