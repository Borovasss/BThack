package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public record SizeCounter(String id, int currentsize, int currentsize2) {

   public JsonObject getDiscordActivity2() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("id", this.id);
      JsonArray jsonArray = new JsonArray();
      jsonArray.add(this.currentsize);
      jsonArray.add(this.currentsize2);
      jsonObject.add("size", jsonArray);
      return jsonObject;
   }

   public int getCurrentsize() {
      return this.currentsize;
   }

   public int getCurrentsize2() {
      return this.currentsize2;
   }
}
