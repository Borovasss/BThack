package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;
import java.util.List;

public class SearchComponent extends Component {
   final transient Api6 api6;
   final transient SubSettingContainer setting;
   final transient ComponentHelper componentHelper = new ComponentHelper(this.eventBus);
   final transient ElementHelper elementHelper;
   final transient RenderableImpl2 renderableImpl21;
   final transient RenderableImpl2 renderableImpl22;
   final transient ElementSub6 elementSub61;
   final transient ElementSub6 elementSub62;
   transient RenderableImpl2 renderableImpl23;
   final transient Api2Impl2 api2Impl;
   final transient Api2Impl3 api2Impl3;

   private static void getApi2Impl2() {
      CometRenderer.getShaderColor().setColor(Utils4.api2Impl2.toVector4f());
   }

   public void runVoid() {
      this.width = Math.min(this.getWidth() + this.getWidthOffset(), CRM.getMainFramebuffer().getWidth()) - this.getWidthOffset();
      this.height = Math.min(this.getHeight() + this.getHeightOffset(), CRM.getMainFramebuffer().getHeight()) - this.getHeightOffset();
   }

   @Override
   public void setSize(int num, int num2) {
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      this.renderableImpl23.setSize(num, num2);
      this.elementSub61.setSize(num, num2);
      this.elementSub62.setSize(num, num2);
      this.elementHelper.setSize(num, num2);
      this.componentHelper.setSize(Utils11.getMouseX(), Utils11.getMouseY());
   }

   public Api6 getApi6() {
      return this.api6;
   }

   public ComponentHelper getComponentHelper() {
      return this.componentHelper;
   }

   private BasicRectBatch getBasicRectBatch() {
      return new BasicRectBatch(SearchComponent::getApi2Impl2)
         .rectSized(1.0F, 56.0F, this.getWidthOffset() - 2.0F, 2.0F)
         .rectSized(1.0F, this.getHeightOffset() - 46.0F - 1.0F, this.getWidthOffset() - 2.0F, 2.0F)
         .rectSized(this.getWidthOffset() / 2.0F - 1.0F, this.getHeightOffset() - 46.0F + 1.0F, 2.0F, 45.0F);
   }

   public Api2Impl3 getApi2Impl3() {
      return this.api2Impl3;
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handleClick(this.renderableImpl23, xYButton2);
      ElementUtils.handleClick(this.elementSub61, xYButton2);
      ElementUtils.handleClick(this.elementSub62, xYButton2);
      ElementUtils.handleClick(this.elementHelper, xYButton2);
   }

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      PointAmount pointAmount2 = EntityUtils.withPointAmount11(pointAmount, (int)this.getWidth(), (int)this.getHeight());
      ElementUtils.handlePointClick(this.renderableImpl23, pointAmount2);
      ElementUtils.handlePointClick(this.elementSub61, pointAmount2);
      ElementUtils.handlePointClick(this.elementSub62, pointAmount2);
      ElementUtils.handlePointClick(this.elementHelper, pointAmount2);
   }

   public void setRenderableImpl2(RenderableImpl2 renderableImpl2) {
      if (renderableImpl2 != this.renderableImpl23) {
         this.renderableImpl23 = renderableImpl2;
         this.renderableImpl23.render4();
      }
   }

   public ElementHelper getElementHelper() {
      return this.elementHelper;
   }

   @Override
   public void close() {
      super.close();
      this.api2Impl.close();
      this.elementSub61.close();
      this.elementSub62.close();
      this.renderableImpl21.close();
      this.renderableImpl22.close();
      this.elementHelper.close();
      this.componentHelper.close();
   }

   @Override
   public void setPosition(int num, int num2) {
      super.setPosition(num, num2);
      num -= (int)this.getWidth();
      num2 -= (int)this.getHeight();
      this.applyTransform();
      this.api2Impl.close2();
      this.api2Impl3.close2();
      ElementUtils.updateHover(this.elementSub61, this, num, num2);
      ElementUtils.updateHover(this.elementSub62, this, num, num2);
      ElementUtils.updateHover(this.renderableImpl23, this, num, num2);
      ElementUtils.updateHover(this.elementHelper, this, num, num2);
      this.popMatrix();
      ElementUtils.updateHover(this.componentHelper, null, num, num2);
   }

   @Override
   public void onKeyPress(int num) {
      this.elementHelper.onKeyPress(num);
   }

   public SubSettingContainer getSetting() {
      return this.setting;
   }

   @Override
   public void onCharTyped(int num) {
      this.elementHelper.onCharTyped(num);
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());
      this.renderableImpl23.onMouseMove(xYButton2);
      this.elementSub61.onMouseMove(xYButton2);
      this.elementSub62.onMouseMove(xYButton2);
      this.elementHelper.onMouseMove(xYButton2);
   }

   @Override
   public void render4() {
      super.render4();
      this.api2Impl.render4();
      this.renderableImpl23.render4();
      this.componentHelper.render4();
   }

   public RenderableImpl2 getRenderableImpl2() {
      return this.renderableImpl23;
   }

   protected void handleSetting2(Setting setting) {
      BThackClient.getInstance().getEventBus().register(new EventSub12(setting));
   }

   private TextBatch getTextBatch() {
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(29));
      this.elementSub61.handleTextBatch(textBatch);
      this.elementSub62.handleTextBatch(textBatch);
      return textBatch;
   }

   private void handleElementHelper(ElementHelper elementHelper) {
      if (this.renderableImpl23 != null) {
         this.renderableImpl23.handleElementHelper4(elementHelper);
      }
   }

   private static BasicTextureBatch getBasicTextureBatch() {
      return new BasicTextureBatch()
         .setTexture(RendererInit.config.gLTexture21)
         .rectSized(10.0F, 10.0F, 35.0F, 35.0F, new TextureBorder(0.0F, 0.0F, 1.0F, 1.0F));
   }

   public SearchComponent(EventBus eventBus, SubSettingContainer subSettingContainer, Api6 api6, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i23.x, Utils4.vector2i23.y);
      this.api6 = api6;
      this.setting = subSettingContainer;
      float flt3 = 55.0F;

      class RenderableImpl2Sub1 extends RenderableImpl2 {
         final transient SearchComponent searchComponent;

         @Override
         public List<NamedObject> applyList5() {
            return this.searchComponent.setting.getMap();
         }

         @Override
         public void handleXYButton3(XYButton xYButton, NamedObject namedObject) {
            boolean bool = ((List)this.searchComponent.setting.getValue()).size() >= this.searchComponent.setting.getCurrentsize();
            if (bool) {
               this.searchComponent.setting.getIgnoreBody().onModifyConsumer.accept((List)this.searchComponent.setting.getValue(), namedObject.getObject());
            } else {
               ((List)this.searchComponent.setting.getValue()).add(namedObject.getObject());
            }

            if (!bool || this.searchComponent.setting.getIgnoreBody().modify) {
               this.searchComponent.handleSetting2(this.searchComponent.setting);
            }

            this.render4();
         }

         RenderableImpl2Sub1(EventBus eventBus, float flt, float flt2, SearchComponent searchComponent2) {
            super(eventBus, flt, flt2, searchComponent2);
            this.searchComponent = SearchComponent.this;
         }
      }


      class RenderableImpl2Sub2 extends RenderableImpl2 {
         final transient SearchComponent searchComponent;

         @Override
         public List<NamedObject> applyList5() {
            return this.searchComponent.setting.applyList();
         }

         @Override
         public void handleXYButton3(XYButton xYButton, NamedObject namedObject) {
            ((List)this.searchComponent.setting.getValue()).remove(namedObject.getObject());
            this.searchComponent.handleSetting2(this.searchComponent.setting);
            this.render4();
         }

         RenderableImpl2Sub2(EventBus eventBus, float flt, float flt2, SearchComponent searchComponent2) {
            super(eventBus, flt, flt2, searchComponent2);
            this.searchComponent = SearchComponent.this;
         }
      }

      this.elementHelper = new ElementHelper(eventBus, flt3, 10.0F, this.getWidthOffset() - flt3 - 10.0F, 41.0F, "Search...", 34, this::handleElementHelper);
      this.renderableImpl21 = new RenderableImpl2Sub1(this.eventBus, 0.0F, 58.0F, this);
      this.renderableImpl22 = new RenderableImpl2Sub2(this.eventBus, 0.0F, 58.0F, this);
      this.elementSub61 = new ElementSub6(this.eventBus, 0.0F, this.getHeightOffset() - 46.0F, "Add", this.renderableImpl21);
      this.elementSub62 = new ElementSub6(this.eventBus, this.getWidthOffset() / 2.0F, this.getHeightOffset() - 46.0F, "Remove", this.renderableImpl22);
      this.renderableImpl23 = this.renderableImpl21;
      this.api2Impl = new Api2Impl2(new Api2Impl3<>(this::getBasicRectBatch), new Api2Impl3<>(SearchComponent::getBasicTextureBatch));
      this.api2Impl3 = new Api2Impl3<>(this::getTextBatch);
      this.renderableImpl23.render4();
   }

   @Override
   public void onMouseRelease(XYButton xYButton) {
      this.elementHelper.onMouseRelease(xYButton);
      if (!this.isHovered(xYButton.getX2(), xYButton.getX())) {
         this.eventBus.register(new EventSub13(this));
      }
   }
}
