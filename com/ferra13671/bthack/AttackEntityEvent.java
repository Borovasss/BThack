package com.ferra13671.bthack;

import net.minecraft.entity.Entity;

public class AttackEntityEvent extends Event {
   public final transient Entity entity;

   public AttackEntityEvent(Entity entity) {
      this.entity = entity;
   }
}
