package com.ferra13671.bthack.mixins;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil.Key;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(KeyBinding.class)
public interface KeyBindingMixin {
   @Accessor("boundKey")
   Key applyClass_3675class_306();
}
