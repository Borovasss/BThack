package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredRectBatch;
import java.util.ArrayList;
import java.util.List;

public class ComponentSub3 extends Component implements Api9 {
   final transient List<Component> list1 = new ArrayList();
   final transient List<SettingElement<?>> children = new ArrayList();
   final transient Api2Impl3 api2Impl3;
   final transient Api2Impl api2Impl;

   @Override
   public void onMouseRelease(XYButton xYButton) {
      boolean bool = this.isHovered(xYButton.getX2(), xYButton.getX());

      for (Component component : this.list1) {
         bool = bool || component.isHovered(xYButton.getX2(), xYButton.getX());
      }

      if (!bool) {
         this.eventBus.register(new EventSub13(this));
      }
   }

   @Override
   public void addComponent(Component component) {
      this.list1.add(component);
   }

   private void handleInt(int num, int num2, SettingElement settingElement) {
      settingElement.setSize(num - (int)this.getWidth(), num2 - (int)this.getHeight());
   }

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      PointAmount pointAmount2 = new PointAmount(
         (int)(pointAmount.getX2() - this.getWidth()), (int)(pointAmount.getX() - this.getHeight()), pointAmount.getAmount()
      );
      this.children.forEach((settingElement) -> ComponentSub3.handlePointAmount5(pointAmount, settingElement));
   }

   @Override
   public void onCharTyped(int num) {
      this.children.forEach((settingElement) -> ComponentSub3.handleInt10(num, settingElement));
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = new XYButton((int)(xYButton.getX2() - this.getWidth()), (int)(xYButton.getX() - this.getHeight()), xYButton.getX3());
      this.children.forEach((settingElement) -> ComponentSub3.handleXYButton2(xYButton, settingElement));
   }

   @Override
   public void setSize(int num, int num2) {
      this.children.forEach((settingElement) -> this.handleInt(num, num2, settingElement));
   }

   private static void handleXYButton2(XYButton xYButton, SettingElement settingElement) {
      settingElement.onMouseMove(xYButton);
   }

   private static void handleInt3(int num, SettingElement settingElement) {
      settingElement.onKeyPress(num);
   }

   private void handleApi2Impl4(Api2Impl api2Impl) {
      this.children.forEach((settingElement) -> ComponentSub3.handleApi2Impl8(api2Impl, settingElement));
   }

   @Override
   public void onSettingChanged(ListSetting listSetting) {
   }

   private static void handlePointAmount5(PointAmount pointAmount, SettingElement settingElement) {
      settingElement.handlePointAmount(pointAmount);
   }

   @Override
   public void setPosition(int num, int num2) {
      int num3 = num - (int)this.getWidth();
      int num4 = num2 - (int)this.getHeight();
      super.setPosition(num3, num4);
      this.applyTransform();
      this.api2Impl3.close2();
      this.api2Impl.close2();
      this.children.forEach((settingElement) -> this.handleInt6(num, num2, settingElement));
      this.popMatrix();
   }

   private void handleInt6(int num, int num2, SettingElement settingElement) {
      ElementUtils.updateHover(settingElement, this, num, num2);
   }

   @Api8(event = EventSub13.class)
   public void handleEventSub137(EventSub13 eventSub13) {
      this.list1.remove(eventSub13.component);
   }

   @Override
   public void close() {
      super.close();
      this.api2Impl3.close();
      this.api2Impl.close();
      this.children.forEach(SettingElement::close);
   }

   @Override
   public void render4() {
      super.render4();
      this.api2Impl3.render4();
      this.api2Impl.render4();
   }

   @Override
   public void onKeyPress(int num) {
      this.children.forEach((settingElement) -> ComponentSub3.handleInt3(num, settingElement));
   }

   private static void handleApi2Impl8(Api2Impl api2Impl, SettingElement settingElement) {
      settingElement.render(api2Impl);
   }

   private static void handleXYButton9(XYButton xYButton, SettingElement settingElement) {
      ElementUtils.handleClick(settingElement, xYButton);
   }

   private static void handleInt10(int num, SettingElement settingElement) {
      settingElement.onCharTyped(num);
   }

   @Override
   public void init() {
   }

   private static ColoredRectBatch withSettingElement11(SettingElement settingElement) {
      float flt = settingElement.getHeight() + settingElement.getHeightOffset() + Utils4.pos.y;
      return new ColoredRectBatch()
         .rectSized(Utils4.pos.x, flt, 80.0F, 2.0F, RectColors.horizontalGradient(RenderColor.TRANSLUCENT, Utils4.renderColor7))
         .rectSized(Utils4.pos.x + 80, flt, 101.0F, 2.0F, RectColors.oneColor(Utils4.renderColor7))
         .rectSized(Utils4.pos.x + 80 + 101, flt, 80.0F, 2.0F, RectColors.horizontalGradient(Utils4.renderColor7, RenderColor.TRANSLUCENT));
   }

   public ComponentSub3(EventBus eventBus, Toggleable toggleable, float flt, float flt2) {
      super(eventBus, flt, flt2, 305.0F, 0.0F);
      float flt3 = this.widthOffset - Utils4.pos.x * 2;
      float flt4 = 0.0F;
      SettingElement<?> settingElement = toggleable.getModuleKeybind().createSettingElement(eventBus, this, Utils4.pos.x, Utils4.pos.y + flt4, flt3);
      this.children.add(settingElement);
      flt4 += settingElement.getHeightOffset() + Utils4.pos.y * 2 + 2.0F;
      SettingElement<?> settingElement2 = toggleable.getModuleKeybind2().createSettingElement(eventBus, this, Utils4.pos.x, Utils4.pos.y + flt4, flt3);
      this.children.add(settingElement2);
      flt4 += settingElement2.getHeightOffset() + Utils4.pos.y;
      this.heightOffset = Utils4.pos.y + flt4;
      this.api2Impl3 = new Api2Impl3<>(() -> ComponentSub3.withSettingElement11(settingElement));
      this.api2Impl = new Api2Impl(this::handleApi2Impl4);
      this.render4();
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = new XYButton((int)(xYButton.getX2() - this.getWidth()), (int)(xYButton.getX() - this.getHeight()), xYButton.getX3());
      this.children.forEach((settingElement) -> ComponentSub3.handleXYButton9(xYButton, settingElement));
   }
}
