package com.ferra13671.bthack;

import java.util.function.Function;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

public final class SlotTypeUtils implements MinecraftInstance {
   public static int getInt(int num3, int num4, Function<Integer, Integer> function) {
      int num5 = -1;
      int num6 = -1;

      for (int num2 = num3; num2 < num4; num2++) {
         int num = function.apply(num2);
         if (num > num6) {
            num5 = num2;
            num6 = num;
         }
      }

      return num5;
   }

   public static SlotType of(TagKey tagKey) {
      return of(tagKey, 0, 36);
   }

   public static SlotType of(net.minecraft.item.Item item) {
      return of(item, 0, 36);
   }

   public static SlotType of(net.minecraft.item.Item item, int num, int num2) {
      return of(item, num, num2, SlotTypeUtils::getClass_17992);
   }

   private static Integer getClass_68621(TagKey tagKey, Function function, Integer integer) {
      ItemStack itemStack = mc.player.getInventory().getStack(integer);
      return itemStack.isIn(tagKey) ? (Integer)function.apply(itemStack) : -1;
   }

   public static SlotType of(Class class_, int num, int num2) {
      return of(class_, num, num2, SlotTypeUtils::getClass_17994);
   }

   public static SlotType of(TagKey tagKey, int num, int num2) {
      return of(tagKey, num, num2, SlotTypeUtils::getClass_17998);
   }

   public static SlotType of(TagKey tagKey, int num, int num2, Function<ItemStack, Integer> function) {
      return SlotType.of(getInt(num, num2, (integer) -> SlotTypeUtils.getClass_68621(tagKey, function, integer)));
   }

   public static SlotType of(Class class_, int num, int num2, Function<ItemStack, Integer> function) {
      return SlotType.of(getInt(num, num2, (integer) -> SlotTypeUtils.getClass6(class_, function, integer)));
   }

   private static Integer getClass_17992(ItemStack itemStack) {
      return 1;
   }

   public static SlotType of(ItemStack itemStack, int num, int num2) {
      return of(itemStack, num, num2, SlotTypeUtils::getClass_17993);
   }

   private static Integer getClass_17993(ItemStack itemStack) {
      return 1;
   }

   public static SlotType of(net.minecraft.item.Item item, int num, int num2, Function<ItemStack, Integer> function) {
      return SlotType.of(getInt(num, num2, (integer) -> SlotTypeUtils.getClass_17927(item, function, integer)));
   }

   public static SlotType of(Class class_) {
      return of(class_, 0, 36);
   }

   private static Integer getClass_17994(ItemStack itemStack) {
      return 1;
   }

   public static int getClass_17925(net.minecraft.item.Item item, boolean bool) {
      int num2 = 0;

      for (int num = 0; num < 36; num++) {
         if (mc.player.getInventory().getStack(num).getItem() == item) {
            num2++;
         }
      }

      if (bool && mc.player.getOffHandStack().getItem() == item) {
         num2++;
      }

      return num2;
   }

   private static Integer getClass6(Class class_, Function function, Integer integer) {
      ItemStack itemStack = mc.player.getInventory().getStack(integer);
      return itemStack.getItem().getClass().equals(class_) ? (Integer)function.apply(itemStack) : -1;
   }

   private static Integer getClass_17927(net.minecraft.item.Item item, Function function, Integer integer) {
      ItemStack itemStack = mc.player.getInventory().getStack(integer);
      return itemStack.getItem() == item ? (Integer)function.apply(itemStack) : -1;
   }

   SlotTypeUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static SlotType of(ItemStack itemStack) {
      return of(itemStack, 0, 36);
   }

   private static Integer getClass_17998(ItemStack itemStack) {
      return 1;
   }

   private static Integer getHelper329(Helper32 helper32, Function function, Integer integer) {
      ItemStack itemStack = mc.player.getInventory().getStack(integer);
      return Helper32.withClass_1799(itemStack).isHelper325(helper32) ? (Integer)function.apply(itemStack) : -1;
   }

   public static void selectSlot(int num) {
      mc.player.getInventory().setSelectedSlot(num);
      mc.interactionManager.tick();
   }

   public static SlotType of(ItemStack itemStack, int num, int num2, Function<ItemStack, Integer> function) {
      Helper32 helper32 = Helper32.withClass_1799(itemStack);
      return SlotType.of(getInt(num, num2, (integer) -> SlotTypeUtils.getHelper329(helper32, function, integer)));
   }
}
