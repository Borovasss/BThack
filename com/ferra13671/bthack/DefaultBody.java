package com.ferra13671.bthack;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;

public enum DefaultBody {
   Default(helper32 -> helper32.getItemStack().getEnchantments().getEnchantmentEntries()),
   Book(
      helper32 -> ((ItemEnchantmentsComponent)helper32.getItemStack().getOrDefault(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT))
         .getEnchantmentEntries()
   );

   public final Function<Helper32, Set<Entry<RegistryEntry<Enchantment>>>> getEnchantmentsFunction;

   public static DefaultBody getFromItem(ItemStack itemStack) {
      return itemStack.getItem() == Items.ENCHANTED_BOOK ? Book : Default;
   }

   private static DefaultBody[] $values() {
      return new DefaultBody[]{Default, Book};
   }

   DefaultBody(Function<Helper32, Set<Entry<RegistryEntry<Enchantment>>>> function) {
      this.getEnchantmentsFunction = function;
   }
}
