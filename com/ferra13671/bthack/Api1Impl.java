package com.ferra13671.bthack;

import com.google.gson.JsonElement;

public abstract class Api1Impl implements Api1 {
   final transient ValueCodec valueCodec;

   @Override
   public ValueCodec getValueCodec() {
      return this.valueCodec;
   }

   public Api1Impl(ValueCodec valueCodec) {
      this.valueCodec = valueCodec;
   }

   protected abstract void set(Object var1);

   @Override
   public final Helper27 getValueCodec2() {
      Object object = this.get();
      return new Helper27((JsonElement)this.valueCodec.getEqualsfunction3().apply(object));
   }

   @Override
   public final void read(Helper27 helper27) {
      helper27.decode(this.valueCodec, this::set);
   }

   protected abstract Object get();
}
