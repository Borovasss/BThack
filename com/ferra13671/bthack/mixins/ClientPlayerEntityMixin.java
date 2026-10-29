package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.CloseHandledScreenEvent;
import com.ferra13671.bthack.TickMovementEvent;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
   @Shadow
   public transient float lastNauseaIntensity;
   @Shadow
   public transient float nauseaIntensity;

   @Inject(method = "closeHandledScreen", at = @At("HEAD"), cancellable = true)
   public void handleCallbackInfo(CallbackInfo callbackInfo) {
      CloseHandledScreenEvent closeHandledScreenEvent = new CloseHandledScreenEvent();
      BThackClient.getInstance().getEventBus().register(closeHandledScreenEvent);
      if (closeHandledScreenEvent.getBoolean()) {
         callbackInfo.cancel();
      }
   }

   @Redirect(method = "tickMovementInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getPitch()F"))
   public float getClass_7461(ClientPlayerEntity clientPlayerEntity) {
      return !BThackClient.getInstance().getPrioritySortedHelper().hasSubscribers()
         ? BThackClient.getInstance().getPrioritySortedHelper().getHelper3().getFloat()
         : clientPlayerEntity.getPitch();
   }

   @Inject(method = "tickNausea", at = @At("HEAD"))
   public void handleBoolean2(boolean bool, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.portal.getValue()) {
         this.lastNauseaIntensity = 0.0F;
         this.nauseaIntensity = 0.0F;
      }
   }

   @Redirect(method = "tickMovementInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getYaw()F"))
   public float getClass_7463(ClientPlayerEntity clientPlayerEntity) {
      return !BThackClient.getInstance().getPrioritySortedHelper().hasSubscribers()
         ? BThackClient.getInstance().getPrioritySortedHelper().getHelper3().getFloat4()
         : clientPlayerEntity.getYaw();
   }

   @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;tick()V", shift = Shift.AFTER))
   public void handleCallbackInfo4(CallbackInfo callbackInfo) {
      BThackClient.getInstance().getEventBus().register(new TickMovementEvent());
   }
}
