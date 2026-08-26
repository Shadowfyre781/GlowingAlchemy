package nox.shadowfyre.glowingalchemy;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // -------------------------------------------------------------------------
    // Template entries (from NeoForge example — kept for reference)
    // TODO: clean these out once Config is formalized
    // -------------------------------------------------------------------------

    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    // -------------------------------------------------------------------------
    // Notebook
    // -------------------------------------------------------------------------

    public static final ModConfigSpec.BooleanValue NOTEBOOK_TRACK_COORDINATES = BUILDER
            .comment(
                "When enabled, a notebook's last known coordinates (dimension, X, Y, Z)",
                "are written to the top of its volume .txt file whenever the item",
                "leaves a player's inventory (dropped, death, placed in container).",
                "Readable offline even if the game is closed.",
                "Default: true"
            )
            .define("notebook.trackCoordinates", true);

    public static final ModConfigSpec.IntValue NOTEBOOK_MAX_VOLUMES = BUILDER
            .comment(
                "Maximum number of notebook volumes a player can create.",
                "Increase this if you somehow fill a shelf's worth of journals.",
                "Default: 99"
            )
            .defineInRange("notebook.maxVolumes", 99, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue NOTEBOOK_MAX_PAGES = BUILDER
            .comment(
                "Maximum number of pages per notebook volume.",
                "Must be between 1 and 256. Default: 96"
            )
            .defineInRange("notebook.maxPages", 96, 1, 256);

    // -------------------------------------------------------------------------

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(Identifier.parse(itemName));
    }
}
