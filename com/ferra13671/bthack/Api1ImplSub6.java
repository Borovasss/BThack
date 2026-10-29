package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Api1ImplSub6 extends Api1Impl {
   public Api1ImplSub6() {
      super(ValueCodec.valueCodec2);
   }

   protected void fromElement(JsonArray jsonArray) {
      for (JsonElement jsonElement : jsonArray) {
         if (jsonElement instanceof JsonObject jsonObject && jsonObject.has("name") && jsonObject.get("name") instanceof JsonPrimitive jsonPrimitive) {
            for (CategoryInfoApi categoryInfoApi : BThackClient.getInstance().getModuleManager().getCategories()) {
               for (Api4 api4 : BThackClient.getInstance().getModuleManager().containsCategory(categoryInfoApi)) {
                  if (api4.getGetname().equals(jsonPrimitive.getAsString())) {
                     api4.getModuleState().read(new Helper27(jsonObject.get("value")));
                  }
               }
            }
         }
      }
   }

   protected JsonArray toElement() {
      JsonArray jsonArray = new JsonArray();

      for (CategoryInfoApi categoryInfoApi : BThackClient.getInstance().getModuleManager().getCategories()) {
         for (Api4 api4 : BThackClient.getInstance().getModuleManager().containsCategory(categoryInfoApi)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", api4.getGetname());
            jsonObject.add("value", api4.getModuleState().getValueCodec2().getJsonElement());
            jsonArray.add(jsonObject);
         }
      }

      return jsonArray;
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonArray)var1);
   }

   @Override
   protected Object get() {
      return this.toElement();
   }
}
