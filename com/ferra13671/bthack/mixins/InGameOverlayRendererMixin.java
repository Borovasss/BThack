package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
   @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
   private static void handleClass_4587(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, Sprite sprite, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.fire.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "renderUnderwaterOverlay", at = @At("HEAD"), cancellable = true)
   private static void handleClass_3101(
      MinecraftClient minecraftClient, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, CallbackInfo callbackInfo
   ) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.water.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "renderInWallOverlay", at = @At("HEAD"), cancellable = true)
   private static void handleClass_10582(Sprite sprite, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.inWallOverlay.getValue()) {
         callbackInfo.cancel();
      }
   }
}
