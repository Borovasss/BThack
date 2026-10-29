package com.ferra13671.bthack;

import com.ferra13671.bthack.mixins.AbstractClientPlayerEntityMixin;
import com.ferra13671.bthack.mixins.ClientPlayerEntityMixin2;
import com.ferra13671.bthack.mixins.InputMixin;
import com.ferra13671.bthack.mixins.KeyBindingMixin;
import com.ferra13671.bthack.mixins.LivingEntityMixin;
import com.ferra13671.bthack.mixins.MinecraftClientMixin2;
import com.ferra13671.bthack.mixins.MouseMixin2;
import com.ferra13671.bthack.mixins.PlayerEntityMixin;
import com.ferra13671.bthack.mixins.SkyRenderingMixin2;
import com.ferra13671.bthack.mixins.WorldRendererMixin2;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.buffers.GpuBuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.InputUtil.Key;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.Vec2f;

public final class Utils9 {
   public static int getClass_1309(LivingEntity livingEntity) {
      return ((LivingEntityMixin)livingEntity).getInt2();
   }

   public static GpuBuffer withClass_99751(SkyRendering skyRendering) {
      return ((SkyRenderingMixin2)skyRendering).applyGpuBuffer1();
   }

   public static boolean isClass_7462(ClientPlayerEntity clientPlayerEntity) {
      return ((ClientPlayerEntityMixin2)clientPlayerEntity).isBoolean();
   }

   public static void handleClass_3103(MinecraftClient minecraftClient, int num) {
      ((MinecraftClientMixin2)minecraftClient).handleInt(num);
   }

   public static void handleClass_13094(LivingEntity livingEntity, int num) {
      ((LivingEntityMixin)livingEntity).handleInt1(num);
   }

   public static GpuBuffer withClass_99755(SkyRendering skyRendering) {
      return ((SkyRenderingMixin2)skyRendering).applyGpuBuffer3();
   }

   public static Key withClass_3046(KeyBinding keyBinding) {
      return ((KeyBindingMixin)keyBinding).applyClass_3675class_306();
   }

   public static boolean isClass_13097(LivingEntity livingEntity, DamageSource damageSource) {
      return ((LivingEntityMixin)livingEntity).isClass_1282(damageSource);
   }

   public static void handleClass_7448(Input input, Vec2f vec2f) {
      ((InputMixin)input).handleClass_241(vec2f);
   }

   public static void handleClass_16579(PlayerEntity playerEntity, GameProfile gameProfile) {
      ((PlayerEntityMixin)playerEntity).handleGameProfile(gameProfile);
   }

   public static double getClass_31210(Mouse mouse) {
      return ((MouseMixin2)mouse).getDouble1();
   }

   public static void handleClass_997511(SkyRendering skyRendering, int num) {
      ((SkyRenderingMixin2)skyRendering).handleInt(num);
   }

   public static double getClass_31212(Mouse mouse) {
      return ((MouseMixin2)mouse).getDouble();
   }

   public static void handleClass_63613(
      ClientPlayerInteractionManager obj, int num, int num2, int num3, SlotActionType slotActionType, PlayerEntity playerEntity
   ) {
      ((ClientPlayerInteractionManagerMixinApi)obj).handleInt(num, num2, num3, slotActionType, playerEntity);
   }

   public static void handleClass_74214(AbstractClientPlayerEntity obj, PlayerListEntry playerListEntry) {
      ((AbstractClientPlayerEntityMixin)obj).handleClass_640(playerListEntry);
   }

   public static int getClass_31015(MinecraftClient minecraftClient) {
      return ((MinecraftClientMixin2)minecraftClient).getInt1();
   }

   public static void handleClass_997516(SkyRendering skyRendering, GpuBuffer gpuBuffer) {
      ((SkyRenderingMixin2)skyRendering).handleGpuBuffer2(gpuBuffer);
   }

   Utils9() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static SkyRendering withClass_76117(WorldRenderer worldRenderer) {
      return ((WorldRendererMixin2)worldRenderer).applyClass_9975();
   }
}
