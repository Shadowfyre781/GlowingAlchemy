package nox.shadowfyre.glowingalchemy.mechanic.notebook;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;

/**
 * NotebookEngine — one-armed robot for the notebook system.
 *
 * Responsibilities:
 *   - Register NotebookInventoryListener on the NeoForge event bus
 *   - Any future notebook-level hooks (keybinds, commands, etc.) go here
 *
 * File I/O setup (ensuring /config/glowingalchemy/notebook/ exists) is
 * handled lazily in NotebookVolumeManager.ensureDirectories(), called
 * the first time the screen opens — no need to do it at startup.
 *
 * // NOTEBOOK-LITE: keep this class but remove NotebookInventoryListener
 * // registration. Replace with a no-op or delete the class entirely.
 */
public class NotebookEngine {

    public static void register(IEventBus modEventBus) {
        // NotebookInventoryListener listens on the NeoForge game event bus
        // (not the mod event bus) since it handles entity/world events.
        NeoForge.EVENT_BUS.register(new NotebookInventoryListener());

        // Future: keybind registration, command registration, etc.
        // modEventBus.addListener(NotebookEngine::onRegisterKeyMappings);
    }
}
