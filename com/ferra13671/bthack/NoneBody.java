package com.ferra13671.bthack;

import java.util.function.Function;

public enum NoneBody implements MinecraftInstance {
   None(aura -> true),
   Only(Aura::isBoolean1),
   Smart(aura -> !mc.player.input.playerInput.jump() || (Boolean)Only.canAttackFunction.apply(aura));

   public final Function<Aura, Boolean> canAttackFunction;

   NoneBody(Function<Aura, Boolean> function) {
      this.canAttackFunction = function;
   }

   private static NoneBody[] $values() {
      return new NoneBody[]{None, Only, Smart};
   }
}
