package com.ferra13671.bthack;

public class EventSub6 extends Event {
   public static class ChangeLookDirectionEvent extends Event {
      public final transient double double1;
      public final transient double double2;

      public ChangeLookDirectionEvent(double dbl, double dbl2) {
         this.double1 = dbl;
         this.double2 = dbl2;
      }
   }

   public static class MouseTickEvent extends Event {
   }
}
