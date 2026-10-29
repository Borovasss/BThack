package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;
import java.awt.Color;

public class RenderableImpl3 extends Element implements Renderable {
   final transient ColorField colorField;
   final transient Api2Impl2 api2Impl;
   transient float roundedRectBatch;
   transient boolean hovered = false;

   public void setRoundedRectBatch(int num) {
      this.roundedRectBatch = Math.clamp(num - this.getHeight(), 0.0F, this.getHeightOffset());
      Color color = (Color)this.colorField.getComponentSub5().getColorSetting().getValue();
      this.colorField
         .getComponentSub5()
         .getColorSetting()
         .setValue2(
            new Color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F - this.roundedRectBatch / this.getHeightOffset())
         );
      this.colorField.getComponentSub5().render4();
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.hovered = false;
      }
   }

   @Override
   public void render4() {
      this.api2Impl.render4();
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.hovered = true;
         this.setRoundedRectBatch(xYButton.getX());
      }
   }

   public RenderableImpl3(EventBus eventBus, ColorField colorField, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i21.x, Utils4.vector2i21.y);
      this.colorField = colorField;
      this.api2Impl = new Api2Impl2(new Api2Impl3<>(this::getRoundedRectBatch));
      this.roundedRectBatch = (1.0F - ((Color)colorField.getComponentSub5().getColorSetting().getValue()).getAlpha() / 255.0F) * this.getHeightOffset();
   }

   @Override
   public void setSize(int num, int num2) {
      if (this.hovered) {
         this.setRoundedRectBatch(num2);
      }
   }

   @Override
   public void setPosition(int num, int num2) {
      this.api2Impl.close2();
   }

   public void handleRoundedRectBatch(RoundedRectBatch roundedRectBatch) {
      float[] fltArr = this.colorField.getComponentSub5().getColorSetting().getFloat();
      roundedRectBatch.rectSized(
         this.getWidth() + this.colorField.getWidth(),
         this.getHeight() + this.colorField.getHeight(),
         this.getWidthOffset(),
         this.getHeightOffset(),
         10.0F,
         RectColors.verticalGradient(RenderColor.of(Color.getHSBColor(fltArr[0], fltArr[1], fltArr[2])), RenderColor.TRANSLUCENT)
      );
   }

   private RoundedRectBatch getRoundedRectBatch() {
      return new RoundedRectBatch()
         .rectSized(
            this.getWidth() - 2.0F,
            this.getHeight() + this.roundedRectBatch - 1.0F - 2.0F,
            this.getWidthOffset() + 4.0F,
            7.0F,
            3.0F,
            RectColors.oneColor(RenderColor.WHITE)
         )
         .rectSized(
            this.getWidth(),
            this.getHeight() + this.roundedRectBatch - 1.0F,
            this.getWidthOffset(),
            3.0F,
            1.0F,
            RectColors.oneColor(RenderColor.of((Color)this.colorField.getComponentSub5().getColorSetting().getValue()))
         );
   }

   public void handleRoundedTextureBatch1(RoundedTextureBatch roundedTextureBatch) {
      roundedTextureBatch.rectSized(
         this.getWidth() + this.colorField.getWidth(),
         this.getHeight() + this.colorField.getHeight(),
         this.getWidthOffset(),
         this.getHeightOffset(),
         10.0F,
         RectColors.oneColor(RenderColor.WHITE),
         new TextureBorder(0.0F, 0.0F, 1.0F, this.getHeightOffset() / this.getWidthOffset())
      );
   }
}
