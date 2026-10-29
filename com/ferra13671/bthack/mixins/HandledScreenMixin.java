package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ItemStackCount;
import com.ferra13671.bthack.ToggleableUtils;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen implements ScreenHandlerProvider {
   @Shadow
   @Final
   protected transient ScreenHandler handler;
   @Shadow
   protected transient @Nullable Slot focusedSlot;

   @Inject(method = "drawMouseoverTooltip", at = @At("HEAD"), cancellable = true)
   public void handleClass_332(DrawContext drawContext, int num, int num2, CallbackInfo callbackInfo) {
      if (this.focusedSlot != null) {
         if (ToggleableUtils.tooltips.isEnabled()) {
            ItemStack itemStack = this.focusedSlot.getStack();
            if (itemStack.contains(DataComponentTypes.CONTAINER)) {
               ContainerComponent containerComponent = (ContainerComponent)itemStack.get(DataComponentTypes.CONTAINER);
               if (containerComponent == null) {
                  return;
               }

               if (containerComponent.stream().toList().isEmpty()) {
                  return;
               }

               callbackInfo.cancel();
               ToggleableUtils.tooltips.setItemStackCount(new ItemStackCount(itemStack, containerComponent.stream().toList()));
            }
         }
      }
   }

   @Inject(method = "mouseDragged", at = @At("HEAD"), cancellable = true)
   public void handleClass_119091(Click click, double dbl, double dbl2, CallbackInfoReturnable callbackInfoReturnable) {
      if (click.button() == 0 && ToggleableUtils.inventoryTweaks.isEnabled() && ToggleableUtils.inventoryTweaks.isClass_17034(this.handler)) {
         Slot slot = this.getSlotAt(click.x(), click.y());
         if (ToggleableUtils.inventoryTweaks.isClass_17351(slot, this.handler)) {
            callbackInfoReturnable.setReturnValue(false);
         }
      }
   }

   @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
   public void handleClass_119092(Click click, boolean bool, CallbackInfoReturnable callbackInfoReturnable) {
      if (click.button() == 0 && ToggleableUtils.inventoryTweaks.isEnabled() && ToggleableUtils.inventoryTweaks.isClass_17032(this.handler)) {
         Slot slot = this.getSlotAt(click.x(), click.y());
         if (ToggleableUtils.inventoryTweaks.isClass_17353(slot, this.handler)) {
            callbackInfoReturnable.setReturnValue(false);
         }
      }
   }

   @Shadow
   protected abstract Slot getSlotAt(double var1, double var3);

   protected HandledScreenMixin(Text text) {
      super(text);
   }
}
