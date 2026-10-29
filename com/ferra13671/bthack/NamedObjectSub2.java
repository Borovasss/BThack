package com.ferra13671.bthack;

import net.minecraft.block.Block;

public class NamedObjectSub2 extends NamedObject {
   public NamedObjectSub2(Block block) {
      super(block);
   }

   @Override
   public void handleApi2Impl(Api2Impl api2Impl, float flt, float flt2) {
      ((Helper6)api2Impl.getBatch(HudTextures.nameBatchCreator6)).withClass_1799(((Block)this.getObject()).asItem().getDefaultStack(), flt, flt2, false);
   }

   @Override
   public String getName() {
      return ((Block)this.getObject()).asItem().getName().getString();
   }

   @Override
   public String getString1() {
      return ((Block)this.getObject()).asItem().getDefaultStack().getRegistryEntry().getIdAsString();
   }
}
