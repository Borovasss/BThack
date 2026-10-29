package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import java.awt.Color;
import org.joml.Vector4f;

public class ColorSetting extends Setting {
   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementSub3(eventBus, api9, this, flt, flt2, flt3);
   }

   public float[] getFloat() {
      return Color.RGBtoHSB(((Color)this.value).getRed(), ((Color)this.value).getGreen(), ((Color)this.value).getBlue(), null);
   }

   protected ColorSetting(ColorSetting.ColorSettingBuilder colorSettingBuilder) {
      super(colorSettingBuilder);
   }

   private void handleVector4f1(Vector4f vector4f) {
      this.setValue2(new Color(vector4f.x, vector4f.y, vector4f.z, vector4f.w));
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodecUtils.valueCodec2, this::handleVector4f1);
   }

   public static ColorSetting.ColorSettingBuilder builder() {
      return new ColorSetting.ColorSettingBuilderSub1();
   }

   @Override
   public JsonElement toJson() {
      return (JsonElement)ValueCodecUtils.valueCodec2
         .getEqualsfunction3()
         .apply(
            new Vector4f(
               ((Color)this.getValue()).getRed() / 255.0F,
               ((Color)this.getValue()).getGreen() / 255.0F,
               ((Color)this.getValue()).getBlue() / 255.0F,
               ((Color)this.getValue()).getAlpha() / 255.0F
            )
         );
   }

   public abstract static class ColorSettingBuilder extends Setting.SettingBuilder {
      public abstract ColorSetting build();

      protected abstract ColorSetting.ColorSettingBuilder toBuilder();

      @Override
      public String toString() {
         return "ColorSetting.ColorSettingBuilder(super=" + super.toString() + ")";
      }
   }

   private static final class ColorSettingBuilderSub1 extends ColorSetting.ColorSettingBuilder {
      protected ColorSetting.ColorSettingBuilderSub1 toBuilder() {
         return this;
      }

      @Override
      public ColorSetting build() {
         return new ColorSetting(this);
      }

      ColorSettingBuilderSub1() {
      }
   }
}
