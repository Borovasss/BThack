package com.ferra13671.bthack;

import java.util.function.Function;

public enum CooldownBody implements MinecraftInstance {
   Cooldown(aura -> mc.player.getAttackCooldownProgress(aura.sprintReset.getValue() ? 1.0F : 0.0F)),
   Timer(aura -> (float)aura.helper45.getPassedTime() / ((Float)aura.delay.getValue() * 1000.0F));

   public final Function<Aura, Float> delayFunction;

   private static CooldownBody[] $values() {
      return new CooldownBody[]{Cooldown, Timer};
   }

   CooldownBody(Function<Aura, Float> function) {
      this.delayFunction = function;
   }
}
