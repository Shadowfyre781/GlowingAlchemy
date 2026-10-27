package nox.shadowfyre.glowingalchemy.core.registry.shared;

import java.util.ArrayList;
import java.util.List;

public final class ExpansionEngine {

    private ExpansionEngine() {
    }

    public static List<GeneratedBlock> generate(
            List<BlockDefinition> definitions
    ) {
        List<GeneratedBlock> generated = new ArrayList<>();

        for (BlockDefinition definition : definitions) {
            generated.addAll(FamilyExpander.expand(definition));
        }

        return generated;
    }
}