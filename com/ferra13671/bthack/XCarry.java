package com.ferra13671.bthack;

import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.text.Text;

@ModuleInfo(name = "XCarry", category = "misc")
public class XCarry extends Module {
   public final transient BooleanSetting onlyInventory = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Only inventory"))
         .value(true))
      .build();

   @Api8(event = EventSub4.EventSub4Sub1.class)
   public void handleEventSub4Sub1(EventSub4.EventSub4Sub1 eventSub4Sub1) {
      if (eventSub4Sub1.getPacket() instanceof CloseHandledScreenC2SPacket obj
         && (!(Boolean)this.onlyInventory.getValue() || obj.getSyncId() == mc.player.playerScreenHandler.syncId)) {
         eventSub4Sub1.runVoid();
         mc.player.sendMessage(Text.literal("Closed"), false);
      }
   }
}
