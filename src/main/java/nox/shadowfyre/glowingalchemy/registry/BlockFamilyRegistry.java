package nox.shadowfyre.glowingalchemy.registry;

/*
public final class BlockFamilyRegistry {
    public static final Map<Identifier, BlockDefinition> REGISTERED_DEFS = new HashMap<>();
    public static final Map<Identifier, Block> REGISTERED_BLOCKS = new HashMap<>();
    public static final Map<Identifier, Integer> BLOCK_TINTS = new HashMap<>();

    private BlockFamilyRegistry() {
    }

    public static void registerDefinition(BlockDefinition definition) {
        Identifier familyId = Identifier.parse(definition.namespace() + ":" + definition.familyId());
        REGISTERED_DEFS.put(familyId, definition);
    }

    public static void expandAndRegisterAll() {
        for (BlockDefinition definition : REGISTERED_DEFS.values()) {
            expandAndRegister(definition);
        }
    }

    public static void expandAndRegister(BlockDefinition definition) {
        String namespace = definition.namespace();
        String familyId = definition.familyId();
        BlockShapeTemplate archetype = definition.BlockShape();

        for (String colorName : definition.colors()) {
            for (String shape : archetype.shapes()) {
                String shapeId = definition.resolveShapeId(shape, colorName);
                Identifier blockId = Identifier.parse(namespace + ":" + familyId + "/" + shapeId);

                if (REGISTERED_BLOCKS.containsKey(blockId)) {
                    continue;
                }

                Block block = createBlockForShape(shape, definition, colorName);
                REGISTERED_BLOCKS.put(blockId, block);

                if (isTintedDefinition(definition)) {
                    BLOCK_TINTS.put(blockId, resolveVanillaTintColor(colorName));
                }
            }
        }
    }

    public static Block createBlockForShape(String shape, BlockDefinition definition, String colorName) {
        String shapeKey = shape.toLowerCase();

        return switch (shapeKey) {
            case "stair", "stairs" -> new StairBlock(Blocks.STONE.defaultBlockState(), defaultProperties());
            case "slab" -> new SlabBlock(defaultProperties());
            case "wall" -> new WallBlock(defaultProperties());
            case "fence" -> new FenceBlock(defaultProperties());
            case "fence_gate" -> new FenceGateBlock(defaultProperties(), null);
            case "pressure_plate" -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, defaultProperties());
            case "button" -> new ButtonBlock(defaultProperties(), false);
            case "trapdoor" -> new TrapDoorBlock(defaultProperties(), false);
            case "door" -> new DoorBlock(defaultProperties(), null);
            default -> new Block(defaultProperties());
        };
    }

    public static List<Identifier> getBlockIdsForDefinition(BlockDefinition definition) {
        List<Identifier> ids = new ArrayList<>();

        for (String colorName : definition.colors()) {
            for (String shape : definition.BlockShape().shapes()) {
                String shapeId = definition.resolveShapeId(shape, colorName);
                ids.add(Identifier.parse(definition.namespace() + ":" + definition.familyId() + "/" + shapeId));
            }
        }

        return ids;
    }

    public static boolean isTintedDefinition(BlockDefinition definition) {
        return definition.baseTextureId() != null && definition.baseTextureId().contains("tint");
    }

    public static int resolveVanillaTintColor(String colorName) {
        return switch (colorName) {
            case "white" -> 0xFFFFFFFF;
            case "orange" -> 0xFFF9801D;
            case "magenta" -> 0xFFC74EBD;
            case "light_blue" -> 0xFF3AB3DA;
            case "yellow" -> 0xFFFED83D;
            case "lime" -> 0xFF80C71F;
            case "pink" -> 0xFFF38BAA;
            case "gray" -> 0xFF474F52;
            case "light_gray" -> 0xFF9D9D97;
            case "cyan" -> 0xFF169C9C;
            case "purple" -> 0xFF8932B8;
            case "blue" -> 0xFF3C44AA;
            case "brown" -> 0xFF835432;
            case "green" -> 0xFF5E7C16;
            case "red" -> 0xFFB02E26;
            case "black" -> 0xFF1D1D21;
            case "neutral" -> 0xFFD8D8D8;
            default -> 0xFFFFFFFF;
        };
    }

    private static BlockBehaviour.Properties defaultProperties() {
        return BlockBehaviour.Properties.of();
    }
}
*/
 