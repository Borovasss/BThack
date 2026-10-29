package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ClearBody;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class WorldMixin {
   @Shadow
   @Final
   transient boolean isClient;

   @Inject(method = "getThunderGradient", at = @At("HEAD"), cancellable = true)
   public void handleFloat(float flt, CallbackInfoReturnable callbackInfoReturnable) {
      if (this.isClient && ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom.getValue()) {
         callbackInfoReturnable.setReturnValue(((ClearBody)ToggleableUtils.ambience.weather.getValue()).thunderGradient);
      }
   }

   @Inject(method = "getRainGradient", at = @At("HEAD"), cancellable = true)
   public void handleFloat1(float flt, CallbackInfoReturnable callbackInfoReturnable) {
      if (this.isClient && ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom.getValue()) {
         callbackInfoReturnable.setReturnValue(((ClearBody)ToggleableUtils.ambience.weather.getValue()).rainGradient);
      }
   }
}
