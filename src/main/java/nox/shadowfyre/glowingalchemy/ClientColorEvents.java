package nox.shadowfyre.glowingalchemy
;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;



// Subscribe only on the physical client side
@EventBusSubscriber(modid = "Glowing_Alchemy", value = Dist.CLIENT)
public class ClientColorEvents {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent event) {
        event.register((state, level, pos, tintIndex) -> {
            // Check tint index and return color in 0xRRGGBB format
            if (tintIndex == 0) {
                return 0x00FF00; // Example: bright green
            }
            return 0xFFFFFF; // Default fallback
        }, GlowingAlchemy.GlowWool.get()); // Pass your block(s) here
    }
}
