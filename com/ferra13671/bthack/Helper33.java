package com.ferra13671.bthack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class Helper33 {
   final transient List<Helper1> subscriptions = new CopyOnWriteArrayList<>();
   transient Consumer<List<Object>> consumer = Helper33::handleList;
   final transient Class eventClass;

   public void setConsumer(Consumer<List<Object>> consumer) {
      this.consumer = consumer;
   }

   public List<Helper1> getSubscriptions() {
      return this.subscriptions;
   }

   public Helper33(Class class_) {
      this.eventClass = class_;
   }

   private static void handleList(List list) {
   }

   public Consumer<List<Object>> getConsumer() {
      return this.consumer;
   }

   public Class getEventClass() {
      return this.eventClass;
   }
}
