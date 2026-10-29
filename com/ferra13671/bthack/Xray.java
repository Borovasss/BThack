package com.ferra13671.bthack;

import java.util.ArrayList;

@ModuleInfo(name = "Xray", category = "render")
public class Xray extends Module {
   public final transient SettingGroup blocks = ((SettingGroup.SettingBuilderSub1Sub3)SettingGroup.builder().name("Blocks")).build();
   public final transient SettingGroup2 fluids = ((SettingGroup2.SettingBuilderSub1Sub2)((SettingGroup2.SettingBuilderSub1Sub2)SettingGroup2.builder()
            .name("Fluids"))
         .value(new ArrayList(NamedObjectUtils.hashMap3.keySet().stream().toList())))
      .build();

   private void onEnable() {
      RendererInit.runLater(mc.worldRenderer::reload);
   }

   @Override
   protected void onEnable3() {
      if (BThackClient.isInitialized()) {
         this.setEnabled(false, true);
      } else {
         super.onEnable3();
         this.onEnable();
      }
   }

   @Override
   protected void onDisable2() {
      super.onDisable2();
      if (!BThackClient.isInitialized()) {
         this.onEnable();
      }
   }

   @Api8(event = EventSub12.class)
   public void runVoid() {
      if (this.isEnabled()) {
         this.onEnable();
      }
   }

   @Api8(event = EventSub11.class)
   public void handleEventSub111(EventSub11 eventSub11) {
      eventSub11.int1 = 15;
   }
}
