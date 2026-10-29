package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.EventSub6;
import com.ferra13671.bthack.OtherClientPlayerEntityHelper;
import com.ferra13671.bthack.SetVelocityEvent;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
   @Shadow
   transient Vec3d velocity;

   @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
   public void handleDouble(double dbl, double dbl2, CallbackInfo callbackInfo) {
      EventSub6.ChangeLookDirectionEvent obj = new EventSub6.ChangeLookDirectionEvent(dbl, dbl2);
      BThackClient.getInstance().getEventBus().register(obj);
      if (obj.getBoolean()) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
   public void handleClass_12971(Entity entity, CallbackInfo callbackInfo) {
      if (entity instanceof OtherClientPlayerEntityHelper) {
         callbackInfo.cancel();
      }
   }

   @Inject(method = "setVelocity", at = @At("HEAD"), cancellable = true)
   public void setField_18276(Vec3d vec3d, CallbackInfo callbackInfo) {
      SetVelocityEvent setVelocityEvent = new SetVelocityEvent(vec3d);
      BThackClient.getInstance().getEventBus().register(setVelocityEvent);
      if (setVelocityEvent.getBoolean()) {
         callbackInfo.cancel();
         this.velocity = setVelocityEvent.vec3d;
      }
   }
}
