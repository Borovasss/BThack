package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {
   @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
   public void handleClass_4587(
      MatrixStack matrixStack,
      OrderedRenderCommandQueue obj,
      ItemStack itemStack,
      EquipmentSlot equipmentSlot,
      int num,
      BipedEntityRenderState bipedEntityRenderState,
      CallbackInfo callbackInfo
   ) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.armor.getValue()) {
         callbackInfo.cancel();
      }
   }
}
