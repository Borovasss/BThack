package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.mojang.blaze3d.buffers.GpuBuffer.MappedView;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.nio.ByteBuffer;
import net.minecraft.client.gl.MappableRingBuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
   @Shadow
   @Final
   transient MappableRingBuffer fogBuffer;
   @Unique
   transient FogData fogData;

   @Unique
   public void handleFloat(float flt, float flt2, FogData fogData) {
      this.fogData = new FogData();
      fogData.environmentalStart = this.fogData.environmentalStart = flt;
      fogData.renderDistanceStart = this.fogData.renderDistanceStart = flt;
      fogData.environmentalEnd = this.fogData.environmentalEnd = flt2;
      fogData.renderDistanceEnd = this.fogData.renderDistanceEnd = flt2;
      fogData.skyEnd = this.fogData.skyEnd = flt2;
      fogData.cloudEnd = this.fogData.cloudEnd = flt2;
   }

   @Inject(method = "getFogColor", at = @At("HEAD"), cancellable = true)
   private void handleClass_41841(Camera camera, float flt, ClientWorld clientWorld, int num, float flt2, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom5.getValue()) {
         callbackInfoReturnable.setReturnValue(RenderColor.of((Color)ToggleableUtils.ambience.color2.getValue()).toVector4f());
      }
   }

   @Inject(method = "applyFog", at = @At("RETURN"))
   public void setFogData(
      Camera camera,
      int num,
      RenderTickCounter renderTickCounter,
      float flt,
      ClientWorld clientWorld,
      CallbackInfoReturnable callbackInfoReturnable,
      Vector4f vector4f
   ) {
      if (this.fogData != null) {
         MappedView gpuBuffer$MappedView = RenderSystem.getDevice().createCommandEncoder().mapBuffer(this.fogBuffer.getBlocking(), false, true);

         try {
            this.applyFog(
               gpuBuffer$MappedView.data(),
               0,
               vector4f,
               this.fogData.environmentalStart,
               this.fogData.environmentalEnd,
               this.fogData.renderDistanceStart,
               this.fogData.renderDistanceEnd,
               this.fogData.skyEnd,
               this.fogData.cloudEnd
            );
         } catch (Throwable var12) {
            if (gpuBuffer$MappedView != null) {
               try {
                  gpuBuffer$MappedView.close();
               } catch (Throwable var11) {
                  var12.addSuppressed(var11);
               }
            }

            throw var12;
         }

         if (gpuBuffer$MappedView != null) {
            gpuBuffer$MappedView.close();
         }

         this.fogData = null;
      }
   }

   @Shadow
   protected abstract void applyFog(ByteBuffer var1, int var2, Vector4f var3, float var4, float var5, float var6, float var7, float var8, float var9);

   @Inject(
      method = "applyFog",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/fog/FogRenderer;applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
         shift = Shift.BEFORE
      )
   )
   public void handleClass_41842(
      Camera camera,
      int num,
      RenderTickCounter renderTickCounter,
      float flt3,
      ClientWorld clientWorld,
      CallbackInfoReturnable callbackInfoReturnable,
      FogData fogData
   ) {
      if (ToggleableUtils.xray.isEnabled()) {
         float flt = num * 100;
         float flt2 = num * 100.25F;
         this.handleFloat(flt, flt2, fogData);
      } else {
         if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom6.getValue()) {
            this.handleFloat((Float)ToggleableUtils.ambience.start.getValue(), (Float)ToggleableUtils.ambience.end.getValue(), fogData);
         }
      }
   }
}
