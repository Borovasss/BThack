package com.ferra13671.bthack;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class EventDispatcher<T> {
   public static final transient EventDispatcher eventDispatcher1 = new EventDispatcherSub2();
   public static final transient EventDispatcher eventDispatcher2 = new EventDispatcherSub3();
   public static final transient EventDispatcher eventDispatcher3 = new EventDispatcherSub1();

   public abstract void unsubscribe(T var1, EventBusImpl var2);

   private static boolean isObject(Object object, Method method, Helper1 helper1) {
      return helper1.getObject().equals(object) && helper1.getMethod().equals(method);
   }

   public abstract void subscribe(T var1, EventBusImpl var2);

   protected void handleHelper331(Helper33 helper33) {
      helper33.setConsumer(Utils2.buildConsumer(helper33.getSubscriptions(), EventDispatcher::handleHelper12));
   }

   private static void handleHelper12(Helper1 helper1, List list) {
      try {
         if (helper1.isNoArgs()) {
            helper1.getMethod().invoke(helper1.getObject());
         } else {
            helper1.getMethod().invoke(helper1.getObject(), list.toArray());
         }
      } catch (Exception exception) {
         throw new RuntimeExceptionSub1(exception);
      }
   }

   protected void handleMethod3(Method method, Helper33 helper33, Object object) {
      helper33.getSubscriptions().removeIf((helper1) -> EventDispatcher.isObject(object, method, helper1));
      this.handleHelper331(helper33);
   }

   protected void handleMethod4(Method method, Helper33 helper33, Object object) {
      boolean bool = true;

      for (Helper1 helper1 : helper33.getSubscriptions()) {
         if (helper1.getMethod().equals(method) && helper1.getObject().equals(object)) {
            bool = false;
            break;
         }
      }

      if (bool) {
         helper33.getSubscriptions().add(new Helper1(object, method, method.getParameterTypes().length == 0));
      }

      helper33.getSubscriptions().sort(Comparator.comparing(EventDispatcher::getHelper15));
      Collections.reverse(helper33.getSubscriptions());
      this.handleHelper331(helper33);
   }

   private static Integer getHelper15(Helper1 helper1) {
      return helper1.getMethod().isAnnotationPresent(Api8.class) ? helper1.getMethod().getAnnotation(Api8.class).priority() : 0;
   }
}
