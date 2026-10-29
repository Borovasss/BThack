package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.EventSub6;
import com.ferra13671.bthack.OnMouseButtonEvent;
import com.ferra13671.bthack.OnMouseScrollEvent;
import com.ferra13671.bthack.PressBody;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
   @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
   public void handleLong(long lng, double dbl, double dbl2, CallbackInfo callbackInfo) {
      OnMouseScrollEvent onMouseScrollEvent = new OnMouseScrollEvent((float)dbl2);
      BThackClient.getInstance().getEventBus().register(onMouseScrollEvent);
      if (onMouseScrollEvent.getBoolean()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Mouse;isCursorLocked()Z", shift = Shift.BEFORE))
   public void handleCallbackInfo1(CallbackInfo callbackInfo) {
      BThackClient.getInstance().getEventBus().register(new EventSub6.MouseTickEvent());
   }

   @Inject(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/InactivityFpsLimiter;onInput()V", shift = Shift.AFTER))
   public void handleLong2(long lng, MouseInput mouseInput, int num, CallbackInfo callbackInfo) {
      if (num == 0 || num == 1) {
         BThackClient.getInstance().getEventBus().register(new OnMouseButtonEvent(mouseInput.button(), num == 1 ? PressBody.Press : PressBody.Release));
      }
   }
}
