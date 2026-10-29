package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import java.util.Collection;

class FakePlayerArgument extends CommandArgumentBase {
   @OverriddenMethod
   @Override
   public Collection getExamples() {
      return BThackClient.getInstance().getAttackTracker().getRecentTargets().stream().map(OtherClientPlayerEntityHelper::getString).toList();
   }


   @OverriddenMethod
   public OtherClientPlayerEntityHelper parse(String string) throws ExceptionSub1 {
      OtherClientPlayerEntityHelper obj2 = null;

      for (OtherClientPlayerEntityHelper obj : BThackClient.getInstance().getAttackTracker().getRecentTargets()) {
         if (obj.getString().equals(string)) {
            obj2 = obj;
         }
      }

      if (obj2 == null) {
         throw new ExceptionSub1(String.format("fake player with name '%s' does not exists!", string));
      } else {
         return obj2;
      }
   }
}
