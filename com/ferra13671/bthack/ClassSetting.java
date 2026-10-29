package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ClassSetting extends Setting {
   final transient Class class1;

   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementSub5(eventBus, api9, this, flt, flt2, flt3);
   }

   protected ClassSetting(ClassSetting.SettingBuilderSub2 settingBuilderSub2) {
      super(settingBuilderSub2);
      this.class1 = settingBuilderSub2.class1;
   }

   public static ClassSetting.SettingBuilderSub2 builder(Class class_) {
      return new ClassSetting.SettingBuilderSub2(class_);
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodecUtils.withClass16(this.class1), this::setValue2);
   }

   @Override
   public JsonElement toJson() {
      return new JsonPrimitive(((Enum)this.getValue()).name());
   }

   public Class getEnumClass() {
      return this.class1;
   }

   public static class SettingBuilderSub2 extends Setting.SettingBuilder {
      protected final transient Class class1;

      public SettingBuilderSub2(Class class_) {
         this.class1 = class_;
      }

      protected ClassSetting.SettingBuilderSub2 toBuilder() {
         return this;
      }

      public ClassSetting build() {
         return new ClassSetting(this);
      }
   }
}
