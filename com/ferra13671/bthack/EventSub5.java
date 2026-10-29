package com.ferra13671.bthack;

import net.minecraft.world.chunk.WorldChunk;

public class EventSub5 extends Event {
   public final transient WorldChunk worldChunk;

   public EventSub5(WorldChunk worldChunk) {
      this.worldChunk = worldChunk;
   }
}
