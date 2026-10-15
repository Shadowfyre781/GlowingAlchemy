
package nox.shadowfyre.glowingalchemy.debug;

import nox.shadowfyre.glowingalchemy.forest_primeval.registry.FP_BlockDefinitions;
import nox.shadowfyre.glowingalchemy.core.registry.shared.GeneratedBlock;
import nox.shadowfyre.glowingalchemy.core.registry.shared.ExpansionEngine;
import java.util.List;

public class ExpansionTest {

    public static void run() {


        List<GeneratedBlock> blocks =
                ExpansionEngine.generate(FP_BlockDefinitions.ALL);

        System.out.println("Generated " + blocks.size() + " blocks.");
        for (GeneratedBlock block : blocks) {
            System.out.println(">" + block.name());
        }
    }
}