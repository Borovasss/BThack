package com.ferra13671.bthack;

import net.minecraft.util.math.Vec3d;

@ModuleInfo(name = "Elytra Boost", category = "movement")
public class ElytraBoost extends Module {
   public final transient NumberSetting maxSpeed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Max Speed"))
            .id("max-speed"))
         .value(50.0F))
      .min(20.0F)
      .max(200.0F)
      .decimalPlaces(1)
      .build();
   public final transient NumberSetting boostStrength = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Boost Strength"))
            .id("boost-strength"))
         .value(0.07F))
      .min(0.02F)
      .max(0.14F)
      .decimalPlaces(3)
      .build();
   public final transient BooleanSetting yMotionAlso = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Y Motion Also"))
            .id("y-motion-also"))
         .value(false))
      .build();
   public final transient NumberSetting startBoostPitch = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Start Boost Pitch"))
            .id("start-boost-pitch"))
         .value(0.0F))
      .min(80.0F)
      .max(0.0F)
      .decimalPlaces(1)
      .build();
   public final transient BooleanSetting pauseIfBlink = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Pause If Blink"))
            .id("pause-if-blink"))
         .value(true))
      .build();

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized() && (!(Boolean)this.pauseIfBlink.getValue() || !ToggleableUtils.blink.isEnabled())) {
         if (mc.player.getEntityPos().distanceTo(new Vec3d(mc.player.lastRenderX, mc.player.lastRenderY, mc.player.lastRenderZ)) * 20.0
               < ((Float)this.maxSpeed.getValue()).floatValue()
            && mc.player.isGliding()
            && mc.player.getPitch() > (Float)this.startBoostPitch.getValue()) {
            double dbl = 1.0F + (Float)this.boostStrength.getValue();
            mc.player.setVelocity(mc.player.getVelocity().multiply(dbl, this.yMotionAlso.getValue() ? dbl : 1.0, dbl));
         }
      }
   }
}
