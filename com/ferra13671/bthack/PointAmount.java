package com.ferra13671.bthack;

public record PointAmount(int x2, int x4, float amount) {

   public float getAmount() {
      return this.amount;
   }

   public int getX2() {
      return this.x2;
   }

   public int getX() {
      return this.x4;
   }
}
