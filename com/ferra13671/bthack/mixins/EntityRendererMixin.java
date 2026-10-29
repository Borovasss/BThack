package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import com.ferra13671.bthack.ToggleableUtils;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
   @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
   public void handleClass_1297(Entity entity, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.nametags.isEnabled() && entity instanceof PlayerEntity) {
         callbackInfoReturnable.setReturnValue(null);
      }
   }

   @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
   public void handleClass_12971(Entity entity, Frustum frustum, double dbl, double dbl2, double dbl3, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.noRender.isEnabled()
         && (
            (Boolean)ToggleableUtils.noRender.fallingBlocks.getValue() && entity instanceof FallingBlockEntity
               || (Boolean)ToggleableUtils.noRender.armorStands.getValue() && entity instanceof ArmorStandEntity
         )) {
         callbackInfoReturnable.setReturnValue(false);
      }
   }

   @ModifyReturnValue(method = "getBlockLight", at = @At("RETURN"))
   public int getInt2(int num) {
      return SoundManager.getInt3(num);
   }

   @ModifyExpressionValue(
      method = "updateRenderState",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/entity/EntityRenderer;getBlockLight(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/BlockPos;)I"
      )
   )
   public int getInt3(int num) {
      return SoundManager.getInt3(num);
   }
}
