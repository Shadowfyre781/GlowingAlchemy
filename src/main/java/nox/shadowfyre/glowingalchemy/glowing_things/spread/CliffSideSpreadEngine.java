package nox.shadowfyre.glowingalchemy.glowing_things.spread;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;
import nox.shadowfyre.glowingalchemy.glowing_things.registry.ModTags;

public final class CliffSideSpreadEngine {
    private static final int MAX_DEPTH = 3;

    private CliffSideSpreadEngine() {
    }

    public static void trySpreadDownCliff(ServerLevel level, BlockPos sourcePos, BlockState sourceState, RandomSource random) {
        if (!sourceState.is(ModTags.Blocks.SIDE_SPREADERS)) {
            return;
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!isOpenCliffSide(level, sourcePos, direction)) {
                continue;
            }

            TagKey<Block> replaceableTag = getReplaceableTagFor(sourceState.getBlock());

            for (int depth = 1; depth <= MAX_DEPTH; depth++) {
                BlockPos targetPos = sourcePos.below(depth);

                if (!isOpenCliffSide(level, targetPos, direction)) {
                    break;
                }

                BlockState targetState = level.getBlockState(targetPos);

                if (!targetState.is(replaceableTag)) {
                    continue;
                }

                level.setBlockAndUpdate(targetPos, sourceState.getBlock().defaultBlockState());

                // Keeps spread gradual like vanilla random ticking.
                if (random.nextInt(3) != 0) {
                    break;
                }
            }
        }
    }

    private static boolean isOpenCliffSide(ServerLevel level, BlockPos pos, Direction direction) {
        BlockPos sidePos = pos.relative(direction);

        return level.getBlockState(sidePos).isAir()
                && level.getBlockState(sidePos.above()).isAir();
    }

    private static TagKey<Block> getReplaceableTagFor(Block sourceBlock) {
        Identifier sourceId = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(sourceBlock);

        Identifier tagId = Identifier.fromNamespaceAndPath(
                GlowingAlchemy.MODID,
                "side_spread_replaceable/" + sourceId.getNamespace() + "/" + sourceId.getPath()
        );

        return TagKey.create(Registries.BLOCK, tagId);
    }
    private static final SpreadGroup[] SPREAD_GROUPS = {
            new SpreadGroup(
                    ModTags.Blocks.SPREAD_SOURCES_GLOWGRASS,
                    ModTags.Blocks.SPREAD_REPLACEABLE_GLOWGRASS
            ),
            new SpreadGroup(
                    ModTags.Blocks.SPREAD_SOURCES_GLOWMYCELIUM,
                    ModTags.Blocks.SPREAD_REPLACEABLE_GLOWMYCELIUM
            )
    };
}