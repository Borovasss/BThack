package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import com.ferra13671.bthack.ToggleableUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.collection.WeightedPool;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
   @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
   public void handleString(String string, CallbackInfo callbackInfo) {
      SoundManager.handleString4(string, callbackInfo);
   }

   @WrapWithCondition(
      method = "onExplosion",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V")
   )
   public boolean isClass_6381(
      ClientWorld clientWorld, ParticleEffect particleEffect, double dbl, double dbl2, double dbl3, double dbl4, double dbl5, double dbl6
   ) {
      return !ToggleableUtils.noRender.isEnabled() || !(Boolean)ToggleableUtils.noRender.explosions.getValue();
   }

   @WrapWithCondition(
      method = "onExplosion",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/world/ClientWorld;addBlockParticleEffects(Lnet/minecraft/util/math/Vec3d;FILnet/minecraft/util/collection/WeightedPool;)V"
      )
   )
   public boolean isClass_6382(ClientWorld clientWorld, Vec3d vec3d, float flt, int num, WeightedPool weightedPool) {
      return !ToggleableUtils.noRender.isEnabled() || !(Boolean)ToggleableUtils.noRender.explosions.getValue();
   }
}
