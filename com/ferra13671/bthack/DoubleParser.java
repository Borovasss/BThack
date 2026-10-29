package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;

class DoubleParser extends CommandArgumentBase {

   @OverriddenMethod
   public Double parse(String string) throws ExceptionSub1 {
      try {
         return Double.parseDouble(string);
      } catch (Exception exception) {
         throw new ExceptionSub1(String.format("'%s' is not double!", string));
      }
   }
}
