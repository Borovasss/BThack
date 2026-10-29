package com.ferra13671.bthack;

import java.util.function.Consumer;

public class Helper8<T> {
   final transient Class<T> class1;
   final transient Consumer<T> consumer1;

   public Helper8(Class<T> class_, Consumer<T> consumer) {
      this.class1 = class_;
      this.consumer1 = consumer;
   }

   public Class<T> getEventClass() {
      return this.class1;
   }

   public Consumer<T> getConsumer() {
      return this.consumer1;
   }
}
