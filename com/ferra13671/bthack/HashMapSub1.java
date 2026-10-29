package com.ferra13671.bthack;

import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

class HashMapSub1 extends HashMap<Object, NamedObject> {
   HashMapSub1() {
      Registries.BLOCK.forEach(this::handleClass_2248);
   }

   private void handleClass_2248(Block block) {
      this.put(block, new NamedObjectSub2(block));
   }
}
