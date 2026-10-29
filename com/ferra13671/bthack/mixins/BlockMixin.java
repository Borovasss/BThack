package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
   @Inject(method = "shouldDrawSide", at = @At("HEAD"), cancellable = true)
   private static void handleClass_2680(BlockState blockState, BlockState blockState2, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleClass_268013(blockState, blockState2, callbackInfoReturnable);
   }
}
