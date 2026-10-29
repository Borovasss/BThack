package com.ferra13671.bthack;

import java.util.Collection;

class ModuleArgument extends CommandArgumentBase {
   @Override
   public Collection getExamples() {
      return BThackClient.getInstance().getModuleManager().getModuleSet().stream().map(Api4::getGetname).toList();
   }

   public Toggleable parse(String string) throws ExceptionSub1 {
      Toggleable toggleable2 = null;

      for (Toggleable toggleable : BThackClient.getInstance().getModuleManager().getModuleSet()) {
         if (toggleable.getGetname().equals(string)) {
            toggleable2 = toggleable;
         }
      }

      if (toggleable2 == null) {
         throw new ExceptionSub1(String.format("module with name '%s' does not exists!", string));
      } else {
         return toggleable2;
      }
   }
}
