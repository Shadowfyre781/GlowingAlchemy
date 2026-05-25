package nox.shadowfyre.glowingalchemy;

import org.slf4j.Logger;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import nox.shadowfyre.glowingalchemy.core.ModCreativeTabs;
import net.neoforged.neoforge.registries.RegistryBuilder;

@Mod(GlowingAlchemy.MODID)
public class GlowingAlchemy {
    public static final String MODID = "glowingalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GlowingAlchemy(net.neoforged.bus.api.IEventBus modEventBus, net.neoforged.fml.ModContainer modContainer) {
        Registration.init(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);

        // This replaces the broken @EventBusSubscriber annotation
        modEventBus.addListener(GlowingAlchemyClient::onClientSetup);
        modEventBus.addListener(GlowingAlchemyClient::registerBlockColors);

        // IMPORTANT: Ensure your Data Generator is also registered here

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}