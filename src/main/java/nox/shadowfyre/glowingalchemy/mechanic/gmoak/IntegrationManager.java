package nox.shadowfyre.glowingalchemy.mechanic.gmoak;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IntegrationManager {
    // We use a Map to keep categories organized (e.g., "ORE" -> list of blocks)
    private static final Map<String, List<IntegrationMetadata>> INTEGRATIONS = new ConcurrentHashMap<>();

    public static void addIntegration(IntegrationMetadata data) {
        INTEGRATIONS.computeIfAbsent(data.modId(), k -> new ArrayList<>()).add(data);
    }

    public static List<IntegrationMetadata> getByCategory(String category) {
        return INTEGRATIONS.getOrDefault(category, new ArrayList<>());
    }

    public static boolean isSupported(String category, String modId, String blockId) {
        return getByCategory(category).stream()
                .anyMatch(meta -> meta.modId().equals(modId) && meta.blockId().equals(blockId));
    }
}