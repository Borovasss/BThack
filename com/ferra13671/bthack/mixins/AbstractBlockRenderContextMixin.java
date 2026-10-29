package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import com.ferra13671.ferraguard.annotations.KeepAllFieldsName;
import net.caffeinemc.mods.sodium.client.render.model.AbstractBlockRenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@KeepAllFieldsName
@Pseudo
@Mixin(AbstractBlockRenderContext.class)
public class AbstractBlockRenderContextMixin {
   @Shadow
   protected transient BlockState state;
   @Shadow
   protected transient BlockRenderView level;
   @Shadow
   protected transient BlockPos pos;

   @Inject(at = @At("HEAD"), method = "shouldDrawSide", cancellable = true)
   public void handleClass_2350(Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
      SoundManager.handleClass_268012(this.state, this.level, this.pos, direction, callbackInfoReturnable);
   }
}
