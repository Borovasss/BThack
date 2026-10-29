package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
   @Inject(method = "send", at = @At("HEAD"), cancellable = true)
   public void handleClass_2596(Packet packet, ChannelFutureListener channelFutureListener, boolean bool, CallbackInfo callbackInfo) {
      SoundManager.handleCallbackInfo14(callbackInfo);
   }

   @ModifyVariable(method = "send", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   public Packet withClass_25961(Packet packet) {
      return SoundManager.withClass_259616(packet);
   }

   @Inject(
      method = "disconnect",
      at = @At(value = "INVOKE", target = "Lio/netty/channel/Channel;close()Lio/netty/channel/ChannelFuture;", shift = Shift.BEFORE)
   )
   public void handleClass_98122(DisconnectionInfo disconnectionInfo, CallbackInfo callbackInfo) {
      SoundManager.runVoid11();
   }

   @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
   public void handleArg3(ChannelHandlerContext channelHandlerContext, Packet packet, CallbackInfo callbackInfo) {
      SoundManager.handleCallbackInfo6(callbackInfo);
   }

   @ModifyVariable(method = "channelRead0", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   public Packet withClass_25964(Packet packet) {
      return SoundManager.withClass_259615(packet);
   }
}
