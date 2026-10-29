package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.RendererInit;
import com.ferra13671.bthack.ToggleableUtils;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.memory.ObjectAllocator;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
   @Shadow
   @Final
   transient WorldRenderState worldRenderState;

   @Inject(method = "render", at = @At("HEAD"))
   public void handleClass_9922(
      ObjectAllocator objectAllocator,
      RenderTickCounter renderTickCounter,
      boolean bool,
      Camera camera,
      Matrix4f matrix4f,
      Matrix4f matrix4f2,
      Matrix4f matrix4f3,
      GpuBufferSlice gpuBufferSlice,
      Vector4f vector4f,
      boolean bool2,
      CallbackInfo callbackInfo
   ) {
      RendererInit.boolean1 = true;
      RendererInit.matrix4f1 = matrix4f;
      RendererInit.matrix4f2 = matrix4f2;
      RendererInit.matrix4f3 = RenderSystem.getModelViewMatrix();
   }

   @Inject(method = "renderSky", at = @At("HEAD"))
   public void handleClass_99091(FrameGraphBuilder frameGraphBuilder, Camera camera, GpuBufferSlice gpuBufferSlice, CallbackInfo callbackInfo) {
      if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom3.getValue()) {
         this.worldRenderState.skyRenderState.skyColor = ((Color)ToggleableUtils.ambience.color3.getValue()).getRGB();
      }
   }

   @Inject(method = "renderTargetBlockOutline", at = @At("HEAD"), cancellable = true)
   public void handleClass_4597class_45982(Immediate obj, MatrixStack matrixStack, boolean bool, WorldRenderState worldRenderState, CallbackInfo callbackInfo) {
      if (BThackClient.getInstance() != null && BThackClient.getInstance().getModuleManager() != null && ToggleableUtils.blockOutline.isEnabled()) {
         callbackInfo.cancel();
      }
   }
}
