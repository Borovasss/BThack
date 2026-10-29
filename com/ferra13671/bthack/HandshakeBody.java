package com.ferra13671.bthack;

import com.google.gson.JsonObject;
import java.util.function.Function;

public enum HandshakeBody {
   Handshake(jsonObject -> {
      throw new UnsupportedOperationException("Cannot create C2S packet from json.");
   }),
   Frame(jsonObject -> {
      if (DiscordUser.isJsonObject(jsonObject)) {
         return new DiscordUser(jsonObject);
      } else if (CodeMessage.isJsonObject(jsonObject)) {
         return new CodeMessage(jsonObject);
      } else {
         throw new UnsupportedOperationException("Cannot create C2S packet from json.");
      }
   }),
   Close(jsonObject -> new CodeMessage2(jsonObject.get("code").getAsInt(), jsonObject.get("message").getAsString())),
   Ping(jsonObject -> new Api7Impl()),
   Pong(jsonObject -> new Api7Impl());

   public final Function<JsonObject, Api7> toPacketFunction;
   static final HandshakeBody[] VALUES = values();

   private static HandshakeBody[] $values() {
      return new HandshakeBody[]{Handshake, Frame, Close, Ping, Pong};
   }

   public static HandshakeBody valueOf(int num) {
      return VALUES[num];
   }

   HandshakeBody(Function<JsonObject, Api7> function) {
      this.toPacketFunction = function;
   }
}
