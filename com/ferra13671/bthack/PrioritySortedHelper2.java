package com.ferra13671.bthack;

import java.util.function.Function;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientTickEndC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PrioritySortedHelper2 extends PrioritySorted implements MinecraftInstance {
   final transient List<Packet<?>> packetBuffer = new CopyOnWriteArrayList<>();
   transient boolean sending = false;

   public boolean unregister(MoveOptions moveOptions) {
      boolean bool = super.unsubscribe(moveOptions);
      if (this.subscribers.isEmpty()) {
         this.releaseBuffer();
      }

      return bool;
   }

   private void releaseBuffer() {
      if (!BThackClient.isInitialized()) {
         this.sending = true;

         for (Packet<?> packet : this.packetBuffer) {
            mc.getNetworkHandler().sendPacket(packet);
         }

         this.sending = false;
      }

      this.packetBuffer.clear();
   }

   protected void resetIfCurrent(MoveOptions moveOptions) {
      if (moveOptions == this.current) {
         this.releaseBuffer();
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick(MinecraftClientTickEvent obj) {
      if ((this.subscribers.isEmpty() || BThackClient.isInitialized()) && !this.packetBuffer.isEmpty()) {
         this.releaseBuffer();
      }
   }

   @Api8(event = EventSub4.EventSub4Sub1.class)
   public void onPacket(EventSub4.EventSub4Sub1 eventSub4Sub1) {
      if (!BThackClient.isInitialized() && !this.subscribers.isEmpty() && !this.sending) {
         if (!(Boolean)((MoveOptions)this.current).getOnlymovement().get()
            || eventSub4Sub1.getPacket() instanceof PlayerMoveC2SPacket
            || eventSub4Sub1.getPacket() instanceof ClientTickEndC2SPacket) {
            this.packetBuffer.add(eventSub4Sub1.getPacket());
            eventSub4Sub1.runVoid();
         }
      }
   }

   public PrioritySortedHelper2() {
      super((Function<Object, Integer>)(Object o) -> ((MoveOptions)o).getPriority());
      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   @Override
   public boolean unsubscribe(Object var1) {
      return this.unregister((MoveOptions)var1);
   }
}
