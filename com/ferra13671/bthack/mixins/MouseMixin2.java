package com.ferra13671.bthack.mixins;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Mouse.class)
public interface MouseMixin2 {
   @Accessor("x")
   double getDouble();

   @Accessor("y")
   double getDouble1();
}
