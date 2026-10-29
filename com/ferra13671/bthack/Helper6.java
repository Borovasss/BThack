package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.FramebufferUtils;
import com.ferra13671.cometrenderer.minecraft.MinecraftFramebuffer;
import com.ferra13671.cometrenderer.minecraft.batch.IPrimitiveBatch;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.render.fog.FogRenderer.FogType;
import net.minecraft.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Helper6 implements IPrimitiveBatch, MinecraftInstance {
   static final transient FogRenderer fogRenderer = new FogRenderer();
   public static final transient Framebuffer framebuffer = new SimpleFramebuffer("A", 1, 1, true);
   public static final transient com.ferra13671.cometrenderer.buffer.framebuffer.Framebuffer framebuffer1 = new MinecraftFramebuffer(
      framebuffer, new Color(255, 255, 255, 0), 0.0, 0
   );
   final transient List<ItemStackXY> list1 = new ArrayList<>();
   transient boolean built = false;

   public Helper6 build() {
      this.built = true;
      return this;
   }

   public Helper6 withClass_1799(ItemStack itemStack, float flt, float flt2, boolean bool) {
      this.list1.add(new Helper6.ItemStackXY(itemStack, flt, flt2, bool));
      return this;
   }

   public Helper6 tryDraw() {
      framebuffer1.clearAll();
      FramebufferUtils.resizeToParent(framebuffer1, CRM.getMainFramebuffer());
      RendererInit.guiRenderState.clear();
      DrawContext drawContext = new DrawContext(
         mc, RendererInit.guiRenderState, (int)mc.mouse.getScaledX(mc.getWindow()), (int)mc.mouse.getScaledY(mc.getWindow())
      );
      float flt = 2.0F / mc.getWindow().getScaleFactor();
      float flt2 = CRM.getMainFramebuffer().getWidth();
      float flt3 = CRM.getMainFramebuffer().getHeight();
      float flt4 = 0.0F;
      float flt5 = 0.0F;

      for (Helper6.ItemStackXY itemStackXY : this.list1) {
         Vector2f vector2f = Utils5.withMatrix4f(RenderSystem.getModelViewMatrix(), itemStackXY.x2, itemStackXY.x);
         if (flt2 > vector2f.x) {
            flt2 = vector2f.x;
         }

         if (flt3 > vector2f.y) {
            flt3 = vector2f.y;
         }

         if (flt4 < vector2f.x + 32.0F) {
            flt4 = vector2f.x + 32.0F;
         }

         if (flt5 < vector2f.y + 32.0F) {
            flt5 = vector2f.y + 32.0F;
         }

         vector2f = vector2f.mul(0.5F);
         drawContext.getMatrices().pushMatrix();
         drawContext.getMatrices().scale(flt).translate(vector2f.x, vector2f.y);
         drawContext.drawItem(itemStackXY.itemstack, 0, 0);
         if (itemStackXY.decoration) {
            drawContext.drawStackOverlay(mc.textRenderer, itemStackXY.itemstack, 0, 0);
         }

         drawContext.getMatrices().popMatrix();
      }

      RenderSystem.getModelViewStack().pushMatrix();
      RenderSystem.getModelViewStack().set(new Matrix4f());
      RendererInit.framebuffer = framebuffer;
      RendererInit.guiRenderer.incrementFrame();
      RendererInit.guiRenderer.render(fogRenderer.getFogBuffer(FogType.NONE));
      RendererInit.guiRenderState.clear();
      RendererInit.framebuffer = null;
      RendererInit.cRMInstance1.setupUIMatrix();
      CRM.getMainFramebuffer().bind(true);
      CometRenderer.setDefaultBlend();
      new AbstractPrimitiveBatchSub1(framebuffer1, true).withFloat(flt2, flt3, flt4, flt5).build().tryDraw().close();
      RenderSystem.getModelViewStack().popMatrix();
      return this;
   }

   public void close() {
      this.list1.clear();
   }

   public boolean isBuilt() {
      return this.built;
   }

   private record ItemStackXY(ItemStack itemstack, float x2, float x, boolean decoration) {

      public boolean isDecoration() {
         return this.decoration;
      }

      public float getX() {
         return this.x;
      }

      public ItemStack getItemstack() {
         return this.itemstack;
      }

      public float getX2() {
         return this.x2;
      }
   }
}
