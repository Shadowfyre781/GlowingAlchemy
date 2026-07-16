package nox.shadowfyre.glowingalchemy.registry;

import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

/**
 * Registers a BlockTintSource for every block in BlockFamilyRegistry that has a
 * tint assigned. Untinted blocks (unique/none color group) are skipped --
 * their texture is already the final color.
 *
 * NeoForge 26.1 renamed RegisterColorHandlersEvent.Block -> .BlockTintSources,
 * and register() now takes a List<BlockTintSource> (indexed by the model's
 * tintindex) plus a varargs of blocks that all share that list. Since every
 * block here needs its own distinct color, we call register() once per block
 * with a single-entry list.
 *
 * Call BlockFamilyColorHandler.register(event) from a RegisterColorHandlersEvent.BlockTintSources
 * listener in GlowingAlchemyClient.
 */
public class BlockFamilyColorHandler {
    public static void register(RegisterColorHandlersEvent.BlockTintSources event) {
        for (var entry : BlockFamilyRegistry.BLOCK_TINTS.entrySet()) {
            String id = entry.getKey();
            var color = entry.getValue();
            var block = BlockFamilyRegistry.REGISTERED_BLOCKS.get(id);
            if (block == null) continue;

            event.register(List.of(state -> color.colorHex()), block.get());
        }
    }
}
