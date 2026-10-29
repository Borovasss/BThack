package com.ferra13671.bthack;

class Helper12 {
   static final transient int[] int1 = new int[GammaBody.values().length];

   static {
      try {
         int1[GammaBody.Potion.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         int1[GammaBody.BlockLight.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
