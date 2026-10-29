package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import java.awt.Color;

public class ColorField extends Element implements Renderable {
   final transient ComponentSub5 componentSub5;
   final transient ElementSub1 elementSub1;
   final transient RenderableImpl renderableImpl;
   final transient RenderableImpl3 renderableImpl3;
   final transient Api2Impl3 api2Impl3;

   public ColorField(EventBus eventBus, ComponentSub5 componentSub5, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i17.x, 164.0F);
      this.componentSub5 = componentSub5;
      this.elementSub1 = new ElementSub1(eventBus, this, 12.0F, 12.0F);
      this.renderableImpl = new RenderableImpl(eventBus, this, this.elementSub1.getWidth() + this.elementSub1.getWidthOffset() + 17.0F, 12.0F);
      this.renderableImpl3 = new RenderableImpl3(eventBus, this, this.renderableImpl.getWidth() + this.renderableImpl.getWidthOffset() + 17.0F, 12.0F);
      this.api2Impl3 = new Api2Impl3<>(this::getTextBatch);
   }

   public ComponentSub5 getComponentSub5() {
      return this.componentSub5;
   }

   @Override
   public void setSize(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      this.elementSub1.setSize(num, num2);
      this.renderableImpl.setSize(num, num2);
      this.renderableImpl3.setSize(num, num2);
   }

   public RenderableImpl getRenderableImpl() {
      return this.renderableImpl;
   }

   public void handleRoundedRectBatch(RoundedRectBatch roundedRectBatch) {
      this.elementSub1.handleRoundedRectBatch(roundedRectBatch);
      this.renderableImpl3.handleRoundedRectBatch(roundedRectBatch);
   }

   public ElementSub1 getElementSub1() {
      return this.elementSub1;
   }

   public RenderableImpl3 getRenderableImpl3() {
      return this.renderableImpl3;
   }

   @Override
   public void setPosition(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      this.applyTransform();
      ElementUtils.updateHover(this.elementSub1, this, num, num2);
      ElementUtils.updateHover(this.renderableImpl, this, num, num2);
      ElementUtils.updateHover(this.renderableImpl3, this, num, num2);
      this.api2Impl3.close2();
      this.popMatrix();
   }

   private TextBatch getTextBatch() {
      float flt = this.renderableImpl3.getWidth() + this.renderableImpl3.getWidthOffset() + Utils4.vector2i22.x;
      float flt2 = 12.0F;
      Color color = (Color)this.getComponentSub5().getColorSetting().getValue();
      float[] fltArr = this.getComponentSub5().getColorSetting().getFloat();
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(12));
      textBatch.text(new RenderText(String.format("Red: %s", color.getRed()), flt, flt2).withColor(Utils4.renderColor14));
      flt2 += textBatch.getFontOrNull().getTextHeight() + Utils4.vector2i22.y;
      textBatch.text(new RenderText(String.format("Green: %s", color.getGreen()), flt, flt2).withColor(Utils4.renderColor14));
      flt2 += textBatch.getFontOrNull().getTextHeight() + Utils4.vector2i22.y;
      textBatch.text(new RenderText(String.format("Blue: %s", color.getBlue()), flt, flt2).withColor(Utils4.renderColor14));
      flt2 += textBatch.getFontOrNull().getTextHeight() + Utils4.vector2i22.y;
      textBatch.text(new RenderText(String.format("Alpha: %s", color.getAlpha()), flt, flt2).withColor(Utils4.renderColor14));
      flt2 += textBatch.getFontOrNull().getTextHeight() + Utils4.vector2i22.y;
      textBatch.text(new RenderText(String.format("Hue: %s", Helper41.getFloat(fltArr[0], 2)), flt, flt2).withColor(Utils4.renderColor14));
      flt2 += textBatch.getFontOrNull().getTextHeight() + Utils4.vector2i22.y;
      textBatch.text(new RenderText(String.format("Saturation: %s", Helper41.getFloat(fltArr[1], 2)), flt, flt2).withColor(Utils4.renderColor14));
      flt2 += textBatch.getFontOrNull().getTextHeight() + Utils4.vector2i22.y;
      textBatch.text(new RenderText(String.format("Brightness: %s", Helper41.getFloat(fltArr[2], 2)), flt, flt2).withColor(Utils4.renderColor14));
      return textBatch;
   }

   public void handleRoundedTextureBatch1(RoundedTextureBatch roundedTextureBatch) {
      this.renderableImpl3.handleRoundedTextureBatch1(roundedTextureBatch);
   }

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      PointAmount pointAmount2 = EntityUtils.withPointAmount11(pointAmount, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handlePointClick(this.elementSub1, pointAmount2);
      ElementUtils.handlePointClick(this.renderableImpl, pointAmount2);
      ElementUtils.handlePointClick(this.renderableImpl3, pointAmount2);
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      this.elementSub1.onMouseMove(xYButton2);
      this.renderableImpl.onMouseMove(xYButton2);
      this.renderableImpl3.onMouseMove(xYButton2);
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handleClick(this.elementSub1, xYButton2);
      ElementUtils.handleClick(this.renderableImpl, xYButton2);
      ElementUtils.handleClick(this.renderableImpl3, xYButton2);
   }

   @Override
   public void render4() {
      this.renderableImpl.render4();
      this.renderableImpl3.render4();
      this.api2Impl3.render4();
   }
}
