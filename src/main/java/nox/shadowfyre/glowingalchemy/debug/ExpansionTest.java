
package nox.shadowfyre.glowingalchemy.debug;

import nox.shadowfyre.glowingalchemy.registry.BlockDefinitions;
import nox.shadowfyre.glowingalchemy.registry.GeneratedBlock;
import nox.shadowfyre.glowingalchemy.registry.ExpansionEngine;
import java.util.List;

public class ExpansionTest {

    public static void run() {

        BlockDefinitions.registerAll(); // feed the registry first

        List<GeneratedBlock> blocks = ExpansionEngine.generateAll();

        System.out.println("Generated " + blocks.size() + " blocks.");
        for (GeneratedBlock block : blocks) {
            System.out.println(">" + block.name());
        }
    }
}