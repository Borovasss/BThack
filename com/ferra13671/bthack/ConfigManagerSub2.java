package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;

public class ConfigManagerSub2 extends ConfigManager {
   final transient HashMap<String, Api1> map;

   @Override
   public void handleOutputStream4(OutputStream outputStream) {
      JsonObject jsonObject = new JsonObject();
      this.map.forEach((string, api1) -> ConfigManagerSub2.handleJsonObject1(jsonObject, string, api1));

      try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
         outputStreamWriter.write(gson1.toJson(jsonObject));
      } catch (Exception exception) {
         new Exception("Cannot save config. Reason: " + exception.getMessage()).printStackTrace();
      }
   }

   private static void handleJsonObject1(JsonObject jsonObject, String string, Api1 api1) {
      JsonElement jsonElement = api1.getValueCodec2().getJsonElement();
      if (jsonElement != null) {
         jsonObject.add(string, jsonElement);
      }
   }

   @Override
   public void fromJson(InputStream inputStream) {
      JsonElement jsonElement = this.withInputStream3(inputStream).getJsonElement();
      if (jsonElement != null && (Boolean)ValueCodec.valueCodec1.getEqualsfunction2().apply(jsonElement)) {
         JsonObject jsonObject = (JsonObject)jsonElement;
         this.map.forEach((string, api1) -> ConfigManagerSub2.handleJsonElement2(jsonObject, string, api1));
      }
   }

   private static void handleJsonElement2(JsonElement jsonElement, String string, Api1 api1) {
      api1.read(new Helper27(((JsonObject)ValueCodec.valueCodec1.getEqualsfunction().apply(jsonElement)).get(string)));
   }

   public ConfigManagerSub2(Path path, HashMap hashMap) {
      super(path);
      this.map = hashMap;
   }

   public ConfigManagerSub2(HashMap hashMap) {
      super(null);
      this.map = hashMap;
   }
}
