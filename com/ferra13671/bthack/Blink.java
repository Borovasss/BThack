package com.ferra13671.bthack;

@ModuleInfo(name = "Blink", category = "movement")
public class Blink extends Module {
   public final transient BooleanSetting onlyMovement = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Only Movement"))
         .value(false))
      .build();
   public final transient BooleanSetting reset = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Reset"))
         .value(false))
      .build();
   public final transient NumberSetting resetDelay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Reset Delay"))
            .value(2.0F))
         .min(0.5F)
         .max(15.0F)
         .visibleSupplier(this.reset::getValue))
      .build();
   final transient Helper45 helper45 = new Helper45();
   final transient MoveOptions moveOptions = new MoveOptions(1000, this.onlyMovement::getValue);

   @Override
   public void onEnable3() {
      super.onEnable3();
      BThackClient.getInstance().getPrioritySortedHelper2().subscribe(this.moveOptions);
   }

   @Override
   public void onDisable2() {
      super.onDisable2();
      BThackClient.getInstance().getPrioritySortedHelper2().unregister(this.moveOptions);
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized()) {
         this.setEnabled(false, false);
      } else {
         if ((Boolean)this.reset.getValue() && this.helper45.passed((int)((Float)this.resetDelay.getValue() * 1000.0F))) {
            this.helper45.reset();
            this.moveOptions.runVoid();
         }
      }
   }
}
