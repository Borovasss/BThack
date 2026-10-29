package com.ferra13671.bthack;

import java.awt.Color;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

@ModuleInfo(name = "Full Bright", category = "render")
public class FullBright extends Module {
   public final transient ClassSetting mode = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(GammaBody.class)
            .name("Mode"))
         .value(GammaBody.Gamma))
      .build();
   public final transient ColorSetting gammaColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Gamma Color"))
            .value(Color.WHITE))
         .visibleSupplier(this::isMode))
      .build();
   public final transient NumberSetting blockLight = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Block Light"))
            .value(15.0F))
         .min(1.0F)
         .max(15.0F)
         .decimalPlaces(0)
         .visibleSupplier(this::isMode2))
      .build();

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (this.mode.getValue() == GammaBody.Potion) {
         this.getMode();
      }
   }

   @Api8(event = EventSub12.class)
   public void handleEventSub12(EventSub12 eventSub12) {
      if (!BThackClient.isInitialized()) {
         if (eventSub12.setting == this.mode) {
            RendererInit.runLater(mc.worldRenderer::reload);
            if (this.mode.getValue() != GammaBody.Potion) {
               this.getMode2();
            }
         }

         if (eventSub12.setting == this.blockLight && this.mode.getValue() == GammaBody.BlockLight) {
            RendererInit.runLater(mc.worldRenderer::reload);
         }
      }
   }

   @Override
   protected void onDisable2() {
      super.onDisable2();
      switch (Helper12.int1[((GammaBody)this.mode.getValue()).ordinal()]) {
         case 1:
            this.getMode2();
            break;
         case 2:
            if (!BThackClient.isInitialized()) {
               RendererInit.runLater(mc.worldRenderer::reload);
            }
      }
   }

   private Boolean isMode2() {
      return this.mode.getValue() == GammaBody.BlockLight;
   }

   private Boolean isMode() {
      return this.mode.getValue() == GammaBody.Gamma;
   }

   @Api8(event = EventSub11.class)
   public void handleEventSub111(EventSub11 eventSub11) {
      if (this.mode.getValue() == GammaBody.BlockLight) {
         eventSub11.int1 = ((Float)this.blockLight.getValue()).intValue();
      }
   }

   private void getMode() {
      if (!BThackClient.isInitialized() && !mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
         mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1000000000, 0));
      }
   }

   @Api8(event = GetGlTextureViewEvent.class)
   public void onGetGlTextureView(GetGlTextureViewEvent getGlTextureViewEvent) {
      if (RendererInit.boolean1 && this.mode.getValue() == GammaBody.Gamma) {
         getGlTextureViewEvent.setColor((Color)this.gammaColor.getValue());
      }
   }

   private void getMode2() {
      if (!BThackClient.isInitialized() && mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
         mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
      }
   }

   @Override
   protected void onEnable3() {
      super.onEnable3();
      if (this.mode.getValue() == GammaBody.BlockLight) {
         RendererInit.runLater(mc.worldRenderer::reload);
      }
   }
}
