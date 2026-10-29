package com.ferra13671.bthack;

import com.ferra13671.bthack.mixins.AbstractSignEditScreenMixin;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;

@ModuleInfo(name = "Auto Sign", category = "misc")
public class AutoSign extends Module {
   public final transient BooleanSetting closeScreen = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Close Screen"))
         .value(true))
      .build();
   public final transient BooleanSetting backSide = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Back Side"))
         .value(false))
      .build();
   public final transient CustomSetting line1 = ((CustomSetting.SettingBuilderSub3)CustomSetting.builder().name("Line 1")).build();
   public final transient CustomSetting line2 = ((CustomSetting.SettingBuilderSub3)CustomSetting.builder().name("Line 2")).build();
   public final transient CustomSetting line3 = ((CustomSetting.SettingBuilderSub3)CustomSetting.builder().name("Line 3")).build();
   public final transient CustomSetting line4 = ((CustomSetting.SettingBuilderSub3)CustomSetting.builder().name("Line 4")).build();

   @Api8(event = SetScreenEvent.class)
   public void onSetScreen(SetScreenEvent setScreenEvent) {
      if (setScreenEvent.screen instanceof SignEditScreen signEditScreen) {
         mc.getNetworkHandler()
            .sendPacket(
               new UpdateSignC2SPacket(
                  ((AbstractSignEditScreenMixin)signEditScreen).applyClass_2625().getPos(),
                  !(Boolean)this.backSide.getValue(),
                  (String)this.line1.getValue(),
                  (String)this.line2.getValue(),
                  (String)this.line3.getValue(),
                  (String)this.line4.getValue()
               )
            );
         if ((Boolean)this.closeScreen.getValue()) {
            setScreenEvent.runVoid();
         }
      }
   }
}
