package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ClearBody;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Precipitation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
   @Inject(method = "hasPrecipitation", at = @At("HEAD"), cancellable = true)
   public void handleArg(CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom.getValue()) {
         callbackInfoReturnable.setReturnValue(!((ClearBody)ToggleableUtils.ambience.weather.getValue()).clear);
      }
   }

   @Inject(method = "getPrecipitation", at = @At("HEAD"), cancellable = true)
   public void handleClass_23381(BlockPos blockPos, int num, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom.getValue()) {
         callbackInfoReturnable.setReturnValue(((ClearBody)ToggleableUtils.ambience.weather.getValue()).precipitation);
      }
   }
}
