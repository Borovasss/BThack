package com.ferra13671.bthack;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class EventDispatcherSub3 extends EventDispatcher<Class> {
   public void unsubscribe(Class class_, EventBusImpl eventBusImpl) {
      Utils2.getMethods(class_).forEach((method) -> this.handleEventBusImpl(eventBusImpl, class_, method));
   }

   public void subscribe(Class class_, EventBusImpl eventBusImpl) {
      Utils2.getMethods(class_).forEach((method) -> this.handleEventBusImpl2(eventBusImpl, class_, method));
   }

   private void handleEventBusImpl2(EventBusImpl eventBusImpl, Class class_2, Method method) {
      if (Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(Api8.class)) {
         Api8 api8 = method.getAnnotation(Api8.class);
         Class<? extends Event> class_ = api8.event()[0];
         this.handleMethod4(method, eventBusImpl.getHandlers(class_), class_2);
      }
   }


   private void handleEventBusImpl(EventBusImpl eventBusImpl, Class class_2, Method method) {
      if (Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(Api8.class)) {
         Api8 api8 = method.getAnnotation(Api8.class);
         Class<? extends Event> class_ = api8.event()[0];
         this.handleMethod3(method, eventBusImpl.getHandlers(class_), class_2);
      }
   }
}
