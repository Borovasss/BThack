package com.ferra13671.bthack;

import java.util.function.Supplier;

public record PriorityTimer(int priority, Supplier timerspeedsupplier) {

   public Supplier getTimerspeedsupplier() {
      return this.timerspeedsupplier;
   }

   public int getPriority() {
      return this.priority;
   }
}
