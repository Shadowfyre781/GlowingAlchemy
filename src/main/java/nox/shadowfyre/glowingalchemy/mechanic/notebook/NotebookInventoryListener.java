package nox.shadowfyre.glowingalchemy.mechanic.notebook;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import nox.shadowfyre.glowingalchemy.items.NotebookItem;
import nox.shadowfyre.glowingalchemy.registry.ItemRegistry;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

import java.nio.file.Path;

/**
 * NotebookInventoryListener — NeoForge event listener (server-side)
 *
 * Handles two concerns:
 *
 * 1. Coordinate logging: when an assigned notebook leaves a player's
 *    inventory (dropped, death), write [last seen: ...] to the volume file.
 *
 * 2. Item destruction detection: when a notebook is destroyed (burnt in lava,
 *    despawned, /clear), check the volume back in to NotebookVolumeTracker
 *    so a new copy can be assigned.
 *
 * Register this class on NeoForge.EVENT_BUS in GlowingAlchemy constructor:
 *   NeoForge.EVENT_BUS.register(new NotebookInventoryListener());
 *
 * // NOTEBOOK-LITE: remove this entire class for client-only version.
 * // Without it, coordinate logging and check-in on destruction are no-ops.
 */
public class NotebookInventoryListener {

    // -------------------------------------------------------------------------
    // Item dropped / thrown out of inventory
    // -------------------------------------------------------------------------



    /**
     * Fired when an ItemEntity enters the world (dropped from inventory,
     * thrown, popped off a broken container, etc.)
     *
     * If the item is an assigned notebook, log coordinates and note it
     * is now "in the world" rather than in a player inventory.
     * (It's still checked out — someone owns it — but its location is logged.)
     */
    @SubscribeEvent
    public void onItemEntitySpawn(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        if (event.getLevel().isClientSide()) return;

        ItemStack stack = itemEntity.getItem();
        if (!stack.is(ItemRegistry.NOTEBOOK.get())) return;
        if (!NotebookItem.isAssigned(stack)) return;

        int volume = NotebookItem.getVolume(stack);
        ServerLevel level = (ServerLevel) event.getLevel();

        // Get server config directory
        Path configDir = level.getServer().getServerDirectory().resolve("config");

        // Dimension key as readable string e.g. "minecraft:overworld"
        String dimension = level.dimension().identifier().toString();

        int x = (int) itemEntity.getX();
        int y = (int) itemEntity.getY();
        int z = (int) itemEntity.getZ();


    }

    // -------------------------------------------------------------------------
    // Player death — coordinates logged at death position
    // -------------------------------------------------------------------------

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ServerLevel level = player.level();
        Path configDir = level.getServer().getServerDirectory().resolve("config");
        String dimension = level.dimension().identifier().toString();

        int x = (int) player.getX();
        int y = (int) player.getY();
        int z = (int) player.getZ();

        // Check all inventory slots for assigned notebooks
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.is(ItemRegistry.NOTEBOOK.get())) continue;
            if (!NotebookItem.isAssigned(stack)) continue;

            int volume = NotebookItem.getVolume(stack);

        }
    }

    // -------------------------------------------------------------------------
    // Item destroyed / despawned — check volume back in
    // -------------------------------------------------------------------------

    /**
     * ItemEntity.kill() / despawn after 5 minutes — check the volume back in
     * so a new copy can be assigned.
     *
     * NeoForge fires EntityLeaveLevelEvent when an entity is removed.
     * We check if it's a notebook ItemEntity that was removed for a
     * non-pickup reason (i.e. destroyed, not picked up by a player).
     */
    @SubscribeEvent
    public void onItemEntityRemoved(net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        if (event.getLevel().isClientSide()) return;

        ItemStack stack = itemEntity.getItem();
        if (!stack.is(ItemRegistry.NOTEBOOK.get())) return;
        if (!NotebookItem.isAssigned(stack)) return;

        // If the item entity is being removed because it was picked up,
        // it will already have count = 0 or be marked as picked up.
        // If it's despawning or being destroyed, check it back in.
        if (itemEntity.getItem().isEmpty() || itemEntity.isAlive()) return;

        int volume = NotebookItem.getVolume(stack);
        ServerLevel level = (ServerLevel) event.getLevel();
        NotebookVolumeTracker.get(level).checkIn(volume);

        GlowingAlchemy.LOGGER.info(
                "[Notebook] Volume {} checked back in (item destroyed/despawned)", volume
        );
    }
}
