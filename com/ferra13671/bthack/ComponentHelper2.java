package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicRectBatch;
import java.util.ArrayList;
import java.util.List;

public class ComponentHelper2 extends Component implements MinecraftInstance {
   final transient ScreenHelperSub1 screenHelperSub1;
   transient RenderableImpl6Sub2 renderableImpl6Sub2;
   final transient HudEditorScreen hudEditorScreen;
   final transient List<RenderableImpl6Sub1> list1 = new ArrayList<>();
   transient RenderableImpl6Sub3 renderableImpl6Sub3;
   transient RenderableImpl6Sub1 renderableImpl6Sub1;
   final transient Api2Impl2 api2Impl2 = new Api2Impl2(new Api2Impl3<>(this::getBasicRectBatch));

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      this.renderableImpl6Sub2.onMouseMove(xYButton2);
      this.hudEditorScreen.onMouseMove(xYButton2);
      if (this.renderableImpl6Sub1 != null) {
         this.renderableImpl6Sub1.onMouseMove(xYButton2);
      }

      this.renderableImpl6Sub3.onMouseMove(xYButton2);
   }

   @Api8(event = EventSub15.class, priority = 1000)
   public void getApi2Impl2() {
      this.render4();
      this.api2Impl2.render4();
   }

   public ScreenHelperSub1 getScreenHelperSub1() {
      return this.screenHelperSub1;
   }

   public RenderableImpl6Sub2 getRenderableImpl6Sub2() {
      return this.renderableImpl6Sub2;
   }

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      PointAmount pointAmount2 = EntityUtils.withPointAmount11(pointAmount, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handlePointClick(this.renderableImpl6Sub2, pointAmount2);
      ElementUtils.handlePointClick(this.hudEditorScreen, pointAmount2);
      if (this.renderableImpl6Sub1 != null) {
         ElementUtils.handlePointClick(this.renderableImpl6Sub1, pointAmount2);
      }

      ElementUtils.handlePointClick(this.renderableImpl6Sub3, pointAmount2);
   }

   @Api8(event = EventSub20.class)
   public void runVoid1() {
      if (this.renderableImpl6Sub1 != null) {
         this.renderableImpl6Sub1.render4();
      }
   }

   @Override
   public void close6() {
      this.renderableImpl6Sub2.close6();
      this.hudEditorScreen.close6();
      if (this.renderableImpl6Sub1 != null) {
         this.renderableImpl6Sub1.close6();
      }

      this.renderableImpl6Sub3.close6();
   }

   private static void getApi2Impl22() {
      CometRenderer.getShaderColor().setColor(Utils4.api2Impl2.toVector4f());
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handleClick(this.renderableImpl6Sub2, xYButton2);
      ElementUtils.handleClick(this.hudEditorScreen, xYButton2);
      if (this.renderableImpl6Sub1 != null) {
         ElementUtils.handleClick(this.renderableImpl6Sub1, xYButton2);
      }

      ElementUtils.handleClick(this.renderableImpl6Sub3, xYButton2);
   }

   @Override
   public void render4() {
      this.width = mc.getWindow().getFramebufferWidth() / 2.0F - this.widthOffset / 2.0F;
      this.height = mc.getWindow().getFramebufferHeight() / 2.0F - this.heightOffset / 2.0F;
      super.render4();
      this.renderableImpl6Sub2.render4();
      this.hudEditorScreen.render4();
      this.list1.forEach(RenderableImpl6Sub1::render4);
      this.renderableImpl6Sub3.render4();
   }

   @Override
   public void setPosition(int num, int num2) {
      super.setPosition(num, num2);
      this.applyTransform();
      this.api2Impl2.close2();
      num -= (int)this.width;
      num2 -= (int)this.height;
      ElementUtils.updateHover(this.renderableImpl6Sub2, this, num, num2);
      ElementUtils.updateHover(this.hudEditorScreen, this, num, num2);
      if (this.renderableImpl6Sub1 != null) {
         ElementUtils.updateHover(this.renderableImpl6Sub1, this, num, num2);
      }

      ElementUtils.updateHover(this.renderableImpl6Sub3, this, num, num2);
      this.popMatrix();
   }

   @Api8(event = EventSub16.class)
   public void setRenderableImpl6Sub1(EventSub16 eventSub16) {
      this.hudEditorScreen.render4();

      for (RenderableImpl6Sub1 renderableImpl6Sub1 : this.list1) {
         if (renderableImpl6Sub1.getCategoryInfoApi() == eventSub16.categoryInfoApi) {
            this.renderableImpl6Sub1 = renderableImpl6Sub1;
            return;
         }
      }

      this.renderableImpl6Sub1 = null;
   }

   private BasicRectBatch getBasicRectBatch() {
      return new BasicRectBatch(ComponentHelper2::getApi2Impl22)
         .rectSized(
            this.renderableImpl6Sub2.getWidth() + this.renderableImpl6Sub2.getWidthOffset(),
            this.renderableImpl6Sub2.getHeight(),
            2.0F,
            this.heightOffset - 2.0F
         )
         .rectSized(
            this.renderableImpl6Sub2.getWidth(),
            this.renderableImpl6Sub2.getHeight() + this.renderableImpl6Sub2.getHeightOffset(),
            this.renderableImpl6Sub2.getWidthOffset(),
            2.0F
         )
         .rectSized(211.0F, 65.0F, 640.0F, 2.0F)
         .rectSized(1.0F, this.renderableImpl6Sub3.getHeight() - 2.0F, 208.0F, 2.0F);
   }

   @Override
   public void setSize(int num, int num2) {
      num -= (int)this.width;
      num2 -= (int)this.height;
      this.renderableImpl6Sub2.setSize(num, num2);
      this.hudEditorScreen.setSize(num, num2);
      if (this.renderableImpl6Sub1 != null) {
         this.renderableImpl6Sub1.setSize(num, num2);
      }

      this.renderableImpl6Sub3.setSize(num, num2);
   }

   @Override
   public void onKeyPress(int num) {
      this.renderableImpl6Sub2.onKeyPress(num);
      this.hudEditorScreen.onKeyPress(num);
      if (this.renderableImpl6Sub1 != null) {
         this.renderableImpl6Sub1.onKeyPress(num);
      }

      this.renderableImpl6Sub3.onKeyPress(num);
   }

   @Override
   public void onCharTyped(int num) {
      this.renderableImpl6Sub2.onCharTyped(num);
      this.hudEditorScreen.onCharTyped(num);
      if (this.renderableImpl6Sub1 != null) {
         this.renderableImpl6Sub1.onCharTyped(num);
      }

      this.renderableImpl6Sub3.onCharTyped(num);
   }

   public RenderableImpl6Sub1 getRenderableImpl6Sub1() {
      return this.renderableImpl6Sub1;
   }

   public ComponentHelper2(EventBus eventBus, ScreenHelperSub1 screenHelperSub1) {
      super(eventBus, 0.0F, 0.0F, Utils4.vector2i1.x, Utils4.vector2i1.y);
      this.screenHelperSub1 = screenHelperSub1;
      this.renderableImpl6Sub2 = new RenderableImpl6Sub2(this, eventBus);
      this.hudEditorScreen = new HudEditorScreen(this, eventBus);

      for (CategoryInfoApi categoryInfoApi : BThackClient.getInstance().getModuleManager().getCategories()) {
         this.list1.add(new RenderableImpl6Sub1(this, eventBus, categoryInfoApi));
      }

      this.renderableImpl6Sub3 = new RenderableImpl6Sub3(this, eventBus);
   }

   public HudEditorScreen getHudEditorScreen() {
      return this.hudEditorScreen;
   }
}
