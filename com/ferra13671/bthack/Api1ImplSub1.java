package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.List;

public class Api1ImplSub1 extends Api1Impl {
   final transient List<Setting<?>> settings;

   @Override
   protected Object get() {
      return this.toElement();
   }

   protected JsonArray toElement() {
      JsonArray jsonArray = new JsonArray();

      for (Setting<?> setting : this.settings) {
         JsonObject jsonObject = new JsonObject();
         jsonObject.addProperty("name", setting.getGetname());
         jsonObject.add("value", setting.toJson());
         jsonArray.add(jsonObject);
      }

      return jsonArray;
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonArray)var1);
   }

   protected void fromElement(JsonArray jsonArray) {
      HashMap<String, JsonElement> hashMap = new HashMap<>();

      for (JsonElement jsonElement : jsonArray) {
         if (jsonElement instanceof JsonObject jsonObject && jsonObject.has("name") && jsonObject.get("name").isJsonPrimitive() && jsonObject.has("value")) {
            hashMap.put(jsonObject.getAsJsonPrimitive("name").getAsString(), jsonObject.get("value"));
         }
      }

      for (Setting<?> setting : this.settings) {
         if (hashMap.containsKey(setting.getGetname())) {
            setting.fromJson(new Helper27(hashMap.get(setting.getGetname())));
         }
      }
   }

   public Api1ImplSub1(List<Setting<?>> list) {
      super(ValueCodec.valueCodec2);
      this.settings = list;
   }
}
