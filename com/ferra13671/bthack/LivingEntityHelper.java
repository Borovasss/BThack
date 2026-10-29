package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LivingEntityHelper implements MinecraftInstance {
   final transient Aura mcModule;

   private boolean isClass_1297(Entity entity) {
      float flt = EntityUtils.getVector3f4(
         mc.player.getEyePos().toVector3f(),
         new float[]{mc.gameRenderer.getCamera().getYaw(), mc.gameRenderer.getCamera().getPitch()},
         entity.getBoundingBox().getCenter().toVector3f()
      );
      return flt < (Float)this.mcModule.fov.getValue() / 2.0F;
   }

   private boolean isClass_13091(LivingEntity livingEntity) {
      return livingEntity.isAlive() && this.isClass_13096(livingEntity) && this.isClass_1297(livingEntity);
   }

   public void runVoid() {
      if (this.mcModule.getEating()) {
         this.mcModule.runVoid2();
      } else {
         if (this.mcModule.livingEntity != null) {
            this.runVoid5();
         }

         if (!(Boolean)this.mcModule.lockTarget.getValue() || this.mcModule.livingEntity == null) {
            LivingEntity livingEntity = null;
            if ((Boolean)this.mcModule.players.getValue()) {
               livingEntity = mc.world.getPlayers().stream().filter(this::isClass_7424).max(Comparator.comparing(this::getClass_13093)).orElse(null);
            }

            if (livingEntity == null && (Boolean)this.mcModule.mobs.getValue()) {
               List<LivingEntity> list = new ArrayList<>();

               for (Entity entity : mc.world.getEntities()) {
                  if (entity instanceof MobEntity mobEntity && this.isClass_13091(mobEntity) && this.isClass_13098(mobEntity)) {
                     list.add(mobEntity);
                  }
               }

               livingEntity = list.stream().max(Comparator.comparing(this::getClass_13093)).orElse(null);
            }

            if (this.mcModule.livingEntity == null || this.getClass_13093(livingEntity) > this.getClass_13093(this.mcModule.livingEntity)) {
               this.mcModule.livingEntity = livingEntity;
            }

            if (this.mcModule.livingEntity == null) {
               this.mcModule.runVoid2();
            }
         }
      }
   }

   private int getClass_13093(LivingEntity livingEntity) {
      int num = (Integer)((DistanceBody)this.mcModule.priority.getValue()).priorityFunction.apply(livingEntity, this.mcModule);
      return this.mcModule.enemyPriority.getValue() && livingEntity instanceof PlayerEntity playerEntity && PlayerEntityUtils.isClass_16573(playerEntity)
         ? Integer.MAX_VALUE
         : num;
   }

   private boolean isClass_7424(AbstractClientPlayerEntity obj) {
      return this.isClass_13091(obj) && this.isClass_16577(obj);
   }

   private void runVoid5() {
      if (!this.isClass_13091(this.mcModule.livingEntity)
         || !(
            this.mcModule.livingEntity instanceof PlayerEntity playerEntity ? this.isClass_16577(playerEntity) : this.isClass_13098(this.mcModule.livingEntity)
         )) {
         this.mcModule.livingEntity = null;
      }
   }

   public LivingEntityHelper(Aura aura) {
      this.mcModule = aura;
   }

   private boolean isClass_13096(LivingEntity livingEntity) {
      return EntityUtils.getClass_12975(mc.player, livingEntity.getBoundingBox().getCenter().toVector3f()) < (Float)this.mcModule.targetRange.getValue();
   }

   private boolean isClass_16577(PlayerEntity playerEntity) {
      return playerEntity != mc.player
         && ((Boolean)this.mcModule.teammates.getValue() || !mc.player.isTeammate(playerEntity))
         && ((Boolean)this.mcModule.friends.getValue() || !PlayerEntityUtils.isClass_16571(playerEntity))
         && ((Boolean)this.mcModule.invisibles.getValue() || !this.mcModule.isClass_16573(playerEntity));
   }

   private boolean isClass_13098(LivingEntity livingEntity) {
      return ((Boolean)this.mcModule.hostiles.getValue() || !(livingEntity instanceof HostileEntity))
         && ((Boolean)this.mcModule.passives.getValue() || !(livingEntity instanceof PassiveEntity))
         && ((Boolean)this.mcModule.golems.getValue() || !(livingEntity instanceof GolemEntity));
   }
}
