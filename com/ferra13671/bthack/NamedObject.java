package com.ferra13671.bthack;

public abstract class NamedObject {
   final transient Object object1;

   public NamedObject(Object object) {
      this.object1 = object;
   }

   public abstract void handleApi2Impl(Api2Impl var1, float var2, float var3);

   public abstract String getString1();

   public Object getObject() {
      return this.object1;
   }

   public abstract String getName();
}
