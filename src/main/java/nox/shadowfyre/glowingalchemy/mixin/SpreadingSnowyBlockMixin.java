package nox.shadowfyre.glowingalchemy.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SpreadingSnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import nox.shadowfyre.glowingalchemy.glowing_things.SideWrapConversionEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpreadingSnowyBlock.class)
public class SpreadingSnowyBlockMixin {

   @Inject(method = "randomTick", at = @At("TAIL"))
   private void glowingalchemy$spreadDownCliffs(
          BlockState state,
          ServerLevel level,
          BlockPos pos,
           RandomSource random,
           CallbackInfo ci
   ) {
       SideWrapConversionEngine.tryVerticalConversion(level, pos, state, random);
    }

}