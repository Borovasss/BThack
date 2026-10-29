package com.ferra13671.bthack;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

public record Item(net.minecraft.item.Item item) {

   public static Item withString(String string) {
      net.minecraft.item.Item item2 = null;

      try {
         int num = Integer.parseInt(string);
         net.minecraft.item.Item item = (net.minecraft.item.Item)Registries.ITEM.get(num);
         if (num != 0 && Registries.ITEM.getRawId(item) == 0) {
            return null;
         }

         item2 = item;
      } catch (Exception var5) {
      }

      try {
         item2 = (net.minecraft.item.Item)Registries.ITEM.getOptionalValue(Identifier.of(string)).orElse(null);
      } catch (InvalidIdentifierException var4) {
      }

      return item2 == null ? null : new Item(item2);
   }

   public net.minecraft.item.Item getItem() {
      return this.item;
   }
}
