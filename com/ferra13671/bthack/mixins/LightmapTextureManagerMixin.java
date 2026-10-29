package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.GetGlTextureViewEvent;
import com.ferra13671.bthack.RendererInit;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
   @Unique
   transient GpuTexture gpuTexture;
   @Unique
   transient GpuTextureView gpuTextureView;
   @Unique
   transient Color color1 = Color.WHITE;

   @Inject(method = "getGlTextureView", at = @At("HEAD"), cancellable = true)
   public void handleArg(CallbackInfoReturnable callbackInfoReturnable) {
      GetGlTextureViewEvent getGlTextureViewEvent = new GetGlTextureViewEvent();
      BThackClient.getInstance().getEventBus().register(getGlTextureViewEvent);
      if (getGlTextureViewEvent.getBoolean()) {
         if (!this.color1.equals(getGlTextureViewEvent.getColor() == null ? Color.WHITE : getGlTextureViewEvent.getColor())) {
            if (RenderSystem.isOnRenderThread()) {
               this.setColor(getGlTextureViewEvent.getColor());
            } else {
               RendererInit.runLater(() -> this.handleArg1(getGlTextureViewEvent));
            }
         }

         callbackInfoReturnable.setReturnValue(this.gpuTextureView);
      }
   }

   @Unique
   private void setColor(Color color) {
      RenderSystem.getDevice()
         .createCommandEncoder()
         .clearColorTexture(this.gpuTexture, ColorHelper.getArgb(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue()));
      this.color1 = color;
   }

   private void handleArg1(GetGlTextureViewEvent getGlTextureViewEvent) {
      this.setColor(getGlTextureViewEvent.getColor());
   }

   @Inject(method = "<init>", at = @At("TAIL"))
   public void handleClass_7572(GameRenderer gameRenderer, MinecraftClient minecraftClient, CallbackInfo callbackInfo) {
      GpuDevice gpuDevice = RenderSystem.getDevice();
      this.gpuTexture = gpuDevice.createTexture("BThack Gamma Texture", 12, TextureFormat.RGBA8, 16, 16, 1, 1);
      this.gpuTextureView = gpuDevice.createTextureView(this.gpuTexture);
      this.setColor(Color.WHITE);
   }
}
