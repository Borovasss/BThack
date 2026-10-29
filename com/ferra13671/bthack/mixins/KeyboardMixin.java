package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.OnKeyEvent;
import com.ferra13671.bthack.PressBody;
import net.minecraft.client.Keyboard;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
   @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/InactivityFpsLimiter;onInput()V", shift = Shift.AFTER))
   public void handleLong(long lng, int num, KeyInput keyInput, CallbackInfo callbackInfo) {
      if (num == 0 || num == 1) {
         BThackClient.getInstance().getEventBus().register(new OnKeyEvent(keyInput.key(), num == 1 ? PressBody.Press : PressBody.Release));
      }
   }
}
