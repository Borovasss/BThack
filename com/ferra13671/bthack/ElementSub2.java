package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import java.io.Closeable;
import net.minecraft.util.Formatting;

public class ElementSub2 extends Element implements Closeable {
   final transient RenderableImpl2 colorField;
   final transient NamedObject namedObject;
   final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getRoundedRectBatch);

   public ElementSub2(EventBus eventBus, float flt, float flt2, RenderableImpl2 renderableImpl2, NamedObject namedObject) {
      super(eventBus, flt, flt2, 38.0F, 38.0F);
      this.colorField = renderableImpl2;
      this.namedObject = namedObject;
   }

   @Override
   public void close() {
      super.close();
      this.api2Impl3.close();
   }

   @Override
   protected void close5() {
      this.colorField
         .getSearchComponent()
         .getComponentHelper()
         .handleString(this.namedObject.getName() + "\n" + Formatting.DARK_GRAY + this.namedObject.getString1());
   }

   private RoundedRectBatch getRoundedRectBatch() {
      return new RoundedRectBatch()
         .rectSized(
            this.getWidth(),
            this.getHeight(),
            38.0F,
            38.0F,
            10.0F,
            RectColors.oneColor(Utils4.roundedRectBatch.multiply(RenderColor.of(1.0F, 1.0F, 1.0F, 0.4F)))
         );
   }

   @Override
   public void setPosition(int num, int num2) {
      if ((Boolean)this.helper35.getValue()) {
         this.api2Impl3.close2();
      }
   }

   public void handleApi2Impl(Api2Impl api2Impl) {
      float flt = this.getHeight() + this.colorField.getScrollY();
      if (flt > -38.0F && flt < this.colorField.getHeightOffset()) {
         ((RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator7))
            .rectSized(this.getWidth(), this.getHeight(), 38.0F, 38.0F, 10.0F, RectColors.oneColor(Utils4.renderColor17));
         this.namedObject.handleApi2Impl(api2Impl, this.getWidth() + 3.0F, this.getHeight() + 3.0F);
      }
   }

   @Override
   public void close2() {
      this.colorField.getSearchComponent().getComponentHelper().handleString(null);
   }

   @Override
   public void onClick(XYButton xYButton) {
      this.colorField.handleXYButton3(xYButton, this.namedObject);
   }
}
