package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.DayBody;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.world.ClientWorld.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Properties.class)
public class ClientWorld$PropertiesMixin {
   @Inject(method = "getTimeOfDay", at = @At("HEAD"), cancellable = true)
   public void handleArg(CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom9.getValue()) {
         callbackInfoReturnable.setReturnValue((Long)((DayBody)ToggleableUtils.ambience.worldTime.getValue()).timeSupplier.apply(ToggleableUtils.ambience));
      }
   }
}
