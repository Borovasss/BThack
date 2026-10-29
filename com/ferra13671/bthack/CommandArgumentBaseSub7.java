package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;

class CommandArgumentBaseSub7 extends CommandArgumentBase {
   @OverriddenMethod
   public String parse(String string) throws ExceptionSub1 {
      OtherClientPlayerEntityHelper obj2 = null;

      for (OtherClientPlayerEntityHelper obj : BThackClient.getInstance().getAttackTracker().getRecentTargets()) {
         if (obj.getString().equals(string)) {
            obj2 = obj;
         }
      }

      if (obj2 != null) {
         throw new ExceptionSub1(String.format("fake player with name '%s' already exists!", string));
      } else {
         return string;
      }
   }

}
