package com.ferra13671.bthack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class Utils2 {
   private static void handleBiConsumer(BiConsumer biConsumer, Object object, List list) {
      biConsumer.accept(object, list);
   }

   public static void getMethods(Class<?> class_, List<Method> list) {
      Collections.addAll(list, class_.getDeclaredMethods());
      Class<?> class_2 = class_.getSuperclass();
      if (!class_2.equals(Object.class)) {
         getMethods(class_2, list);
      }
   }

   public static List<Method> getMethods(Class<?> class_) {
      List<Method> list = new ArrayList<>();
      getMethods(class_, list);
      return list;
   }

   private static void handleConsumer1(Consumer consumer, Object object) {
      consumer.accept(object);
   }

   private static void handleRunnable2(Runnable runnable, Runnable runnable2) {
      runnable.run();
      runnable2.run();
   }

   private static void handleList3(List list) {
   }

   private static void runVoid4() {
   }

   private static void handleConsumer5(Consumer consumer, Consumer consumer2, List list) {
      consumer.accept(list);
      consumer2.accept(list);
   }

   Utils2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static <T> Consumer<List<Object>> buildConsumer(List<T> list, BiConsumer<T, List<Object>> biConsumer) {
      Consumer<List<Object>> chain = Utils2::handleList3;

      for (Object object : list) {
         final Consumer<List<Object>> current = l -> Utils2.handleBiConsumer(biConsumer, object, l);
         final Consumer<List<Object>> prev = chain;
         chain = l -> Utils2.handleConsumer5(current, prev, l);
      }

      return chain;
   }

   public static <T> Runnable buildRunnable(List<T> list, Consumer<T> consumer) {
      Runnable chain = Utils2::runVoid4;

      for (Object object : list) {
         final Runnable current = () -> Utils2.handleConsumer1(consumer, object);
         final Runnable prev = chain;
         chain = () -> Utils2.handleRunnable2(current, prev);
      }

      return chain;
   }
}
