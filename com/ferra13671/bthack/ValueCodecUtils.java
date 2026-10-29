package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.util.Util;
import org.joml.Vector2f;
import org.joml.Vector4f;

public final class ValueCodecUtils {
   public static final transient ValueCodec<Vector2f> valueCodec1 = new ValueCodec<Vector2f>(
      ValueCodecUtils::isJsonElement6, ValueCodecUtils::withJsonElement11, ValueCodecUtils::getRoundedTextureBatch
   );
   public static final transient ValueCodec<Vector4f> valueCodec2 = new ValueCodec<Vector4f>(
      ValueCodecUtils::isJsonElement3, ValueCodecUtils::withJsonElement13, ValueCodecUtils::withVector4f
   );
   public static final transient ValueCodec<Helper13> valueCodec3 = new ValueCodec<Helper13>(
      ValueCodecUtils::isJsonElement4, ValueCodecUtils::withJsonElement7, ValueCodecUtils::withHelper132
   );
   public static final transient ValueCodec<Map<String, String>> valueCodec4 = new ValueCodec<Map<String, String>>(
      JsonElement::isJsonObject, ValueCodecUtils::withJsonElement14, ValueCodecUtils::withMap8
   );
   static final transient Function<Class<?>, ValueCodec<Enum>> function1 = Util.memoize(ValueCodecUtils::withClass1);

   private static JsonElement withVector4f(Vector4f vector4f) {
      JsonArray jsonArray = new JsonArray();
      jsonArray.add(vector4f.x);
      jsonArray.add(vector4f.y);
      jsonArray.add(vector4f.z);
      jsonArray.add(vector4f.w);
      return jsonArray;
   }

   private static ValueCodec withClass1(Class class_) {
      return new ValueCodec<>((jsonElement) -> ValueCodecUtils.isClass9(class_, jsonElement), (jsonElement) -> ValueCodecUtils.withClass12(class_, jsonElement), ValueCodecUtils::withEnum10);
   }

   private static JsonElement withHelper132(Helper13 helper13) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("name", helper13.getName());
      if (!helper13.getName().equals(helper13.getString())) {
         jsonObject.addProperty("alias", helper13.getString());
      }

      return jsonObject;
   }

   private static Boolean isJsonElement3(JsonElement jsonElement) {
      return !(jsonElement instanceof JsonArray jsonArray)
         ? false
         : jsonArray.size() >= 4
            && isJsonElement15(jsonArray.get(0))
            && isJsonElement15(jsonArray.get(1))
            && isJsonElement15(jsonArray.get(2))
            && isJsonElement15(jsonArray.get(3));
   }

   private static JsonElement getRoundedTextureBatch(Vector2f vector2f) {
      JsonArray jsonArray = new JsonArray();
      jsonArray.add(vector2f.x);
      jsonArray.add(vector2f.y);
      return jsonArray;
   }

   private static Boolean isJsonElement4(JsonElement jsonElement) {
      return jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("name");
   }

   private static void handleMap5(Map map, String string, JsonElement jsonElement) {
      if (jsonElement instanceof JsonPrimitive jsonPrimitive && jsonPrimitive.isString()) {
         map.put(string, jsonPrimitive.getAsString());
      }
   }

   private static Boolean isJsonElement6(JsonElement jsonElement) {
      return !(jsonElement instanceof JsonArray jsonArray)
         ? false
         : jsonArray.size() >= 2 && isJsonElement15(jsonArray.get(0)) && isJsonElement15(jsonArray.get(1));
   }

   private static Helper13 withJsonElement7(JsonElement jsonElement) {
      JsonObject jsonObject = jsonElement.getAsJsonObject();
      String string = jsonObject.getAsJsonPrimitive("name").getAsString();
      String string2 = jsonObject.has("alias") ? jsonObject.getAsJsonPrimitive("alias").getAsString() : string;
      return new Helper13(string, string2);
   }

   private static JsonElement withMap8(Map<String, String> map) {
      JsonObject jsonObject = new JsonObject();
      map.forEach(jsonObject::addProperty);
      return jsonObject;
   }

   private static Boolean isClass9(Class class_, JsonElement jsonElement) {
      if (jsonElement instanceof JsonPrimitive jsonPrimitive) {
         for (Enum<?> enum_ : (Enum[])class_.getEnumConstants()) {
            if (enum_.name().equals(jsonPrimitive.getAsString())) {
               return true;
            }
         }
      }

      return false;
   }

   ValueCodecUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private static JsonElement withEnum10(Enum enum_) {
      return new JsonPrimitive(enum_.name());
   }

   private static Vector2f withJsonElement11(JsonElement jsonElement) {
      JsonArray jsonArray = jsonElement.getAsJsonArray();
      return new Vector2f(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat());
   }

   private static Enum withClass12(Class class_, JsonElement jsonElement) {
      return Utils.withClass(class_, jsonElement.getAsString());
   }

   private static Vector4f withJsonElement13(JsonElement jsonElement) {
      JsonArray jsonArray = jsonElement.getAsJsonArray();
      return new Vector4f(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat(), jsonArray.get(3).getAsFloat());
   }

   private static Map withJsonElement14(JsonElement jsonElement) {
      JsonObject jsonObject = jsonElement.getAsJsonObject();
      Map<String, String> map = new HashMap<>();
      jsonObject.asMap().forEach((string, jsonElement1) -> ValueCodecUtils.handleMap5(map, string, jsonElement1));
      return map;
   }

   private static boolean isJsonElement15(JsonElement jsonElement) {
      return jsonElement instanceof JsonPrimitive jsonPrimitive && jsonPrimitive.isNumber();
   }

   public static ValueCodec<Enum> withClass16(Class class_) {
      return (ValueCodec<Enum>)function1.apply(class_);
   }
}
