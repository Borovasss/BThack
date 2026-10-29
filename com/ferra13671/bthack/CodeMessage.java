package com.ferra13671.bthack;

import com.google.gson.JsonObject;

public record CodeMessage(int code, String message) implements Api7 {

   public int getCode() {
      return this.code;
   }

   public CodeMessage(JsonObject jsonObject) {
      this(jsonObject.getAsJsonObject("data").get("code").getAsInt(), jsonObject.getAsJsonObject("data").get("message").getAsString());
   }

   public String getMessage() {
      return this.message;
   }

   @Override
   public HandshakeBody getHandshake() {
      return HandshakeBody.Frame;
   }

   public static boolean isJsonObject(JsonObject jsonObject) {
      return jsonObject.has("evt") && jsonObject.get("evt").getAsString().equals("ERROR");
   }
}
