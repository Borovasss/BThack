package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class CustomSetting extends Setting {
   @Override
   public JsonElement toJson() {
      return new JsonPrimitive((String)this.getValue());
   }

   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new TextFieldElement(eventBus, api9, this, flt, flt2, flt3);
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodec.valueCodec4, this::setValue2);
   }

   public static CustomSetting.SettingBuilderSub3 builder() {
      return new CustomSetting.SettingBuilderSub3();
   }

   protected CustomSetting(CustomSetting.SettingBuilderSub3 settingBuilderSub3) {
      super(settingBuilderSub3);
   }

   public static class SettingBuilderSub3 extends Setting.SettingBuilder {
      public CustomSetting build() {
         return new CustomSetting(this);
      }

      protected CustomSetting.SettingBuilderSub3 toBuilder() {
         return this;
      }

      public SettingBuilderSub3() {
         this.value("");
      }
   }
}
