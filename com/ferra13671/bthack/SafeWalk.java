package com.ferra13671.bthack;

import net.minecraft.util.math.BlockPos;

@ModuleInfo(name = "Safe Walk", category = "movement")
public class SafeWalk extends Module {
   @Api8(event = TickMovementEvent.class)
   public void onTickMovement() {
      if (mc.world.getBlockState(BlockPos.ofFloored(mc.player.getEntityPos().add(0.0, -0.5, 0.0))).isAir() && mc.player.isOnGround()) {
         InputUtils.withClass_7447(mc.player.input, true);
      }
   }
}
