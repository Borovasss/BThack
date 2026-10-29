package com.ferra13671.bthack;

import java.io.Closeable;

public abstract class Helper20 implements Closeable {
   protected final transient EventBus eventBus;

   public void onClick(XYButton xYButton) {
   }

   public void handlePointAmount(PointAmount pointAmount) {
   }

   public void setSize(int num, int num2) {
   }

   @Override
   public void close() {
      this.eventBus.unsubscribe(this);
   }

   public void onKeyPress(int num) {
   }

   public void close6() {
   }

   public Helper20(EventBus eventBus) {
      this.eventBus = eventBus;
      this.eventBus.subscribe(this);
   }

   public void onCharTyped(int num) {
   }

   public boolean isHovered(int num, int num2) {
      return false;
   }

   public abstract void setPosition(int var1, int var2);

   public void onMouseMove(XYButton xYButton) {
   }
}
