package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public class Helper15 {
   final transient List<Runnable> hooks = new ArrayList<>();

   private void runShutdown() {
      this.hooks.forEach(Runnable::run);
   }

   public void addHook(Runnable runnable) {
      this.hooks.addLast(runnable);
   }

   public Helper15() {
      Runtime.getRuntime().addShutdownHook(Thread.ofPlatform().unstarted(this::runShutdown));
   }
}
