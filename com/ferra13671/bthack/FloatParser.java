package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;

class FloatParser extends CommandArgumentBase {
   @OverriddenMethod
   public Float parse(String string) throws ExceptionSub1 {
      try {
         return Float.parseFloat(string);
      } catch (Exception exception) {
         throw new ExceptionSub1(String.format("'%s' is not float!", string));
      }
   }

}
