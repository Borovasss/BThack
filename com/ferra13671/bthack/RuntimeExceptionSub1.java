package com.ferra13671.bthack;

public class RuntimeExceptionSub1 extends RuntimeException {
   public RuntimeExceptionSub1() {
      this((String)null, (Throwable)null);
   }

   public RuntimeExceptionSub1(Throwable throwable) {
      this(throwable != null ? throwable.getMessage() : null, throwable);
   }

   public RuntimeExceptionSub1(String string, Throwable throwable) {
      super(string);
      if (throwable != null) {
         super.initCause(throwable);
      }
   }

   public RuntimeExceptionSub1(String string) {
      this(string, (Throwable)null);
   }
}
