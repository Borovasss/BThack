package com.ferra13671.bthack;

import net.minecraft.fluid.Fluid;

public class NamedObjectSub3 extends NamedObject {
   @Override
   public String getName() {
      return ((Fluid)this.getObject()).getDefaultState().getBlockState().getBlock().getName().getString();
   }

   @Override
   public void handleApi2Impl(Api2Impl api2Impl, float flt, float flt2) {
      ((Helper6)api2Impl.getBatch(HudTextures.nameBatchCreator6)).withClass_1799(((Fluid)this.getObject()).getBucketItem().getDefaultStack(), flt, flt2, false);
   }

   public NamedObjectSub3(Fluid fluid) {
      super(fluid);
   }

   @Override
   public String getString1() {
      return ((Fluid)this.getObject()).getDefaultState().getRegistryEntry().getIdAsString();
   }
}
