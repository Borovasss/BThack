package com.ferra13671.bthack;

import com.google.gson.JsonObject;
import java.net.URI;

public record DiscordProfile(String id, String id4, String id3, String id2, boolean bot, String id5, int premium_type) {

   public int getPremium_type() {
      return this.premium_type;
   }

   public boolean isBot() {
      return this.bot;
   }

   public String getId4() {
      return this.id4;
   }

   public String getId() {
      return this.id5;
   }

   public String getId2() {
      return this.id3;
   }

   public InputStreamAvatarType getId22() {
      try {
         return this.id2.startsWith("a_")
            ? new InputStreamAvatarType(
               new URI(String.format("https://cdn.discordapp.com/avatars/%s/%s.gif", this.id, this.id2)).toURL().openStream(), ImageBody.Gif
            )
            : new InputStreamAvatarType(
               new URI(String.format("https://cdn.discordapp.com/avatars/%s/%s.png", this.id, this.id2)).toURL().openStream(), ImageBody.Image
            );
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }
   }

   public String getId3() {
      return this.id2;
   }

   public static DiscordProfile withJsonObject(JsonObject jsonObject) {
      return new DiscordProfile(
         jsonObject.get("id").getAsString(),
         jsonObject.get("username").getAsString(),
         jsonObject.get("discriminator").getAsString(),
         jsonObject.get("avatar").getAsString(),
         jsonObject.get("bot").getAsBoolean(),
         jsonObject.get("flags").getAsString(),
         jsonObject.get("premium_type").getAsInt()
      );
   }
}
