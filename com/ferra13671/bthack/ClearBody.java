package com.ferra13671.bthack;

import net.minecraft.world.biome.Biome.Precipitation;

public enum ClearBody {
   Clear(true, 0.0F, 0.0F, Precipitation.NONE),
   Rain(false, 1.0F, 0.0F, Precipitation.RAIN),
   Thunder(false, 2.0F, 1.0F, Precipitation.RAIN),
   Snow(false, 1.0F, 0.0F, Precipitation.SNOW);

   public final boolean clear;
   public final float rainGradient;
   public final float thunderGradient;
   public final Precipitation precipitation;

   ClearBody(boolean bool, float flt, float flt2, Precipitation biome$Precipitation) {
      this.clear = bool;
      this.rainGradient = flt;
      this.thunderGradient = flt2;
      this.precipitation = biome$Precipitation;
   }

   private static ClearBody[] $values() {
      return new ClearBody[]{Clear, Rain, Thunder, Snow};
   }
}
