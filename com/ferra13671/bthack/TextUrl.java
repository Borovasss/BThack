package com.ferra13671.bthack;

import com.google.gson.JsonObject;

public record TextUrl(String text2, String text) {

   public String getText2() {
      return this.text;
   }

   public String getText() {
      return this.text2;
   }

   public JsonObject getDiscordActivity2() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("label", this.text2);
      jsonObject.addProperty("url", this.text);
      return jsonObject;
   }
}
