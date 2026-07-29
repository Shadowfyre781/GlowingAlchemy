package nox.shadowfyre.glowingalchemy.glowing_things.color;
import net.minecraft.world.level.material.MapColor;

public record GlowColor(
        String name,
        int argb,
        MapColor mapColor
){};
