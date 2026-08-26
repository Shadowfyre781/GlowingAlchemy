package nox.shadowfyre.glowingalchemy.mechanic.notebook;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import nox.shadowfyre.glowingalchemy.Config;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.saveddata.SavedDataType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * NotebookVolumeTracker — server-side SavedData
 *
 * Tracks:
 *   - Which volume numbers are currently "checked out"
 *     (a physical assigned notebook exists in the world for that volume)
 *   - The UUID of the player who checked it out
 *
 * Stored in: world/data/glowingalchemy_notebook_tracker.dat
 *
 * Also handles writing last-known coordinates to the volume's .txt file
 * when a notebook leaves a player's inventory (dropped, death, chest, etc.)
 * This feature is gated by Config.NOTEBOOK_TRACK_COORDINATES.
 *
 * // NOTEBOOK-LITE: This entire class is the server-side surface area.
 * // For a client-only version, delete this class and remove all references
 * // to it in NotebookItem.java and NotebookInventoryListener.java.
 * // Volume enforcement becomes "no-op" (all volumes always available).
 * // Coordinate logging becomes "no-op" (never written).
 */
public class NotebookVolumeTracker extends SavedData {

    private static final Identifier DATA_ID =
            Identifier.fromNamespaceAndPath(
                    GlowingAlchemy.MODID,
                    "notebook_tracker"
            );

    /**
     * One entry in the persistent notebook ledger.
     *
     * Example:
     *
     *   volume = 17
     *   owner  = Alice's UUID
     *
     * This is the serialized form of one checked-out volume.
     */
    private record VolumeEntry(int volume, UUID owner) {

        private static final Codec<VolumeEntry> CODEC =
                RecordCodecBuilder.create(instance -> instance.group(
                        Codec.INT.fieldOf("volume").forGetter(VolumeEntry::volume),
                        Codec.STRING.xmap(UUID::fromString, UUID::toString)
                                .fieldOf("owner")
                                .forGetter(VolumeEntry::owner)
                ).apply(instance, VolumeEntry::new));
    }
    /**
     * Codec for the entire notebook ledger.
     *
     * The ledger is stored as:
     *
     * volumes: [
     *     { volume: 1, owner: "..." },
     *     { volume: 7, owner: "..." }
     * ]
     */
    private static final Codec<NotebookVolumeTracker> CODEC =
            VolumeEntry.CODEC.listOf().xmap(
                    entries -> {
                        NotebookVolumeTracker tracker = new NotebookVolumeTracker();

                        for (VolumeEntry entry : entries) {
                            tracker.checkedOut.put(
                                    entry.volume(),
                                    entry.owner()
                            );
                        }

                        return tracker;
                    },
                    tracker -> tracker.checkedOut.entrySet().stream()
                            .map(entry -> new VolumeEntry(
                                    entry.getKey(),
                                    entry.getValue()
                            ))
                            .toList()
            );



    /**
     * Persistent identity for the notebook ledger.
     *
     * SavedDataStorage uses this to:
     *   - find the ledger on disk
     *   - create a new ledger if none exists
     *   - encode/decode the ledger
     */
    private static final SavedDataType<NotebookVolumeTracker> TYPE =
            new SavedDataType<>(
                    DATA_ID,
                    NotebookVolumeTracker::new,
                    CODEC
            );


    // volume number → UUID of owning player
    private final Map<Integer, UUID> checkedOut = new HashMap<>();


    public static NotebookVolumeTracker get(ServerLevel level) {
        return level.getServer()
                .overworld()
                .getDataStorage()
                .computeIfAbsent(TYPE);
    }



    // -------------------------------------------------------------------------
    // Check-out / check-in API
    // -------------------------------------------------------------------------

    /**
     * Mark a volume as checked out by a player.
     * Called from NotebookItem.assignVolume().
     */
    public boolean checkOut(int volume, UUID playerUUID) {
        if (checkedOut.containsKey(volume)) {
            return false;
        }

        checkedOut.put(volume, playerUUID);
        setDirty();
        return true;

    }

    /**
     * Mark a volume as available again.
     * Called when:
     *   - The notebook item is destroyed (burnt in lava, /clear, etc.)
     *   - The player explicitly un-assigns the volume via the GUI
     */
    public void checkIn(int volume) {
        checkedOut.remove(volume);
        setDirty();
    }

    /**
     * Returns true if a physical copy of this volume exists somewhere
     * in the world (player inventory, chest, ground, etc.)
     * Used by VolumeSelectScreen to gray out unavailable volumes.
     */
    public boolean isCheckedOut(int volume) {
        return checkedOut.containsKey(volume);
    }

    /**
     * Returns the UUID of the player who has this volume checked out,
     * or null if it's available.
     */
    public UUID getOwner(int volume) {
        return checkedOut.getOrDefault(volume, null);
    }

    /**
     * Returns a copy of the full checked-out map.
     * Used by VolumeSelectScreen to build the volume list in one pass.
     */
    public Map<Integer, UUID> getAllCheckedOut() {
        return Map.copyOf(checkedOut);
    }

    // -------------------------------------------------------------------------
    // Coordinate logging
    // Writes last-known position to the volume's .txt file.
    // Gated by Config.NOTEBOOK_TRACK_COORDINATES.
    // Called from NotebookInventoryListener when item leaves player inventory.
    // -------------------------------------------------------------------------

    /**
     * Appends or replaces the [last seen] line at the top of volume_N.txt.
     * Safe to call server-side — file path is resolved from the server's
     * config directory, not the client's.
     *
     * Format:
     *   [last seen: Overworld, X: -342, Y: 64, Z: 128]
     *
     * // NOTEBOOK-LITE: remove this method for client-only version
     */


    // -------------------------------------------------------------------------
// ⚠ MANGO ZONE ⚠
// Emergency registry recovery tools.
// These bypass normal state-transition validation.
// Do not call from ordinary gameplay code.
// -------------------------------------------------------------------------

    /**
     * Emergency: a physical notebook has been found, but the registry
     * incorrectly says the volume is available.
     */
    public void mangoForceClaimVolumeForAdmin(int volume, UUID playerUUID) {
        checkedOut.put(volume, playerUUID);
        setDirty();

        GlowingAlchemy.LOGGER.warn(
                "[MANGO] Volume {} forcibly claimed by {}",
                volume,
                playerUUID
        );
    }

    /**
     * Emergency: the registry says a physical notebook exists, but an
     * administrator has determined that it does not.
     */
    public void mangoForceResetVolumeForAdmin(int volume) {
        UUID previousOwner = checkedOut.remove(volume);
        setDirty();

        GlowingAlchemy.LOGGER.warn(
                "[MANGO] Volume {} forcibly reset. Previous owner: {}",
                volume,
                previousOwner
        );
    }
}
