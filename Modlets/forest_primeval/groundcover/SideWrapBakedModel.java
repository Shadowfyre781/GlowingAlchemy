package nox.shadowfyre.glowingalchemy.mechanic.groundcover;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.DelegateBlockStateModel;
import net.neoforged.neoforge.model.data.ModelData;
import net.neoforged.neoforge.model.data.ModelProperty;

import java.util.List;

public class SideWrapBakedModel extends DelegateBlockStateModel {

    public static final ModelProperty<Integer> ROOT_DEPTH = new ModelProperty<>();

    public SideWrapBakedModel(BlockStateModel originalModel) {
        super(originalModel);
    }

    @Override
    public void collectParts(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> parts) {
        ModelData modelData = level.getModelData(pos);
        Integer depth = modelData.get(ROOT_DEPTH);

        if (depth != null && depth >= 1 && depth <= 4) {
            // TODO: replace or add side-facing parts that use the top texture.
            // For now, delegate normally so the model still renders.
        }

        super.collectParts(level, pos, state, random, parts);
    }
}