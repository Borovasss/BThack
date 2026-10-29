package com.ferra13671.bthack;

import java.util.HashMap;
import java.util.function.Supplier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

class HashMapSub2 extends HashMap<RegistryKey<Enchantment>, Supplier<Float>> {
   final transient AutoArmor autoArmor;

   HashMapSub2(AutoArmor autoArmor) {
      this.autoArmor = autoArmor;
      this.put(Enchantments.PROTECTION, this.autoArmor.protection::getValue);
      this.put(Enchantments.BLAST_PROTECTION, this.autoArmor.blastProtection::getValue);
      this.put(Enchantments.THORNS, this.autoArmor.thorns::getValue);
      this.put(Enchantments.UNBREAKING, this.autoArmor.unbreaking::getValue);
      this.put(Enchantments.MENDING, this.autoArmor.mending::getValue);
      this.put(Enchantments.DEPTH_STRIDER, this.autoArmor.depthStrider::getValue);
      this.put(Enchantments.RESPIRATION, this.autoArmor.respiration::getValue);
      this.put(Enchantments.FEATHER_FALLING, this.autoArmor.featherFalling::getValue);
      this.put(Enchantments.BINDING_CURSE, this.autoArmor.bindingCurse::getValue);
      this.put(Enchantments.VANISHING_CURSE, this.autoArmor.vanishingCurse::getValue);
   }
}
