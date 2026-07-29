package nox.shadowfyre.glowingalchemy;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
//import nox.shadowfyre.glowingalchemy.registry.CreativeTabRegistry;

@Mod(GlowingAlchemy.MODID)
public class GlowingAlchemy {
    public static final String MODID = "glowingalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String GLOWING_THINGS = "glowing_things";
    public static final String REDSTONE_INDUSTRY = "redstone_industry";
    public static final String ELEMENTAL_ALCHEMY = "elemental_alchemy";



    // Constructor: Merge all initialization logic here
    public GlowingAlchemy(IEventBus modEventBus, ModContainer modContainer) {
        // Just call your registry logic
        //ModRegistryCore.register(modEventBus);

        // 2. Setup Events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // MANUALLY REGISTER YOUR DATA GENERATOR HERE:
       // modEventBus.addListener(ModDataGenerators::gatherData);
        //CreativeTabRegistry.TABS.register(modEventBus);
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