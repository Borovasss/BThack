package com.ferra13671.bthack;

public class OnMouseButtonEvent extends Event {
   public final transient int x;
   public final transient PressBody pressBody;

   public OnMouseButtonEvent(int num, PressBody pressBody) {
      this.x = num;
      this.pressBody = pressBody;
   }
}
