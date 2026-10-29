package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidRenderer.class)
public class FluidRendererMixin {
   @Inject(method = "render", at = @At("HEAD"), cancellable = true)
   public void handleClass_1920(
      BlockRenderView blockRenderView,
      BlockPos blockPos,
      VertexConsumer vertexConsumer,
      BlockState blockState,
      FluidState fluidState,
      CallbackInfo callbackInfo
   ) {
      SoundManager.handleClass_36107(fluidState, callbackInfo);
   }

   @Inject(method = "isSideCovered", at = @At("HEAD"), cancellable = true)
   private static void handleClass_23501(Direction direction, float flt, BlockState blockState, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleArg9(callbackInfoReturnable);
   }
}
