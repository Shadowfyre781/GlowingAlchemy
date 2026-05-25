package nox.shadowfyre.glowingalchemy;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class GlowingAlchemyClient {

    // 1. Your mandatory client setup initializer
    public static void onClientSetup(final net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) {
        // Leave empty or add client configurations here
    }

    // 2. The explicit block color registry method hooked to the sub-class event
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {

        // Loop through the 16 colors in your GlowPalette list
        for (GlowColor color : GlowPalette.FULL_16) {

            // Mask out the 0xFF alpha channel from your custom hex formats
            int transparentHex = color.colorHex() & 0xFFFFFF;

            // Pull the dynamic block from your Registration dictionary map
            Block blockInstance = Registration.GLOW_WOOLS.get(color).get();

            // Run the block tint assignment logic
            event.register((state, level, pos, tintIndex) -> {
                if (tintIndex == 0) {
                    return transparentHex;
                }
                return 0xFFFFFFFF;
            }, blockInstance);
        }
    }
}
