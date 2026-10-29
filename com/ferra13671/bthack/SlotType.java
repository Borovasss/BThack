package com.ferra13671.bthack;

import net.minecraft.util.Hand;

public record SlotType(int slot, HotbarBody type) implements MinecraftInstance {

   public int getSlot() {
      return this.slot;
   }

   public static SlotType of(int num) {
      return new SlotType(num, HotbarBody.fromSlotId(num));
   }

   public static SlotType withClass_1268(Hand hand) {
      return hand == Hand.MAIN_HAND ? new SlotType(mc.player.getInventory().getSelectedSlot(), HotbarBody.Hotbar) : Helper7.slotType5;
   }

   public boolean isSlot() {
      return this.slot == -1;
   }

   public SlotClickParams withSlotType(SlotType slotType, Api3 api3) {
      return new SlotClickParams(this, slotType, api3);
   }

   public HotbarBody getType() {
      return this.type;
   }

   public int getSlotType1(SlotType slotType) {
      return this.slot + (this.type == HotbarBody.Hotbar && slotType.getType() == HotbarBody.Inventory ? 36 : 0);
   }

   public SlotClickParams withClass_12682(Hand hand, Api3 api3) {
      return new SlotClickParams(hand == Hand.MAIN_HAND ? of(mc.player.getInventory().getSelectedSlot()) : Helper7.slotType5, this, api3);
   }
}
