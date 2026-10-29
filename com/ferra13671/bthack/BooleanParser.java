package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import java.util.Collection;
import java.util.List;

class BooleanParser extends CommandArgumentBase {
   @OverriddenMethod
   @Override
   public Collection getExamples() {
      return List.of("true", "false");
   }

   @OverriddenMethod
   public Boolean parse(String string) throws ExceptionSub1 {
      if (this.getExamples().contains(string)) {
         return Boolean.parseBoolean(string);
      } else {
         throw new ExceptionSub1(String.format("'%s' is not boolean!", string));
      }
   }

}
