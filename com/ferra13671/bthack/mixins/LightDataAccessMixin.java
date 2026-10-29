package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Pseudo
@Mixin(value = LightDataAccess.class, remap = false)
public class LightDataAccessMixin {
   @ModifyVariable(method = "compute", at = @At("STORE"), name = "bl")
   public int getInt(int num) {
      return SoundManager.getInt3(num);
   }
}
