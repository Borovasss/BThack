package com.ferra13671.bthack;

@ModuleInfo(name = "Auto Save", category = "client", enabled = true)
public class AutoSave extends Module {
   public final transient NumberSetting delay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder().name("Delay"))
         .value(60.0F))
      .min(5.0F)
      .max(600.0F)
      .build();
   final transient Helper45 helper45 = new Helper45();

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.getInstance().isClientInitialized() && this.helper45.passed((int)((Float)this.delay.getValue() * 1000.0F))) {
         BThackClient.getInstance().getClientPaths().save();
         ChatUtils.handleString(BThackClient.getInstance().getTranslationManager().translate("module.auto-save.saved"), true);
         this.helper45.reset();
      }
   }
}
