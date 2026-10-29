package com.ferra13671.bthack;

public enum HotbarBody {
   Hotbar,
   Inventory;

   private static HotbarBody[] $values() {
      return new HotbarBody[]{Hotbar, Inventory};
   }

   public static HotbarBody fromSlotId(int num) {
      return num < 9 ? Hotbar : Inventory;
   }
}
