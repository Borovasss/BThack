package com.ferra13671.bthack;

public class NamedObjectSub1 extends NamedObject {
   @Override
   public String getName() {
      return ((net.minecraft.item.Item)this.getObject()).getName().getString();
   }

   public NamedObjectSub1(net.minecraft.item.Item item) {
      super(item);
   }

   @Override
   public void handleApi2Impl(Api2Impl api2Impl, float flt, float flt2) {
      ((Helper6)api2Impl.getBatch(HudTextures.nameBatchCreator6))
         .withClass_1799(((net.minecraft.item.Item)this.getObject()).getDefaultStack(), flt, flt2, false);
   }

   @Override
   public String getString1() {
      return ((net.minecraft.item.Item)this.getObject()).getDefaultStack().getRegistryEntry().getIdAsString();
   }
}
