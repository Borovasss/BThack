package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.function.Supplier;

public class Api1ImplSub3 extends Api1Impl {
   final transient Supplier<List<Api1Impl2<?>>> settings;

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonArray)var1);
   }

   protected void fromElement(JsonArray jsonArray) {
      jsonArray.forEach(this::handleJsonElement);
   }

   private void handleJsonObject(JsonObject jsonObject, String string) {
      for (Api1Impl2<?> api1Impl2 : this.settings.get()) {
         if (api1Impl2.getName().equals(string)) {
            api1Impl2.read(new Helper27(jsonObject.get("property")));
            break;
         }
      }
   }

   protected JsonArray toElement() {
      JsonArray jsonArray = new JsonArray();

      for (Api1Impl2<?> api1Impl2 : this.settings.get()) {
         JsonObject jsonObject = new JsonObject();
         jsonObject.addProperty("name", api1Impl2.getName());
         jsonObject.add("property", api1Impl2.getValueCodec2().getJsonElement());
         jsonArray.add(jsonObject);
      }

      return jsonArray;
   }

   private void handleJsonElement(JsonElement jsonElement) {
      if (jsonElement instanceof JsonObject jsonObject) {
         new Helper27(jsonObject.get("name")).decode(ValueCodec.valueCodec4, (string) -> this.handleJsonObject(jsonObject, string));
      }
   }

   public Api1ImplSub3(Supplier<List<Api1Impl2<?>>> supplier) {
      super(ValueCodec.valueCodec2);
      this.settings = supplier;
   }

   @Override
   protected Object get() {
      return this.toElement();
   }
}
