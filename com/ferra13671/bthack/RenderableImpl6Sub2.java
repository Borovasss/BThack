package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.BasicTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;

public class RenderableImpl6Sub2 extends RenderableImpl6 {
   final transient Api2Impl2 api2Impl = new Api2Impl2(
      new Api2Impl3<>(RenderableImpl6Sub2::getBasicTextureBatch), new Api2Impl3<>(RenderableImpl6Sub2::getTextBatch)
   );

   public RenderableImpl6Sub2(ComponentHelper2 componentHelper2, EventBus eventBus) {
      super(componentHelper2, eventBus, Utils4.vector2i6.x, Utils4.vector2i6.y);
   }

   private static BasicTextureBatch getBasicTextureBatch() {
      return new BasicTextureBatch()
         .setTexture(RendererInit.config.gLTexture6)
         .rectSized(25.0F, 15.0F, 35.0F, 35.0F, new TextureBorder(0.0F, 0.0F, 1.0F, 1.0F));
   }

   private static TextBatch getTextBatch() {
      return new TextBatch(FontStyle.SEMIBOLD.getOrLoad(34))
         .text(new RenderText("BThack", 71.0F, 32.5F - FontStyle.SEMIBOLD.getOrLoad(34).getTextHeight() / 2.0F));
   }

   @Override
   public void render4() {
      this.width = 1.0F;
      this.height = 1.0F;
      this.api2Impl.render4();
   }

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      this.api2Impl.close2();
      this.popMatrix();
   }
}
