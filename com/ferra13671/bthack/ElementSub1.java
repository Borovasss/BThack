package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import java.awt.Color;
import org.joml.Vector4f;

public class ElementSub1 extends Element {
   final transient ColorField colorField;
   transient boolean hovered = false;
   final transient Vector4f color = new Vector4f();

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.hovered = true;
         this.handleInt1(xYButton.getX2(), xYButton.getX());
      }
   }

   @Override
   public void setSize(int num, int num2) {
      if (this.hovered) {
         this.handleInt1(num, num2);
      }
   }

   public ElementSub1(EventBus eventBus, ColorField colorField, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i20.x, Utils4.vector2i20.y);
      this.colorField = colorField;
      float[] fltArr = colorField.getComponentSub5().getColorSetting().getFloat();
      this.color.x = fltArr[1] * this.getWidthOffset();
      this.color.y = (1.0F - fltArr[2]) * this.getHeightOffset();
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.hovered = false;
      }
   }

   public void handleRoundedRectBatch(RoundedRectBatch roundedRectBatch) {
      float[] fltArr = this.colorField.getComponentSub5().getColorSetting().getFloat();
      roundedRectBatch.rectSized(
            this.getWidth() + this.colorField.getWidth(),
            this.getHeight() + this.colorField.getHeight(),
            this.getWidthOffset(),
            this.getHeightOffset(),
            10.0F,
            RectColors.horizontalGradient(
               RenderColor.WHITE, RenderColor.of(Color.getHSBColor(this.colorField.getRenderableImpl().getRoundedRectBatch2(), 1.0F, 1.0F))
            )
         )
         .rectSized(
            this.getWidth() + this.colorField.getWidth(),
            this.getHeight() + this.colorField.getHeight(),
            this.getWidthOffset(),
            this.getHeightOffset(),
            10.0F,
            RectColors.verticalGradient(RenderColor.TRANSLUCENT, RenderColor.BLACK)
         )
         .rectSized(
            this.getWidth() + this.colorField.getWidth() + this.color.x - 8.0F,
            this.getHeight() + this.colorField.getHeight() + this.color.y - 8.0F,
            16.0F,
            16.0F,
            8.0F,
            RectColors.oneColor(RenderColor.WHITE)
         )
         .rectSized(
            this.getWidth() + this.colorField.getWidth() + this.color.x - 6.0F,
            this.getHeight() + this.colorField.getHeight() + this.color.y - 6.0F,
            12.0F,
            12.0F,
            6.0F,
            RectColors.oneColor(RenderColor.of(Color.getHSBColor(this.colorField.getRenderableImpl().getRoundedRectBatch2(), fltArr[1], fltArr[2])))
         );
   }

   public void handleInt1(int num, int num2) {
      this.color.x = Math.clamp(num - this.getWidth(), 0.0F, this.getWidthOffset());
      this.color.y = Math.clamp(num2 - this.getHeight(), 0.0F, this.getHeightOffset());
      Color color = new Color(
         Color.HSBtoRGB(
            this.colorField.getRenderableImpl().getRoundedRectBatch2(), this.color.x / this.getWidthOffset(), 1.0F - this.color.y / this.getHeightOffset()
         )
      );
      color = new Color(color.getRed(), color.getGreen(), color.getBlue(), ((Color)this.colorField.getComponentSub5().getColorSetting().getValue()).getAlpha());
      this.colorField.getComponentSub5().getColorSetting().setValue2(color);
      this.colorField.getComponentSub5().render4();
   }
}
