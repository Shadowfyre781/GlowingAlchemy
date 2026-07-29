package nox.shadowfyre.glowingalchemy.registry;

public enum BlockShape {

    BLOCK("", "Block", ""),
    SLAB("slab", "Slab", "slab"),
    STAIRS("stairs", "Stairs", "stairs"),
    WALL("wall", "Wall", "wall"),
    FENCE("fence", "Fence", "fence"),
    FENCE_GATE("fence_gate", "Fence Gate", "fence_gate"),
    BUTTON("button", "Button", "button"),
    PRESSURE_PLATE("pressure_plate", "Pressure Plate", "pressure_plate"),
    TRAPDOOR("trapdoor", "Trapdoor", "trapdoor"),
    DOOR("door", "Door", "door"),
    FAN("fan", "Fan", "fan"),
    BUD("bud", "Bud", "bud");
    private final String resourceSuffix;
    private final String displayName;
    private final String nameSuffix;

    BlockShape(String resourceSuffix, String displayName, String nameSuffix) {
        this.resourceSuffix = resourceSuffix;
        this.displayName = displayName;
        this.nameSuffix = nameSuffix;
    }

    public String resourceSuffix() {
        return resourceSuffix;
    }

    public String displayName() {
        return displayName;
    }

    public String nameSuffix() {
        return nameSuffix;
    }

    public boolean isBaseBlock() {
        return resourceSuffix.isEmpty();
    }

    public boolean hasItemModel() {
        return true;
    }

    public boolean requiresLootTable() {
        return true;
    }

    /*
    shape.generateModel(...)
shape.requiresLootTable()
shape.hasItemModel()
shape.isDirectional()
shape.isMultipart()
    */
}