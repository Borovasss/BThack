package com.ferra13671.bthack.mixins;

import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WorldRenderer.class)
public interface WorldRendererMixin2 {
   @Accessor("skyRendering")
   SkyRendering applyClass_9975();
}
