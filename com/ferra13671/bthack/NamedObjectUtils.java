package com.ferra13671.bthack;

import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;

public final class NamedObjectUtils {
   public static final transient HashMap<Object, NamedObject> hashMap1 = new HashMapSub4();
   public static final transient HashMap<Object, NamedObject> hashMap2 = new HashMapSub1();
   public static final transient HashMap<Object, NamedObject> hashMap3 = new HashMapSub3();

   public static NamedObject set(Object object) {
      return object instanceof net.minecraft.item.Item item
         ? (NamedObject)hashMap1.get(item)
         : (object instanceof Block block ? (NamedObject)hashMap2.get(block) : (object instanceof Fluid fluid ? (NamedObjectSub3)hashMap3.get(fluid) : null));
   }

   NamedObjectUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
