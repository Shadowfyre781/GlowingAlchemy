package nox.shadowfyre.glowingalchemy.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;
import nox.shadowfyre.glowingalchemy.datagen.generators.BlockStateGenerator;
import nox.shadowfyre.glowingalchemy.registry.ExpansionEngine;
import nox.shadowfyre.glowingalchemy.registry.GeneratedBlock;

import java.util.List;

public final class DatagenEngine {

    private DatagenEngine() {}

    public static void run(GatherDataEvent.Client event) {

        List<GeneratedBlock> blocks = ExpansionEngine.generateAll();

        // Each generator gets the full block list and the event
        // Add new generators here as they are built
        BlockStateGenerator.run(blocks, event);
        // BlockModelGenerator.run(blocks, event);    // next
        // ItemModelGenerator.run(blocks, event);     // next
        // LootTableGenerator.run(blocks, event);     // next
        // LanguageGenerator.run(blocks, event);      // next
    }
}