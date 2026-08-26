package nox.shadowfyre.glowingalchemy.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import nox.shadowfyre.glowingalchemy.client.gui.NotebookScreen;
import nox.shadowfyre.glowingalchemy.client.gui.VolumeSelectScreen;
import nox.shadowfyre.glowingalchemy.mechanic.notebook.NotebookVolumeTracker;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.client.gui.Gui;
import java.util.function.Consumer;
import java.util.List;

/**
 * NotebookItem — personal player journal, backed by flat files in
 * .minecraft/config/glowingalchemy/notebook/
 *
 * States:
 *   BLANK  — no volume NBT tag; stacks to 16 like vanilla books
 *   ASSIGNED — has a volume number in NBT; stacksTo(1), named "Notebook — Vol. N: Title"
 *
 * Server-side:
 *   NotebookVolumeTracker (SavedData) tracks which volumes are "checked out"
 *   (i.e. a physical assigned copy exists somewhere in the world).
 *   One assigned copy per volume enforced at assignment time.
 *
 * // NOTEBOOK-LITE: This class is the primary candidate for extraction into a
 * // standalone client-only mod. To do so:
 * //   - Remove all NotebookVolumeTracker calls
 * //   - Remove the PlayerEvent.ItemPickup / ItemEntity drop listeners
 * //   - Volume enforcement and coordinate logging become no-ops
 * //   - Everything else (file I/O, screen, parser) is already client-only
 * //   See: NotebookVolumeTracker.java for the server-side surface area
 */
public class NotebookItem extends Item {

    // NBT keys
    public static final String TAG_VOLUME    = "notebook_volume";   // int  — assigned volume number
    public static final String TAG_TITLE     = "notebook_title";    // String — player-set volume title
    public static final String TAG_OWNER     = "notebook_owner";    // String (UUID) — assigned on first volume select

    // Vanilla books stack to 16
    private static final int BLANK_STACK_SIZE = 16;

    public NotebookItem() {
        // We handle stacksTo dynamically via getMaxStackSize() below,
        // so pass 1 here as the baseline and override per-stack.
        super(new Item.Properties().stacksTo(1));
    }

    // -------------------------------------------------------------------------
    // Stack size: blank = 16, assigned = 1
    // -------------------------------------------------------------------------

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return isAssigned(stack) ? 1 : BLANK_STACK_SIZE;
    }

    // -------------------------------------------------------------------------
    // Display name
    // -------------------------------------------------------------------------

    @Override
    public Component getName(ItemStack stack) {
        if (!isAssigned(stack)) {
            return Component.translatable("item.glowingalchemy.notebook.blank");
            // en_us: "Blank Notebook"
        }

        int vol = getVolume(stack);
        String title = getTitle(stack);

        if (title == null || title.isBlank()) {
            return Component.translatable("item.glowingalchemy.notebook.assigned_untitled", vol);
            // en_us: "Notebook — Vol. %s"
        }

        return Component.translatable("item.glowingalchemy.notebook.assigned_titled", vol, title);
        // en_us: "Notebook — Vol. %s: %s"
    }

    // -------------------------------------------------------------------------
    // Tooltip
    // -------------------------------------------------------------------------

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> tooltip,
            TooltipFlag flag) {
        if (!isAssigned(stack)) {
            tooltip.accept(
                    Component.translatable(
                            "item.glowingalchemy.notebook.tooltip.blank"
                    ).withStyle(ChatFormatting.GRAY)
            );
            // en_us: "Right-click to assign or create a volume."
        } else {
            tooltip.accept(
                    Component.translatable(
                            "item.glowingalchemy.notebook.tooltip.volume",
                            getVolume(stack)
                    ).withStyle(ChatFormatting.GRAY)
            );
            // en_us: "Volume %s"

            // Show last-seen coordinates if present in the file
            // (populated by NotebookVolumeTracker when the item leaves inventory)
            // We don't store coords in NBT — the .txt file is the source of truth.
        }
    }

    // -------------------------------------------------------------------------
    // Right-click: open GUI
    // -------------------------------------------------------------------------

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            if (!isAssigned(stack)) {
                // Open volume select screen — player picks or creates a volume
                // Screen handles assignment and writes volume NBT back to the stack
                openVolumeSelectScreen();
            } else {
                // Open notebook editor directly on the assigned volume
                openNotebookScreen(getVolume(stack));
            }
        }

        return InteractionResult.SUCCESS;
    }

    // -------------------------------------------------------------------------
    // Client screen helpers (calls proxied through GlowingAlchemyClient)
    // These are stubs — actual screen classes wired in Part 2
    // -------------------------------------------------------------------------

    private void openVolumeSelectScreen() {
        Minecraft.getInstance().gui.setScreen(
                new VolumeSelectScreen()

        );
    }

    private void openNotebookScreen(int volume) {
        Minecraft.getInstance().gui.setScreen(
                new NotebookScreen(volume)
        );
    }
    // -------------------------------------------------------------------------
    // NBT helpers
    // -------------------------------------------------------------------------

    public static boolean isAssigned(ItemStack stack) {
        CustomData data = stack.get(
                nox.shadowfyre.glowingalchemy.registry.ModDataComponents.NOTEBOOK_DATA
        );

        return data != null && data.contains(TAG_VOLUME);
    }

    public static int getVolume(ItemStack stack) {
        if (!isAssigned(stack)) return -1;

        CustomData data = stack.get(
                nox.shadowfyre.glowingalchemy.registry.ModDataComponents.NOTEBOOK_DATA
        );

        return data.copyTag()
                .getIntOr(TAG_VOLUME, -1);
    }

    public static String getTitle(ItemStack stack) {
        if (!isAssigned(stack)) return null;

        CustomData data = stack.get(
                nox.shadowfyre.glowingalchemy.registry.ModDataComponents.NOTEBOOK_DATA
        );

        var tag = data.copyTag();

        return tag.getString(TAG_TITLE)
                .orElse(null);
    }
    /**
     * Assigns a volume to a blank notebook stack.
     * Called from VolumeSelectScreen after the player confirms a volume.
     * Also marks the volume as checked-out in NotebookVolumeTracker (server-side).
     */
    public static void assignVolume(ItemStack stack, int volume, String title,
                                    ServerPlayer player) {

        // Split the stack down to 1 — an assigned notebook can't stack.
        // Caller (VolumeSelectScreen packet handler) is responsible for
        // shrinking the stack and returning the remainder to inventory.

        CustomData.update(
                nox.shadowfyre.glowingalchemy.registry.ModDataComponents.NOTEBOOK_DATA.value(),
                stack,
                tag -> {
                    tag.putInt(TAG_VOLUME, volume);

                    if (title != null && !title.isBlank()) {
                        tag.putString(TAG_TITLE, title);
                    }

                    tag.putString(TAG_OWNER, player.getUUID().toString());
                }
        );

        // NOTEBOOK-LITE: remove this call for client-only version
        NotebookVolumeTracker.get(player.level())
                .checkOut(volume, player.getUUID());
    }

    /**
     * Strips volume assignment from a stack (e.g. if volume is deleted).
     * Also checks the volume back in to NotebookVolumeTracker.
     */
    public static void unassignVolume(ItemStack stack, ServerPlayer player) {

        if (!isAssigned(stack)) return;

        int volume = getVolume(stack);

        CustomData.update(
                nox.shadowfyre.glowingalchemy.registry.ModDataComponents.NOTEBOOK_DATA.value(),
                stack,
                tag -> {
                    tag.remove(TAG_VOLUME);
                    tag.remove(TAG_TITLE);
                    tag.remove(TAG_OWNER);
                }
        );

        // NOTEBOOK-LITE: remove this call for client-only version
            NotebookVolumeTracker.get(player.level())
                .checkIn(volume);
    }}