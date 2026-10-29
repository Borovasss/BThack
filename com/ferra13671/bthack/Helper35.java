package com.ferra13671.bthack;

import java.util.function.Consumer;

public class Helper35<T> {
   transient T value;
   final transient Consumer<T> consumer1;

   public void setValue2(T object) {
      if (this.value != object && !this.value.equals(object)) {
         this.value = object;
         this.consumer1.accept(object);
      }
   }

   public Helper35(T object, Consumer<T> consumer) {
      this.value = object;
      this.consumer1 = consumer;
   }

   public Helper35(Consumer<T> consumer) {
      this(null, consumer);
   }

   public T getValue() {
      return this.value;
   }
}
