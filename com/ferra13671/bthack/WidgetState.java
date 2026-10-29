package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.joml.Vector2f;

public class WidgetState extends Api1Impl {
   final transient HudElement hudElement;
   final transient Api1ImplSub1 api1ImplSub1;

   public WidgetState(HudElement hudElement) {
      super(ValueCodec.valueCodec1);
      this.hudElement = hudElement;
      this.api1ImplSub1 = new Api1ImplSub1(this.hudElement.getSettings());
   }

   protected void fromElement(JsonObject jsonObject) {
      new Helper27(jsonObject.get("enabled")).decode(ValueCodec.valueCodec3, this::handleBoolean);
      new Helper27(jsonObject.get("position")).decode(ValueCodecUtils.valueCodec1, this::handleVector2f);
      this.api1ImplSub1.read(new Helper27(jsonObject.get("settings")));
   }

   protected JsonObject toElement() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("enabled", this.hudElement.isEnabled());
      jsonObject.add(
         "position", (JsonElement)ValueCodecUtils.valueCodec1.getEqualsfunction3().apply(new Vector2f(this.hudElement.getWidth(), this.hudElement.getHeight()))
      );
      jsonObject.add("settings", this.api1ImplSub1.toElement());
      return jsonObject;
   }

   @Override
   protected Object get() {
      return this.toElement();
   }

   private void handleVector2f(Vector2f vector2f) {
      this.hudElement.setX(vector2f.x);
      this.hudElement.setY(vector2f.y);
   }

   private void handleBoolean(Boolean boolean_) {
      this.hudElement.setEnabled(boolean_, true);
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonObject)var1);
   }
}
