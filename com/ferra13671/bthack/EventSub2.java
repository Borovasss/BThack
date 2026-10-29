package com.ferra13671.bthack;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class EventSub2 extends Event {
   public final transient BlockPos blockPos;
   public final transient BlockState blockState;

   public EventSub2(BlockPos blockPos, BlockState blockState) {
      this.blockPos = blockPos;
      this.blockState = blockState;
   }
}
