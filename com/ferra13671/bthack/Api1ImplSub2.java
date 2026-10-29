package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.function.Consumer;

public class Api1ImplSub2 extends Api1Impl {
   final transient Consumer<Consumer<Helper13>> settings;
   final transient Consumer<Helper13> consumer1;

   protected void fromElement(JsonArray jsonArray) {
      jsonArray.forEach(this::handleJsonElement);
   }

   protected JsonArray toElement() {
      JsonArray jsonArray = new JsonArray();
      this.settings.accept((helper13) -> Api1ImplSub2.handleJsonArray1(jsonArray, helper13));
      return jsonArray;
   }

   @Override
   protected Object get() {
      return this.toElement();
   }

   private void handleJsonElement(JsonElement jsonElement) {
      if ((Boolean)ValueCodecUtils.valueCodec3.getEqualsfunction2().apply(jsonElement)) {
         this.consumer1.accept((Helper13)ValueCodecUtils.valueCodec3.getEqualsfunction().apply(jsonElement));
      }
   }

   private static void handleJsonArray1(JsonArray jsonArray, Helper13 helper13) {
      jsonArray.add((JsonElement)ValueCodecUtils.valueCodec3.getEqualsfunction3().apply(helper13));
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonArray)var1);
   }

   public Api1ImplSub2(Consumer<Consumer<Helper13>> consumer, Consumer<Helper13> consumer2) {
      super(ValueCodec.valueCodec2);
      this.settings = consumer;
      this.consumer1 = consumer2;
   }
}
