
package nox.shadowfyre.glowingalchemy.core.datagen.shared;

import net.neoforged.neoforge.data.event.GatherDataEvent;
import nox.shadowfyre.glowingalchemy.core.datagen.shared.generators.BlockStateGenerator;
import nox.shadowfyre.glowingalchemy.core.registry.shared.ExpansionEngine;
import nox.shadowfyre.glowingalchemy.core.registry.shared.BlockDefinition;
import nox.shadowfyre.glowingalchemy.core.registry.shared.GeneratedBlock;

import java.util.List;

public final class DatagenEngine {

    private DatagenEngine() {}

    public static void run(
            GatherDataEvent.Client event,
            List<BlockDefinition> definitions
    ) {
        List<GeneratedBlock> blocks =
                ExpansionEngine.generate(definitions);

        // Each generator gets the full block list and the event.
        // Add new generators here as they are built.
        BlockStateGenerator.run(blocks, event);
        // BlockModelGenerator.run(blocks, event);

        // LootTableGenerator.run(blocks, event);
        // LanguageGenerator.run(blocks, event);
    }
}
