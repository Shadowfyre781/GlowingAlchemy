
package nox.shadowfyre.glowingalchemy.registry;

import java.util.ArrayList;
import java.util.List;

public final class BlockDefinitionRegistry {

    private static final List<BlockDefinition> DEFINITIONS =
            new ArrayList<>();

    private BlockDefinitionRegistry() {
    }

    public static void register(BlockDefinition definition) {
        DEFINITIONS.add(definition);
    }

    public static List<BlockDefinition> definitions() {
        return List.copyOf(DEFINITIONS);
    }
}