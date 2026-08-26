package nox.shadowfyre.glowingalchemy.client.gui;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import nox.shadowfyre.glowingalchemy.mechanic.notebook.NotebookVolumeManager;
import nox.shadowfyre.glowingalchemy.client.gui.PageParser;
import nox.shadowfyre.glowingalchemy.client.gui.PageParser.ImageSize;
import nox.shadowfyre.glowingalchemy.client.gui.PageParser.NotebookPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NotebookScreen — the main notebook editor
 *
 * Visual: Patchouli book texture as background (the "leather cover"),
 * custom MultiLineEditBox as the "tablet screen" inside it.
 *
 * Editing model:
 *   - The screen works on one continuous string (the volume body).
 *   - PageParser splits it into virtual pages for display.
 *   - When editing, the player is actually editing the raw body string;
 *     the current page's text is spliced back in on page-turn or close.
 *   - Page turns auto-save to disk.
 *
 * Page navigation:
 *   - [<] [>] buttons flip between parsed pages
 *   - Current page indicator shown at bottom center
 *   - Page 1 is reserved for the title (read-only display, editable via
 *     a small title field at the top)
 *
 * Image rendering:
 *   - Images referenced by [img:filename|size] tags are loaded from
 *     .minecraft/config/glowingalchemy/notebook/images/
 *   - DynamicTexture objects are cached to avoid per-frame disk reads
 *   - Cache cleared on screen close to avoid memory leaks
 *
 * // NOTEBOOK-LITE: fully compatible, no changes needed for client-only extraction.
 */
public class NotebookScreen extends Screen {

    // Patchouli book texture — replace with custom art when ready
    // Custom path: ResourceLocation.fromNamespaceAndPath("glowingalchemy",
    //              "textures/gui/notebook/open_spread.png")
    private static final Identifier BOOK_BG =
            Identifier.fromNamespaceAndPath("patchouli", "textures/gui/book_brown.png");

    // Book panel dimensions (matches Patchouli's open book spread)
    private static final int BG_WIDTH  = 272;
    private static final int BG_HEIGHT = 178;

    // Inner writing area insets (inside the book texture's page margin)
    private static final int PAGE_INSET_X = 20;
    private static final int PAGE_INSET_Y = 18;
    private static final int PAGE_WIDTH   = BG_WIDTH / 2 - PAGE_INSET_X * 2;  // ~96px per page
    private static final int PAGE_HEIGHT  = BG_HEIGHT - PAGE_INSET_Y * 2 - 24; // ~114px

    // -------------------------------------------------------------------------
    // State
    // -------------------------------------------------------------------------

    private final int volume;

    // Raw body text — single source of truth, written to disk on save
    private String rawBody;

    // Parsed pages — rebuilt whenever rawBody changes
    private List<NotebookPage> pages;

    // Current page index (0-based internally, displayed as 1-based)
    private int currentPage = 0;

    // Edit box for the current page's text
    private MultiLineEditBox textBox;

    // Title edit box (shown at top of page 1)
    private net.minecraft.client.gui.components.EditBox titleBox;

    // Navigation buttons
    private Button prevButton;
    private Button nextButton;

    // Image texture cache: filename → registered ResourceLocation
    private final Map<String, Identifier> imageCache = new HashMap<>();

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public NotebookScreen(int volume) {
        super(Component.translatable("screen.glowingalchemy.notebook", volume));
        this.volume = volume;
        this.rawBody = NotebookVolumeManager.readBody(volume);
        this.pages = PageParser.parse(rawBody);
    }

    // -------------------------------------------------------------------------
    // Init
    // -------------------------------------------------------------------------

    @Override
    protected void init() {
        int left = (this.width  - BG_WIDTH)  / 2;
        int top  = (this.height - BG_HEIGHT) / 2;

        // ── Text edit box (right page of the spread) ──────────────────────────
        // Patchouli's book opens as a two-page spread.
        // Left page = page N, right page = page N+1 (even/odd pairs).
        // For now we show one page at a time in the right panel;
        // two-page spread can be added as a visual enhancement later.
        int textLeft = left + BG_WIDTH / 2 + PAGE_INSET_X;
        int textTop  = top  + PAGE_INSET_Y;

        textBox = new MultiLineEditBox.Builder()
                .setX(textLeft)
                .setY(textTop)
                .setPlaceholder(Component.translatable("screen.glowingalchemy.notebook.placeholder"))
                .setShowBackground(false)  // we have the book texture already
                .setTextShadow(false)      // cleaner on parchment look
                .build(this.font, PAGE_WIDTH, PAGE_HEIGHT,
                        Component.translatable("screen.glowingalchemy.notebook.editor"));
        textBox.setCharacterLimit(PageParser.MAX_CHARS_PER_PAGE);
        loadPageIntoEditor();
        addRenderableWidget(textBox);

        // ── Title box (only on page 0 / displayed page 1) ─────────────────────
        titleBox = new net.minecraft.client.gui.components.EditBox(
                this.font,
                left + PAGE_INSET_X,
                top + PAGE_INSET_Y,
                BG_WIDTH / 2 - PAGE_INSET_X * 2,
                12,
                Component.translatable("screen.glowingalchemy.notebook.title")
        );
        titleBox.setMaxLength(48);
        titleBox.setBordered(false);
        titleBox.setValue(NotebookVolumeManager.readTitle(volume));
        titleBox.setVisible(currentPage == 0);
        addRenderableWidget(titleBox);

        // ── Navigation buttons ─────────────────────────────────────────────────
        prevButton = Button.builder(Component.literal("◀"), btn -> changePage(-1))
                .bounds(left + 16, top + BG_HEIGHT - 24, 20, 16)
                .build();
        prevButton.active = currentPage > 0;
        addRenderableWidget(prevButton);

        nextButton = Button.builder(Component.literal("▶"), btn -> changePage(1))
                .bounds(left + BG_WIDTH - 36, top + BG_HEIGHT - 24, 20, 16)
                .build();
        nextButton.active = currentPage < pages.size() - 1;
        addRenderableWidget(nextButton);

        // ── Close / done button ────────────────────────────────────────────────
        addRenderableWidget(
                Button.builder(Component.translatable("screen.glowingalchemy.notebook.close"),
                                btn -> this.onClose())
                        .bounds(left + BG_WIDTH / 2 - 24, top + BG_HEIGHT - 24, 48, 16)
                        .build()
        );
    }

    // -------------------------------------------------------------------------
    // Render
    // -------------------------------------------------------------------------

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float delta) {
        this.extractBackground(g, mouseX, mouseY, delta);

        int left = (this.width  - BG_WIDTH)  / 2;
        int top  = (this.height - BG_HEIGHT) / 2;

        // Book background
        g.blit(BOOK_BG, left, top, 0, 0, BG_WIDTH, BG_HEIGHT, 512, 512);

        // Page number indicator  "3 / 12"
        String pageStr = (currentPage + 1) + " / " + pages.size();
        int strW = this.font.width(pageStr);
        g.text(this.font, pageStr,
                left + BG_WIDTH / 2 - strW / 2,
                top + BG_HEIGHT - 18,
                0x3B2A1A, false);

        // Volume label on left page top
        String volLabel = "Vol. " + volume;
        String titleStr = NotebookVolumeManager.readTitle(volume);
        if (!titleStr.isBlank()) volLabel += ": " + titleStr;
        g.text(this.font, volLabel,
                left + PAGE_INSET_X,
                top + 6,
                0x7A5C3A, false);

        // Render image for current page (if any)
        renderPageImage(g, left, top);

        // Render all widgets (text box, buttons, title box)
        super.extractRenderState(g, mouseX, mouseY, delta);
    }

    private void renderPageImage(GuiGraphicsExtractor g, int left, int top) {
        NotebookPage page = pages.get(currentPage);
        if (!page.hasImage()) return;

        Identifier tex = getOrLoadImage(page.imagePath());
        if (tex == null) return;

        // Left page is the image display area
        int imgLeft = left + PAGE_INSET_X;
        int imgTop  = top  + PAGE_INSET_Y;

        if (page.imageSize() == ImageSize.FULL) {
            // Full page: image fills the entire left panel
            g.blit(tex, imgLeft, imgTop, 0, 0,
                    PAGE_WIDTH, PAGE_HEIGHT, PAGE_WIDTH, PAGE_HEIGHT);
        } else if (page.imageSize() == ImageSize.HALF) {
            // Half page: image in top half of left panel
            int halfH = PAGE_HEIGHT / 2;
            g.blit(tex, imgLeft, imgTop, 0, 0,
                    PAGE_WIDTH, halfH, PAGE_WIDTH, halfH);
        }
    }

    // -------------------------------------------------------------------------
    // Page navigation
    // -------------------------------------------------------------------------

    private void changePage(int delta) {
        saveCurrentPage();
        int target = currentPage + delta;
        if (target < 0 || target >= pages.size()) return;

        currentPage = target;
        loadPageIntoEditor();

        prevButton.active = currentPage > 0;
        nextButton.active = currentPage < pages.size() - 1;
        titleBox.setVisible(currentPage == 0);
    }

    // -------------------------------------------------------------------------
    // Editor ↔ rawBody sync
    // -------------------------------------------------------------------------

    /**
     * Loads the current page's text into the edit box.
     * Called on init and page turn.
     */
    private void loadPageIntoEditor() {
        if (textBox == null) return;
        NotebookPage page = pages.get(currentPage);
        textBox.setValue(page.text());
    }

    /**
     * Splices the edited text back into rawBody and re-parses.
     * Called on page turn and screen close.
     *
     * Strategy: rebuild rawBody by concatenating all page texts with
     * their image tags and [page] separators, then re-parse.
     * This preserves the [last seen] header and title line (those are
     * managed by NotebookVolumeManager, not PageParser).
     */
    private void saveCurrentPage() {
        if (textBox == null) return;

        // Splice edited text back into the current page
        String editedText = textBox.getValue();
        NotebookPage current = pages.get(currentPage);

        // Rebuild the page with the edited text (preserve image tag if present)
        String imageTag = current.hasImage()
                ? "[img:" + current.imagePath() + "|" + current.imageSize().name().toLowerCase() + "]"
                : "";

        // Replace this page's text in a simplified reconstruction:
        // We rebuild rawBody from the current pages list, substituting
        // the edited text for the current page.
        StringBuilder rebuilt = new StringBuilder();
        for (int i = 0; i < pages.size(); i++) {
            if (i > 0) rebuilt.append("\n[page]\n");
            NotebookPage p = pages.get(i);
            if (i == currentPage) {
                // Use edited text
                if (!imageTag.isBlank()) rebuilt.append(imageTag).append("\n");
                rebuilt.append(editedText);
            } else {
                // Preserve existing pages unchanged
                if (p.hasImage()) {
                    rebuilt.append("[img:")
                            .append(p.imagePath()).append("|")
                            .append(p.imageSize().name().toLowerCase())
                            .append("]\n");
                }
                rebuilt.append(p.text());
            }
        }

        rawBody = rebuilt.toString();
        pages = PageParser.parse(rawBody);

        // Save title if on page 0
        if (currentPage == 0 && titleBox != null) {
            NotebookVolumeManager.writeTitle(volume, titleBox.getValue());
        }

        // Write body to disk
        NotebookVolumeManager.writeBody(volume, rawBody);
    }

    // -------------------------------------------------------------------------
    // Image loading
    // -------------------------------------------------------------------------

    /**
     * Loads a PNG from the notebook images directory and registers it
     * as a DynamicTexture. Caches the result to avoid re-loading every frame.
     */
    private Identifier getOrLoadImage(String filename) {
        if (imageCache.containsKey(filename)) {
            return imageCache.get(filename);
        }

        Path imagePath = NotebookVolumeManager.getImagesDir().resolve(filename);
        if (!Files.exists(imagePath)) {
            // Cache null so we don't retry every frame
            imageCache.put(filename, null);
            return null;
        }

        try (InputStream stream = new FileInputStream(imagePath.toFile())) {
            NativeImage nativeImage = NativeImage.read(stream);
            String safeName = filename.toLowerCase().replaceAll("[^a-z0-9_.-]", "_");

            DynamicTexture dynTex   = new DynamicTexture( () -> "notebook:" + safeName,
                    nativeImage);

            // NeoForge 26.2: use ResourceLocation.fromNamespaceAndPath()
            Identifier loc = Identifier.fromNamespaceAndPath(
                    "glowingalchemy", "dynamic/notebook/" + safeName);

            Minecraft.getInstance().getTextureManager().register(loc, dynTex);
            imageCache.put(filename, loc);
            return loc;

        } catch (IOException e) {
            imageCache.put(filename, null); // Cache failure to avoid retrying
            return null;
        }
    }

    /**
     * Releases all DynamicTexture registrations to avoid memory leaks.
     * Called on screen close.
     */
    private void clearImageCache() {
        var manager = Minecraft.getInstance().getTextureManager();
        imageCache.forEach((name, loc) -> {
            if (loc != null) manager.release(loc);
        });
        imageCache.clear();
    }

    // -------------------------------------------------------------------------
    // Lifecycle
    // -------------------------------------------------------------------------

    @Override
    public void onClose() {
        saveCurrentPage();
        clearImageCache();
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void removed() {
        saveCurrentPage();
        clearImageCache();
        super.removed();
    }
}