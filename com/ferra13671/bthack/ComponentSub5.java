package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;

public class ComponentSub5 extends Component {
   final transient ColorSetting colorSetting;
   final transient Api6 api6;
   final transient ElementSub7 elementSub7;
   final transient ColorField colorField;
   final transient Api2Impl2 api2Impl;

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      PointAmount pointAmount2 = EntityUtils.withPointAmount11(pointAmount, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handlePointClick(this.elementSub7, pointAmount2);
      ElementUtils.handlePointClick(this.colorField, pointAmount2);
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      this.elementSub7.onMouseMove(xYButton2);
      this.colorField.onMouseMove(xYButton2);
   }

   public ColorSetting getColorSetting() {
      return this.colorSetting;
   }

   @Override
   public void setPosition(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      super.setPosition(num, num2);
      this.applyTransform();
      this.api2Impl.close2();
      ElementUtils.updateHover(this.elementSub7, this, num, num2);
      ElementUtils.updateHover(this.colorField, this, num, num2);
      this.popMatrix();
   }

   public ElementSub7 getElementSub7() {
      return this.elementSub7;
   }

   @Override
   public void setSize(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      this.elementSub7.setSize(num, num2);
      this.colorField.setSize(num, num2);
   }

   private RoundedTextureBatch getRoundedTextureBatch() {
      RoundedTextureBatch roundedTextureBatch = new RoundedTextureBatch().setTexture(RendererInit.config.gLTexture7);
      this.elementSub7.handleRoundedTextureBatch1(roundedTextureBatch);
      this.colorField.handleRoundedTextureBatch1(roundedTextureBatch);
      return roundedTextureBatch;
   }

   public Api6 getApi6() {
      return this.api6;
   }

   public ColorField getColorField() {
      return this.colorField;
   }

   public void runVoid() {
      this.width = Math.min(this.getWidth() + this.getWidthOffset(), CRM.getMainFramebuffer().getWidth()) - this.getWidthOffset();
      this.height = Math.min(this.getHeight() + this.getHeightOffset(), CRM.getMainFramebuffer().getHeight()) - this.getHeightOffset();
   }

   private BasicRectBatch getBasicRectBatch() {
      return new BasicRectBatch(ComponentSub5::runVoid2).rectSized(this.colorField.getWidth(), this.colorField.getHeight() - 2.0F, this.widthOffset, 2.0F);
   }

   @Override
   public void onMouseRelease(XYButton xYButton) {
      if (!this.isHovered(xYButton.getX2(), xYButton.getX())) {
         this.eventBus.register(new EventSub13(this));
      }
   }

   public ComponentSub5(EventBus eventBus, ColorSetting colorSetting, Api6 api6, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i17.x, Utils4.vector2i17.y);
      this.colorSetting = colorSetting;
      this.api6 = api6;
      this.elementSub7 = new ElementSub7(eventBus, this, 0.0F, 0.0F);
      this.colorField = new ColorField(eventBus, this, 0.0F, 0.0F + this.elementSub7.getHeightOffset() + 2.0F);
      this.api2Impl = new Api2Impl2(
         new Api2Impl3<>(this::getBasicRectBatch), new Api2Impl3<>(this::getRoundedTextureBatch), new Api2Impl3<>(this::getRoundedRectBatch)
      );
   }

   @Override
   public void render4() {
      super.render4();
      this.api2Impl.render4();
      this.colorField.render4();
      this.api6.render4();
   }

   private static void runVoid2() {
      CometRenderer.getShaderColor().setColor(Utils4.api2Impl2.toVector4f());
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handleClick(this.elementSub7, xYButton2);
      ElementUtils.handleClick(this.colorField, xYButton2);
   }

   private RoundedRectBatch getRoundedRectBatch() {
      RoundedRectBatch roundedRectBatch = new RoundedRectBatch();
      this.elementSub7.handleRoundedRectBatch(roundedRectBatch);
      this.colorField.handleRoundedRectBatch(roundedRectBatch);
      return roundedRectBatch;
   }
}
