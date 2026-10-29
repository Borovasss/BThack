package com.ferra13671.bthack;

import java.util.function.Supplier;

public record RotationOptions(int priority, Supplier rotategetter3, Supplier rotategetter2, Supplier rotategetter) {

   public Supplier getRotategetter2() {
      return this.rotategetter2;
   }

   public int getPriority() {
      return this.priority;
   }

   public Supplier getRotategetter() {
      return this.rotategetter;
   }

   public Supplier getRotategetter3() {
      return this.rotategetter3;
   }
}
