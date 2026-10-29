package com.ferra13671.bthack;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;

public class ClickSlotEvent extends Event {
   public final transient int int1;
   public final transient int int2;
   public final transient int x;
   public final transient SlotActionType slotActionType;
   public final transient PlayerEntity playerEntity;

   public ClickSlotEvent(int num, int num2, int num3, SlotActionType slotActionType, PlayerEntity playerEntity) {
      this.int1 = num;
      this.int2 = num2;
      this.x = num3;
      this.slotActionType = slotActionType;
      this.playerEntity = playerEntity;
   }
}
