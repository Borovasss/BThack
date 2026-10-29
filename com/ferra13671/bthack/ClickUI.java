package com.ferra13671.bthack;

@ModuleInfo(name = "Click UI", category = "client", quitKeybindToggling = true)
public class ClickUI extends Module {
   public transient boolean boolean1 = false;

   private static void onEnable2() {
      mc.setScreen(BThackClient.getInstance().getClickUiScreen());
   }

   @Override
   protected void onEnable3() {
      if (BThackClient.getInstance().isClientInitialized()) {
         mc.execute(ClickUI::onEnable2);
      } else {
         this.setEnabled(false, true);
      }
   }

   @Override
   protected void onDisable2() {
      if (!this.boolean1 && BThackClient.getInstance().isClientInitialized()) {
         mc.execute(ClickUI::onEnable);
      }
   }

   public ClickUI() {
      this.getModuleKeybind().setValue2(new Helper36(344, KeyboardEnum.Keyboard));
   }

   private static void onEnable() {
      mc.setScreen(null);
   }
}
