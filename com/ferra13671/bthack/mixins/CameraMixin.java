package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.EventSub8;
import com.ferra13671.bthack.ToggleableUtils;
import com.ferra13671.bthack.UpdateEvent;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Camera.class)
public class CameraMixin {
   @Shadow
   transient float lastTickProgress;

   @Inject(method = "isThirdPerson", at = @At("HEAD"), cancellable = true)
   public void handleArg(CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.freeCamera.isEnabled()) {
         callbackInfoReturnable.setReturnValue(true);
      }
   }

   @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
   public void handleArgs1(Args args) {
      EventSub8 eventSub8 = new EventSub8((Float)args.get(0), (Float)args.get(1));
      BThackClient.getInstance().getEventBus().register(eventSub8);
      args.set(0, eventSub8.float1);
      args.set(1, eventSub8.float2);
   }

   @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setPos(DDD)V"))
   public void handleArgs2(Args args) {
      UpdateEvent updateEvent = new UpdateEvent((Double)args.get(0), (Double)args.get(1), (Double)args.get(2), this.lastTickProgress);
      BThackClient.getInstance().getEventBus().register(updateEvent);
      args.set(0, updateEvent.getWidth());
      args.set(1, updateEvent.getHeight());
      args.set(2, updateEvent.getDouble());
   }

   @ModifyArg(
      method = "update",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setPos(Lnet/minecraft/util/math/Vec3d;)V"),
      index = 0
   )
   public Vec3d withClass_2433(Vec3d vec3d) {
      UpdateEvent updateEvent = new UpdateEvent(vec3d.x, vec3d.y, vec3d.z, this.lastTickProgress);
      BThackClient.getInstance().getEventBus().register(updateEvent);
      return new Vec3d(updateEvent.getWidth(), updateEvent.getHeight(), updateEvent.getDouble());
   }
}
