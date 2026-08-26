package nox.shadowfyre.glowingalchemy.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PageParser — converts raw volume body text into a List<NotebookPage>
 *
 * Markup syntax (offline-editable in any text editor):
 *
 *   [page]                    — manual page break
 *   [img:filename.png|half]   — half-page image (~320 char capacity remaining)
 *   [img:filename.png|full]   — full-page image (no text on this page)
 *
 * Pagination rules:
 *   - Base capacity: MAX_CHARS_PER_PAGE (640) characters per page
 *   - Half image: capacity halved to 320 for that page
 *   - Full image: capacity = 0 (image fills the page, no text)
 *   - [page] tag: always starts a new page regardless of char count
 *   - Overflow: text that exceeds page capacity spills onto the next page
 *     automatically. Images do NOT carry over to overflow pages.
 *   - Hard cap: MAX_PAGES (96) pages per volume. Content past the cap is
 *     silently truncated (the raw file is never modified).
 *
 * The title line (first line of the volume file) is NOT passed to this parser.
 * NotebookVolumeManager.readBody() strips it before handing off here.
 *
 * // NOTEBOOK-LITE: fully compatible, no changes needed for client-only extraction.
 */
public class PageParser {

    public static final int MAX_CHARS_PER_PAGE = 640;
    public static final int MAX_PAGES          = 96;

    // Matches [img:filename.png|half] or [img:filename.png|full]
    // Case-insensitive on the size token.
    private static final Pattern IMG_PATTERN =
            Pattern.compile("\\[img:([^|\\]]+)\\|(half|full)\\]",
                    Pattern.CASE_INSENSITIVE);

    // Manual page break markers
    private static final Pattern PAGE_BREAK =
            Pattern.compile("\\[page\\]|^---$", Pattern.MULTILINE);

    // -------------------------------------------------------------------------
    // Public API
    // -------------------------------------------------------------------------

    /**
     * Parse raw body text into pages.
     * @param rawBody  the volume body (title line already stripped)
     * @return         immutable list of pages, max MAX_PAGES entries
     */
    public static List<NotebookPage> parse(String rawBody) {
        if (rawBody == null || rawBody.isBlank()) {
            return List.of(new NotebookPage("", null, ImageSize.NONE));
        }

        List<NotebookPage> pages = new ArrayList<>();

        // Step 1: Split on manual page breaks
        String[] manualSections = PAGE_BREAK.split(rawBody);

        for (String section : manualSections) {
            if (pages.size() >= MAX_PAGES) break;
            parseSection(section.trim(), pages);
        }

        // Always return at least one (blank) page so the screen has something to show
        if (pages.isEmpty()) {
            pages.add(new NotebookPage("", null, ImageSize.NONE));
        }

        return Collections.unmodifiableList(pages);
    }

    // -------------------------------------------------------------------------
    // Internal: parse one manual section (between [page] tags)
    // -------------------------------------------------------------------------

    private static void parseSection(String section, List<NotebookPage> pages) {
        if (pages.size() >= MAX_PAGES) return;

        // Extract image tag from this section (only the first one is used per section)
        ImageInfo img = extractImage(section);

        // Remove image tags from text
        String cleanText = IMG_PATTERN.matcher(section).replaceAll("").trim();

        // Calculate character capacity for the first page of this section
        int capacity = switch (img.size()) {
            case FULL -> 0;          // Image fills the whole page
            case HALF -> MAX_CHARS_PER_PAGE / 2;
            case NONE -> MAX_CHARS_PER_PAGE;
        };

        if (capacity == 0 || cleanText.isEmpty()) {
            // Full-image page, or no text at all
            pages.add(new NotebookPage(
                    cleanText.isEmpty() ? "" : cleanText,
                    img.filename(),
                    img.size()
            ));
            return;
        }

        // Split text into capacity-sized chunks
        // First chunk gets the image; overflow chunks are plain text
        boolean firstChunk = true;
        int index = 0;

        while (index < cleanText.length() && pages.size() < MAX_PAGES) {
            // Find a clean break point (prefer word boundary over mid-word split)
            int end = Math.min(index + capacity, cleanText.length());
            if (end < cleanText.length()) {
                // Walk back to the last space to avoid splitting mid-word
                int lastSpace = cleanText.lastIndexOf(' ', end);
                if (lastSpace > index) {
                    end = lastSpace;
                }
            }

            String chunk = cleanText.substring(index, end).trim();
            index = end;
            // Skip leading space on the next chunk
            if (index < cleanText.length() && cleanText.charAt(index) == ' ') index++;

            if (firstChunk) {
                pages.add(new NotebookPage(chunk, img.filename(), img.size()));
                firstChunk = false;
            } else {
                // Overflow pages: no image, full capacity
                pages.add(new NotebookPage(chunk, null, ImageSize.NONE));
            }

            // Reset capacity for overflow pages
            capacity = MAX_CHARS_PER_PAGE;
        }

        // Edge case: section was non-empty but cleanText ended up empty after
        // stripping the image tag (e.g. "[img:map.png|full]" with no other text)
        if (firstChunk) {
            pages.add(new NotebookPage("", img.filename(), img.size()));
        }
    }

    // -------------------------------------------------------------------------
    // Image extraction
    // -------------------------------------------------------------------------

    private static ImageInfo extractImage(String text) {
        Matcher m = IMG_PATTERN.matcher(text);
        if (m.find()) {
            String filename = m.group(1).trim();
            ImageSize size = m.group(2).equalsIgnoreCase("full")
                    ? ImageSize.FULL : ImageSize.HALF;
            return new ImageInfo(filename, size);
        }
        return new ImageInfo(null, ImageSize.NONE);
    }

    // -------------------------------------------------------------------------
    // Supporting types
    // -------------------------------------------------------------------------

    public enum ImageSize {
        NONE,
        HALF,   // Top half of the page; text flows below
        FULL    // Entire page; no text rendered
    }

    /**
     * One parsed page of the notebook.
     *
     * @param text      The text content for this page (may be empty)
     * @param imagePath Filename from the [img:] tag, or null if no image
     * @param imageSize NONE / HALF / FULL
     */
    public record NotebookPage(String text, String imagePath, ImageSize imageSize) {
        public boolean hasImage() {
            return imagePath != null && !imagePath.isBlank();
        }
        public boolean hasText() {
            return text != null && !text.isBlank();
        }
    }

    /** Internal use only — result of extractImage() */
    private record ImageInfo(String filename, ImageSize size) {}
}
