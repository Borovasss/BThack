package com.ferra13671.bthack;

public class ExceptionSub1 extends Exception {
   public ExceptionSub1(String string) {
      this(string, (Throwable)null);
   }

   public ExceptionSub1(Throwable throwable) {
      this(throwable != null ? throwable.getMessage() : null, throwable);
   }

   public ExceptionSub1() {
      this((String)null, (Throwable)null);
   }

   public ExceptionSub1(String string, Throwable throwable) {
      super(string);
      if (throwable != null) {
         super.initCause(throwable);
      }
   }
}
