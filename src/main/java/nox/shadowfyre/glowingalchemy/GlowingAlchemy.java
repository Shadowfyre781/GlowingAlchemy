package nox.shadowfyre.glowingalchemy;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.*;
import com.mojang.logging.LogUtils;
import nox.shadowfyre.glowingalchemy.registry.ModRegistryCore;
import org.slf4j.Logger;
import nox.shadowfyre.glowingalchemy.datagen.GMOakMetadataProvider;
import nox.shadowfyre.glowingalchemy.registry.BlockRegistry;

@Mod(GlowingAlchemy.MODID)
public class GlowingAlchemy {
    public static final String MODID = "glowingalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();

    // Constructor: Merge all initialization logic here
    public GlowingAlchemy(IEventBus modEventBus, ModContainer modContainer) {
        // Just call your registry logic
        ModRegistryCore.register(modEventBus);

        // ... rest of your code ...

        // 2. Setup Events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);


        NeoForge.EVENT_BUS.register(this);

        // 3. Config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        // Your existing config logging code...
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            // event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }



    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
} // <--- THIS BRACE CLOSES THE CLASS