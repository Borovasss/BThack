package com.ferra13671.bthack;

import java.util.HashMap;
import net.minecraft.registry.Registries;

class HashMapSub4 extends HashMap<Object, NamedObject> {
   HashMapSub4() {
      Registries.ITEM.forEach(this::handleClass_1792);
   }

   private void handleClass_1792(net.minecraft.item.Item item) {
      this.put(item, new NamedObjectSub1(item));
   }
}
