package com.ferra13671.bthack;

public class Manager {
   transient boolean atlas = false;

   public boolean isAtlas() {
      return this.atlas;
   }

   public void getAtlas2() {
      this.getAtlas3();
      this.atlas = true;
   }

   protected void getAtlas3() {
      if (this.atlas) {
         throw new IllegalStateException("Manager already frozen");
      }
   }

   protected void getAtlas() {
      if (!this.atlas) {
         throw new IllegalStateException("Manager has not been frozen.");
      }
   }
}
