package com.ferra13671.bthack;

public class OnKeyEvent extends Event {
   public final transient int int1;
   public final transient PressBody pressBody;

   public OnKeyEvent(int num, PressBody pressBody) {
      this.int1 = num;
      this.pressBody = pressBody;
   }
}
