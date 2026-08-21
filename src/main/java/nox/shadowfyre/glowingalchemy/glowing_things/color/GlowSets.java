package nox.shadowfyre.glowingalchemy.glowing_things.color;

import java.util.List;

import static nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColors.*;

public final class GlowSets {

    private GlowSets() {}
    public static final GlowSet FULL_16=
            new GlowSet("Full_16",
            List.of(
                    RED,
                    ORANGE,
                    BROWN,
                    BLACK,
                    GRAY,
                    LIGHT_GRAY,
                    WHITE,
                    YELLOW,
                    LIME,
                    GREEN,
                    CYAN,
                    LIGHT_BLUE,
                    BLUE,
                    PURPLE,
                    MAGENTA,
                    PINK
            ));

    public static final GlowSet PASTEL=
            new GlowSet("Pastel",
                    List.of(
                            PASTEL_RED,
                            PASTEL_ORANGE,
                            PASTEL_BROWN,
                            PASTEL_YELLOW,
                            PASTEL_LIME,
                            PASTEL_GREEN,
                            PASTEL_MINT,
                            PASTEL_CYAN,
                            PASTEL_LIGHT_BLUE,
                            PASTEL_BLUE,
                            PASTEL_PURPLE,
                            PASTEL_MAGENTA,
                            PASTEL_PINK));

   public static final GlowSet BOLD=
    new GlowSet("Bold",
            List.of(
                    BOLD_RED,
                    BOLD_ORANGE,
                    BOLD_BROWN,
                    BOLD_YELLOW,
                    BOLD_LIME,
                    BOLD_GREEN,
                    BOLD_MINT,
                    BOLD_CYAN,
                    BOLD_LIGHT_BLUE,
                    BOLD_BLUE,
                    BOLD_PURPLE,
                    BOLD_MAGENTA,
                    BOLD_PINK
            ));
    public static final GlowSet VANILLA=
            new GlowSet("Vanilla",
                    List.of(
                            VANILLA_RED,
                    VANILLA_ORANGE,
                    VANILLA_BROWN,
                    VANILLA_BLACK,
                    VANILLA_GRAY,
                    VANILLA_LIGHT_GRAY,
                    VANILLA_WHITE,
                    VANILLA_YELLOW,
                    VANILLA_LIME,
                    VANILLA_GREEN,
                    VANILLA_CYAN,
                    VANILLA_LIGHT_BLUE,
                    VANILLA_BLUE,
                    VANILLA_PURPLE,
                    VANILLA_MAGENTA,
                    VANILLA_PINK
                    ));

    public static final GlowSet MONOCHROME=
            new GlowSet("monochrome",
                    List.of(
                            BLACK,
                            GRAY,
                            NEUTRAL_GRAY,
                            LIGHT_GRAY,
                            WHITE
                           // LIGHT_GRAY,
                            //NEUTRAL_GRAY,
                           // GRAY
                    ));

    public static final GlowSet ALL_18=
            new GlowSet("All_18",
                    List.of(
                            RED,
                            ORANGE,
                            BROWN,
                            BLACK,
                            GRAY,
                            NEUTRAL_GRAY,
                            LIGHT_GRAY,
                            WHITE,
                            YELLOW,
                            LIME,
                            GREEN,
                            MINT,
                            CYAN,
                            LIGHT_BLUE,
                            BLUE,
                            PURPLE,
                            MAGENTA,
                            PINK
                            ));
    public static final GlowSet IRIDESCENT=
            new GlowSet("Iridescent",
                    List.of(
                            RED,
                            ORANGE,
                            YELLOW,
                            LIME,
                            GREEN,
                            MINT,
                            CYAN,
                            LIGHT_BLUE,
                            BLUE,
                            PURPLE,
                            MAGENTA,
                            PINK
                    ));
    public static final GlowSet MAPLE=
            new GlowSet("Maple",
                    List.of(
                            RED,
                            BOLD_RED,
                            YELLOW,
                            PURPLE,
                            PASTEL_PURPLE,
                            BOLD_PURPLE,
                            PINK,
                            GREEN
                    ));

    public static final List<GlowSet> ALL_CRAYON_BOXES = List.of(
            FULL_16,
            PASTEL,
            BOLD,
            VANILLA,
            MONOCHROME,
            MAPLE,
            IRIDESCENT,
            ALL_18
    );
    public static final GlowSet NONE =
            new GlowSet("None", List.of());
};//defines custom boxes of crayons

