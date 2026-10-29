package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.function.Consumer;
import net.minecraft.client.util.InputUtil;
import org.joml.Vector2f;

public class ElementHelper extends Element implements MinecraftInstance {
   transient boolean boolean1 = false;
   final transient Helper45 helper45 = new Helper45();
   transient boolean boolean2 = false;
   transient boolean boolean3 = false;
   final transient Consumer<ElementHelper> consumer1;
   final transient StringBuilder stringBuilder1 = new StringBuilder();
   final transient Api2Impl api2Impl;

   public void getApi2Impl() {
      if (!this.stringBuilder1.isEmpty()) {
         if (this.boolean3) {
            this.stringBuilder1.delete(0, this.stringBuilder1.length());
            this.boolean3 = false;
         } else {
            this.stringBuilder1.delete(this.stringBuilder1.length() - 1, this.stringBuilder1.length());
         }

         this.api2Impl.render4();
         this.consumer1.accept(this);
      }
   }

   public void handleString(String string) {
      if (this.boolean3) {
         this.getApi2Impl();
      }

      this.stringBuilder1.append(string);
      this.consumer1.accept(this);
   }

   public ElementHelper(EventBus eventBus, float flt, float flt2, float flt3, float flt4, String string, int num, Consumer<ElementHelper> consumer) {
      super(eventBus, flt, flt2, flt3, flt4);
      this.consumer1 = consumer;
      this.api2Impl = new Api2Impl((api2Impl) -> this.handleInt1(num, string, api2Impl));
   }

   @Override
   public void onMouseRelease(XYButton xYButton) {
      if (!this.isHovered(xYButton.getX2(), xYButton.getX())) {
         this.boolean1 = false;
         this.boolean3 = false;
         this.api2Impl.render4();
      }
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         if (!this.boolean1) {
            this.boolean1 = true;
            this.api2Impl.render4();
         } else if (this.boolean3) {
            this.boolean3 = false;
            this.api2Impl.render4();
         }
      }
   }

   @Override
   public void setPosition(int num, int num2) {
      if (this.helper45.passed(500)) {
         this.boolean2 = !this.boolean2;
         this.helper45.reset();
         if (this.boolean1) {
            this.api2Impl.render4();
         }
      }

      Vector2f vector2f = Utils5.withMatrix4f(RenderSystem.getModelViewMatrix(), this.getWidth(), this.getHeight());
      RendererInit.handleInt1((int)vector2f.x, (int)vector2f.y, (int)this.getWidthOffset(), (int)this.getHeightOffset());
      this.api2Impl.close2();
      RendererInit.runVoid8();
   }

   private void handleInt1(int num, String string, Api2Impl api2Impl) {
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(num);
      String string2 = this.stringBuilder1.toString().isEmpty() && !this.boolean1 ? string : this.stringBuilder1.toString();
      float flt = this.getHeight() + this.getHeightOffset() / 2.0F - tTFFont.getTextHeight(string2) / 2.0F;
      float flt2 = tTFFont.getTextWidth(string2);
      float flt3 = this.getWidth() - Math.max(0.0F, flt2 - this.getWidthOffset() + 10.0F);
      string2 = string2 + (this.boolean1 && this.boolean2 ? "|" : "");
      if (this.boolean3) {
         ((ColoredRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator10))
            .rectSized(this.getWidth(), flt, Math.min(flt2, this.getWidthOffset()), tTFFont.getTextHeight(), RectColors.oneColor(Utils4.renderColor20));
      }

      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(
            new RenderText(string2, flt3, flt)
               .withColor(this.stringBuilder1.toString().isEmpty() ? Utils4.renderColor2 : Utils4.renderColor3)
               .withFont(tTFFont)
         );
   }

   @Override
   public void onCharTyped(int num) {
      if (this.boolean1) {
         this.handleString(String.valueOf((char)num));
         this.api2Impl.render4();
      }
   }

   @Override
   public void close() {
      super.close();
      this.api2Impl.close();
   }

   @Override
   public void onKeyPress(int num) {
      if (this.boolean1) {
         if (num == 259) {
            this.getApi2Impl();
         }

         if (InputUtil.isKeyPressed(mc.getWindow(), 345) || InputUtil.isKeyPressed(mc.getWindow(), 341)) {
            switch (num) {
               case 65:
                  this.boolean3 = true;
                  this.api2Impl.render4();
                  break;
               case 67:
                  this.getMc();
                  break;
               case 86:
                  this.handleString(mc.keyboard.getClipboard());
                  break;
               case 88:
                  if (this.boolean3) {
                     this.getMc();
                     this.getApi2Impl();
                  }
            }
         }
      }
   }

   public void getMc() {
      if (this.boolean3) {
         mc.keyboard.setClipboard(this.stringBuilder1.toString());
      }
   }

   public String getStringBuilder() {
      return this.stringBuilder1.toString();
   }
}
