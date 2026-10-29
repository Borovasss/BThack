package com.ferra13671.bthack;

import com.mojang.authlib.GameProfile;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class OtherClientPlayerEntityHelper extends OtherClientPlayerEntity implements MinecraftInstance {
   transient String string1;
   transient boolean boolean1 = false;
   transient boolean boolean2 = false;
   transient boolean boolean3 = false;

   public void setBoolean2(boolean bool) {
      this.boolean2 = bool;
   }

   public void runVoid() {
      if (!this.boolean1 && this.hurtTime <= 0) {
         mc.world.playSound(mc.player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
         if (mc.player.fallDistance > 0.0) {
            mc.world.playSound(mc.player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
         }

         if (mc.player.getAttackCooldownProgress(0.0F) >= 0.85) {
            this.setBoolean(this.getClass_17997(mc.player.getMainHandStack(), this));
         } else {
            this.setBoolean(1.0F);
         }

         new EntityDamageS2CPacket(this, mc.world.getDamageSources().generic()).apply(mc.player.networkHandler);
      }
   }

   public void handleFloat1(float flt, float flt2) {
      this.setYaw(flt);
      this.setPitch(flt2);
      this.headYaw = flt;
      this.bodyYaw = flt;
   }

   public void handleFloat2(float flt) {
      this.getAttributes().getCustomInstance(EntityAttributes.MAX_HEALTH).setBaseValue(flt);
      this.setHealth(Math.min(flt, this.getHealth()));
   }

   public OtherClientPlayerEntityHelper(UUID uUID) {
      this(uUID, "FakePlayer_" + uUID);
   }

   public String getString() {
      return this.string1;
   }

   public boolean getBoolean2() {
      return this.boolean2;
   }

   public void setBoolean3(boolean bool) {
      this.boolean1 = bool;
   }

   public void setString(String string) {
      this.string1 = string;
      Utils9.handleClass_16579(this, new GameProfile(this.uuid, this.string1));
   }

   public OtherClientPlayerEntityHelper(UUID uUID, String string) {
      super(mc.world, new GameProfile(uUID, string));
      this.string1 = string;
      Utils9.handleClass_74214(this, new PlayerListEntry(this.getGameProfile(), false));
      this.setPose(EntityPose.STANDING);
      this.setId(new AtomicInteger((int)(Math.random() * 1000000.0)).incrementAndGet());
      this.handleFloat1(mc.player.getYaw(), mc.player.getPitch());
      this.handleClass_2435(mc.player.getEntityPos());
      this.getAttributes().unpack(mc.player.getAttributes().pack());
   }

   public void setHealth(float flt) {
      super.setHealth(Math.min(flt, this.getMaxHealth()));
   }

   public void tick() {
      super.tick();
      if (this.boolean2 && this.getStackInHand(Hand.OFF_HAND).getItem() != Items.TOTEM_OF_UNDYING) {
         this.runVoid4();
      }

      if (this.boolean3) {
         new EntityStatusS2CPacket(this, (byte)35).apply(mc.player.networkHandler);
         this.boolean3 = false;
      }
   }

   public void runVoid3() {
      mc.world.removeEntity(this.getId(), RemovalReason.DISCARDED);
   }

   public OtherClientPlayerEntityHelper() {
      this(UUID.randomUUID());
   }

   private void runVoid4() {
      this.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.TOTEM_OF_UNDYING));
   }

   private void setBoolean(float flt) {
      this.onDamaged(mc.world.getDamageSources().generic());
      float flt2 = this.getAbsorptionAmount() - flt;
      this.setAbsorptionAmount(Math.max(0.0F, flt2));
      if (flt2 < 0.0F) {
         this.setHealth(this.getHealth() + flt2);
      }

      if (this.isDead() && Utils9.isClass_13097(this, mc.world.getDamageSources().generic())) {
         this.setHealth(10.0F);
         this.getAttributes().getCustomInstance(EntityAttributes.MAX_ABSORPTION).setBaseValue(10.0);
         this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), this);
         this.setAbsorptionAmount(10.0F);
         this.boolean3 = true;
         if (this.boolean2) {
            this.runVoid4();
         }
      }
   }

   public void handleClass_2435(Vec3d vec3d) {
      this.setPosition(vec3d);
      this.resetPosition();
      this.refreshPosition();
   }

   public void runVoid6() {
      for (int num = 0; num < mc.player.getInventory().size(); num++) {
         this.getInventory().setStack(num, mc.player.getInventory().getStack(num).copy());
      }

      this.getInventory().setSelectedSlot(mc.player.getInventory().getSelectedSlot());
   }

   public boolean getBoolean() {
      return this.boolean1;
   }

   private float getClass_17997(ItemStack itemStack, PlayerEntity playerEntity) {
      if (mc.player == null) {
         return 0.0F;
      }

      double dbl = mc.player.getAttributes().hasAttribute(EntityAttributes.ATTACK_DAMAGE)
         ? mc.player.getAttributeBaseValue(EntityAttributes.ATTACK_DAMAGE)
         : 0.0;
      float flt = (float)((AttributeModifiersComponent)itemStack.getItem()
            .getDefaultStack()
            .getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT))
         .applyOperations(EntityAttributes.ATTACK_DAMAGE, dbl, EquipmentSlot.MAINHAND);
      if (mc.player.fallDistance > 0.0) {
         flt += flt / 2.0F;
      }

      if (mc.player.hasStatusEffect(StatusEffects.STRENGTH)) {
         int num = Objects.requireNonNull(mc.player.getStatusEffect(StatusEffects.STRENGTH)).getAmplifier() + 1;
         flt += 3 * num;
      }

      return DamageUtil.getDamageLeft(
         playerEntity,
         flt,
         mc.world.getDamageSources().generic(),
         playerEntity.getArmor(),
         (float)playerEntity.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).getValue()
      );
   }
}
