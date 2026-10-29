package com.ferra13671.bthack;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooShootBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;

public class Helper32 implements MinecraftInstance {
   final transient ItemStack itemStack;
   final transient DefaultBody defaultBody;

   public List<EnchantmentLevel> getEnchantmentLevels() {
      List<EnchantmentLevel> list = new ArrayList<>();
      if (!this.itemStack.isEmpty()) {
         for (Entry<RegistryEntry<Enchantment>> object2IntMap$Entry : this.defaultBody.getEnchantmentsFunction.apply(this)) {
            list.add(new EnchantmentLevel((RegistryEntry)object2IntMap$Entry.getKey(), object2IntMap$Entry.getIntValue()));
         }
      }

      return list;
   }

   public boolean getItemStack4() {
      return this.itemStack.isIn(ItemTags.SWORDS) || this.itemStack.isIn(ItemTags.AXES);
   }

   public EnchantmentLevel withClass_5321(RegistryKey registryKey) {
      for (EnchantmentLevel enchantmentLevel : this.getEnchantmentLevels()) {
         if (enchantmentLevel.getEnchantment().matchesKey(registryKey)) {
            return enchantmentLevel;
         }
      }

      return null;
   }

   public boolean isClass_53211(RegistryKey registryKey) {
      return this.withClass_5321(registryKey) != null;
   }

   public static Helper32 withClass_1268(Hand hand) {
      return new Helper32(mc.player.getStackInHand(hand));
   }

   public double getClass_26802(BlockState blockState) {
      if (!this.getItemStack3()) {
         return -1.0;
      }

      if (!this.itemStack.isSuitableFor(blockState)
         && (!this.itemStack.isIn(ItemTags.SWORDS) || !(blockState.getBlock() instanceof BambooBlock) && !(blockState.getBlock() instanceof BambooShootBlock))
         && (!(this.itemStack.getItem() instanceof ShearsItem) || !(blockState.getBlock() instanceof LeavesBlock))
         && !blockState.isIn(BlockTags.WOOL)) {
         return -1.0;
      }

      double dbl = 0.0;
      dbl += this.itemStack.getMiningSpeedMultiplier(blockState) * 1000.0F;
      dbl += this.getClass_53213(Enchantments.UNBREAKING);
      dbl += this.getClass_53213(Enchantments.EFFICIENCY);
      dbl += this.getClass_53213(Enchantments.MENDING);
      if (this.itemStack.isIn(ItemTags.SWORDS) && (blockState.getBlock() instanceof BambooBlock || blockState.getBlock() instanceof BambooShootBlock)) {
         dbl += 9000.0F + ((ToolComponent)this.itemStack.get(DataComponentTypes.TOOL)).getSpeed(blockState) * 1000.0F;
      }

      return dbl;
   }

   public boolean isItemStack() {
      return this.itemStack.contains(DataComponentTypes.FOOD);
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public boolean getItemStack3() {
      return this.itemStack.isIn(ItemTags.SWORDS)
         || this.itemStack.isIn(ItemTags.AXES)
         || this.itemStack.isIn(ItemTags.PICKAXES)
         || this.itemStack.isIn(ItemTags.SHOVELS)
         || this.itemStack.isIn(ItemTags.HOES);
   }

   public int getClass_53213(RegistryKey registryKey) {
      for (EnchantmentLevel enchantmentLevel : this.getEnchantmentLevels()) {
         if (enchantmentLevel.getEnchantment().matchesKey(registryKey)) {
            return enchantmentLevel.getLevel();
         }
      }

      return -1;
   }

   public EnchantmentLevel withClass_53214(RegistryKey registryKey) {
      EnchantmentLevel enchantmentLevel = this.withClass_5321(registryKey);
      if (enchantmentLevel == null) {
         throw new NoSuchElementException(String.format("Cannot find enchantment: '%s' in ItemStack: '%s'", registryKey.toString(), this.itemStack.toString()));
      } else {
         return enchantmentLevel;
      }
   }

   public boolean isHelper325(Helper32 helper32) {
      return this.getItemStack().getItem() == helper32.getItemStack().getItem()
         && Objects.equals(this.getItemStack().getComponents(), helper32.getItemStack().getComponents());
   }

   public static Helper32 withClass_1799(ItemStack itemStack) {
      return new Helper32(itemStack);
   }

   public float getItemStack2() {
      return 1.0F - (float)this.itemStack.getDamage() / this.itemStack.getMaxDamage();
   }

   public Helper32(ItemStack itemStack) {
      this.itemStack = itemStack;
      this.defaultBody = DefaultBody.getFromItem(itemStack);
   }
}
