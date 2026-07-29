package nox.shadowfyre.glowingalchemy.blocks;

import java.util.List;

public enum BlockShapeTemplate {
    WOOD_PLANK_SET(List.of(BlockShape.BLOCK, BlockShape.SLAB, BlockShape.STAIRS, BlockShape.BUTTON, BlockShape.PRESSURE_PLATE, BlockShape.FENCE, BlockShape.FENCE_GATE, BlockShape.DOOR, BlockShape.TRAPDOOR)),
    MASONRY_SET(List.of(BlockShape.BLOCK, BlockShape.SLAB, BlockShape.STAIRS, BlockShape.WALL)),
    ADD_ON_MASONRY_SET(List.of(BlockShape.SLAB, BlockShape.STAIRS, BlockShape.WALL)),
    DEEP_MASONRY_SET(List.of(BlockShape.BLOCK, BlockShape.SLAB, BlockShape.STAIRS, BlockShape.WALL)),
    SINGLE_ENTRY(List.of(BlockShape.BLOCK)),
    CORAL_SET(List.of(BlockShape.BLOCK, BlockShape.FAN, BlockShape.BUD));

    private final List<BlockShape> shapes;

    BlockShapeTemplate(List<BlockShape> shapes) {
        this.shapes = List.copyOf(shapes);
    }

    public List<BlockShape> shapes() {
        return shapes;
    }}