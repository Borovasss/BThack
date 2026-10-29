package com.ferra13671.bthack;

import net.minecraft.util.math.MathHelper;

@ModuleInfo(name = "Zoom", category = "render")
public class Zoom extends Module {
   public final transient NumberSetting startZoom = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Start Zoom"))
         .value(8.0F))
      .min(2.0F)
      .max(64.0F)
      .decimalPlaces(0)
      .build();
   public final transient NumberSetting zoomStep = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Zoom Step"))
         .value(1.0F))
      .min(0.1F)
      .max(6.5F)
      .decimalPlaces(1)
      .build();
   public final transient NumberSetting smoothness = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Smoothness"))
         .value(0.6F))
      .min(0.0F)
      .max(0.9F)
      .build();
   final transient Helper29 helper29 = new Helper29(Easing.CUBIC_OUT, 10000);
   public transient float float1 = 1.0F;
   public transient float float2 = 1.0F;
   transient double double1 = -1.0;

   @Override
   public void onDisable2() {
      super.onDisable2();
      if (this.double1 != -1.0) {
         mc.options.getMouseSensitivity().setValue(this.double1);
      }

      this.double1 = -1.0;
      this.helper29.setLong();
      this.float1 = 1.0F;
   }

   @Api8(event = OnMouseScrollEvent.class)
   public void onOnMouseScroll(OnMouseScrollEvent onMouseScrollEvent) {
      if (mc.currentScreen == null) {
         float flt = onMouseScrollEvent.amount * ((Float)this.zoomStep.getValue() * 0.1F) * this.float1;
         this.float1 = Math.max(1.0F, this.float1 + flt);
         this.helper29.setLong();
         this.onDisable();
         onMouseScrollEvent.runVoid();
      }
   }

   @Override
   public void onEnable3() {
      super.onEnable3();
      this.float1 = (Float)this.startZoom.getValue();
      this.float2 = 1.0F;
      this.helper29.setLong();
      this.double1 = (Double)mc.options.getMouseSensitivity().getValue();
      this.onDisable();
   }

   private void onDisable() {
      float flt = ((Integer)mc.options.getFov().getValue()).intValue() / this.float1 / ((Integer)mc.options.getFov().getValue()).intValue();
      float flt2 = 45.0F - flt * 45.0F;
      mc.options.getMouseSensitivity().setValue(this.double1 * (1.0 / Math.tan(Math.toRadians(45.0F + flt2))));
   }

   public float getFloat(float flt) {
      this.helper29.setInt((int)(10000.0F * (Float)this.smoothness.getValue()));
      this.float2 = MathHelper.lerp((float)this.helper29.getEasing2(), this.float2, this.float1);
      return flt / this.float2;
   }
}
