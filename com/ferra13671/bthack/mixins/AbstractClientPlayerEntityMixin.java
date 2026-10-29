package com.ferra13671.bthack.mixins;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractClientPlayerEntity.class)
public interface AbstractClientPlayerEntityMixin {
   @Accessor("playerListEntry")
   void handleClass_640(PlayerListEntry var1);
}
