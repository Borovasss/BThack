package com.ferra13671.bthack;

import java.lang.reflect.Method;

public class Helper1 {
   final transient Object object;
   final transient Method method;
   final transient boolean noArgs;

   public Method getMethod() {
      return this.method;
   }

   public boolean isNoArgs() {
      return this.noArgs;
   }

   public Object getObject() {
      return this.object;
   }

   public Helper1(Object object, Method method, boolean bool) {
      this.object = object;
      this.method = method;
      this.noArgs = bool;
   }
}
