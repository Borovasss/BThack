package com.ferra13671.bthack.mixins;

import com.mojang.blaze3d.buffers.GpuBuffer;
import net.minecraft.client.render.SkyRendering;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SkyRendering.class)
public interface SkyRenderingMixin2 {
   @Mutable
   @Accessor("starIndexCount")
   void handleInt(int var1);

   @Invoker("createStars")
   GpuBuffer applyGpuBuffer1();

   @Mutable
   @Accessor("starVertexBuffer")
   void handleGpuBuffer2(GpuBuffer var1);

   @Accessor("starVertexBuffer")
   GpuBuffer applyGpuBuffer3();
}
