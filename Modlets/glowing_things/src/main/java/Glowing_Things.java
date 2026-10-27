package nox.shadowfyre.glowingalchemy.glowing_things;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import nox.shadowfyre.glowingalchemy.glowing_things.registry.GT_BlockDefinitions;
import nox.shadowfyre.glowingalchemy.glowing_things.registry.GT_BlockRegistry;
import nox.shadowfyre.glowingalchemy.core.registry.shared.RegistrationEngine;
import nox.shadowfyre.glowingalchemy.core.registry.shared.ExpansionEngine;
import nox.shadowfyre.glowingalchemy.core.registry.shared.GeneratedBlock;

import java.util.List;

public class Glowing_Things {
   // @Mod("glowing_things")

    public Glowing_Things(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Bind Glowing Things' own registers.
        GT_BlockRegistry.register(modEventBus);

        // 2. Register its namespace with the shared engine.
        RegistrationEngine.registerNamespace(
                "glowing_things",
                GT_BlockRegistry.GLOWING_THINGS_BLOCKS,
                GT_BlockRegistry.GLOWING_THINGS_ITEMS
        );

        // 3. Expand only Glowing Things definitions.
        List<GeneratedBlock> blocks =
                ExpansionEngine.generate(GT_BlockDefinitions.ALL);

        // 4. Register its generated blocks and items.
        RegistrationEngine.registerBlocks(blocks);
        RegistrationEngine.registerBlockItems(blocks);
    }
    }


