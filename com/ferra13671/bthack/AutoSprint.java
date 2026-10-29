package com.ferra13671.bthack;

@ModuleInfo(name = "Auto Sprint", category = "movement")
public class AutoSprint extends Module {
   public final transient BooleanSetting inwater = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("In water"))
         .value(true))
      .build();

   private boolean isInwater() {
      return (Boolean)this.inwater.getValue() || !mc.player.isSubmergedInWater();
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized() && (!ToggleableUtils.aura.isEnabled() || ToggleableUtils.aura.boolean1)) {
         if (this.isBoolean() && !mc.player.isSprinting()) {
            mc.player.setSprinting(true);
         }
      }
   }

   public boolean isBoolean() {
      return mc.player.input.playerInput.forward()
         && !mc.player.input.playerInput.sneak()
         && !mc.player.horizontalCollision
         && mc.player.getHungerManager().getFoodLevel() > 3
         && !Utils9.isClass_7462(mc.player)
         && this.isInwater();
   }
}
