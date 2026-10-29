package com.ferra13671.bthack;

import java.lang.reflect.Method;

final class EventDispatcherSub1 extends EventDispatcher<Helper8> {
   private void handleEventBusImpl2(EventBusImpl eventBusImpl, Helper8 helper8, Method method) {
      if (method.getParameterTypes().length > 0) {
         try {
            method.setAccessible(true);
            this.handleMethod4(method, eventBusImpl.getHandlers(helper8.getEventClass()), helper8.getConsumer());
         } catch (Exception var5) {
         }
      }
   }

   public void subscribe(Helper8 helper8, EventBusImpl eventBusImpl) {
      Utils2.getMethods(helper8.getConsumer().getClass()).forEach((method) -> this.handleEventBusImpl2(eventBusImpl, helper8, method));
   }

   private void handleEventBusImpl(EventBusImpl eventBusImpl, Helper8 helper8, Method method) {
      if (method.getParameterTypes().length > 0) {
         try {
            this.handleMethod3(method, eventBusImpl.getHandlers(helper8.getEventClass()), helper8.getConsumer());
         } catch (Exception var5) {
         }
      }
   }

   public void unsubscribe(Helper8 helper8, EventBusImpl eventBusImpl) {
      Utils2.getMethods(helper8.getConsumer().getClass()).forEach((method) -> this.handleEventBusImpl(eventBusImpl, helper8, method));
   }

}
