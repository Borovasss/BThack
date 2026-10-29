package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ClearBody;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WeatherRendering;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticlesMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WeatherRendering.class)
public class WeatherRenderingMixin {
   @Inject(method = "addParticlesAndSound", at = @At("HEAD"), cancellable = true)
   public void handleClass_638(ClientWorld clientWorld, Camera camera, int num, ParticlesMode particlesMode, int num2, CallbackInfo callbackInfo) {
      if (ToggleableUtils.ambience.isEnabled()
         && (Boolean)ToggleableUtils.ambience.custom.getValue()
         && ((ClearBody)ToggleableUtils.ambience.weather.getValue()).clear) {
         callbackInfo.cancel();
      }
   }
}
