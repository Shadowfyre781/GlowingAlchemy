package nox.shadowfyre.glowingalchemy.mechanic.groundcover;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.model.data.ModelData;

public class SideWrapDataEngine {

    public static ModelData getWrapModelData(BlockGetter level, BlockPos pos, BlockState state, Direction facing) {
        BlockPos sidePos = pos.relative(facing);
        BlockState sideState = level.getBlockState(sidePos);

        // The side must be exposed to air.
        if (!sideState.isAir()) {
            return ModelData.EMPTY;
        }

        // No overhang above the adjacent air block.
        if (!level.getBlockState(sidePos.above()).isAir()) {
            return ModelData.EMPTY;
        }

        int distanceToFirstExposedSurface = -1;

        // Allow wrapping only within 4 blocks below an exposed surface.
        for (int i = 1; i <= 4; i++) {
            BlockPos checkPos = pos.above(i);
            BlockPos checkSidePos = checkPos.relative(facing);

            BlockState checkState = level.getBlockState(checkPos);
            BlockState checkSideState = level.getBlockState(checkSidePos);

            if (checkState.is(state.getBlock()) && checkSideState.isAir() && level.getBlockState(checkSidePos.above()).isAir()) {
                distanceToFirstExposedSurface = i;
                break;
            }
        }

        if (distanceToFirstExposedSurface == -1) {
            return ModelData.EMPTY;
        }

        return ModelData.builder()
                .with(SideWrapBakedModel.ROOT_DEPTH, distanceToFirstExposedSurface)
                .build();
    }
}