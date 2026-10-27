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
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import nox.shadowfyre.glowingalchemy.core.registry.Core_BlockRegistry;
import nox.shadowfyre.glowingalchemy.core.registry.shared.RegistrationEngine;
import nox.shadowfyre.glowingalchemy.core.registry.shared.ExpansionEngine;
import nox.shadowfyre.glowingalchemy.core.registry.shared.GeneratedBlock;
import nox.shadowfyre.glowingalchemy.core.datagen.shared.DatagenEngine;
import nox.shadowfyre.glowingalchemy.debug.BlockListWriter;
import nox.shadowfyre.glowingalchemy.core.registry.Core_BlockDefinitions;
import org.slf4j.Logger;


import java.nio.file.Path;
import java.util.List;
//import nox.shadowfyre.glowingalchemy.registry.CreativeTabRegistry;

@Mod(GlowingAlchemy.MODID)
public class GlowingAlchemy {
    public static final String MODID = "glowingalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();



    // Constructor: Merge all initialization logic here
    public GlowingAlchemy(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Bind Core's DeferredRegisters to the event bus.
        Core_BlockRegistry.register(modEventBus);

        // 2. Tell the shared engine which registers belong to Core.
        RegistrationEngine.registerNamespace(
                MODID,
                Core_BlockRegistry.BLOCKS,
                Core_BlockRegistry.ITEMS
        );

        // 3. Expand only Core's definitions.
        List<GeneratedBlock> blocks =
                ExpansionEngine.generate(Core_BlockDefinitions.definitions());

        // 4. Register Core's generated blocks and items.
        RegistrationEngine.registerBlocks(blocks);
        RegistrationEngine.registerBlockItems(blocks);

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


    private void onGatherData(GatherDataEvent.Client event) {
        System.out.println("[BlockListWriter] onGatherData fired");
        BlockListWriter.write(Path.of("reports"));
        DatagenEngine.run(event, Core_BlockDefinitions.definitions());
    }




    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
        BlockListWriter.write(Path.of("debug"));  // ADD
    }
} // <--- THIS BRACE CLOSES THE CLASS