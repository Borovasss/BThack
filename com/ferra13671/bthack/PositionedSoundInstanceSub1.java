package com.ferra13671.bthack;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.client.sound.Sound.RegistrationType;
import net.minecraft.client.sound.SoundInstance.AttenuationType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.FloatSupplier;
import net.minecraft.util.math.random.Random;

public class PositionedSoundInstanceSub1 extends PositionedSoundInstance {
   static final transient Random random = Random.create();
   static final transient FloatSupplier floatSupplier = PositionedSoundInstanceSub1::getClass_5819;
   final transient WeightedSoundSet weightedSoundSet = new WeightedSoundSet(this.id, null);

   public PositionedSoundInstanceSub1(Identifier identifier, float flt) {
      super(identifier, SoundCategory.MASTER, flt, 1.0F, random, false, 0, AttenuationType.NONE, 0.0, 0.0, 0.0, true);
      this.weightedSoundSet.add(new Sound(identifier, floatSupplier, floatSupplier, 1, RegistrationType.FILE, false, false, 16));
   }

   public WeightedSoundSet getSoundSet(net.minecraft.client.sound.SoundManager soundManager) {
      this.sound = this.weightedSoundSet.getSound(random);
      return this.weightedSoundSet;
   }

   private static float getClass_5819(Random random) {
      return 1.0F;
   }
}
