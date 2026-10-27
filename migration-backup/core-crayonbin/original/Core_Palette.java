package nox.shadowfyre.glowingalchemy.core.crayonbin;

import java.util.List;

import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.*;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.BLACK;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.BLUE;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.CYAN;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.GRAY;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.GREEN;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.LIGHT_BLUE;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.LIGHT_GRAY;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.LIME;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.MAGENTA;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.PINK;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.PURPLE;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.WHITE;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.YELLOW;


public class Core_Palette {
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
    public static final GlowSet VANILLA =
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
    public static final GlowSet NONE =
            new GlowSet("None", List.of());


}
