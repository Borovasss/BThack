package com.ferra13671.bthack;

import java.util.function.BiFunction;
import net.minecraft.entity.LivingEntity;

public enum DistanceBody implements MinecraftInstance {
   Distance(
      (livingEntity, aura) -> (int)(
         ((Float)aura.targetRange.getValue() - EntityUtils.getClass_12975(mc.player, livingEntity.getBoundingBox().getCenter().toVector3f())) * 1000.0F
      )
   ),
   HealthMax((livingEntity, aura) -> (int)(livingEntity.getHealth() * 1000.0F)),
   HealthMin((livingEntity, aura) -> 10000000 - (Integer)HealthMax.priorityFunction.apply(livingEntity, aura));

   public final BiFunction<LivingEntity, Aura, Integer> priorityFunction;

   private static DistanceBody[] $values() {
      return new DistanceBody[]{Distance, HealthMax, HealthMin};
   }

   DistanceBody(BiFunction<LivingEntity, Aura, Integer> biFunction) {
      this.priorityFunction = biFunction;
   }
}
