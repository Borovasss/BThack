package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderManager.class)
public class BlockEntityRenderManagerMixin {
   @Inject(method = "render", at = @At("HEAD"), cancellable = true)
   public void handleClass_11954(
      BlockEntityRenderState blockEntityRenderState,
      MatrixStack matrixStack,
      OrderedRenderCommandQueue obj,
      CameraRenderState cameraRenderState,
      CallbackInfo callbackInfo
   ) {
      SoundManager.handleClass_1195410(blockEntityRenderState, callbackInfo);
   }
}
