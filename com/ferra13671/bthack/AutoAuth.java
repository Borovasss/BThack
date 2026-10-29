package com.ferra13671.bthack;

import java.util.List;

@ModuleInfo(name = "Auto Auth", category = "misc")
public class AutoAuth extends ModuleSub1 {
   private static String getString() {
      return "l " + (String)BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets().get(mc.getSession().getUsername());
   }

   @Override
   protected List applyList() {
      return List.of(
         new ModuleSub1.TriggerOptions(List.of("/reg", "/register"), AutoAuth::getString2),
         new ModuleSub1.TriggerOptions(List.of("/l", "/login"), AutoAuth::getString)
      );
   }

   private static String getString2() {
      return "reg " + (String)BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets().get(mc.getSession().getUsername());
   }
}
