package nox.shadowfyre.glowingalchemy.mechanic.gmoak;

import net.minecraft.util.StringRepresentable;

public enum GMOakType implements StringRepresentable {
    IRON("iron"),
    COPPER("copper"),
    GOLD("gold"),
    NETHERITE("netherite");

    private final String name;

    GMOakType(String name) { this.name = name; }

    @Override
    public String getSerializedName() { return this.name; }
}