package com.ferra13671.bthack;

import java.util.HashMap;
import java.util.Map;

public class Helper42 {
   final transient Helper23Sub1 secretCodec = new Helper23Sub1();
   final transient Map autoAuthSecrets = new HashMap();
   final transient Map auto2faSecrets = new HashMap();

   public Map getAuto2faSecrets() {
      return this.auto2faSecrets;
   }

   public Map getAutoAuthSecrets() {
      return this.autoAuthSecrets;
   }

   public Helper23Sub1 getHelper23Sub1() {
      return this.secretCodec;
   }
}
