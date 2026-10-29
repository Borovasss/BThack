package com.ferra13671.bthack.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
public interface MinecraftClientMixin2 {
   @Accessor("itemUseCooldown")
   void handleInt(int var1);

   @Accessor("itemUseCooldown")
   int getInt1();
}
