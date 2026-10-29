package com.ferra13671.bthack;

import java.util.Collections;
import java.util.HashMap;

public class EventBusImpl implements EventBus {
   final transient HashMap<Class<?>, Helper33> handlers = new HashMap();

   @Override
   public void unsubscribe(Object object) {
      if (object instanceof Helper8) {
         Helper8<?> helper8 = (Helper8<?>)object;
         EventDispatcher.eventDispatcher3.unsubscribe(helper8, this);
      } else if (object instanceof Class) {
         Class<?> class_ = (Class<?>)object;
         EventDispatcher.eventDispatcher2.unsubscribe(class_, this);
      } else {
         EventDispatcher.eventDispatcher1.unsubscribe(object, this);
      }
   }

   @Override
   public void subscribe(Object object) {
      if (object instanceof Helper8) {
         Helper8<?> helper8 = (Helper8<?>)object;
         EventDispatcher.eventDispatcher3.subscribe(helper8, this);
      } else if (object instanceof Class) {
         Class<?> class_ = (Class<?>)object;
         EventDispatcher.eventDispatcher2.subscribe(class_, this);
      } else {
         EventDispatcher.eventDispatcher1.subscribe(object, this);
      }
   }

   @Override
   public void register(Event event) {
      this.getHandlers(event.getClass()).getConsumer().accept(Collections.singletonList(event));
   }

   private static Helper33 getOrCreateHandlerList(Class class_, Class class_2) {
      return new Helper33(class_);
   }

   protected Helper33 getHandlers(Class class_) {
      return this.handlers.computeIfAbsent(class_, (class_2) -> EventBusImpl.getOrCreateHandlerList(class_, class_2));
   }
}
