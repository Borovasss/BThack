package com.ferra13671.bthack.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ClientPlayerEntity.class)
public interface ClientPlayerEntityMixin2 {
   @Invoker("isBlockedFromSprinting")
   boolean isBoolean();
}
