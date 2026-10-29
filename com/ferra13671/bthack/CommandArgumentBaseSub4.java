package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.mojang.brigadier.arguments.ArgumentType;

class CommandArgumentBaseSub4 extends CommandArgumentBase {

   @OverriddenMethod
   public String parse(String string) {
      return null;
   }

   @OverriddenMethod
   @Override
   public ArgumentType applyArgumentType() {
      return new Helper47(this);
   }
}
