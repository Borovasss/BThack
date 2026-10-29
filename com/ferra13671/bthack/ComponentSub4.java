package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicRectBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ComponentSub4 extends Component implements Api9 {
   final transient RenderableImpl8 renderableImpl8;
   final transient Stack<RenderableImpl5> stack = new Stack<>();
   final transient List<Component> list1 = new ArrayList();
   final transient Api2Impl3 api2Impl3;

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
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      this.renderableImpl8.onMouseMove(xYButton2);
      ((RenderableImpl5)this.stack.peek()).onMouseMove(xYButton2);
   }

   @Override
   public void init() {
      if (this.isStack()) {
         ((RenderableImpl5)this.stack.peek()).close();
         this.stack.pop();
         this.render4();
      }
   }

   public RenderableImpl8 getRenderableImpl8() {
      return this.renderableImpl8;
   }

   @Override
   public void setSize(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      this.renderableImpl8.setSize(num, num2);
      ((RenderableImpl5)this.stack.peek()).setSize(num, num2);
   }

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      PointAmount pointAmount2 = EntityUtils.withPointAmount11(pointAmount, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handlePointClick(this.renderableImpl8, pointAmount2);
      ElementUtils.handlePointClick((Element)this.stack.peek(), pointAmount2);
   }

   @Api8(event = EventSub13.class)
   public void handleEventSub131(EventSub13 eventSub13) {
      this.list1.remove(eventSub13.component);
   }

   public ComponentSub4(EventBus eventBus, ListSettingApi listSettingApi, float flt, float flt2) {
      super(eventBus, flt, flt2, 305.0F, 0.0F);
      this.renderableImpl8 = new RenderableImpl8(eventBus, this);
      this.stack.push(new RenderableImpl5(eventBus, listSettingApi, this));
      this.api2Impl3 = new Api2Impl3<>(this::getBasicRectBatch);
      this.render4();
   }

   private static void init2() {
      CometRenderer.getShaderColor().setColor(Utils4.api2Impl2.toVector4f());
   }

   @Override
   public void onKeyPress(int num) {
      this.renderableImpl8.onKeyPress(num);
      ((RenderableImpl5)this.stack.peek()).onKeyPress(num);
   }

   public Stack getStack() {
      return this.stack;
   }

   @Override
   public void onCharTyped(int num) {
      this.renderableImpl8.onCharTyped(num);
      ((RenderableImpl5)this.stack.peek()).onCharTyped(num);
   }

   public boolean isStack() {
      return this.stack.size() > 1;
   }

   @Override
   public void close() {
      super.close();
      this.renderableImpl8.close();
      this.stack.forEach(RenderableImpl5::close);
      this.api2Impl3.close();
   }

   @Override
   public void addComponent(Component component) {
      this.list1.add(component);
   }

   @Override
   public void onSettingChanged(ListSetting listSetting) {
      this.stack.push(new RenderableImpl5(this.eventBus, listSetting, this));
      this.render4();
   }

   @Override
   public void setPosition(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      super.setPosition(num, num2);
      this.applyTransform();
      ElementUtils.updateHover(this.renderableImpl8, this, num, num2);
      ElementUtils.updateHover((Element)this.stack.peek(), this, num, num2);
      this.api2Impl3.close2();
      this.popMatrix();
   }

   @Override
   public void render4() {
      this.renderableImpl8.render4();
      ((RenderableImpl5)this.stack.peek()).render4();
      this.heightOffset = this.renderableImpl8.getHeightOffset() + 2.0F + ((RenderableImpl5)this.stack.peek()).getHeightOffset();
      this.api2Impl3.render4();
      super.render4();
   }

   private BasicRectBatch getBasicRectBatch() {
      return new BasicRectBatch(ComponentSub4::init2)
         .rectSized(1.0F, ((RenderableImpl5)this.stack.peek()).getHeight() - 2.0F, this.getWidthOffset() - 2.0F, 2.0F);
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handleClick(this.renderableImpl8, xYButton2);
      ElementUtils.handleClick((Element)this.stack.peek(), xYButton2);
   }
}
