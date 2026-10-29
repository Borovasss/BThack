package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedBlurBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;

public class Component extends Element implements Renderable {
   final transient Api2Impl2 api2Impl2 = new Api2Impl2(
      new Api2Impl3<>(this::applyOp), new Api2Impl3<>(this::getBlurBatch), new Api2Impl3<>(this::getRectBatch), new Api2Impl3<>(this::getTextBatch)
   );

   private RoundedRectBatch getRectBatch() {
      return new RoundedRectBatch().rectSized(0.0F, 0.0F, this.widthOffset, this.heightOffset, 20.0F, RectColors.oneColor(Utils4.renderColor1));
   }

   private RoundedBlurBatch getBlurBatch() {
      return new RoundedBlurBatch(RendererInit.roundedBlurBatch).rectSized(0.0F, 0.0F, this.widthOffset, this.heightOffset, 20.0F);
   }

   public Component(EventBus eventBus, float flt, float flt2, float flt3, float flt4) {
      super(eventBus, flt, flt2, flt3, flt4);
   }

   @Override
   public void render4() {
      this.api2Impl2.render4();
   }

   private AbstractPrimitiveBatchHelper getTextBatch() {
      return new AbstractPrimitiveBatchHelper()
         .withFloat2(
            0.0F, 0.0F, this.widthOffset, this.heightOffset, 20.0F, 2.0F, RectColors.oneColor(RenderColor.TRANSLUCENT), RectColors.oneColor(Utils4.api2Impl2)
         );
   }

   private AbstractPrimitiveBatchHelper2 applyOp() {
      return new AbstractPrimitiveBatchHelper2()
         .withFloat2(5.0F, 5.0F, this.widthOffset - 10.0F, this.heightOffset - 10.0F, 20.0F, RectColors.oneColor(RenderColor.of(0.0F, 0.0F, 0.0F, 0.3F)));
   }

   @Override
   public void close() {
      this.api2Impl2.close();
      super.close();
   }

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      this.api2Impl2.close2();
      this.popMatrix();
   }
}
