package com.ferra13671.bthack;

import net.minecraft.entity.player.PlayerEntity;

@ModuleInfo(name = "No Friend Damage", category = "combat")
public class NoFriendDamage extends Module {
   @Api8(event = AttackEntityEvent.class, priority = Integer.MAX_VALUE)
   public void onAttackEntity(AttackEntityEvent attackEntityEvent) {
      if (attackEntityEvent.entity instanceof PlayerEntity playerEntity && PlayerEntityUtils.isClass_16571(playerEntity)) {
         attackEntityEvent.runVoid();
      }
   }
}
