package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.SoundManager;
import com.ferra13671.bthack.ToggleableUtils;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlockState.class)
public class AbstractBlock$AbstractBlockStateMixin {
   @ModifyReturnValue(method = "getLuminance", at = @At("RETURN"))
   public int getInt(int num) {
      return SoundManager.getInt3(num);
   }

   @Inject(method = "getModelOffset", at = @At("HEAD"), cancellable = true)
   public void handleClass_23381(BlockPos blockPos, CallbackInfoReturnable callbackInfoReturnable) {
      if (BThackClient.getInstance() != null
         && BThackClient.getInstance().getModuleManager() != null
         && ToggleableUtils.noRender.isEnabled()
         && (Boolean)ToggleableUtils.noRender.textureRotation.getValue()) {
         callbackInfoReturnable.setReturnValue(new Vec3d(0.0, 0.0, 0.0));
      }
   }
}
