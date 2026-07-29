package nox.shadowfyre.glowingalchemy.registry;

import java.util.ArrayList;
import java.util.List;

public final class ExpansionEngine {

    private ExpansionEngine() {
    }

    public static List<GeneratedBlock> generateAll() {

        List<GeneratedBlock> generated = new ArrayList<>();

        for (BlockDefinition definition : BlockDefinitionRegistry.definitions()){
            generated.addAll(FamilyExpander.expand(definition));
        }

        return generated;
    }
}