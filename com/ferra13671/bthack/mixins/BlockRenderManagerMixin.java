package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.model.BlockStateModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {
   @Inject(method = "getModel", at = @At("HEAD"), cancellable = true)
   public void handleClass_2680(BlockState blockState, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleClass_268017(blockState, callbackInfoReturnable);
   }
}
