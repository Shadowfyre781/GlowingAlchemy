package nox.shadowfyre.glowingalchemy.glowing_things.color;

import java.util.List;

public record GlowSet(String name, List<GlowColor> colors) {

    public GlowSet {
        colors = List.copyOf(colors);
    }

    public GlowColor previous (GlowColor color) {
        int index = indexOf(color);
        if (index < 0) {
            throw new IllegalArgumentException(
                    color.name() + " is not in GlowSet " + name);
        }
        return get(index - 1);
    }



    public GlowColor next(GlowColor color) {
        int index = indexOf(color);
        if (index < 0) {
            throw new IllegalArgumentException(
                    color.name() + " is not in GlowSet " + name);
        }
        return get(index + 1);
}

public int size() {
    return colors.size();
}

public GlowColor get(int index) {
    return colors.get(Math.floorMod(index, colors.size()));
}

public int indexOf(GlowColor color) {
    return colors.indexOf(color);
}

public boolean contains(GlowColor color) {
    return colors.contains(color);
}

public boolean isEmpty() {
    return colors.isEmpty();
}

public boolean hasColors() {
    return !colors.isEmpty();
}
}//defines what a box of crayons is