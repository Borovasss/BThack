package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BooleanSetting extends Setting<Boolean> {
   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementSub1(eventBus, api9, this, flt, flt2, flt3);
   }

   public static BooleanSetting.BooleanSettingBuilder builder() {
      return new BooleanSetting.BooleanSettingBuilderSub1();
   }

   @Override
   public JsonElement toJson() {
      return new JsonPrimitive((Boolean)this.getValue());
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodec.valueCodec3, this::setValue2);
   }

   protected BooleanSetting(BooleanSetting.BooleanSettingBuilder booleanSettingBuilder) {
      super(booleanSettingBuilder);
   }

   public abstract static class BooleanSettingBuilder extends Setting.SettingBuilder {
      @Override
      public String toString() {
         return "BooleanSetting.BooleanSettingBuilder(super=" + super.toString() + ")";
      }

      protected abstract BooleanSetting.BooleanSettingBuilder toBuilder();

      public abstract BooleanSetting build();
   }

   private static final class BooleanSettingBuilderSub1 extends BooleanSetting.BooleanSettingBuilder {
      protected BooleanSetting.BooleanSettingBuilderSub1 toBuilder() {
         return this;
      }

      BooleanSettingBuilderSub1() {
      }

      @Override
      public BooleanSetting build() {
         return new BooleanSetting(this);
      }
   }
}
