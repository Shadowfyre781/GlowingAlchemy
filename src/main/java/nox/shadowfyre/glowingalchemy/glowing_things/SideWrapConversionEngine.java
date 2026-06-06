package nox.shadowfyre.glowingalchemy.glowing_things;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import nox.shadowfyre.glowingalchemy.glowing_things.registry.ModTags;

public final class SideWrapConversionEngine {
    private static final int MAX_VERTICAL_DEPTH = 4;

    private static final SpreadGroup[] SPREAD_GROUPS = {
            new SpreadGroup(ModTags.Blocks.SPREAD_SOURCES_GLOWGRASS, ModTags.Blocks.SPREAD_REPLACEABLE_GLOWGRASS),
            new SpreadGroup(ModTags.Blocks.SPREAD_SOURCES_GLOWMYCELIUM, ModTags.Blocks.SPREAD_REPLACEABLE_GLOWMYCELIUM)
    };

    private SideWrapConversionEngine() {
    }

    public static void tryVerticalConversion(ServerLevel level, BlockPos sourcePos, BlockState sourceState, RandomSource random) {
        if (!sourceState.is(ModTags.Blocks.IS_SIDE_WRAPPABLE)) {
            return;
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!hasValidSideExposure(level, sourcePos, direction)) {
                continue;
            }

            convertDownColumn(level, sourcePos, sourceState, direction, random);
        }
    }

    private static void convertDownColumn(ServerLevel level, BlockPos sourcePos, BlockState sourceState, Direction direction, RandomSource random) {
        for (int depth = 1; depth <= MAX_VERTICAL_DEPTH; depth++) {
            BlockPos targetPos = sourcePos.below(depth);
            BlockState targetState = level.getBlockState(targetPos);

            if (!hasValidSideExposure(level, targetPos, direction)) {
                break;
            }

            BlockState convertedState = getConvertedState(sourceState, targetState);

            if (convertedState == null) {
                continue;
            }

            level.setBlockAndUpdate(targetPos, convertedState);

            // Only convert one block most ticks so spreading feels gradual.
            if (random.nextInt(3) != 0) {
                break;
            }
        }
    }

    private static BlockState getConvertedState(BlockState sourceState, BlockState targetState) {
        SpreadGroup group = getMatchingSpreadGroup(sourceState, targetState);

        if (group == null) {
            return null;
        }

        return sourceState.getBlock().defaultBlockState();
    }

    private static SpreadGroup getMatchingSpreadGroup(BlockState sourceState, BlockState targetState) {
        for (SpreadGroup group : SPREAD_GROUPS) {
            if (sourceState.is(group.sourceTag()) && targetState.is(group.replaceableTag())) {
                return group;
            }
        }

        return null;
    }

    private static boolean hasValidSideExposure(ServerLevel level, BlockPos pos, Direction direction) {
        BlockPos sidePos = pos.relative(direction);

        // Side must be exposed to air.
        if (!level.getBlockState(sidePos).isAir()) {
            return false;
        }

        // No overhang above the adjacent air block.
        return level.getBlockState(sidePos.above()).isAir();
    }

    private record SpreadGroup(TagKey<Block> sourceTag, TagKey<Block> replaceableTag) {
    }
}