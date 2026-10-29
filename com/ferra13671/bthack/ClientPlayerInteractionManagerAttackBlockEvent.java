package com.ferra13671.bthack;

import net.minecraft.util.math.BlockPos;

public class ClientPlayerInteractionManagerAttackBlockEvent extends Event {
   public final transient BlockPos blockPos;

   public ClientPlayerInteractionManagerAttackBlockEvent(BlockPos blockPos) {
      this.blockPos = blockPos;
   }
}
