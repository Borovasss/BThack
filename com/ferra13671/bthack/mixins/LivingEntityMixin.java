package com.ferra13671.bthack.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityMixin {
   @Invoker("tryUseDeathProtector")
   boolean isClass_1282(DamageSource var1);

   @Accessor("jumpingCooldown")
   void handleInt1(int var1);

   @Accessor("jumpingCooldown")
   int getInt2();
}
