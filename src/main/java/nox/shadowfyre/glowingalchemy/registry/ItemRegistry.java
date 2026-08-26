package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;
import nox.shadowfyre.glowingalchemy.items.NotebookItem;

/**
 * ItemRegistry — floppy drive for standalone items.
 *
 * Handles DeferredRegister.Items for the glowingalchemy namespace.
 * Block items (BlockItem wrappers) stay in BlockRegistry alongside
 * their parent blocks. This registry is for items that exist
 * independently of any block.
 *
 * Register new items here following the notebook pattern.
 */
public class ItemRegistry {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(GlowingAlchemy.MODID);

    // -------------------------------------------------------------------------
    // Notebook
    // Blank notebooks stack to 16 (handled in NotebookItem.getMaxStackSize).
    // stacksTo(1) here is the baseline — the item overrides per-stack.
    // -------------------------------------------------------------------------

    public static final DeferredItem<NotebookItem> NOTEBOOK =
            ITEMS.register("notebook",
                    () -> new NotebookItem());

    // -------------------------------------------------------------------------
    // Registration
    // Called from GlowingAlchemy constructor alongside BlockRegistry.register()
    // -------------------------------------------------------------------------

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
