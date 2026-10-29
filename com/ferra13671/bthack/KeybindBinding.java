package com.ferra13671.bthack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public record KeybindBinding(Helper38 bind, Consumer<Boolean> activateconsumer, Supplier<Boolean> currentstatesupplier) {

   public void onOnKey(PressBody pressBody) {
      if (pressBody == PressBody.Press) {
         this.activateconsumer.accept((Boolean)this.bind.getToggleBody().pressStateFunction.apply((Boolean)this.currentstatesupplier.get()));
      } else if (pressBody == PressBody.Release && this.bind.getToggleBody().useRelease) {
         this.activateconsumer.accept(this.bind.getToggleBody().releaseState);
      }
   }

   public Helper38 getBind() {
      return this.bind;
   }

   public Supplier<Boolean> getCurrentstatesupplier() {
      return this.currentstatesupplier;
   }

   public Consumer<Boolean> getActivateconsumer() {
      return this.activateconsumer;
   }
}
