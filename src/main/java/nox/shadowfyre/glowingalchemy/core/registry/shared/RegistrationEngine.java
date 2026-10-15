package nox.shadowfyre.glowingalchemy.core.registry.shared;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.neoforged.neoforge.registries.DeferredItem;


public final class RegistrationEngine {



    // Lookup table: registry name → registered holder
    // e.g. "glowingalchemy:birch_log" → DeferredBlock
    private record RegistryTarget(
            DeferredRegister.Blocks blocks,
            DeferredRegister.Items items
    ) {}

    private static final Map<String, RegistryTarget> REGISTRY_TARGETS =
            new HashMap<>();

    public static void registerNamespace(
            String namespace,
            DeferredRegister.Blocks blocks,
            DeferredRegister.Items items
    ) {
        RegistryTarget previous = REGISTRY_TARGETS.putIfAbsent(
                namespace,
                new RegistryTarget(blocks, items)
        );

        if (previous != null) {
            throw new IllegalStateException(
                    "Duplicate registration namespace: " + namespace
            );
        }
    }

    private static final Map<String, DeferredBlock<Block>> REGISTRY_MAP = new HashMap<>();
    private static final Map<String, DeferredItem<Item>> ITEM_REGISTRY_MAP = new HashMap<>();

    // Call this from your mod constructor, before the event bus fires

    // Call this after BlockDefinitions.registerAll() and before the event bus fires
    public static void registerBlocks(List<GeneratedBlock> blocks) {

        for (GeneratedBlock block : blocks) {
            String name = block.name();
            String namespace = block.namespace();

            DeferredRegister.Blocks target = resolveRegister(namespace);
            if (target == null) {
                throw new IllegalStateException(
                        "Unknown registration namespace '" + namespace
                                + "' for generated block '" + name + "'"
                );
            }//if (REGISTRY_MAP.containsKey(fullName))

            String fullName = namespace + ":" + name;

            if (REGISTRY_MAP.containsKey(fullName)) {
                throw new IllegalStateException(
                        "Duplicate generated block: " + fullName
                );
            }//if (REGISTRY_MAP.containsKey(fullName))

            DeferredBlock<Block> holder = target.registerBlock(
                    name,
                    Block::new,
                    () -> BlockBehaviour.Properties.of()
                            .strength(1.5f, 6.0f)
            );

            REGISTRY_MAP.put(fullName, holder);
        }//for (GeneratedBlock
    }//registerBlocks

    // Register the BlockItem corresponding to each generated block.
    public static void registerBlockItems(List<GeneratedBlock> blocks) {
        System.out.println(
                "[RegistrationEngine] registerBlockItems() called with "
                        + blocks.size()
                        + " blocks"
        );
        for (GeneratedBlock block : blocks) {
            String name = block.name();
            String namespace = block.namespace();
            String fullName = namespace + ":" + name;
            DeferredBlock<Block> holder = get(namespace, name);
            if (holder == null) {
                throw new IllegalStateException( "Cannot create BlockItem, missing block: " + fullName );
            }
            if (ITEM_REGISTRY_MAP.containsKey(fullName)) {
                throw new IllegalStateException( "Duplicate generated BlockItem: " + fullName );
            }

            DeferredRegister.Items target = resolveItemRegister(namespace);

            if (target == null) {
                throw new IllegalStateException(
                        "Unknown namespace for BlockItem: " + fullName
                );

            }

            DeferredItem<Item> itemHolder = target.registerItem(
                    name,
                    properties -> new BlockItem(
                            holder.get(),
                            properties
                    )
            );
            System.out.println(
                    "[RegistrationEngine] BlockItem registrations created = "
                            + ITEM_REGISTRY_MAP.size()
            );
            ITEM_REGISTRY_MAP.put(fullName, itemHolder);

            System.out.println("[RegistrationEngine] BlockItem registered: " + fullName);
        } }



    private static DeferredRegister.Items resolveItemRegister(String namespace) {
        RegistryTarget target = REGISTRY_TARGETS.get(namespace);
        return target == null ? null : target.items();
    }


    // Retrieve a registered block.
public static DeferredBlock<Block> get( String namespace, String name ) {
    return REGISTRY_MAP.get(namespace + ":" + name);
}
// Retrieve the BlockItem corresponding to a registered block.
public static DeferredItem<Item> getBlockItem( String namespace, String name ) {
    return ITEM_REGISTRY_MAP.get(namespace + ":" + name);
}

    private static DeferredRegister.Blocks resolveRegister(String namespace) {
        RegistryTarget target = REGISTRY_TARGETS.get(namespace);
        return target == null ? null : target.blocks();
    }
        }
