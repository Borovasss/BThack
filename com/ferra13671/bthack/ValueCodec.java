package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.function.Function;

public record ValueCodec<T>(Function<JsonElement, Boolean> equalsfunction3, Function<JsonElement, T> equalsfunction, Function<T, JsonElement> equalsfunction2) {
   public static final transient ValueCodec<JsonObject> valueCodec1 = new ValueCodec<JsonObject>(JsonElement::isJsonObject, JsonElement::getAsJsonObject, ValueCodec::withJsonObject2);
   public static final transient ValueCodec<JsonArray> valueCodec2 = new ValueCodec<JsonArray>(JsonElement::isJsonArray, JsonElement::getAsJsonArray, ValueCodec::withJsonArray4);
   public static final transient ValueCodec<Boolean> valueCodec3 = new ValueCodec<Boolean>(ValueCodec::getRoundedTextureBatch, ValueCodec::isJsonElement6, JsonPrimitive::new);
   public static final transient ValueCodec<String> valueCodec4 = new ValueCodec<String>(ValueCodec::isJsonElement5, ValueCodec::getJsonElement, JsonPrimitive::new);
   public static final transient ValueCodec<Number> valueCodec5 = new ValueCodec<Number>(ValueCodec::isJsonElement1, ValueCodec::withJsonElement3, JsonPrimitive::new);

   public Function<JsonElement, T> getEqualsfunction() {
      return this.equalsfunction;
   }

   private static String getJsonElement(JsonElement jsonElement) {
      return jsonElement.getAsJsonPrimitive().getAsString();
   }

   private static Boolean isJsonElement1(JsonElement jsonElement) {
      return jsonElement instanceof JsonPrimitive jsonPrimitive ? jsonPrimitive.isNumber() : false;
   }

   private static JsonElement withJsonObject2(JsonObject jsonObject) {
      return jsonObject;
   }

   public Function<JsonElement, Boolean> getEqualsfunction2() {
      return this.equalsfunction3;
   }

   private static Number withJsonElement3(JsonElement jsonElement) {
      return jsonElement.getAsJsonPrimitive().getAsNumber();
   }

   private static JsonElement withJsonArray4(JsonArray jsonArray) {
      return jsonArray;
   }

   private static Boolean isJsonElement5(JsonElement jsonElement) {
      return jsonElement instanceof JsonPrimitive jsonPrimitive ? jsonPrimitive.isString() : false;
   }

   public Function<T, JsonElement> getEqualsfunction3() {
      return this.equalsfunction2;
   }

   private static Boolean getRoundedTextureBatch(JsonElement jsonElement) {
      return jsonElement instanceof JsonPrimitive jsonPrimitive ? jsonPrimitive.isBoolean() : false;
   }

   private static Boolean isJsonElement6(JsonElement jsonElement) {
      return jsonElement.getAsJsonPrimitive().getAsBoolean();
   }
}
