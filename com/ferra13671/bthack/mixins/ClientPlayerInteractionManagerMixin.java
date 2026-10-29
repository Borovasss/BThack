package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.AttackEntityEvent;
import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.CancelBlockBreakingEvent;
import com.ferra13671.bthack.ClickSlotEvent;
import com.ferra13671.bthack.ClientPlayerInteractionManagerAttackBlockEvent;
import com.ferra13671.bthack.ClientPlayerInteractionManagerMixinApi;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin implements ClientPlayerInteractionManagerMixinApi {
   @Shadow
   transient BlockPos currentBreakingPos;
   @Unique
   transient boolean boolean1 = false;

   @Inject(
      method = "cancelBlockBreaking",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V",
         shift = Shift.AFTER
      )
   )
   public void handleCallbackInfo(CallbackInfo callbackInfo) {
      BThackClient.getInstance().getEventBus().register(new CancelBlockBreakingEvent(this.currentBreakingPos, false));
   }

   @Inject(method = "clickSlot", at = @At("HEAD"), cancellable = true)
   public void handleInt1(int num, int num2, int num3, SlotActionType slotActionType, PlayerEntity playerEntity, CallbackInfo callbackInfo) {
      if (!this.boolean1) {
         ClickSlotEvent clickSlotEvent = new ClickSlotEvent(num, num2, num3, slotActionType, playerEntity);
         BThackClient.getInstance().getEventBus().register(clickSlotEvent);
         if (clickSlotEvent.getBoolean()) {
            callbackInfo.cancel();
         }
      }
   }

   @Shadow
   public abstract void clickSlot(int var1, int var2, int var3, SlotActionType var4, PlayerEntity var5);

   @Inject(
      method = "updateBlockBreakingProgress",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;sendSequencedPacket(Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/client/network/SequencedPacketCreator;)V",
         ordinal = 1,
         shift = Shift.AFTER
      )
   )
   public void handleClass_23382(BlockPos blockPos, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
      BThackClient.getInstance().getEventBus().register(new CancelBlockBreakingEvent(blockPos, false));
   }

   @Override
   public void handleInt(int num, int num2, int num3, SlotActionType slotActionType, PlayerEntity playerEntity) {
      this.boolean1 = true;
      this.clickSlot(num, num2, num3, slotActionType, playerEntity);
      this.boolean1 = false;
   }

   @Inject(
      method = "attackBlock",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V",
         shift = Shift.AFTER
      )
   )
   public void handleClass_23383(BlockPos blockPos, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
      BThackClient.getInstance().getEventBus().register(new CancelBlockBreakingEvent(blockPos, true));
   }

   @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
   public void handleClass_16574(PlayerEntity playerEntity, Entity entity, CallbackInfo callbackInfo) {
      AttackEntityEvent attackEntityEvent = new AttackEntityEvent(entity);
      BThackClient.getInstance().getEventBus().register(attackEntityEvent);
      if (attackEntityEvent.getBoolean()) {
         callbackInfo.cancel();
      }
   }

   @Inject(
      method = "attackBlock",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;sendSequencedPacket(Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/client/network/SequencedPacketCreator;)V",
         ordinal = 1,
         shift = Shift.BEFORE
      ),
      cancellable = true
   )
   public void handleClass_23385(BlockPos blockPos, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
      ClientPlayerInteractionManagerAttackBlockEvent obj = new ClientPlayerInteractionManagerAttackBlockEvent(blockPos);
      BThackClient.getInstance().getEventBus().register(obj);
      if (obj.getBoolean()) {
         callbackInfoReturnable.setReturnValue(false);
      }
   }
}
