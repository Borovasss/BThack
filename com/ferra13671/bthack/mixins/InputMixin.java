package com.ferra13671.bthack.mixins;

import net.minecraft.client.input.Input;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Input.class)
public interface InputMixin {
   @Accessor("movementVector")
   void handleClass_241(Vec2f var1);
}
