package com.ferra13671.bthack;

import com.google.gson.JsonObject;

public record DiscordUser(DiscordProfile discorduser) implements Api7 {

   public DiscordProfile getDiscorduser() {
      return this.discorduser;
   }

   public static boolean isJsonObject(JsonObject jsonObject) {
      return jsonObject.has("cmd") && jsonObject.get("cmd").getAsString().equals("DISPATCH");
   }

   public DiscordUser(JsonObject jsonObject) {
      this(DiscordProfile.withJsonObject(jsonObject.getAsJsonObject("data").getAsJsonObject("user")));
   }

   @Override
   public HandshakeBody getHandshake() {
      return HandshakeBody.Frame;
   }
}
