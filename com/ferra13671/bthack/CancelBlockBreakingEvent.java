package com.ferra13671.bthack;

import net.minecraft.util.math.BlockPos;

public class CancelBlockBreakingEvent extends Event {
   public final transient BlockPos blockPos;
   public final transient boolean boolean1;

   public CancelBlockBreakingEvent(BlockPos blockPos, boolean bool) {
      this.blockPos = blockPos;
      this.boolean1 = bool;
   }
}
