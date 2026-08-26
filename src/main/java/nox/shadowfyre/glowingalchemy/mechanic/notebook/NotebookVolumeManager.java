package nox.shadowfyre.glowingalchemy.mechanic.notebook;

import net.minecraft.client.Minecraft;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * NotebookVolumeManager — client-side file I/O
 *
 * Owns the flat-file structure under:
 *   .minecraft/config/glowingalchemy/notebook/
 *     volume_1.txt
 *     volume_2.txt
 *     ...
 *     images/
 *       my_map.png
 *       sketch.png
 *
 * Volume files are plain UTF-8 text. The first line is always the volume
 * title (may be blank). The remainder is raw page content using markup:
 *
 *   [page]              — manual page break
 *   [img:filename|half] — half-page image (reduces char capacity by 50%)
 *   [img:filename|full] — full-page image (consumes entire page)
 *
 * This class is entirely client-side. It should never be called from
 * server-side code.
 *
 * // NOTEBOOK-LITE: this class is fully compatible with a client-only mod.
 * // No changes needed here for extraction.
 */
public class NotebookVolumeManager {

    // -------------------------------------------------------------------------
    // Paths
    // -------------------------------------------------------------------------

    public static Path getNotebookDir() {
        return Minecraft.getInstance().gameDirectory.toPath()
                .resolve("config/glowingalchemy/notebook");
    }

    public static Path getImagesDir() {
        return getNotebookDir().resolve("images");
    }

    public static Path getVolumePath(int volume) {
        return getNotebookDir().resolve("volume_" + volume + ".txt");
    }

    // -------------------------------------------------------------------------
    // Directory setup
    // -------------------------------------------------------------------------

    /**
     * Ensures notebook and images directories exist.
     * Safe to call multiple times.
     */
    public static void ensureDirectories() {
        try {
            Files.createDirectories(getNotebookDir());
            Files.createDirectories(getImagesDir());
        } catch (IOException e) {
            GlowingAlchemy.LOGGER.error("[Notebook] Failed to create notebook directories: {}",
                    e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Volume discovery
    // -------------------------------------------------------------------------

    /**
     * Returns a sorted list of all volume numbers that have a file on disk.
     * Used by VolumeSelectScreen to build the shelf.
     */
    public static List<Integer> getExistingVolumes() {
        List<Integer> volumes = new ArrayList<>();
        Path dir = getNotebookDir();
        if (!Files.exists(dir)) return volumes;

        try (var stream = Files.list(dir)) {
            stream.filter(p -> p.getFileName().toString().matches("volume_\\d+\\.txt"))
                    .forEach(p -> {
                        String name = p.getFileName().toString();
                        // Extract number between "volume_" and ".txt"
                        try {
                            int n = Integer.parseInt(name.substring(7, name.length() - 4));
                            volumes.add(n);
                        } catch (NumberFormatException ignored) {}
                    });
        } catch (IOException e) {
            GlowingAlchemy.LOGGER.error("[Notebook] Failed to list volumes: {}", e.getMessage());
        }

        volumes.sort(Integer::compareTo);
        return volumes;
    }

    /**
     * Returns the next available volume number (max existing + 1, minimum 1).
     */
    public static int getNextVolumeNumber() {
        List<Integer> existing = getExistingVolumes();
        if (existing.isEmpty()) return 1;
        return existing.get(existing.size() - 1) + 1;
    }

    // -------------------------------------------------------------------------
    // Read / write
    // -------------------------------------------------------------------------

    /**
     * Reads the full raw content of a volume file.
     * Returns empty string if the file doesn't exist yet.
     */
    public static String readVolume(int volume) {
        Path path = getVolumePath(volume);
        if (!Files.exists(path)) return "";
        try {
            return Files.readString(path);
        } catch (IOException e) {
            GlowingAlchemy.LOGGER.error("[Notebook] Failed to read volume {}: {}", volume,
                    e.getMessage());
            return "";
        }
    }

    /**
     * Writes the full raw content of a volume file.
     * Creates the file if it doesn't exist.
     */
    public static void writeVolume(int volume, String content) {
        ensureDirectories();
        Path path = getVolumePath(volume);
        try {
            Files.writeString(path, content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            GlowingAlchemy.LOGGER.error("[Notebook] Failed to write volume {}: {}", volume,
                    e.getMessage());
        }
    }

    /**
     * Creates a new volume file with a title line and empty body.
     * Safe to call if the file already exists — will not overwrite.
     */
    public static void createVolume(int volume, String title) {
        Path path = getVolumePath(volume);
        if (Files.exists(path)) return; // Never overwrite existing data

        ensureDirectories();
        String initial = (title != null && !title.isBlank() ? title : "") + "\n";
        writeVolume(volume, initial);
    }

    // -------------------------------------------------------------------------
    // Title helpers
    // The first line of the volume file is always the title.
    // -------------------------------------------------------------------------

    /**
     * Reads the title from the first line of a volume file.
     * Returns empty string if untitled or file doesn't exist.
     */
    public static String readTitle(int volume) {
        String content = readVolume(volume);
        if (content.isBlank()) return "";
        int nl = content.indexOf('\n');
        if (nl < 0) return content.trim(); // Entire file is the title (edge case)
        return content.substring(0, nl).trim();
    }

    /**
     * Updates the title (first line) without touching the rest of the file.
     */
    public static void writeTitle(int volume, String title) {
        String content = readVolume(volume);
        int nl = content.indexOf('\n');
        String body = nl >= 0 ? content.substring(nl) : "\n";
        writeVolume(volume, (title != null ? title.trim() : "") + body);
    }

    /**
     * Returns the body content (everything after the first line).
     * This is what PageParser receives.
     */
    public static String readBody(int volume) {
        String content = readVolume(volume);
        int nl = content.indexOf('\n');
        if (nl < 0) return ""; // No body yet
        return content.substring(nl + 1);
    }

    /**
     * Writes the body content (everything after the first line),
     * preserving the existing title.
     */
    public static void writeBody(int volume, String body) {
        String title = readTitle(volume);
        writeVolume(volume, title + "\n" + body);
    }

    // -------------------------------------------------------------------------
    // Volume existence / deletion
    // -------------------------------------------------------------------------

    public static boolean volumeExists(int volume) {
        return Files.exists(getVolumePath(volume));
    }

    /**
     * Deletes a volume file. Irreversible.
     * Caller should check-in the volume to NotebookVolumeTracker before calling.
     */
    public static void deleteVolume(int volume) {
        try {
            Files.deleteIfExists(getVolumePath(volume));
        } catch (IOException e) {
            GlowingAlchemy.LOGGER.error("[Notebook] Failed to delete volume {}: {}", volume,
                    e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Config-relative path for server-side coordinate logging
    // (called from NotebookVolumeTracker, which resolves its own path)
    // This method is here for reference only — server uses its own path resolution.
    // -------------------------------------------------------------------------

    /**
     * Returns the [last seen] line from a volume file, if present.
     * Used by NotebookScreen to display last-known location in a tooltip or header.
     */
    public static Optional<String> readLastSeen(int volume) {
        String content = readVolume(volume);
        if (content.startsWith("[last seen:")) {
            int end = content.indexOf('\n');
            return Optional.of(end >= 0 ? content.substring(0, end).trim() : content.trim());
        }
        return Optional.empty();
    }
}
