package com.ferra13671.bthack;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class EventDispatcherSub2 extends EventDispatcher<Object> {
   @Override
   public void unsubscribe(Object object, EventBusImpl eventBusImpl) {
      Utils2.getMethods(object.getClass()).forEach((method) -> this.handleEventBusImpl(eventBusImpl, object, method));
   }

   @Override
   public void subscribe(Object object, EventBusImpl eventBusImpl) {
      Utils2.getMethods(object.getClass()).forEach((method) -> this.handleEventBusImpl2(eventBusImpl, object, method));
   }

   private void handleEventBusImpl(EventBusImpl eventBusImpl, Object object, Method method) {
      if (!Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(Api8.class)) {
         Api8 api8 = method.getAnnotation(Api8.class);
         Class<? extends Event> class_ = api8.event()[0];
         this.handleMethod3(method, eventBusImpl.getHandlers(class_), object);
      }
   }

   private void handleEventBusImpl2(EventBusImpl eventBusImpl, Object object, Method method) {
      if (!Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(Api8.class)) {
         Api8 api8 = method.getAnnotation(Api8.class);
         Class<? extends Event> class_ = api8.event()[0];
         this.handleMethod4(method, eventBusImpl.getHandlers(class_), object);
      }
   }
}
