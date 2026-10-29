package com.ferra13671.bthack;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;

public record EnchantmentLevel(RegistryEntry enchantment, int level) {

   public int getLevel() {
      return this.level;
   }

   public RegistryEntry getEnchantment() {
      return this.enchantment;
   }
}
