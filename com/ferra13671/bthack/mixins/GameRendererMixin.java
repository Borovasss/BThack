package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.ferra13671.bthack.OnResizedEvent;
import com.ferra13671.bthack.RendererInit;
import com.ferra13671.bthack.ScreenHelper;
import com.ferra13671.bthack.ToggleableUtils;
import com.ferra13671.bthack.Utils11;
import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.systems.ProjectionType;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.render.BannerResultGuiElementRenderer;
import net.minecraft.client.gui.render.BookModelGuiElementRenderer;
import net.minecraft.client.gui.render.EntityGuiElementRenderer;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.PlayerSkinGuiElementRenderer;
import net.minecraft.client.gui.render.ProfilerChartGuiElementRenderer;
import net.minecraft.client.gui.render.SignGuiElementRenderer;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.ProjectionMatrix3;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.command.RenderDispatcher;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
   @Shadow
   @Final
   transient MinecraftClient client;
   @Shadow
   @Final
   transient ProjectionMatrix3 hudProjectionMatrix;
   @Shadow
   @Final
   transient OrderedRenderCommandQueueImpl orderedRenderCommandQueue;
   @Shadow
   @Final
   transient RenderDispatcher entityRenderDispatcher;

   @Inject(method = "onResized", at = @At("TAIL"))
   public void handleInt(int num, int num2, CallbackInfo callbackInfo) {
      BThackClient.getInstance().getEventBus().register(new OnResizedEvent(num, num2));
   }

   @Inject(method = "render", at = @At("TAIL"))
   public void handleClass_97791(RenderTickCounter renderTickCounter, boolean bool, CallbackInfo callbackInfo) {
      boolean bool2 = ToggleableUtils.tooltips.isEnabled() && ToggleableUtils.tooltips.getItemStackCount() != null;
      boolean bool3 = this.client.currentScreen instanceof ScreenHelper;
      if (bool2 || bool3) {
         RendererInit.cRMInstance1.setupUIMatrix();
         RenderSystem.getModelViewStack().pushMatrix();
         RenderSystem.getModelViewMatrix().set(new Matrix4f());
         CRM.getMainFramebuffer().bind(true);
         CRM.clearSamplers();
         CometRenderer.setDefaultBlend();
         int num = Utils11.getMouseX();
         int num2 = Utils11.getMouseY();
         if (bool3) {
            ((ScreenHelper)this.client.currentScreen).setPosition(num, num2);
         }

         if (bool2) {
            ToggleableUtils.tooltips.setPosition(num, num2);
         }

         RenderSystem.getModelViewStack().popMatrix();
      }

      RendererInit.runVoid6();
   }

   @Inject(method = "updateCrosshairTarget", at = @At("HEAD"), cancellable = true)
   public void handleFloat2(float flt, CallbackInfo callbackInfo) {
      if (BThackClient.getInstance() != null
         && BThackClient.getInstance().getModuleManager() != null
         && ToggleableUtils.freeCamera.isEnabled()
         && (Boolean)ToggleableUtils.freeCamera.raycast.getValue()) {
         callbackInfo.cancel();
         ToggleableUtils.freeCamera.getRotate();
      }
   }

   @WrapWithCondition(
      method = "renderWorld",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;renderHand(FZLorg/joml/Matrix4f;)V")
   )
   public boolean isClass_7573(GameRenderer gameRenderer, float flt, boolean bool, Matrix4f matrix4f) {
      if (ToggleableUtils.handView.isEnabled() && (Boolean)ToggleableUtils.handView.customFov.getValue()) {
         RenderSystem.backupProjectionMatrix();
         RenderSystem.setProjectionMatrix(
            this.hudProjectionMatrix
               .set(
                  this.client.getWindow().getFramebufferWidth(), this.client.getWindow().getFramebufferHeight(), (Float)ToggleableUtils.handView.fov.getValue()
               ),
            ProjectionType.PERSPECTIVE
         );
         this.renderHand(flt, bool, matrix4f);
         RenderSystem.restoreProjectionMatrix();
         return false;
      } else {
         return true;
      }
   }

   @Inject(method = "showFloatingItem", at = @At("HEAD"), cancellable = true)
   public void handleClass_17994(ItemStack itemStack, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.totemAnimation.getValue()) {
         callbackInfo.cancel();
      }
   }

   @ModifyReturnValue(method = "getFov", at = @At("RETURN"))
   public float getFloat5(float flt) {
      return !ToggleableUtils.zoom.isEnabled() && !(ToggleableUtils.zoom.float2 - ToggleableUtils.zoom.float1 > 0.1F)
         ? flt
         : ToggleableUtils.zoom.getFloat(flt);
   }

   @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
   public void handleClass_45876(MatrixStack matrixStack, float flt, CallbackInfo callbackInfo) {
      if (ToggleableUtils.noRender.isEnabled() && (Boolean)ToggleableUtils.noRender.hurtCamera.getValue()) {
         callbackInfo.cancel();
      }
   }

   @Shadow
   protected abstract void renderHand(float var1, boolean var2, Matrix4f var3);

   @Inject(method = "<init>", at = @At("TAIL"))
   public void handleClass_3107(
      MinecraftClient minecraftClient,
      HeldItemRenderer heldItemRenderer,
      BufferBuilderStorage bufferBuilderStorage,
      BlockRenderManager blockRenderManager,
      CallbackInfo callbackInfo
   ) {
      RendererInit.guiRenderState = new GuiRenderState();
      RendererInit.guiRenderer = new GuiRenderer(
         RendererInit.guiRenderState,
         bufferBuilderStorage.getEntityVertexConsumers(),
         this.orderedRenderCommandQueue,
         this.entityRenderDispatcher,
         List.of(
            new EntityGuiElementRenderer(bufferBuilderStorage.getEntityVertexConsumers(), minecraftClient.getEntityRenderDispatcher()),
            new PlayerSkinGuiElementRenderer(bufferBuilderStorage.getEntityVertexConsumers()),
            new BookModelGuiElementRenderer(bufferBuilderStorage.getEntityVertexConsumers()),
            new BannerResultGuiElementRenderer(bufferBuilderStorage.getEntityVertexConsumers(), minecraftClient.getAtlasManager()),
            new SignGuiElementRenderer(bufferBuilderStorage.getEntityVertexConsumers(), minecraftClient.getAtlasManager()),
            new ProfilerChartGuiElementRenderer(bufferBuilderStorage.getEntityVertexConsumers())
         )
      );
   }
}
