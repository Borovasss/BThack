package com.ferra13671.bthack;

import com.google.gson.JsonObject;

public class Helper21 implements Helper21Api {
   final transient long long1;
   final transient int int1;

   @Override
   public HandshakeBody getHandshake() {
      return HandshakeBody.Handshake;
   }

   public Helper21(long lng, int num) {
      this.long1 = lng;
      this.int1 = num;
   }

   @Override
   public JsonObject getDiscordActivity2() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("v", this.int1);
      jsonObject.addProperty("client_id", Long.toString(this.long1));
      return jsonObject;
   }
}
