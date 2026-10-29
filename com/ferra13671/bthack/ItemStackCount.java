package com.ferra13671.bthack;

import java.util.List;
import net.minecraft.item.ItemStack;

public record ItemStackCount(ItemStack itemstack, List stacks) {

   public ItemStack getItemstack() {
      return this.itemstack;
   }

   public List getStacks() {
      return this.stacks;
   }
}
