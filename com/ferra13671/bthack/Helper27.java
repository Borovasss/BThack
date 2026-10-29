package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import java.util.function.Consumer;

public class Helper27 {
   final transient JsonElement jsonElement1;

   public <T> void decode(ValueCodec<T> valueCodec, Consumer<T> consumer) {
      if (this.jsonElement1 != null && (Boolean)valueCodec.getEqualsfunction2().apply(this.jsonElement1)) {
         consumer.accept(valueCodec.getEqualsfunction().apply(this.jsonElement1));
      }
   }

   public Helper27(JsonElement jsonElement) {
      this.jsonElement1 = jsonElement;
   }

   public JsonElement getJsonElement() {
      return this.jsonElement1;
   }

   public <T> T withValueCodec(ValueCodec<T> valueCodec, T object) {
      return this.jsonElement1 != null && valueCodec.getEqualsfunction2().apply(this.jsonElement1)
         ? valueCodec.getEqualsfunction().apply(this.jsonElement1)
         : object;
   }
}
