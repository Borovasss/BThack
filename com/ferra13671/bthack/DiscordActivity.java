package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public record DiscordActivity(
   String details4,
   String details6,
   String details,
   String details2,
   String details3,
   String details5,
   Long starttime,
   SizeCounter party,
   TextUrl button1,
   TextUrl button12
) {

   public SizeCounter getParty() {
      return this.party;
   }

   public DiscordActivity withString2(String string) {
      return this.details3 == string
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, this.details2, string, this.details5, this.starttime, this.party, this.button1, this.button12
         );
   }

   private JsonObject getDiscordActivity() {
      JsonObject jsonObject = new JsonObject();
      if (this.starttime != null) {
         jsonObject.addProperty("start", this.starttime);
      }

      return jsonObject;
   }

   private JsonObject getDiscordActivity3() {
      JsonObject jsonObject = new JsonObject();
      if (this.details != null) {
         jsonObject.addProperty("large_image", this.details);
      }

      if (this.details2 != null) {
         jsonObject.addProperty("large_text", this.details2);
      }

      if (this.details3 != null) {
         jsonObject.addProperty("small_image", this.details3);
      }

      if (this.details5 != null) {
         jsonObject.addProperty("small_text", this.details5);
      }

      return jsonObject;
   }

   public DiscordActivity withString3(String string) {
      return this.details5 == string
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, this.details2, this.details3, string, this.starttime, this.party, this.button1, this.button12
         );
   }

   public DiscordActivity withString6(String string) {
      return this.details == string
         ? this
         : new DiscordActivity(
            this.details4, this.details6, string, this.details2, this.details3, this.details5, this.starttime, this.party, this.button1, this.button12
         );
   }

   public String getDetails() {
      return this.details2;
   }

   public DiscordActivity withString(String string) {
      return this.details4 == string
         ? this
         : new DiscordActivity(
            string, this.details6, this.details, this.details2, this.details3, this.details5, this.starttime, this.party, this.button1, this.button12
         );
   }

   public JsonObject getDiscordActivity2() {
      JsonObject jsonObject = new JsonObject();
      if (this.details4 != null) {
         jsonObject.addProperty("details", this.details4);
      }

      if (this.details6 != null) {
         jsonObject.addProperty("state", this.details6);
      }

      jsonObject.add("assets", this.getDiscordActivity3());
      jsonObject.add("timestamps", this.getDiscordActivity());
      if (this.party != null) {
         jsonObject.add("party", this.party.getDiscordActivity2());
      }

      if (this.button1 != null || this.button12 != null) {
         jsonObject.add("buttons", this.getButton13());
      }

      jsonObject.addProperty("instance", false);
      return jsonObject;
   }

   public TextUrl getButton12() {
      return this.button12;
   }

   public TextUrl getButton1() {
      return this.button1;
   }

   public DiscordActivity withString5(String string) {
      return this.details2 == string
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, string, this.details3, this.details5, this.starttime, this.party, this.button1, this.button12
         );
   }

   public DiscordActivity withString4(String string) {
      return this.details6 == string
         ? this
         : new DiscordActivity(
            this.details4, string, this.details, this.details2, this.details3, this.details5, this.starttime, this.party, this.button1, this.button12
         );
   }

   public String getDetails6() {
      return this.details4;
   }

   public String getDetails5() {
      return this.details;
   }

   public DiscordActivity withLong(Long long_) {
      return this.starttime == long_
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, this.details2, this.details3, this.details5, long_, this.party, this.button1, this.button12
         );
   }

   public String getDetails4() {
      return this.details6;
   }

   public String getDetails2() {
      return this.details3;
   }

   public DiscordActivity withSizeCounter(SizeCounter sizeCounter) {
      return this.party == sizeCounter
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, this.details2, this.details3, this.details5, this.starttime, sizeCounter, this.button1, this.button12
         );
   }

   public String getDetails3() {
      return this.details5;
   }

   public DiscordActivity withTextUrl(TextUrl textUrl) {
      return this.button12 == textUrl
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, this.details2, this.details3, this.details5, this.starttime, this.party, this.button1, textUrl
         );
   }

   private JsonArray getButton13() {
      JsonArray jsonArray = new JsonArray();
      if (this.button1 != null) {
         jsonArray.add(this.button1.getDiscordActivity2());
      }

      if (this.button12 != null) {
         jsonArray.add(this.button12.getDiscordActivity2());
      }

      return jsonArray;
   }

   public DiscordActivity withTextUrl2(TextUrl textUrl) {
      return this.button1 == textUrl
         ? this
         : new DiscordActivity(
            this.details4, this.details6, this.details, this.details2, this.details3, this.details5, this.starttime, this.party, textUrl, this.button12
         );
   }

   public Long getStarttime() {
      return this.starttime;
   }
}
