package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;
import java.awt.Color;

public class RenderableImpl extends Element implements Renderable {
   final transient ColorField colorField;
   final transient Api2Impl2 api2Impl2;
   transient float roundedRectBatch;
   transient boolean hovered = false;

   @Override
   public void setSize(int num, int num2) {
      if (this.hovered) {
         this.setRoundedRectBatch(num2);
      }
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.hovered = false;
      }
   }

   public float getRoundedRectBatch2() {
      return this.roundedRectBatch / this.getHeightOffset();
   }

   public RenderableImpl(EventBus eventBus, ColorField colorField, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i21.x, Utils4.vector2i21.y);
      this.colorField = colorField;
      this.roundedRectBatch = this.colorField.getComponentSub5().getColorSetting().getFloat()[0] * this.getHeightOffset();
      this.api2Impl2 = new Api2Impl2(new Api2Impl3<>(this::getRoundedTextureBatch), new Api2Impl3<>(this::getRoundedRectBatch));
   }

   private RoundedTextureBatch getRoundedTextureBatch() {
      return new RoundedTextureBatch()
         .setTexture(RendererInit.config.gLTexture8)
         .rectSized(
            0.0F, 0.0F, this.getWidthOffset(), this.getHeightOffset(), 10.0F, RectColors.oneColor(RenderColor.WHITE), new TextureBorder(0.0F, 0.0F, 1.0F, 1.0F)
         );
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.hovered = true;
         this.setRoundedRectBatch(xYButton.getX());
      }
   }

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      this.api2Impl2.close2();
      this.popMatrix();
   }

   @Override
   public void render4() {
      this.api2Impl2.render4();
   }

   public void setRoundedRectBatch(int num) {
      this.roundedRectBatch = Math.clamp(num - this.getHeight(), 0.0F, this.getHeightOffset());
      float[] fltArr = this.colorField.getComponentSub5().getColorSetting().getFloat();
      Color color = Color.getHSBColor(this.getRoundedRectBatch2(), fltArr[1], fltArr[2]);
      this.colorField
         .getComponentSub5()
         .getColorSetting()
         .setValue2(
            new Color(color.getRed(), color.getGreen(), color.getBlue(), ((Color)this.colorField.getComponentSub5().getColorSetting().getValue()).getAlpha())
         );
      this.colorField.getComponentSub5().render4();
   }

   private RoundedRectBatch getRoundedRectBatch() {
      return new RoundedRectBatch()
         .rectSized(-2.0F, this.roundedRectBatch - 1.0F - 2.0F, this.getWidthOffset() + 4.0F, 7.0F, 3.0F, RectColors.oneColor(RenderColor.WHITE))
         .rectSized(
            0.0F,
            this.roundedRectBatch - 1.0F,
            this.getWidthOffset(),
            3.0F,
            1.0F,
            RectColors.oneColor(RenderColor.of(Color.getHSBColor(this.getRoundedRectBatch2(), 1.0F, 1.0F)))
         );
   }
}
