package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
   @Inject(method = "getRenderingSeed", at = @At("HEAD"), cancellable = true)
   public void handleClass_2680(BlockState blockState, BlockPos blockPos, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.textureRotation.getValue()) {
         callbackInfoReturnable.setReturnValue(0L);
      }
   }
}
