package com.ferra13671.bthack;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class Helper23Sub1 extends Helper23 {
   final transient Clock clock1 = Clock.systemUTC();

   public String getHelper2(Helper2 helper2) {
      return (String)this.withHelper21(helper2).getFirst();
   }

   public List withHelper21(Helper2 helper2) {
      return this.withHelper22(helper2, 0, 0);
   }

   public List withHelper22(Helper2 helper2, int num, int num2) {
      long lng2 = this.clock1.millis() / 1000L / 30L;
      List<String> list = new ArrayList<>();

      for (long lng = lng2 - num; lng <= lng2 + num2; lng++) {
         list.add(this.getByte1(helper2.value, lng));
      }

      return list;
   }

   public Helper23Sub1() {
      super(Helper23.SixBody.SIX, Helper23.Sha1Body.SHA1);
   }
}
