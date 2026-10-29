package com.ferra13671.bthack;

import net.minecraft.network.packet.Packet;

public class EventSub4 extends Event {
   transient Packet packet;

   public Packet getPacket() {
      return this.packet;
   }

   public void setPacket(Packet packet) {
      this.packet = packet;
   }

   public EventSub4(Packet packet) {
      this.packet = packet;
   }

   public static class EventSub4Sub1 extends EventSub4 {
      public EventSub4Sub1(Packet packet) {
         super(packet);
      }
   }

   public static class EventSub4Sub2 extends EventSub4 {
      public EventSub4Sub2(Packet packet) {
         super(packet);
      }
   }
}
