package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkOcclusionDataBuilder.class)
public class ChunkOcclusionDataBuilderMixin {
   @Inject(method = "markClosed", at = @At("HEAD"), cancellable = true)
   public void handleClass_2338(BlockPos blockPos, CallbackInfo callbackInfo) {
      if (ToggleableUtils.freeCamera.isEnabled() || ToggleableUtils.xray.isEnabled()) {
         callbackInfo.cancel();
      }
   }
}
