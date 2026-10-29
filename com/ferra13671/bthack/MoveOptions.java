package com.ferra13671.bthack;

import java.util.function.Supplier;

public record MoveOptions(int priority, Supplier onlymovement) {

   public Supplier getOnlymovement() {
      return this.onlymovement;
   }

   public int getPriority() {
      return this.priority;
   }

   public void runVoid() {
      BThackClient.getInstance().getPrioritySortedHelper2().resetIfCurrent(this);
   }
}
