package com.ferra13671.bthack;

public abstract class Event {
   transient boolean boolean1 = false;

   public void runVoid() {
      this.setBoolean(true);
   }

   public void setBoolean(boolean bool) {
      this.boolean1 = bool;
   }

   public boolean getBoolean() {
      return this.boolean1;
   }
}
