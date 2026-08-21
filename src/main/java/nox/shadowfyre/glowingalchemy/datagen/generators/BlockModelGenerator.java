package nox.shadowfyre.glowingalchemy.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import nox.shadowfyre.glowingalchemy.registry.GeneratedBlock;
import nox.shadowfyre.glowingalchemy.registry.RegistrationEngine;

import net.minecraft.client.resources.model.sprite.Material;
//import net.minecraft.client.renderer.RenderType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class BlockModelGenerator extends ModelProvider {

    private final List<GeneratedBlock> blocks;
    private final String namespace;

    public BlockModelGenerator(PackOutput output, String namespace, List<GeneratedBlock> blocks) {
        super(output, namespace);
        this.namespace = namespace;
        this.blocks = blocks.stream()
                .filter(b -> b.namespace().equals(namespace))
                .toList();
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return this.blocks.stream()
                .map(b -> RegistrationEngine.get(b.namespace(), b.name()))
                .filter(Objects::nonNull)
                .map(holder -> (Holder<Block>) holder);
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.empty();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (GeneratedBlock block : blocks) {

            var holder = RegistrationEngine.get(block.namespace(), block.name());
            if (holder == null) {
                System.err.println("[BlockModelGenerator] No holder for: "
                        + block.namespace() + ":" + block.name());
                continue;
            }

            Block b = holder.get();

            // Resolve texture path:
            // Colored blocks use a greyscale base texture named after the family+blockId
            // Uncolored blocks use their own texture reference from the definition
            String texturePath;
            if (block.definition().isColored()) {
                // e.g. glowingalchemy:block/limestone_base_grey
                texturePath = block.namespace() + ":block/"
                        + block.definition().familyId() + "_"
                        + block.definition().blockId() + "_grey";
            } else {
                // e.g. glowingalchemy:block/limestone
                texturePath = block.namespace() + ":block/"
                        + block.definition().texture();
            }

            Identifier textureId = Identifier.parse(texturePath);

            try {
                // Simple cube_all model — all six faces use the same texture
                // TODO: shape-specific models for SLAB, STAIRS, WALL etc.
                ModelTemplates.CUBE_ALL.create(
                        b,
                        new TextureMapping().put(TextureSlot.ALL, new Material(textureId)),
                        blockModels.modelOutput
                );
            } catch (Exception e) {
                System.err.println("[BlockModelGenerator] Failed on: "
                        + block.namespace() + ":" + block.name()
                        + " — " + e.getMessage());
            }
        }
    }

    public static void run(List<GeneratedBlock> blocks, GatherDataEvent.Client event) {
        event.createProvider(output ->
                new BlockModelGenerator(output, "glowingalchemy", blocks));
        event.createProvider(output ->
                new BlockModelGenerator(output, "glowing_things", blocks));
    }
}