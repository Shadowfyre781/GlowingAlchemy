package nox.shadowfyre.glowingalchemy.glowing_things;

import net.minecraft.world.level.material.MapColor;
import java.util.List;


public class GlowPalette {
    public static final List<GlowColor> COLORS = List.of(
            // Chromatic
            new GlowColor("red", 0xFFFF0000, "chromatic", MapColor.COLOR_RED),
            new GlowColor("orange", 0xFFFF8000, "chromatic", MapColor.COLOR_ORANGE),
            new GlowColor("brown", 0xFF804000, "chromatic", MapColor.COLOR_BROWN),
            new GlowColor("yellow", 0xFFFFFF00, "chromatic", MapColor.COLOR_YELLOW),
            new GlowColor("lime", 0xFF80FF00, "chromatic", MapColor.COLOR_LIGHT_GREEN),
            new GlowColor("green", 0xFF00FF00, "chromatic", MapColor.COLOR_GREEN),
            new GlowColor("cyan", 0xFF00FFFF, "chromatic", MapColor.COLOR_CYAN),
            new GlowColor("light_blue", 0xFF0080FF, "chromatic", MapColor.COLOR_LIGHT_BLUE),
            new GlowColor("blue", 0xFF0000FF, "chromatic", MapColor.COLOR_BLUE),
            new GlowColor("purple", 0xFF8000FF, "chromatic", MapColor.COLOR_PURPLE),
            new GlowColor("magenta", 0xFFFF00FF, "chromatic", MapColor.COLOR_MAGENTA),
            new GlowColor("pink", 0xFFFF007F, "chromatic", MapColor.COLOR_PINK),

            // Pastels (Using matching MapColors)
            new GlowColor("pastel_red", 0xFFFF8080, "pastel", MapColor.COLOR_RED),
            new GlowColor("pastel_orange", 0xFFFFC080, "pastel", MapColor.COLOR_ORANGE),
            new GlowColor("pastel_brown", 0xFFC08040, "pastel", MapColor.COLOR_BROWN),
            new GlowColor("pastel_yellow", 0xFFFFFF80, "pastel", MapColor.COLOR_YELLOW),
            new GlowColor("pastel_lime", 0xFFC0FF80, "pastel", MapColor.COLOR_LIGHT_GREEN),
            new GlowColor("pastel_green", 0xFF80FF80, "pastel", MapColor.COLOR_GREEN),
            new GlowColor("pastel_cyan", 0xFF80FFFF, "pastel", MapColor.COLOR_CYAN),
            new GlowColor("pastel_lt_blue", 0xFF80C0FF, "pastel", MapColor.COLOR_LIGHT_BLUE),
            new GlowColor("pastel_blue", 0xFF8080FF, "pastel", MapColor.COLOR_BLUE),
            new GlowColor("pastel_purple", 0xFFC080FF, "pastel", MapColor.COLOR_PURPLE),
            new GlowColor("pastel_magenta", 0xFFFF80FF, "pastel", MapColor.COLOR_MAGENTA),
            new GlowColor("pastel_pink", 0xFFFF80BF, "pastel", MapColor.COLOR_PINK),

            // Bolds
            new GlowColor("bold_red", 0xFF800000, "bold", MapColor.COLOR_RED),
            new GlowColor("bold_orange", 0xFFC04000, "bold", MapColor.COLOR_ORANGE),
            new GlowColor("bold_brown", 0xFF402000, "bold", MapColor.COLOR_BROWN),
            new GlowColor("bold_yellow", 0xFFE0C000, "bold", MapColor.COLOR_YELLOW),
            new GlowColor("bold_lime", 0xFF40C000, "bold", MapColor.COLOR_LIGHT_GREEN),
            new GlowColor("bold_green", 0xFF008000, "bold", MapColor.COLOR_GREEN),
            new GlowColor("bold_cyan", 0xFF008080, "bold", MapColor.COLOR_CYAN),
            new GlowColor("bold_lt_blue", 0xFF0040C0, "bold", MapColor.COLOR_LIGHT_BLUE),
            new GlowColor("bold_blue", 0xFF000080, "bold", MapColor.COLOR_BLUE),
            new GlowColor("bold_purple", 0xFF4000C0, "bold", MapColor.COLOR_PURPLE),
            new GlowColor("bold_magenta", 0xFF800080, "bold", MapColor.COLOR_MAGENTA),
            new GlowColor("bold_pink", 0xFFC00040, "bold", MapColor.COLOR_PINK),

            // Monochrome
            new GlowColor("black", 0xFF000000, "monochrome", MapColor.COLOR_BLACK),
            new GlowColor("grey", 0xFF555555, "monochrome", MapColor.COLOR_GRAY),
            new GlowColor("light_grey", 0xFFAAAAAA, "monochrome", MapColor.COLOR_LIGHT_GRAY),
            new GlowColor("white", 0xFFFFFFFF, "monochrome", MapColor.SNOW),

            // Special
            new GlowColor("rainbow", -1, "special", MapColor.COLOR_MAGENTA)
    );
    // Filtered lists: This automatically creates your sub-groups from the main list!
    public static final List<GlowColor> PASTELS = COLORS.stream()
            .filter(c -> c.subfolder().equals("pastel")).toList();

    public static final List<GlowColor> BOLDS = COLORS.stream()
            .filter(c -> c.subfolder().equals("bold")).toList();

    public static final List<GlowColor> MONOCHROME = COLORS.stream()
            .filter(c -> c.subfolder().equals("monochrome")).toList();
    // ... rest of your filter lists (PASTELS, BOLDS, etc)
    // 1. The FULL 16 List (The vanilla-equivalent set in your specific order)
    // This grabs the standard Chromatic colors and the Monochrome colors
    public static final List<GlowColor> FULL_16 = COLORS.stream()
            .filter(c -> c.subfolder().equals("chromatic") || c.subfolder().equals("monochrome"))
            .filter(c -> !c.name().equals("rainbow")) // Exclude rainbow from the "standard" 16
            .toList();

    // 2. The IRIDESCENT List (Chromatic colors minus Brown)
    // Perfect for rainbows and high-saturation effects
    public static final List<GlowColor> IRIDESCENT = COLORS.stream()
            .filter(c -> c.subfolder().equals("chromatic"))
            .filter(c -> !c.name().equals("brown"))
            .filter(c -> !c.name().equals("rainbow"))
            .toList();

}