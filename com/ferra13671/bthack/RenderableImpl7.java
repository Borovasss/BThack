package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicTextureBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;

public class RenderableImpl7 extends Element implements Renderable {
   final transient ComponentSub4 componentSub4;
   final transient Api2Impl3 api2Impl3;

   @Override
   public void render4() {
      this.width = this.componentSub4.getRenderableImpl8().getWidth() + 7.0F;
      this.height = this.componentSub4.getRenderableImpl8().getHeight() + 7.0F;
      this.api2Impl3.render4();
   }

   public RenderableImpl7(EventBus eventBus, ComponentSub4 componentSub4) {
      super(eventBus, 0.0F, 0.0F, 35.0F, 35.0F);
      this.componentSub4 = componentSub4;
      this.api2Impl3 = new Api2Impl3<>(this::getBasicTextureBatch);
   }

   @Override
   public void close2() {
      this.render4();
   }

   @Override
   public void setPosition(int num, int num2) {
      this.api2Impl3.close2();
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.componentSub4.init();
      }
   }

   private void render() {
      CometRenderer.getShaderColor().setColor((this.helper35.getValue() ? Utils4.renderColor6 : Utils4.renderColor5).toVector4f());
   }

   @Override
   protected void close5() {
      this.render4();
   }

   private BasicTextureBatch getBasicTextureBatch() {
      return new BasicTextureBatch(this::render)
         .setTexture(RendererInit.config.gLTexture20)
         .rectSized(this.getWidth(), this.getHeight(), this.getWidthOffset(), this.getHeightOffset(), new TextureBorder(0.0F, 0.0F, 1.0F, 1.0F));
   }

   @Override
   public void close() {
      this.api2Impl3.close();
      super.close();
   }
}
