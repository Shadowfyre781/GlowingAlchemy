package nox.shadowfyre.glowingalchemy.forest_primeval.color;

import nox.shadowfyre.glowingalchemy.core.crayonbin.GlowSet;

import java.util.List;

import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.*;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.BOLD_PURPLE;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.GREEN;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.PASTEL_PURPLE;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.PINK;
import static nox.shadowfyre.glowingalchemy.core.crayonbin.GlowColors.PURPLE;

public class FP_Palette {

    public static final GlowSet MAPLE =
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
    public static final GlowSet JACARTA =
            new GlowSet("Jacarta",
                    List.of(

                            PURPLE,
                            PASTEL_PURPLE,
                            BOLD_PURPLE

                    ));
}