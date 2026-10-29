package com.ferra13671.bthack;

import com.google.gson.JsonObject;

public class Helper21ApiImpl implements Helper21Api {
   final transient int int1;
   final transient DiscordActivity discordActivity;

   @Override
   public JsonObject getDiscordActivity2() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("pid", this.int1);
      jsonObject.add("activity", this.discordActivity.getDiscordActivity2());
      JsonObject jsonObject2 = new JsonObject();
      jsonObject2.addProperty("cmd", "SET_ACTIVITY");
      jsonObject2.add("args", jsonObject);
      return jsonObject2;
   }

   @Override
   public HandshakeBody getHandshake() {
      return HandshakeBody.Frame;
   }

   public Helper21ApiImpl(int num, DiscordActivity discordActivity) {
      this.int1 = num;
      this.discordActivity = discordActivity;
   }
}
