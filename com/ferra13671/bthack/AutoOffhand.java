package com.ferra13671.bthack;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

@ModuleInfo(name = "Auto Offhand", category = "combat")
public class AutoOffhand extends Module {
   public final transient BooleanSetting replaceOther = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Replace Other"))
         .value(true))
      .build();
   public final transient SettingGroup3 item = ((SettingGroup3.SettingBuilderSub1Sub1)((SettingGroup3.SettingBuilderSub1Sub1)((SettingGroup3.SettingBuilderSub1Sub1)SettingGroup3.builder()
               .name("Item"))
            .withInt(1))
         .withIgnoreBody(IgnoreBody.ReplaceFirst))
      .build();

   private boolean getReplaceOther() {
      ItemStack itemStack = mc.player.getOffHandStack();
      return this.replaceOther.getValue() ? !((List)this.item.getValue()).contains(itemStack.getItem()) : itemStack.isEmpty();
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized() && !((List)this.item.getValue()).isEmpty() && this.getReplaceOther()) {
         SlotType slotType = SlotType.withClass_1268(Hand.OFF_HAND);
         SlotType slotType2 = SlotTypeUtils.of((net.minecraft.item.Item)((List)this.item.getValue()).getFirst());
         if (!slotType2.isSlot()) {
            slotType2.withSlotType(slotType, Api3.DEFAULT).runVoid1();
         }
      }
   }
}
