package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class ElementSub6 extends Element {
   final transient RenderableImpl2 colorField;
   final transient String name;

   @Override
   public void onClick(XYButton xYButton) {
      this.colorField.getSearchComponent().setRenderableImpl2(this.colorField);
      this.colorField.getSearchComponent().getApi2Impl3().render4();
   }

   @Override
   protected void close5() {
      this.colorField.getSearchComponent().getApi2Impl3().render4();
   }

   public ElementSub6(EventBus eventBus, float flt, float flt2, String string, RenderableImpl2 renderableImpl2) {
      super(eventBus, flt, flt2, Utils4.vector2i23.x / 2.0F, 46.0F);
      this.colorField = renderableImpl2;
      this.name = string;
   }

   public void handleTextBatch(TextBatch textBatch) {
      textBatch.text(
         new RenderText(
               this.name,
               this.getWidth() + this.getWidthOffset() / 2.0F - textBatch.getFontOrNull().getTextWidth(this.name) / 2.0F,
               this.getHeight() + this.getHeightOffset() / 2.0F - textBatch.getFontOrNull().getTextHeight(this.name) / 2.0F
            )
            .withColor(
               this.colorField.getSearchComponent().getRenderableImpl2() == this.colorField
                  ? Utils4.roundedRectBatch
                  : (this.helper35.getValue() ? Utils4.renderColor3 : Utils4.renderColor2)
            )
      );
   }

   @Override
   public void close2() {
      this.colorField.getSearchComponent().getApi2Impl3().render4();
   }
}
