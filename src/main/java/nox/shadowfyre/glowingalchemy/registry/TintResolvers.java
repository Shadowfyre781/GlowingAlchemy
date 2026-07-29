package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColor;

public final class TintResolvers {
    private TintResolvers() {}

    public static int resolveTintColor(BlockDefinition def, String colorName) {
        if (def.usesVanillaTintTable()) {
            return resolveVanillaTintColor(def.familyId(), colorName);
        }
        return resolvePaletteTintColor(def.tintSourceId(), colorName);
    }

    private static int resolveVanillaTintColor(String familyName, String colorName) {
        return switch (familyName) {
            case "illumiglass" -> switch (colorName) {
                case "white" -> 0xF9FFFE;
                case "orange" -> 0xF9801D;
                case "magenta" -> 0xC74EBD;
                case "light_blue" -> 0x3AB3DA;
                case "yellow" -> 0xFED83D;
                case "lime" -> 0x80C71F;
                case "pink" -> 0xF38BAA;
                case "gray" -> 0x474F52;
                case "light_gray" -> 0x9D9D97;
                case "cyan" -> 0x169C9C;
                case "purple" -> 0x8932B8;
                case "blue" -> 0x3C44AA;
                case "brown" -> 0x835432;
                case "green" -> 0x5E7C16;
                case "red" -> 0xB02E26;
                case "black" -> 0x1D1D21;
                default -> 0xFFFFFF;
            };
            case "glowool" -> switch (colorName) {
                case "white" -> 0xF0F0F0;
                case "orange" -> 0xD87F33;
                case "magenta" -> 0xB24CD8;
                case "light_blue" -> 0x6699D8;
                case "yellow" -> 0xE5E533;
                case "lime" -> 0x7FCC19;
                case "pink" -> 0xF27FA5;
                case "gray" -> 0x4C4C4C;
                case "light_gray" -> 0x999999;
                case "cyan" -> 0x4C7F99;
                case "purple" -> 0x7F3FB2;
                case "blue" -> 0x334CB2;
                case "brown" -> 0x664C33;
                case "green" -> 0x667F33;
                case "red" -> 0x993333;
                case "black" -> 0x191919;
                default -> 0xFFFFFF;
            };
            case "glowcrete" -> switch (colorName) {
                case "white" -> 0xEDEBE7;
                case "orange" -> 0xD0842A;
                case "magenta" -> 0xB35D9A;
                case "light_blue" -> 0x7CAFC6;
                case "yellow" -> 0xD9C24A;
                case "lime" -> 0x8DBB45;
                case "pink" -> 0xD48AA7;
                case "gray" -> 0x636363;
                case "light_gray" -> 0x9A9A9A;
                case "cyan" -> 0x4E8A8A;
                case "purple" -> 0x7B5A9C;
                case "blue" -> 0x5468A4;
                case "brown" -> 0x80604A;
                case "green" -> 0x6A7F46;
                case "red" -> 0xA05050;
                case "black" -> 0x2B2B2B;
                default -> 0xFFFFFF;
            };
            default -> 0xFFFFFF;
        };
    }

    private static int resolvePaletteTintColor(String paletteId, String colorName) {
        // Hook this into your GlowPalette registry
        return GlowColor.get(paletteId).color(colorName);
    }
}