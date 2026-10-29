package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import java.util.List;
import net.caffeinemc.mods.sodium.fabric.model.FabricModelAccess;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FabricModelAccess.class)
public class FabricModelAccessMixin {
   @Inject(method = "getQuads", at = @At("HEAD"), cancellable = true)
   public void handleClass_1920(
      BlockRenderView blockRenderView,
      BlockPos blockPos,
      BlockModelPart blockModelPart,
      BlockState blockState,
      Direction direction,
      Random random,
      BlockRenderLayer blockRenderLayer,
      CallbackInfoReturnable callbackInfoReturnable
   ) {
      SoundManager.handleClass_26801(blockState, callbackInfoReturnable);
   }
}
