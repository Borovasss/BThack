package com.ferra13671.bthack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.packet.Packet;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class SoundManager {
   static transient EventSub4.EventSub4Sub1 eventSub4Sub11;
   static transient EventSub4.EventSub4Sub2 eventSub4Sub21;

   public static void handleClass_2680(BlockState blockState, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled()) {
         callbackInfoReturnable.setReturnValue(((List)ToggleableUtils.xray.fluids.getValue()).contains(blockState.getFluidState().getFluid()));
      }
   }

   public static void handleClass_26801(BlockState blockState, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled() && !((List)ToggleableUtils.xray.blocks.getValue()).contains(blockState.getBlock())) {
         callbackInfoReturnable.setReturnValue(List.of());
      }
   }

   public static void handleClass_36102(FluidState fluidState, FluidState fluidState2, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled()) {
         if (((List)ToggleableUtils.xray.fluids.getValue()).contains(fluidState.getFluid())) {
            callbackInfoReturnable.setReturnValue(!((List)ToggleableUtils.xray.fluids.getValue()).contains(fluidState2.getFluid()));
         } else {
            callbackInfoReturnable.setReturnValue(true);
         }
      }
   }

   public static int getInt3(int num) {
      EventSub11 eventSub11 = new EventSub11(num);
      BThackClient.getInstance().getEventBus().register(eventSub11);
      return eventSub11.int1;
   }

   public static void handleString4(String string, CallbackInfo callbackInfo) {
      String string2 = (String)BThackClient.getInstance().getCommandPrefixSetting().getValue();
      if (string.startsWith(string2)) {
         try {
            BThackClient.getInstance()
               .getCommandManager()
               .getCommandDispatcher()
               .execute(string.substring(string2.length()), BThackClient.getInstance().getCommandManager().getClientCommandSource());
         } catch (CommandSyntaxException commandSyntaxException) {
            ChatUtils.handleString(Formatting.RED + commandSyntaxException.getMessage());
         }

         callbackInfo.cancel();
      }
   }

   public static InputStream withClass_29605(Identifier identifier2, ResourceFactory resourceFactory) throws IOException {
      if (identifier2.getNamespace().equals("bthack") && identifier2.getPath().startsWith("sounds/sound-manager/sounds/")) {
         Identifier identifier = Identifier.of(identifier2.getNamespace(), identifier2.getPath().substring(7, identifier2.getPath().length() - 4));
         return new ByteArrayInputStream(BThackClient.getInstance().getIdentifierHelper().withClass_29602(identifier));
      } else {
         return resourceFactory.open(identifier2);
      }
   }

   public static void handleCallbackInfo6(CallbackInfo callbackInfo) {
      if (eventSub4Sub21 != null && eventSub4Sub21.getBoolean()) {
         callbackInfo.cancel();
      }
   }

   public static void handleClass_36107(FluidState fluidState, CallbackInfo callbackInfo) {
      if (ToggleableUtils.xray.isEnabled() && !((List)ToggleableUtils.xray.fluids.getValue()).contains(fluidState.getFluid())) {
         callbackInfo.cancel();
      }
   }

   public static void handleClass_26808(BlockState blockState, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled() && ((List)ToggleableUtils.xray.fluids.getValue()).contains(blockState.getFluidState().getFluid())) {
         callbackInfoReturnable.setReturnValue(true);
      }
   }

   public static void handleArg9(CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled()) {
         callbackInfoReturnable.setReturnValue(false);
      }
   }

   SoundManager() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static void handleClass_1195410(BlockEntityRenderState blockEntityRenderState, CallbackInfo callbackInfo) {
      if (ToggleableUtils.xray.isEnabled() && !((List)ToggleableUtils.xray.blocks.getValue()).contains(blockEntityRenderState.blockState.getBlock())) {
         callbackInfo.cancel();
      }
   }

   public static void runVoid11() {
      BThackClient.getInstance().getEventBus().register(new EventSub9());
   }

   public static void handleClass_268012(
      BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, Direction direction, CallbackInfoReturnable callbackInfoReturnable
   ) {
      if (ToggleableUtils.xray.isEnabled()) {
         if (((List)ToggleableUtils.xray.blocks.getValue()).contains(blockState.getBlock())) {
            callbackInfoReturnable.setReturnValue(
               !((List)ToggleableUtils.xray.blocks.getValue()).contains(blockRenderView.getBlockState(blockPos.offset(direction)).getBlock())
            );
         } else {
            callbackInfoReturnable.setReturnValue(false);
         }
      }
   }

   public static void handleClass_268013(BlockState blockState, BlockState blockState2, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled()) {
         if (((List)ToggleableUtils.xray.blocks.getValue()).contains(blockState.getBlock())) {
            callbackInfoReturnable.setReturnValue(!((List)ToggleableUtils.xray.blocks.getValue()).contains(blockState2.getBlock()));
         } else {
            callbackInfoReturnable.setReturnValue(false);
         }
      }
   }

   public static void handleCallbackInfo14(CallbackInfo callbackInfo) {
      if (eventSub4Sub11 != null && eventSub4Sub11.getBoolean()) {
         callbackInfo.cancel();
      }
   }

   public static Packet withClass_259615(Packet packet) {
      EventSub4.EventSub4Sub2 eventSub4Sub2 = new EventSub4.EventSub4Sub2(packet);
      BThackClient.getInstance().getEventBus().register(eventSub4Sub2);
      eventSub4Sub21 = eventSub4Sub2;
      return eventSub4Sub2.getPacket();
   }

   public static Packet withClass_259616(Packet packet) {
      EventSub4.EventSub4Sub1 eventSub4Sub1 = new EventSub4.EventSub4Sub1(packet);
      BThackClient.getInstance().getEventBus().register(eventSub4Sub1);
      eventSub4Sub11 = eventSub4Sub1;
      return eventSub4Sub1.getPacket();
   }

   public static void handleClass_268017(BlockState blockState, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled() && !((List)ToggleableUtils.xray.blocks.getValue()).contains(blockState.getBlock())) {
         class Helper31 implements BlockStateModel {
            public void addParts(Random random, List list) {
            }

            public Sprite particleSprite() {
               return null;
            }
         }

         callbackInfoReturnable.setReturnValue(new Helper31());
      }
   }

   public static void handleClass_361018(FluidState fluidState, CallbackInfoReturnable callbackInfoReturnable) {
      if (ToggleableUtils.xray.isEnabled()) {
         callbackInfoReturnable.setReturnValue(((List)ToggleableUtils.xray.fluids.getValue()).contains(fluidState.getFluid()) ? 1 : 0);
      }
   }
}
