package com.ferra13671.bthack;

import net.minecraft.screen.slot.SlotActionType;

public interface Api3 extends MinecraftInstance {
   Api3 DEFAULT = Api3::onSlotSwitch2;

   private static void swapSlots(int num, int num2) {
      swapSlots2(num, num2);
   }

   void onSlotSwitch(int var1, HotbarBody var2, int var3, HotbarBody var4, boolean var5);

   private static void onSlotSwitch2(int num, HotbarBody hotbarBody, int num2, HotbarBody hotbarBody2, boolean bool) {
      if (hotbarBody == hotbarBody2 && hotbarBody == HotbarBody.Hotbar) {
         SlotTypeUtils.selectSlot(bool ? num : num2);
      } else if (ToggleableUtils.guiMove.isEnabled()) {
         ToggleableUtils.guiMove.defer(() -> Api3.swapSlots(num, num2));
      } else {
         swapSlots2(num, num2);
      }
   }

   static void swapSlots2(int num, int num2) {
      mc.interactionManager.tick();
      mc.interactionManager.clickSlot(0, num, 0, SlotActionType.PICKUP, mc.player);
      mc.interactionManager.tick();
      mc.interactionManager.clickSlot(0, num2, 0, SlotActionType.PICKUP, mc.player);
      mc.interactionManager.tick();
      mc.interactionManager.clickSlot(0, num, 0, SlotActionType.PICKUP, mc.player);
      mc.interactionManager.tick();
   }
}
