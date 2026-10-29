package com.ferra13671.bthack;

import java.util.Map;

public class Api1ImplSub4 extends Api1Impl {
   final transient Map map1;

   protected Map toElement() {
      return this.map1;
   }

   @Override
   protected Object get() {
      return this.toElement();
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((Map)var1);
   }

   protected void fromElement(Map map) {
      this.map1.clear();
      this.map1.putAll(map);
   }

   public Api1ImplSub4(Map map) {
      super(ValueCodecUtils.valueCodec4);
      this.map1 = map;
   }
}
