package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Api1ImplSub5 extends Api1Impl {
   public Api1ImplSub5() {
      super(ValueCodec.valueCodec2);
   }

   protected JsonArray toElement() {
      JsonArray jsonArray = new JsonArray();

      for (Hud hud : BThackClient.getInstance().getManagerHelper().getElements()) {
         JsonObject jsonObject = new JsonObject();
         jsonObject.addProperty("name", hud.getGetname());
         jsonObject.add("value", hud.getModuleState().getValueCodec2().getJsonElement());
         jsonArray.add(jsonObject);
      }

      return jsonArray;
   }

   @Override
   protected Object get() {
      return this.toElement();
   }

   protected void fromElement(JsonArray jsonArray) {
      for (JsonElement jsonElement : jsonArray) {
         if (jsonElement instanceof JsonObject jsonObject && jsonObject.has("name") && jsonObject.get("name") instanceof JsonPrimitive jsonPrimitive) {
            for (Hud hud : BThackClient.getInstance().getManagerHelper().getElements()) {
               if (hud.getGetname().equals(jsonPrimitive.getAsString())) {
                  hud.getModuleState().read(new Helper27(jsonObject.get("value")));
               }
            }
         }
      }
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonArray)var1);
   }
}
