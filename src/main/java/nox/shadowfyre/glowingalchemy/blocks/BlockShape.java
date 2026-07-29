package nox.shadowfyre.glowingalchemy.blocks;

public enum BlockShape {
           SLAB("slab"),
        STAIRS("stairs"),
        WALL("wall"),
        BLOCK("block"),
        BUTTON("button"),
        PRESSURE_PLATE("pressure_plate"),
        FENCE("fence"),
        FENCE_GATE("fence_gate"),
        DOOR("door"),
        TRAPDOOR("trapdoor"),
    FAN("fan"),
            BUD("bud");
    private final String id;

    BlockShape(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public boolean hasItemModel() {
        return true;
    }

    public boolean requiresLootTable() {
        return true;
    }
}
