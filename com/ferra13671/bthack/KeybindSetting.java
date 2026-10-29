package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class KeybindSetting extends Setting {
   public static KeybindSetting.KeybindSettingBuilder builder() {
      return new KeybindSetting.KeybindSettingBuilderSub1();
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodec.valueCodec1, this::handleJsonObject);
   }

   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementSub2(eventBus, api9, this, flt, flt2, flt3);
   }

   protected KeybindSetting(KeybindSetting.KeybindSettingBuilder keybindSettingBuilder) {
      super(keybindSettingBuilder);
   }

   @Override
   public JsonElement toJson() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("key", ((Helper36)this.getValue()).getInt());
      jsonObject.addProperty("controller", ((Helper36)this.getValue()).getKeyboardEnum().name());
      return jsonObject;
   }

   private void handleJsonObject(JsonObject jsonObject) {
      this.setValue2(
         new Helper36(
            ((Number)new Helper27(jsonObject.get("key")).withValueCodec(ValueCodec.valueCodec5, 0)).intValue(),
            (KeyboardEnum)new Helper27(jsonObject.get("controller")).withValueCodec(ValueCodecUtils.withClass16(KeyboardEnum.class), KeyboardEnum.Keyboard)
         )
      );
   }

   public abstract static class KeybindSettingBuilder extends Setting.SettingBuilder {
      @Override
      public String toString() {
         return "KeybindSetting.KeybindSettingBuilder(super=" + super.toString() + ")";
      }

      protected abstract KeybindSetting.KeybindSettingBuilder toBuilder();

      public abstract KeybindSetting build();
   }

   private static final class KeybindSettingBuilderSub1 extends KeybindSetting.KeybindSettingBuilder {
      protected KeybindSetting.KeybindSettingBuilderSub1 toBuilder() {
         return this;
      }

      KeybindSettingBuilderSub1() {
      }

      @Override
      public KeybindSetting build() {
         return new KeybindSetting(this);
      }
   }
}
