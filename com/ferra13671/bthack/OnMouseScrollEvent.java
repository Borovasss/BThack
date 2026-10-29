package com.ferra13671.bthack;

public class OnMouseScrollEvent extends Event {
   public final transient float amount;

   public OnMouseScrollEvent(float flt) {
      this.amount = flt;
   }
}
