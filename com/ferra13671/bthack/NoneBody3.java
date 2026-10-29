package com.ferra13671.bthack;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKey;

public enum NoneBody3 {
   None(null),
   Fortune(Enchantments.FORTUNE),
   SilkTouch(Enchantments.SILK_TOUCH);

   public final RegistryKey enchantment;

   NoneBody3(RegistryKey registryKey) {
      this.enchantment = registryKey;
   }

   private static NoneBody3[] $values() {
      return new NoneBody3[]{None, Fortune, SilkTouch};
   }

   public double getExtraPriority(Helper32 helper32) {
      if (this.enchantment == null) {
         return 0.0;
      } else {
         return helper32.isClass_53211(this.enchantment) ? 10000 * helper32.getClass_53213(this.enchantment) : 0.0;
      }
   }
}
