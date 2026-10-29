package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;

class IntegerParser extends CommandArgumentBase {
   @OverriddenMethod
   public Integer parse(String string) throws ExceptionSub1 {
      try {
         return Integer.parseInt(string);
      } catch (Exception exception) {
         throw new ExceptionSub1(String.format("'%s' is not integer!", string));
      }
   }

}
