package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.gltextureutils.Pair;
import com.mojang.blaze3d.buffers.GpuBuffer;
import java.awt.Color;
import net.minecraft.client.render.SkyRendering;
import org.joml.Vector4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkyRendering.class)
public class SkyRenderingMixin {
   @Shadow
   transient int starIndexCount;

   @ModifyArg(
      method = "renderStars",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gl/DynamicUniforms;write(Lorg/joml/Matrix4fc;Lorg/joml/Vector4fc;Lorg/joml/Vector3fc;Lorg/joml/Matrix4fc;)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;"
      ),
      index = 1
   )
   public Vector4fc withVector4fc(Vector4fc vector4fc) {
      return (Vector4fc)(ToggleableUtils.ambience.isEnabled() && ToggleableUtils.ambience.custom7.getValue() && ToggleableUtils.ambience.custom8.getValue()
         ? RenderColor.of((Color)ToggleableUtils.ambience.color.getValue()).toVector4f()
         : vector4fc);
   }

   @Inject(method = "createStars", at = @At("HEAD"), cancellable = true)
   public void setField_58198(CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.ambience.isEnabled() && (Boolean)ToggleableUtils.ambience.custom7.getValue()) {
         Pair<GpuBuffer, Integer> pair = ToggleableUtils.ambience.getCustom2();
         this.starIndexCount = (Integer)pair.getRight();
         callbackInfoReturnable.setReturnValue((GpuBuffer)pair.getLeft());
      }
   }
}
