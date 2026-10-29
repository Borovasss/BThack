package com.ferra13671.bthack;

public interface EventBus {
   void subscribe(Object var1);

   void register(Event var1);

   void unsubscribe(Object var1);
}
