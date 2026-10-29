package com.ferra13671.bthack;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class Helper28 {
   transient long lastPacketTime = 0L;
   transient float tps;

   @Api8(event = EventSub4.EventSub4Sub1.class)
   public void onKeepAlive(EventSub4.EventSub4Sub1 eventSub4Sub1) {
      if (eventSub4Sub1.getPacket() instanceof WorldTimeUpdateS2CPacket) {
         this.tps = 20.0F / ((float)(System.currentTimeMillis() - this.lastPacketTime) / 1000.0F);
         this.lastPacketTime = System.currentTimeMillis();
      }
   }

   public Helper28() {
      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   public float getTps() {
      return this.tps;
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized()) {
         this.lastPacketTime = System.currentTimeMillis();
         this.tps = -1.0F;
      }
   }
}
