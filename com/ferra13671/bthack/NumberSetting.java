package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class NumberSetting extends Setting<Float> {
   transient Float min;
   transient Float max;
   transient Integer decimals;

   public Integer getValueInt() {
      return this.decimals;
   }

   public Float getMin() {
      return this.min;
   }

   @Override
   public JsonElement toJson() {
      return new JsonPrimitive((Number)this.getValue());
   }

   public String getAsString() {
      return this.formatValue((Float)this.value);
   }

   private void setValueNumber(Number number) {
      this.setValue(number.floatValue());
   }

   @Override
   public void setValue2(Object var1) {
      this.setValue((Float)var1);
   }

   public void setValue(Float float_) {
      super.setValue2(EntityUtils.decimalPlaces(float_, this.decimals));
   }

   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementHelper(eventBus, api9, this, flt, flt2, flt3);
   }

   public Float getValue2() {
      return this.max;
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodec.valueCodec5, this::setValueNumber);
   }

   public String getMaxAsString() {
      return this.formatValue(this.min);
   }

   public String getMinAsString() {
      return this.formatValue(this.max);
   }

   protected NumberSetting(NumberSetting.NumberSettingBuilder numberSettingBuilder) {
      super(numberSettingBuilder);
      this.min = numberSettingBuilder.float1;
      this.max = numberSettingBuilder.float2;
      if (numberSettingBuilder.boolean1) {
         this.decimals = numberSettingBuilder.integer1;
      } else {
         this.decimals = getDecimals();
      }
   }

   private static Integer getDecimals() {
      return 2;
   }

   private String formatValue(Float float_) {
      return this.decimals <= 0 ? Integer.toString(float_.intValue()) : float_.toString();
   }

   public static NumberSetting.NumberSettingBuilder builder() {
      return new NumberSetting.NumberSettingBuilderSub1();
   }

   public abstract static class NumberSettingBuilder extends Setting.SettingBuilder {
      transient Float float1;
      transient Float float2;
      transient boolean boolean1;
      transient Integer integer1;

      public NumberSetting.NumberSettingBuilder max(Float float_) {
         this.float2 = float_;
         return this.toBuilder();
      }

      @Override
      public String toString() {
         return "NumberSetting.NumberSettingBuilder(super="
            + super.toString()
            + ", min="
            + this.float1
            + ", max="
            + this.float2
            + ", decimal$value="
            + this.integer1
            + ")";
      }

      protected abstract NumberSetting.NumberSettingBuilder toBuilder();

      public abstract NumberSetting build();

      public NumberSetting.NumberSettingBuilder decimalPlaces(Integer integer) {
         this.integer1 = integer;
         this.boolean1 = true;
         return this.toBuilder();
      }

      public NumberSetting.NumberSettingBuilder min(Float float_) {
         this.float1 = float_;
         return this.toBuilder();
      }
   }

   private static final class NumberSettingBuilderSub1 extends NumberSetting.NumberSettingBuilder {
      @Override
      public NumberSetting build() {
         return new NumberSetting(this);
      }

      protected NumberSetting.NumberSettingBuilderSub1 toBuilder() {
         return this;
      }

      NumberSettingBuilderSub1() {
      }
   }
}
