package com.ferra13671.bthack;

import java.util.HashMap;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.Registries;

class HashMapSub3 extends HashMap<Object, NamedObject> {
   private void handleClass_3611(Fluid fluid) {
      if (fluid != Fluids.EMPTY) {
         this.put(fluid, new NamedObjectSub3(fluid));
      }
   }

   HashMapSub3() {
      Registries.FLUID.forEach(this::handleClass_3611);
   }
}
