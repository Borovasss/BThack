package com.ferra13671.bthack;

@ModuleInfo(name = "Jump Delay", category = "movement")
public class JumpDelay extends Module {
   public final transient NumberSetting delay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder().name("Delay"))
         .value(0.0F))
      .min(0.0F)
      .max(10.0F)
      .decimalPlaces(0)
      .build();

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized()) {
         if (Utils9.getClass_1309(mc.player) > (Float)this.delay.getValue()) {
            Utils9.handleClass_13094(mc.player, ((Float)this.delay.getValue()).intValue());
         }
      }
   }
}
