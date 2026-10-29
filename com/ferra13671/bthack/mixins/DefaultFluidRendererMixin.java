package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.DefaultFluidRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(DefaultFluidRenderer.class)
public class DefaultFluidRendererMixin {
   @Inject(method = "isFullBlockFluidSideVisible", at = @At("HEAD"), cancellable = true)
   public void handleClass_1922(
      BlockView blockView, BlockPos blockPos, Direction direction, FluidState fluidState, CallbackInfoReturnable callbackInfoReturnable
   ) {
      SoundManager.handleClass_36102(fluidState, blockView.getFluidState(blockPos.offset(direction)), callbackInfoReturnable);
   }

   @Inject(method = "getUpFaceExposureByNeighbors", at = @At("HEAD"), cancellable = true)
   public void handleClass_19201(BlockRenderView blockRenderView, BlockPos blockPos, FluidState fluidState, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleClass_361018(fluidState, callbackInfoReturnable);
   }

   @Inject(method = "isFullBlockFluidSelfVisible", at = @At("HEAD"), cancellable = true)
   public void handleClass_26802(BlockState blockState, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleClass_26808(blockState, callbackInfoReturnable);
   }

   @Inject(method = "isSideExposedOffset", at = @At("HEAD"), cancellable = true)
   public void handleClass_19203(
      BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, Direction direction, float flt, CallbackInfoReturnable callbackInfoReturnable
   ) {
      SoundManager.handleClass_2680(blockState, callbackInfoReturnable);
   }

   @Inject(
      method = "isFluidSideExposed(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;F)Z",
      at = @At("HEAD"),
      cancellable = true
   )
   public void handleClass_26804(BlockState blockState, BlockState blockState2, Direction direction, float flt, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleClass_36102(blockState.getFluidState(), blockState2.getFluidState(), callbackInfoReturnable);
   }
}
