package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.MinecraftClientTickEvent;
import com.ferra13671.bthack.PrioritySortedSub2;
import com.ferra13671.bthack.PriorityTimer;
import com.ferra13671.bthack.RendererInit;
import com.ferra13671.bthack.SetScreenEvent;
import com.ferra13671.bthack.ToggleableUtils;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.GameOptions;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
   @Shadow
   public transient @Nullable ClientPlayerEntity player;
   @Shadow
   @Final
   public transient GameOptions options;
   @Shadow
   public transient @Nullable ClientPlayerInteractionManager interactionManager;
   @Unique
   transient SetScreenEvent setScreenEvent = null;

   @Inject(method = "getTargetMillisPerTick", at = @At("RETURN"), cancellable = true)
   public void handleFloat(float flt, CallbackInfoReturnable callbackInfoReturnable) {
      if (BThackClient.getInstance() != null) {
         PrioritySortedSub2 prioritySortedSub2 = BThackClient.getInstance().getPrioritySortedSub2();
         if (prioritySortedSub2 != null && !prioritySortedSub2.hasSubscribers()) {
            callbackInfoReturnable.setReturnValue(
               (Float)callbackInfoReturnable.getReturnValue() / (Float)((PriorityTimer)prioritySortedSub2.getCurrent()).getTimerspeedsupplier().get()
            );
         }
      }
   }

   @ModifyExpressionValue(
      method = "handleBlockBreaking",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")
   )
   public boolean isBoolean1(boolean bool) {
      return !ToggleableUtils.multiTask.isEnabled() && bool;
   }

   @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
   public void setSetScreenEvent(Screen screen, CallbackInfo callbackInfo) {
      if (this.setScreenEvent != null) {
         if (this.setScreenEvent.getBoolean()) {
            callbackInfo.cancel();
         }

         this.setScreenEvent = null;
      }
   }

   @Inject(
      method = "handleInputEvents",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z", ordinal = 0, shift = Shift.BEFORE)
   )
   private void handleCallbackInfo2(CallbackInfo callbackInfo) {
      if (ToggleableUtils.multiTask.isEnabled() && (Boolean)ToggleableUtils.multiTask.attacking.getValue() && this.player.isUsingItem()) {
         if (!this.options.useKey.isPressed()) {
            this.interactionManager.stopUsingItem(this.player);
         }

         while (this.options.useKey.wasPressed()) {
         }
      }
   }

   @Inject(method = "getFramebuffer", at = @At("HEAD"), cancellable = true)
   public void handleArg3(CallbackInfoReturnable callbackInfoReturnable) {
      if (RendererInit.framebuffer != null) {
         callbackInfoReturnable.setReturnValue(RendererInit.framebuffer);
      }
   }

   @Inject(method = "tick", at = @At("HEAD"))
   public void handleCallbackInfo4(CallbackInfo callbackInfo) {
      BThackClient.getInstance().getEventBus().register(new MinecraftClientTickEvent());
   }

   @ModifyExpressionValue(
      method = "handleInputEvents",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z", ordinal = 0)
   )
   public boolean isBoolean5(boolean bool) {
      return (!ToggleableUtils.multiTask.isEnabled() || !(Boolean)ToggleableUtils.multiTask.attacking.getValue()) && bool;
   }

   @ModifyVariable(method = "setScreen", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   public Screen withClass_4376(Screen screen) {
      SetScreenEvent setScreenEvent = new SetScreenEvent(screen);
      BThackClient.getInstance().getEventBus().register(setScreenEvent);
      this.setScreenEvent = setScreenEvent;
      return setScreenEvent.screen;
   }

   @ModifyExpressionValue(
      method = "doItemUse",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;isBreakingBlock()Z")
   )
   public boolean isBoolean7(boolean bool) {
      return !ToggleableUtils.multiTask.isEnabled() && bool;
   }
}
