package com.ferra13671.bthack;

import com.google.gson.JsonElement;

public class Api1Impl2<T> implements Api1 {
   final transient String name;
   final transient Class class1;
   final transient ValueCodec valueCodec;
   transient Object value;

   public Api1Impl2(String string, Class class_, ValueCodec valueCodec, Object object) {
      this(string, class_, valueCodec);
      this.value = object;
   }

   @Override
   public void read(Helper27 helper27) {
      helper27.decode(this.valueCodec, this::setValue);
   }

   private void setValue(Object object) {
      this.value = object;
   }

   public String getName() {
      return this.name;
   }

   public Api1Impl2(String string, Class class_, ValueCodec valueCodec) {
      this.name = string;
      this.class1 = class_;
      this.valueCodec = valueCodec;
   }

   @Override
   public Helper27 getValueCodec2() {
      return new Helper27(this.value == null ? null : (JsonElement)this.valueCodec.getEqualsfunction3().apply(this.value));
   }

   public Object getValue() {
      return this.value;
   }

   public void setValue2(Object object) {
      this.value = object;
   }

   @Override
   public ValueCodec getValueCodec() {
      return this.valueCodec;
   }
}
