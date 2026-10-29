package com.ferra13671.bthack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Helper44 implements MinecraftInstance {
   final transient List<OtherClientPlayerEntityHelper> recentTargets = new CopyOnWriteArrayList();

   @Api8(event = AttackEntityEvent.class, priority = Integer.MIN_VALUE)
   public void onAttack(AttackEntityEvent attackEntityEvent) {
      if (!attackEntityEvent.getBoolean()) {
         for (OtherClientPlayerEntityHelper obj : this.recentTargets) {
            if (attackEntityEvent.entity.getId() == obj.getId()) {
               obj.runVoid();
               attackEntityEvent.runVoid();
            }
         }
      }
   }

   public List<OtherClientPlayerEntityHelper> getRecentTargets() {
      return this.recentTargets;
   }

   @Api8(event = EventSub9.class)
   public void clear() {
      this.recentTargets.forEach(OtherClientPlayerEntityHelper::runVoid3);
      this.recentTargets.clear();
   }

   public void onEntityRemoved(OtherClientPlayerEntityHelper obj) {
      obj.runVoid3();
      this.recentTargets.remove(obj);
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized()) {
         if (!this.recentTargets.isEmpty()) {
            for (OtherClientPlayerEntityHelper obj : this.recentTargets) {
               if (obj.isDead() && obj.deathTime > 20) {
                  this.onEntityRemoved(obj);
               }
            }
         }
      }
   }

   public Helper44() {
      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   public void onEntityAdded(OtherClientPlayerEntityHelper obj) {
      this.recentTargets.add(obj);
      mc.world.addEntity(obj);
   }
}
