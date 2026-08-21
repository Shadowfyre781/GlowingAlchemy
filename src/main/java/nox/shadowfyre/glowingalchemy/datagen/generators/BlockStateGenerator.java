package nox.shadowfyre.glowingalchemy.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
//import net.neoforged.neoforge.client.model.generators.BlockModelGenerators;
//import net.neoforged.neoforge.client.model.generators.ItemModelGenerators;
//import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import nox.shadowfyre.glowingalchemy.registry.BlockShape;
import nox.shadowfyre.glowingalchemy.registry.GeneratedBlock;
import nox.shadowfyre.glowingalchemy.registry.RegistrationEngine;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import java.util.Objects;
import java.util.stream.Stream;


public final class BlockStateGenerator extends ModelProvider {

    private final List<GeneratedBlock> blocks;
    private final String namespace;

    public BlockStateGenerator(PackOutput output, String namespace, List<GeneratedBlock> blocks) {
        super(output, namespace);
        this.namespace = namespace;
        this.blocks = blocks.stream()
                .filter(b -> b.namespace().equals(namespace))
                .toList();
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        // Only tell the validator about blocks we are actually generating models for
        return this.blocks.stream()
                .map(b -> RegistrationEngine.get(b.namespace(), b.name()))
                .filter(Objects::nonNull)
                .map(holder -> (Holder<Block>) holder);
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        // Return empty — item models handled separately later
        return Stream.empty();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (GeneratedBlock block : blocks) {
            var holder = RegistrationEngine.get(block.namespace(), block.name());
            if (holder == null) {
                System.err.println("[BlockStateGenerator] No holder for: "
                        + block.namespace() + ":" + block.name());
                continue;
            }
            try {
                blockModels.createTrivialCube(holder.get());
            } catch (Exception e) {
                System.err.println("[BlockStateGenerator] Failed on: "
                        + block.namespace() + ":" + block.name()
                        + " — " + e.getMessage());
            }
        }
    }

    public static void run(List<GeneratedBlock> blocks, GatherDataEvent.Client event) {
        // One provider per namespace
        event.createProvider(output ->
                new BlockStateGenerator(output, "glowingalchemy", blocks));
        event.createProvider(output ->
                new BlockStateGenerator(output, "glowing_things", blocks));
    }
}