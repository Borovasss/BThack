package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredRectBatch;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenderableImpl5 extends Element implements Renderable, Closeable {
   final transient ListSettingApi listSettingApi;
   final transient ComponentSub4 componentSub4;
   final transient List<SettingElement<?>> children = new ArrayList();
   final transient List<SettingElement<?>> list1 = new CopyOnWriteArrayList();
   final transient Api2Impl2 api2Impl2;
   final transient Api2Impl api2Impl;
   transient float float1 = 0.0F;
   transient Float scrollY = 0.0F;

   private void handleInt(int num, int num2, SettingElement settingElement) {
      settingElement.setSize(num - (int)this.getWidth(), num2 - (int)this.getHeight() - this.scrollY.intValue());
   }

   public RenderableImpl5(EventBus eventBus, ListSettingApi listSettingApi, ComponentSub4 componentSub4) {
      super(eventBus, 0.0F, 0.0F, Utils4.vector2i14.x, 0.0F);
      this.listSettingApi = listSettingApi;
      this.componentSub4 = componentSub4;
      this.close3();
      float flt = this.widthOffset - Utils4.pos.x * 2;

      for (Setting<?> setting : (List<Setting<?>>)listSettingApi.getSettings()) {
         this.children.add(setting.createSettingElement(eventBus, this.getComponentSub4(), 0.0F, 0.0F, flt));
      }

      this.close4();
      this.api2Impl2 = new Api2Impl2(new Api2Impl3<>(this::getColoredRectBatch));
      this.api2Impl = new Api2Impl(this::handleApi2Impl6);
   }

   private static void handleInt1(int num, SettingElement settingElement) {
      settingElement.onKeyPress(num);
   }

   private void close3() {
      this.width = 0.0F;
      this.height = this.componentSub4.getRenderableImpl8().getHeightOffset() + 2.0F;
   }

   private static void handleApi2Impl2(Api2Impl api2Impl, SettingElement settingElement) {
      settingElement.render(api2Impl);
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)(this.getHeight() + this.scrollY));
      this.list1.forEach((settingElement) -> RenderableImpl5.handleXYButton3(xYButton, settingElement));
   }

   @Override
   public void setPosition(int num, int num2) {
      RendererInit.handleInt1(
         (int)(this.componentSub4.getWidth() + this.getWidth()),
         (int)(this.componentSub4.getHeight() + this.getHeight()),
         (int)this.getWidthOffset(),
         (int)this.getHeightOffset()
      );
      int num3 = num - (int)this.getWidth();
      int num4 = num2 - (int)this.getHeight();
      this.applyTransform();
      RenderSystem.getModelViewMatrix().translate(0.0F, this.scrollY, 0.0F);
      this.api2Impl2.close2();
      this.api2Impl.close2();
      this.list1.forEach((settingElement) -> this.handleInt5(num, num2, settingElement));
      RenderSystem.getModelViewMatrix().translate(0.0F, -this.scrollY, 0.0F);
      this.popMatrix();
      RendererInit.runVoid8();
   }

   private static void handleXYButton3(XYButton xYButton, SettingElement settingElement) {
      settingElement.onMouseMove(xYButton);
   }

   private static void handleInt4(int num, SettingElement settingElement) {
      settingElement.onCharTyped(num);
   }

   @Override
   public void setSize(int num, int num2) {
      this.list1.forEach((settingElement) -> this.handleInt(num, num2, settingElement));
   }

   @Override
   public void close() {
      this.api2Impl2.close();
      this.api2Impl.close();
      this.children.forEach(SettingElement::close);
   }

   private void close4() {
      this.list1.clear();
      float flt = 0.0F;

      for (int num = 0; num < this.children.size(); num++) {
         SettingElement<?> settingElement = (SettingElement<?>)this.children.get(num);
         if ((Boolean)settingElement.getSetting().getSupplier().get()) {
            settingElement.setX(Utils4.pos.x);
            settingElement.setY(Utils4.pos.y + flt);
            this.list1.add(settingElement);
            flt += settingElement.getHeightOffset() + (num < this.children.size() - 1 ? Utils4.pos.y * 2 + 2 : Utils4.pos.y);
         }
      }

      this.float1 = Utils4.pos.y + flt;
      this.heightOffset = Math.min(Utils4.vector2i14.y, this.float1);
   }

   @Override
   public void render4() {
      this.close4();
      this.close3();
      this.api2Impl2.render4();
      this.api2Impl.render4();
   }

   private void handleInt5(int num, int num2, SettingElement settingElement) {
      ElementUtils.updateHover(settingElement, this, num, num2 - this.scrollY.intValue());
   }

   public ComponentSub4 getComponentSub4() {
      return this.componentSub4;
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)(this.getHeight() + this.scrollY));
      this.list1.forEach((settingElement) -> RenderableImpl5.handleXYButton7(xYButton, settingElement));
   }

   @Override
   public void onCharTyped(int num) {
      this.list1.forEach((settingElement) -> RenderableImpl5.handleInt4(num, settingElement));
   }

   private void handleApi2Impl6(Api2Impl api2Impl) {
      this.list1.forEach((settingElement) -> RenderableImpl5.handleApi2Impl2(api2Impl, settingElement));
   }

   public ListSettingApi getListSettingApi() {
      return this.listSettingApi;
   }

   private ColoredRectBatch getColoredRectBatch() {
      ColoredRectBatch coloredRectBatch = new ColoredRectBatch();

      for (int num = 0; num < this.list1.size() - 1; num++) {
         SettingElement<?> settingElement = (SettingElement<?>)this.list1.get(num);
         float flt = settingElement.getHeight() + settingElement.getHeightOffset() + Utils4.pos.y;
         coloredRectBatch.rectSized(Utils4.pos.x, flt, 80.0F, 2.0F, RectColors.horizontalGradient(RenderColor.TRANSLUCENT, Utils4.renderColor7))
            .rectSized(Utils4.pos.x + 80, flt, 101.0F, 2.0F, RectColors.oneColor(Utils4.renderColor7))
            .rectSized(Utils4.pos.x + 80 + 101, flt, 80.0F, 2.0F, RectColors.horizontalGradient(Utils4.renderColor7, RenderColor.TRANSLUCENT));
      }

      return coloredRectBatch;
   }

   private static void handleXYButton7(XYButton xYButton, SettingElement settingElement) {
      ElementUtils.handleClick(settingElement, xYButton);
   }

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      this.scrollY = this.scrollY + pointAmount.getAmount() * 30.0F;
      this.scrollY = EntityUtils.getFloat6(this.scrollY, this.float1, this.heightOffset);
   }

   @Override
   public void onKeyPress(int num) {
      this.list1.forEach((settingElement) -> RenderableImpl5.handleInt1(num, settingElement));
   }
}
