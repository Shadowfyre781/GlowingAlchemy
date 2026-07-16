package nox.shadowfyre.glowingalchemy.blocks;

public enum BlockArchetype {
    // Defines our standard shape template groupings
    WOOD_PLANK_SET(new String[]{"block", "slab", "stairs", "fence", "fence_gate", "button", "pressure_plate", "trapdoor", "door", "leaves"}),
    MASONRY_SET(new String[]{"block", "slab", "stairs", "wall"}),
    ADD_ON_MASONRY_SET(new String[]{"slab", "stairs", "wall"}),
    DEEP_MASONRY_SET(new String[]{"block", "slab", "stairs", "wall", "bricks", "door", "button", "pressure_plate"}),
    SINGLE_ENTRY(new String[]{"block"}), // Fallback for standalone unique blocks
CORAL_SET(new String[]{"block", "fan", "fan"});
    private final String[] associatedShapes;

    BlockArchetype(String[] associatedShapes) {
        this.associatedShapes = associatedShapes;
    }

    public String[] getAssociatedShapes() {
        return this.associatedShapes;
    }
}
//"log", "stripped_log", "wood", "stripped_wood", "planks"