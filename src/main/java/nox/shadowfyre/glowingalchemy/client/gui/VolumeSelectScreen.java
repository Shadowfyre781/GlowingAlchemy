package nox.shadowfyre.glowingalchemy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import nox.shadowfyre.glowingalchemy.mechanic.notebook.NotebookVolumeManager;
import nox.shadowfyre.glowingalchemy.mechanic.notebook.NotebookVolumeTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * VolumeSelectScreen — the "shelf" UI
 *
 * Opens when the player right-clicks a blank notebook.
 * Shows all existing volumes (with title), grays out any that are
 * currently checked out (a physical copy exists in the world),
 * and offers a "New Volume" button.
 *
 * Layout concept:
 *   ┌────────────────────────────────────┐
 *   │        Your Notebooks              │
 *   │  ┌──────────────────────────────┐  │
 *   │  │ Vol. 1: Botanicals      [▶]  │  │  ← available (green text)
 *   │  │ Vol. 2: Cave Survey  [out]   │  │  ← checked out (gray, disabled)
 *   │  │ Vol. 3: Untitled        [▶]  │  │
 *   │  └──────────────────────────────┘  │
 *   │  [ New Volume ]                    │
 *   │  Title: [________________]         │  ← only visible when creating new
 *   │                    [Create] [Back] │
 *   └────────────────────────────────────┘
 *
 * Note on server sync:
 *   In a single-player / LAN world, NotebookVolumeTracker is accessible
 *   directly via the integrated server. On a dedicated server this screen
 *   would need a packet to fetch checked-out state. For now we handle this
 *   gracefully by skipping the gray-out if tracker data isn't available
 *   (dedicated server case deferred — see NOTEBOOK-LITE note).
 *
 * // NOTEBOOK-LITE: remove checkedOut map population and gray-out logic.
 * // All volumes will always show as available.
 */
public class VolumeSelectScreen extends Screen {

    // Patchouli's book background texture — borrowing the aesthetic
    // Replace with custom texture path once art is ready:
    // ResourceLocation.fromNamespaceAndPath("glowingalchemy", "textures/gui/notebook/cover.png")
    private static final Identifier BOOK_BG =
            Identifier.fromNamespaceAndPath("patchouli", "textures/gui/book_brown.png");

    private static final int BG_WIDTH  = 272;
    private static final int BG_HEIGHT = 178;

    // Volume list entries built in init()
    private final List<VolumeEntry> entries = new ArrayList<>();

    // "New volume" creation state
    private boolean creatingNew = false;
    private EditBox titleInput;
    private Button createButton;
    private Button newVolumeButton;
    private Button backButton;

    // Scroll offset for long volume lists
    private int scrollOffset = 0;
    private static final int VISIBLE_ROWS = 6;
    private static final int ROW_HEIGHT   = 20;

    public VolumeSelectScreen() {
        super(Component.translatable("screen.glowingalchemy.volume_select"));
        // en_us: "Your Notebooks"
    }

    // -------------------------------------------------------------------------
    // Init
    // -------------------------------------------------------------------------

    @Override
    protected void init() {
        entries.clear();
        creatingNew = false;

        int left = (this.width - BG_WIDTH) / 2;
        int top  = (this.height - BG_HEIGHT) / 2;

        // Fetch checked-out state — works on integrated server, returns empty map
        // on dedicated server (graceful degradation, no crash)
        Map<Integer, UUID> checkedOut = fetchCheckedOut();

        // Build volume list from files on disk
        List<Integer> volumes = NotebookVolumeManager.getExistingVolumes();
        for (int vol : volumes) {
            String title = NotebookVolumeManager.readTitle(vol);
            boolean available = !checkedOut.containsKey(vol);
            entries.add(new VolumeEntry(vol, title, available));
        }

        // Volume row buttons (up to VISIBLE_ROWS shown)
        rebuildVolumeButtons(left, top);

        // New Volume button
        newVolumeButton = Button.builder(
                        Component.translatable("screen.glowingalchemy.new_volume"),
                        btn -> startCreatingNew())
                .bounds(left + 16, top + 16 + VISIBLE_ROWS * ROW_HEIGHT + 8, 120, 20)
                .build();
        addRenderableWidget(newVolumeButton);

        // Title input (hidden until "New Volume" clicked)
        titleInput = new EditBox(this.font,
                left + 16, top + 16 + VISIBLE_ROWS * ROW_HEIGHT + 34,
                160, 16,
                Component.translatable("screen.glowingalchemy.volume_title"));
        titleInput.setMaxLength(48);
        titleInput.setVisible(false);
        titleInput.setFocused(false);
        addRenderableWidget(titleInput);

        // Create button (hidden until "New Volume" clicked)
        createButton = Button.builder(
                        Component.translatable("screen.glowingalchemy.create"),
                        btn -> confirmCreateVolume())
                .bounds(left + 16, top + 16 + VISIBLE_ROWS * ROW_HEIGHT + 56, 80, 20)
                .build();
        createButton.visible = false;
        addRenderableWidget(createButton);

        // Back button
        backButton = Button.builder(
                        Component.translatable("screen.glowingalchemy.back"),
                        btn -> this.onClose())
                .bounds(left + BG_WIDTH - 70, top + BG_HEIGHT - 28, 60, 20)
                .build();
        addRenderableWidget(backButton);
    }

    private void rebuildVolumeButtons(int left, int top) {
        // Remove old volume buttons before rebuilding (called on scroll)
        // For simplicity we rebuild all widgets — fine for small lists
        int visibleStart = scrollOffset;
        int visibleEnd   = Math.min(scrollOffset + VISIBLE_ROWS, entries.size());

        for (int i = visibleStart; i < visibleEnd; i++) {
            VolumeEntry entry = entries.get(i);
            int row = i - scrollOffset;
            int btnY = top + 24 + row * ROW_HEIGHT;

            String label = entry.title().isBlank()
                    ? "Vol. " + entry.volume()
                    : "Vol. " + entry.volume() + ": " + entry.title();

            if (entry.available()) {
                // Available — clickable, opens the notebook
                int finalI = i;
                addRenderableWidget(
                        Button.builder(Component.literal(label),
                                        btn -> openVolume(entries.get(finalI).volume()))
                                .bounds(left + 16, btnY, BG_WIDTH - 52, ROW_HEIGHT - 2)
                                .build()
                );
            } else {
                // Checked out — grayed out, disabled
                Button grayed = Button.builder(
                                Component.literal(label + " [checked out]")
                                        .withStyle(style -> style.withColor(0x888888)),
                                btn -> {} // no-op
                        )
                        .bounds(left + 16, btnY, BG_WIDTH - 52, ROW_HEIGHT - 2)
                        .build();
                grayed.active = false;
                addRenderableWidget(grayed);
            }
        }
    }

    // -------------------------------------------------------------------------
    // Render
    // -------------------------------------------------------------------------

    @Override
    public void extractBackground(GuiGraphicsExtractor g, int mouseX, int mouseY,
                                     float delta) {
        // Dim the game world behind the screen
        this.extractTransparentBackground(g);

        int left = (this.width - BG_WIDTH) / 2;
        int top  = (this.height - BG_HEIGHT) / 2;

        // Book background texture
        // Patchouli's book_brown.png is 512x512; the book panel starts at 0,0
        g.blit(RenderPipelines.GUI_TEXTURED, BOOK_BG,
                left, top, 0, 0, BG_WIDTH, BG_HEIGHT, 512, 512);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY,
                                   float delta) {
        int left = (this.width - BG_WIDTH) / 2;
        int top  = (this.height - BG_HEIGHT) / 2;

        // Title header
        g.centeredText(this.font,
                Component.translatable("screen.glowingalchemy.volume_select"),
                left + BG_WIDTH / 2, top + 8, 0xFF3B2A1A);

        // "Title:" label when creating new
        if (creatingNew) {
            g.text(this.font,
                    Component.translatable("screen.glowingalchemy.volume_title_label"),
                    left + 16, titleInput.getY() - 10, 0xFF3B2A1A, false);
        }

        // Widgets (buttons, edit box)
        super.extractRenderState(g, mouseX, mouseY, delta);
    }

    // -------------------------------------------------------------------------
    // Scroll
    // -------------------------------------------------------------------------

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int maxScroll = Math.max(0, entries.size() - VISIBLE_ROWS);
        int newOffset = (int) Math.max(0, Math.min(maxScroll, scrollOffset - scrollY));
        if (newOffset != scrollOffset) {
            scrollOffset = newOffset;
            // Rebuild — simple approach, acceptable for small lists
            this.init(this.width, this.height);
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // Actions
    // -------------------------------------------------------------------------

    private void openVolume(int volume) {
        Minecraft mc = Minecraft.getInstance();

        // Assign the volume NBT to the held item.
        // On an integrated server (singleplayer / LAN) we can reach the
        // server player directly without a packet. On a dedicated server
        // this falls back gracefully — the file still opens, but the item
        // NBT won't be stamped until a proper packet is implemented.
        //
        // NOTEBOOK-LITE: this block is safe to leave as-is; the client-only
        // version just won't stamp NBT on dedicated servers either.
        if (mc.player != null) {
            var server = mc.getSingleplayerServer();
            if (server != null) {
                // Integrated server path — stamp NBT directly
                server.execute(() -> {
                    var serverPlayer = server.getPlayerList()
                            .getPlayer(mc.player.getUUID());
                    if (serverPlayer == null) return;

                    // Find the blank notebook in the player's main hand or inventory
                    var stack = serverPlayer.getMainHandItem();
                    if (!stack.is(nox.shadowfyre.glowingalchemy.registry.ItemRegistry.NOTEBOOK.get())
                            || nox.shadowfyre.glowingalchemy.items.NotebookItem.isAssigned(stack)) {
                        // Main hand wasn't a blank notebook — search inventory
                        for (int i = 0; i < serverPlayer.getInventory().getContainerSize(); i++) {
                            var s = serverPlayer.getInventory().getItem(i);
                            if (s.is(nox.shadowfyre.glowingalchemy.registry.ItemRegistry.NOTEBOOK.get())
                                    && !nox.shadowfyre.glowingalchemy.items.NotebookItem.isAssigned(s)) {
                                stack = s;
                                break;
                            }
                        }
                    }

                    if (stack.is(nox.shadowfyre.glowingalchemy.registry.ItemRegistry.NOTEBOOK.get())
                            && !nox.shadowfyre.glowingalchemy.items.NotebookItem.isAssigned(stack)) {
                        // Split stack down to 1 before assigning
                        if (stack.getCount() > 1) {
                            stack.shrink(1);
                            var assigned = stack.copyWithCount(1);
                            nox.shadowfyre.glowingalchemy.items.NotebookItem
                                    .assignVolume(assigned, volume,
                                            NotebookVolumeManager.readTitle(volume),
                                            serverPlayer);
                            serverPlayer.getInventory().add(assigned);
                        } else {
                            nox.shadowfyre.glowingalchemy.items.NotebookItem
                                    .assignVolume(stack, volume,
                                            NotebookVolumeManager.readTitle(volume),
                                            serverPlayer);
                        }
                    }
                });
            }
            // Dedicated server: NBT stamping deferred — open screen anyway
            // so the player can still read/write their notes client-side.
        }

        // Open the notebook
        Minecraft.getInstance().gui.setScreen(
                new NotebookScreen(volume)
        );
    }
    private void startCreatingNew() {
        creatingNew = true;
        titleInput.setVisible(true);
        titleInput.setFocused(true);
        createButton.visible = true;
        newVolumeButton.active = false;
    }

    private void confirmCreateVolume() {
        int newVol = NotebookVolumeManager.getNextVolumeNumber();
        String title = titleInput.getValue().trim();
        NotebookVolumeManager.createVolume(newVol, title);
        // Open the new volume immediately
        Minecraft.getInstance().gui.setScreen(
                new NotebookScreen(newVol));
    }

    // -------------------------------------------------------------------------
    // Checked-out state fetch
    // -------------------------------------------------------------------------

    /**
     * Fetches checked-out volume state from the integrated server.
     * Returns an empty map if not available (dedicated server, or server not running).
     *
     * // NOTEBOOK-LITE: replace with return Map.of() always
     */
    private Map<Integer, UUID> fetchCheckedOut() {
        try {
            var server = Minecraft.getInstance().getSingleplayerServer();
            if (server == null) return Map.of(); // Dedicated server — graceful degradation
            return NotebookVolumeTracker.get(server.overworld()).getAllCheckedOut();
        } catch (Exception e) {
            return Map.of();
        }
    }

    // -------------------------------------------------------------------------
    // Misc
    // -------------------------------------------------------------------------

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // -------------------------------------------------------------------------
    // Supporting types
    // -------------------------------------------------------------------------

    private record VolumeEntry(int volume, String title, boolean available) {}
}
