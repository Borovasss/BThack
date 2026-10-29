package com.ferra13671.bthack;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;

@ModuleInfo(name = "Elytra Swap", category = "misc")
public class ElytraSwap extends Module {
   @Override
   protected void onEnable3() {
      super.onEnable3();
      if (!BThackClient.isInitialized()) {
         SlotType slotType = mc.player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA ? this.applySlotType() : this.applySlotType1();
         if (!slotType.isSlot()) {
            Helper7.slotType2.withSlotType(slotType, Api3.DEFAULT).runVoid1();
         }
      }

      this.setEnabled(false, true);
   }

   private SlotType applySlotType() {
      SlotType slotType = SlotTypeUtils.of(ItemTags.CHEST_ARMOR);
      if (slotType.isSlot()) {
         slotType = SlotTypeUtils.of(Items.AIR);
      }

      return slotType;
   }

   private SlotType applySlotType1() {
      return SlotTypeUtils.of(Items.ELYTRA);
   }
}
