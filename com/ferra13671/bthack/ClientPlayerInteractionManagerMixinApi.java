package com.ferra13671.bthack;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;

public interface ClientPlayerInteractionManagerMixinApi {
   void handleInt(int var1, int var2, int var3, SlotActionType var4, PlayerEntity var5);
}
