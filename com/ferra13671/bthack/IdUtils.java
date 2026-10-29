package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class IdUtils {
   private static void handleId(Id id, Map map, String string, JsonElement jsonElement) {
      Id id2 = new Id(id.id().isEmpty() ? string : (string.isEmpty() ? id.id() : String.join(".", id.id(), string)));
      if (jsonElement instanceof JsonPrimitive jsonPrimitive && jsonPrimitive.isString()) {
         map.put(id2, jsonPrimitive.getAsString());
      }

      if (jsonElement instanceof JsonObject jsonObject) {
         withJsonObject1(jsonObject, map, id2);
      }
   }

   static Map withJsonObject1(JsonObject jsonObject, Map map, Id id) {
      if (jsonObject == null) {
         throw new NullPointerException("jsonObject is marked non-null but is null");
      }

      if (map == null) {
         throw new NullPointerException("translations is marked non-null but is null");
      }

      if (id == null) {
         throw new NullPointerException("prefix is marked non-null but is null");
      }

      jsonObject.asMap().forEach((string, jsonElement) -> IdUtils.handleId(id, map, string, jsonElement));
      return map;
   }

   public static Map parse(InputStream inputStream) {
      if (inputStream == null) {
         throw new NullPointerException("inputStream is marked non-null but is null");
      }

      try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
         return PlaceholderParser.withMap5(withJsonObject1(JsonParser.parseReader(inputStreamReader).getAsJsonObject(), new HashMap(), new Id("")));
      } catch (Exception exception) {
         exception.printStackTrace();
         return new HashMap();
      }
   }

   IdUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Map parse(JsonObject jsonObject) {
      if (jsonObject == null) {
         throw new NullPointerException("jsonObject is marked non-null but is null");
      } else {
         return PlaceholderParser.withMap5(withJsonObject1(jsonObject, new HashMap(), new Id("")));
      }
   }
}
