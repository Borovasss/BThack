package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
   @Redirect(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getPitch(F)F"))
   public float getClass_746(ClientPlayerEntity clientPlayerEntity, float flt) {
      return ToggleableUtils.freeCamera.isEnabled()
         ? ToggleableUtils.freeCamera.helper3.getFloat()
         : (
            BThackClient.getInstance().getPrioritySortedHelper().hasSubscribers()
               ? clientPlayerEntity.getPitch(flt)
               : BThackClient.getInstance().getPrioritySortedHelper().getHelper3().getFloat()
         );
   }

   @Inject(
      method = "renderFirstPersonItem",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;I)V",
         shift = Shift.BEFORE
      )
   )
   public void handleClass_7421(
      AbstractClientPlayerEntity obj,
      float flt,
      float flt2,
      Hand hand,
      float flt3,
      ItemStack itemStack,
      float flt4,
      MatrixStack matrixStack,
      OrderedRenderCommandQueue obj2,
      int num,
      CallbackInfo callbackInfo
   ) {
      if (ToggleableUtils.handView.isEnabled()) {
         ToggleableUtils.handView.applyHandTransform(matrixStack, hand);
      }
   }

   @Redirect(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getLerpedPitch(F)F"))
   public float getClass_7462(ClientPlayerEntity clientPlayerEntity, float flt) {
      return ToggleableUtils.freeCamera.isEnabled()
         ? ToggleableUtils.freeCamera.helper3.getFloat()
         : (
            BThackClient.getInstance().getPrioritySortedHelper().hasSubscribers()
               ? clientPlayerEntity.getLerpedPitch(flt)
               : MathHelper.lerp(
                  flt,
                  BThackClient.getInstance().getPrioritySortedHelper().getHelper3().getFloat2(),
                  BThackClient.getInstance().getPrioritySortedHelper().getHelper3().getFloat()
               )
         );
   }

   @Redirect(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getYaw(F)F"))
   public float getClass_7463(ClientPlayerEntity clientPlayerEntity, float flt) {
      return ToggleableUtils.freeCamera.isEnabled()
         ? ToggleableUtils.freeCamera.helper3.getFloat4()
         : (
            BThackClient.getInstance().getPrioritySortedHelper().hasSubscribers()
               ? clientPlayerEntity.getYaw(flt)
               : BThackClient.getInstance().getPrioritySortedHelper().getHelper3().getFloat4()
         );
   }

   @ModifyArgs(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F", ordinal = 1))
   public void handleArgs4(Args args) {
      if (ToggleableUtils.freeCamera.isEnabled()) {
         args.set(1, ToggleableUtils.freeCamera.helper3.getFloat8());
         args.set(2, ToggleableUtils.freeCamera.helper3.getFloat7());
      }
   }

   @Inject(
      method = "renderFirstPersonItem",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderArmHoldingItem(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;IFFLnet/minecraft/util/Arm;)V",
         shift = Shift.BEFORE
      )
   )
   public void handleClass_7425(
      AbstractClientPlayerEntity obj,
      float flt,
      float flt2,
      Hand hand,
      float flt3,
      ItemStack itemStack,
      float flt4,
      MatrixStack matrixStack,
      OrderedRenderCommandQueue obj2,
      int num,
      CallbackInfo callbackInfo
   ) {
      if (ToggleableUtils.handView.isEnabled()) {
         ToggleableUtils.handView.applyHandTransform(matrixStack, hand);
      }
   }

   @ModifyArgs(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F", ordinal = 0))
   public void handleArgs6(Args args) {
      if (ToggleableUtils.freeCamera.isEnabled()) {
         args.set(1, ToggleableUtils.freeCamera.helper3.getFloat5());
         args.set(2, ToggleableUtils.freeCamera.helper3.getFloat3());
      }
   }
}
