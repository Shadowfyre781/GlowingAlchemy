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
import net.neoforged.neoforge.data.event.GatherDataEvent.Client;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import nox.shadowfyre.glowingalchemy.datagen.DatagenEngine;
import nox.shadowfyre.glowingalchemy.debug.BlockListWriter;
import nox.shadowfyre.glowingalchemy.glowing_things.registry.GT_BlockDefinitions;
import nox.shadowfyre.glowingalchemy.registry.*;
import nox.shadowfyre.glowingalchemy.registry.ModDataComponents;
import org.slf4j.Logger;


import java.nio.file.Path;
import java.util.List;
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
        // 1. Bind DeferredRegisters to the event bus FIRST
        RegistrationEngine.register(modEventBus);
        BlockRegistry.register(modEventBus);

        // 2. Populate the definition registry
        BlockDefinitions.registerAll();
        GT_BlockDefinitions.registerAll();

        // 3. Expand definitions into GeneratedBlocks and register them
        List<GeneratedBlock> blocks = ExpansionEngine.generateAll();

        RegistrationEngine.registerBlocks(blocks);
        RegistrationEngine.registerBlockItems(blocks);
        ModDataComponents.register(modEventBus);
        // 4. Hook up event listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onGatherData);

        NeoForge.EVENT_BUS.register(this);

        // 5. Config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

        // 3. Config



    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        // Your existing config logging code...
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            // event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }
    //    private void onGatherData(net.neoforged.neoforge.data.event.GatherDataEvent event) {
   //         BlockListWriter.write(event.getGenerator().getPackOutput().getOutputFolder());
   //     }
    private void onGatherData(GatherDataEvent.Client event) {
        System.out.println("[BlockListWriter] onGatherData fired");
        BlockListWriter.write(Path.of("reports"));
        BlockListWriter.write(Path.of("reports"));
        DatagenEngine.run(event);
    }




    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
        BlockListWriter.write(Path.of("debug"));  // ADD
    }
} // <--- THIS BRACE CLOSES THE CLASS