package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin2 {
   @Unique
   transient boolean boolean1 = false;

   @Inject(method = "isGliding", at = @At("TAIL"), cancellable = true)
   public void setBoolean(CallbackInfoReturnable callbackInfoReturnable) {
      if ((Object)this == MinecraftClient.getInstance().player) {
         boolean bool = (Boolean)callbackInfoReturnable.getReturnValue();
         if (!bool && this.boolean1 && ToggleableUtils.elytraRecast.isEnabled()) {
            callbackInfoReturnable.setReturnValue(ToggleableUtils.elytraRecast.isLegitrecast());
         }

         this.boolean1 = bool;
      }
   }

   @Inject(method = "getEffectFadeFactor", at = @At("HEAD"), cancellable = true)
   public void handleClass_6880(RegistryEntry registryEntry, float flt, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.nausea.getValue() && registryEntry == StatusEffects.NAUSEA) {
         callbackInfoReturnable.setReturnValue(0.0F);
      }
   }
}
