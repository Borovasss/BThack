package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
   @WrapWithCondition(
      method = "renderMiscOverlays",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
         ordinal = 0
      )
   )
   public boolean isClass_329(InGameHud inGameHud, DrawContext drawContext, Identifier identifier, float flt) {
      return !ToggleableUtils.noRender.isEnabled() || !(Boolean)ToggleableUtils.noRender.headOverlay.getValue();
   }

   @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
   public void handleClass_3321(DrawContext drawContext, float flt, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.spyglass.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
   public void handleClass_3322(DrawContext drawContext, float flt, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.portal.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "renderBossBarHud", at = @At("HEAD"), cancellable = true)
   public void handleClass_3323(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.bossBar.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Inject(
      method = "renderSleepOverlay",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V", shift = Shift.BEFORE),
      cancellable = true
   )
   public void handleClass_3324(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.sleep.getValue()) {
         callbackInfo.cancel();
      }
   }

   @WrapWithCondition(
      method = "renderMiscOverlays",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
         ordinal = 1
      )
   )
   public boolean isClass_3295(InGameHud inGameHud, DrawContext drawContext, Identifier identifier, float flt) {
      return !ToggleableUtils.noRender.isEnabled() || !(Boolean)ToggleableUtils.noRender.powderSnow.getValue();
   }

   @Inject(method = "renderVignetteOverlay", at = @At("HEAD"), cancellable = true)
   public void handleClass_3326(DrawContext drawContext, Entity entity, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.vignette.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "renderNauseaOverlay", at = @At("HEAD"), cancellable = true)
   public void handleClass_3327(DrawContext drawContext, float flt, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.nausea.getValue()) {
         callbackInfo.cancel();
      }
   }
}
