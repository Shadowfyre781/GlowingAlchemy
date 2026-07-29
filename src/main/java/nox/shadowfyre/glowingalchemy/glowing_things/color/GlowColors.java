package nox.shadowfyre.glowingalchemy.glowing_things.color;

import net.minecraft.world.level.material.MapColor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class GlowColors {
        public static final int ANIMATED_COLOR = -1;
        private GlowColors() {}
                //;
        public static final GlowColor RED =             new GlowColor("red", 0xFFFF0000, MapColor.COLOR_RED);
        public static final GlowColor ORANGE =          new GlowColor("orange", 0xFFFF8000, MapColor.COLOR_ORANGE);
        public static final GlowColor BROWN =           new GlowColor("brown", 0xFF804000, MapColor.COLOR_BROWN);
        public static final GlowColor YELLOW =          new GlowColor("yellow", 0xFFFFFF00, MapColor.COLOR_YELLOW);
        public static final GlowColor LIME =            new GlowColor("lime", 0xFF80FF00, MapColor.COLOR_LIGHT_GREEN);
        public static final GlowColor GREEN =           new GlowColor("green", 0xFF00FF00, MapColor.COLOR_GREEN);
        public static final GlowColor MINT =          new GlowColor("mint_green", 0xFF00FF80, MapColor.WARPED_NYLIUM);
        public static final GlowColor CYAN =            new GlowColor("cyan", 0xFF00FFFF, MapColor.COLOR_CYAN);
        public static final GlowColor LIGHT_BLUE =      new GlowColor("light_blue", 0xFF0080FF, MapColor.COLOR_LIGHT_BLUE);
        public static final GlowColor BLUE =            new GlowColor("blue", 0xFF0000FF, MapColor.COLOR_BLUE);
        public static final GlowColor PURPLE =          new GlowColor("purple", 0xFF8000FF, MapColor.COLOR_PURPLE);
        public static final GlowColor MAGENTA =         new GlowColor("magenta", 0xFFFF00FF, MapColor.COLOR_MAGENTA);
        public static final GlowColor PINK =            new GlowColor("pink", 0xFFFF007F, MapColor.COLOR_PINK);

                // Pastels (Using matching MapColors)
        public static final GlowColor PASTEL_RED =              new GlowColor("pastel_red", 0xFFFF8080, MapColor.COLOR_RED);
        public static final GlowColor PASTEL_ORANGE =           new GlowColor("pastel_orange", 0xFFFFC080, MapColor.COLOR_ORANGE);
        public static final GlowColor PASTEL_BROWN =            new GlowColor("pastel_brown", 0xFFC08040, MapColor.COLOR_BROWN);
        public static final GlowColor PASTEL_YELLOW =           new GlowColor("pastel_yellow", 0xFFFFFF80, MapColor.COLOR_YELLOW);
        public static final GlowColor PASTEL_LIME =             new GlowColor("pastel_lime", 0xFFC0FF80, MapColor.COLOR_LIGHT_GREEN);
        public static final GlowColor PASTEL_GREEN =            new GlowColor("pastel_green", 0xFF80FF80, MapColor.COLOR_GREEN);
        public static final GlowColor PASTEL_MINT =             new GlowColor("pastel_mint", 0xFF80FFC0, MapColor.WARPED_NYLIUM);// Sea Green
        public static final GlowColor PASTEL_CYAN =             new GlowColor("pastel_cyan", 0xFF80FFFF, MapColor.COLOR_CYAN);
        public static final GlowColor PASTEL_LIGHT_BLUE =       new GlowColor("pastel_light_blue", 0xFF80C0FF, MapColor.COLOR_LIGHT_BLUE);
        public static final GlowColor PASTEL_BLUE =             new GlowColor("pastel_blue", 0xFF8080FF, MapColor.COLOR_BLUE);
        public static final GlowColor PASTEL_PURPLE =           new GlowColor("pastel_purple", 0xFFC080FF, MapColor.COLOR_PURPLE);
        public static final GlowColor PASTEL_MAGENTA =          new GlowColor("pastel_magenta", 0xFFFF80FF, MapColor.COLOR_MAGENTA);
        public static final GlowColor PASTEL_PINK =             new GlowColor("pastel_pink", 0xFFFF80BF, MapColor.COLOR_PINK);

                // Bolds
        public static final GlowColor BOLD_RED =        new GlowColor("bold_red", 0xFF800000, MapColor.COLOR_RED);
        public static final GlowColor BOLD_ORANGE =     new GlowColor("bold_orange", 0xFFC04000, MapColor.COLOR_ORANGE);
        public static final GlowColor BOLD_BROWN =      new GlowColor("bold_brown", 0xFF402000, MapColor.COLOR_BROWN);
        public static final GlowColor BOLD_YELLOW =     new GlowColor("bold_yellow", 0xFFE0C000, MapColor.COLOR_YELLOW);
        public static final GlowColor BOLD_LIME =       new GlowColor("bold_lime", 0xFF40C000, MapColor.COLOR_LIGHT_GREEN);
        public static final GlowColor BOLD_GREEN =      new GlowColor("bold_green", 0xFF008000, MapColor.COLOR_GREEN);
        public static final GlowColor BOLD_MINT =     new GlowColor("bold_mint", 0xFF00C040, MapColor.WARPED_NYLIUM);//AQUAMARINE
        public static final GlowColor BOLD_CYAN =       new GlowColor("bold_cyan", 0xFF008080, MapColor.COLOR_CYAN);
        public static final GlowColor BOLD_LIGHT_BLUE = new GlowColor("bold_light_blue", 0xFF0040C0, MapColor.COLOR_LIGHT_BLUE);
        public static final GlowColor BOLD_BLUE =       new GlowColor("bold_blue", 0xFF000080, MapColor.COLOR_BLUE);
        public static final GlowColor BOLD_PURPLE =     new GlowColor("bold_purple", 0xFF4000C0, MapColor.COLOR_PURPLE);
        public static final GlowColor BOLD_MAGENTA =    new GlowColor("bold_magenta", 0xFF800080, MapColor.COLOR_MAGENTA);
        public static final GlowColor BOLD_PINK =       new GlowColor("bold_pink", 0xFFC00040, MapColor.COLOR_PINK);

                // Monochrome
        public static final GlowColor BLACK =           new GlowColor("black", 0xFF000000, MapColor.COLOR_BLACK);
        public static final GlowColor GRAY =            new GlowColor("gray", 0xFF555555, MapColor.COLOR_GRAY);
        public static final GlowColor NEUTRAL_GRAY =    new GlowColor("neutral_gray", 0xFF808080, MapColor.STONE);
        public static final GlowColor LIGHT_GRAY =      new GlowColor("light_gray", 0xFFAAAAAA, MapColor.COLOR_LIGHT_GRAY);
        public static final GlowColor WHITE =           new GlowColor("white", 0xFFFFFFFF, MapColor.SNOW);

        public static final GlowColor RAINBOW =        new GlowColor("rainbow", ANIMATED_COLOR, MapColor.COLOR_MAGENTA);
                //Vanilla Tints
        public static final GlowColor VANILLA_WHITE =           new GlowColor("vanilla_white",          0XFFF0F0F0, MapColor.SNOW);
        public static final GlowColor VANILLA_ORANGE =          new GlowColor("vanilla_orange",         0XFFD87F33, MapColor.COLOR_ORANGE);
        public static final GlowColor VANILLA_MAGENTA =         new GlowColor("vanilla_magenta",        0XFFB24CD8, MapColor.COLOR_MAGENTA);
        public static final GlowColor VANILLA_LIGHT_BLUE =      new GlowColor("vanilla_light_blue",     0XFF6699D8, MapColor.COLOR_LIGHT_BLUE);
        public static final GlowColor VANILLA_YELLOW =          new GlowColor("vanilla_yellow",         0XFFE5E533, MapColor.COLOR_YELLOW);
        public static final GlowColor VANILLA_LIME =            new GlowColor("vanilla_lime",           0XFF7FCC19, MapColor.COLOR_LIGHT_GREEN);
        public static final GlowColor VANILLA_PINK =            new GlowColor("vanilla_pink",           0XFFF27FA5, MapColor.COLOR_PINK);
        public static final GlowColor VANILLA_GRAY =            new GlowColor("vanilla_gray",           0XFF4C4C4C, MapColor.COLOR_GRAY);
        public static final GlowColor VANILLA_LIGHT_GRAY =      new GlowColor("vanilla_light_gray",     0XFF999999, MapColor.COLOR_LIGHT_GRAY);
        public static final GlowColor VANILLA_CYAN =            new GlowColor("vanilla_cyan",           0XFF4C7F99, MapColor.COLOR_CYAN);
        public static final GlowColor VANILLA_PURPLE =          new GlowColor("vanilla_purple",         0XFF7F3FB2, MapColor.COLOR_PURPLE);
        public static final GlowColor VANILLA_BLUE =            new GlowColor("vanilla_blue",           0XFF334CB2, MapColor.COLOR_BLUE);
        public static final GlowColor VANILLA_BROWN =           new GlowColor("vanilla_brown",          0XFF664C33, MapColor.COLOR_BROWN);
        public static final GlowColor VANILLA_GREEN =           new GlowColor("vanilla_green",          0XFF667F33, MapColor.COLOR_GREEN);
        public static final GlowColor VANILLA_RED =             new GlowColor("vanilla_red",            0XFF993333, MapColor.COLOR_RED);
        public static final GlowColor VANILLA_BLACK =           new GlowColor("vanilla_black",          0XFF191919, MapColor.COLOR_BLACK);




        public static final List<GlowColor> COLORS = List.of(
                RED,
                ORANGE,
                BROWN,
                YELLOW,
                LIME,
                GREEN,
                MINT,
                CYAN,
                LIGHT_BLUE,
                BLUE,
                PURPLE,
                MAGENTA,
                PINK,
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
                PASTEL_PINK,
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
                BOLD_PINK,
                BLACK,
                GRAY,
                NEUTRAL_GRAY,
                LIGHT_GRAY,
                WHITE,
                RAINBOW,
                VANILLA_WHITE,
                VANILLA_ORANGE,
                VANILLA_MAGENTA,
                VANILLA_LIGHT_BLUE,
                VANILLA_YELLOW,
                VANILLA_LIME,
                VANILLA_PINK,
                VANILLA_GRAY,
                VANILLA_LIGHT_GRAY,
                VANILLA_CYAN,
                VANILLA_PURPLE,
                VANILLA_BLUE,
                VANILLA_BROWN,
                VANILLA_GREEN,
                VANILLA_RED,
                VANILLA_BLACK);

        private static final Map<String, GlowColor> BY_NAME =
                COLORS.stream()
                        .collect(Collectors.toUnmodifiableMap(
                                GlowColor::name,
                                Function.identity()));
        public static GlowColor byName(String name) {
                GlowColor color = BY_NAME.get(name);
                if (color == null)
                        throw new IllegalArgumentException("Unknown GlowColor: " + name);
                return color;
        }//CUSTOM ERROR CODE IF YOU TRY TO USE A COLOR NOT SPECIFIED ABOVE AND BELOW
}