package com.ferra13671.bthack;

import java.util.Collections;
import java.util.List;

@ModuleInfo(name = "Auto 2FA", category = "misc")
public class Auto2FA extends ModuleSub1 {
   private static String getString2() {
      return "2fa "
         + BThackClient.getInstance()
            .getAuthSecrets()
            .getHelper23Sub1()
            .getHelper2(Helper2.withString((String)BThackClient.getInstance().getAuthSecrets().getAuto2faSecrets().get(mc.getSession().getUsername())));
   }

   @Override
   protected List applyList() {
      return Collections.singletonList(new ModuleSub1.TriggerOptions(List.of("/2fa"), Auto2FA::getString2));
   }
}
