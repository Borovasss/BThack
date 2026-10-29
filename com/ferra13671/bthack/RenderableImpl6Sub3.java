package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;

public class RenderableImpl6Sub3 extends RenderableImpl6 {
   final transient Api2Impl2 api2Impl = new Api2Impl2(
      new Api2Impl3<>(RenderableImpl6Sub3::getRoundedTextureBatch), new Api2Impl3<>(RenderableImpl6Sub3::getTextBatch)
   );

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      this.api2Impl.close2();
      HourMinuteDay hourMinuteDay = HourMinuteDay.withValue();
      new TextBatch(FontStyle.LIGHT.getOrLoad(12))
         .text(
            new RenderText(
                  "".concat(getInt(hourMinuteDay.getHour()) + ":" + getInt(hourMinuteDay.getHour4()))
                     + "\n"
                     + getInt(hourMinuteDay.getHour5())
                     + "."
                     + getInt(hourMinuteDay.getHour3())
                     + "."
                     + hourMinuteDay.getHour2(),
                  1 + Utils4.vector2i10.x,
                  Utils4.vector2i10.y
               )
               .withColor(Utils4.renderColor4)
         )
         .build()
         .tryDraw()
         .close();
      this.popMatrix();
   }

   private static TextBatch getTextBatch() {
      return new TextBatch(FontStyle.LIGHT.getOrLoad(20))
         .text(new RenderText(BThackClient.getInstance().getIcon().applyFormattedText(), 1 + Utils4.textBatch.x, Utils4.textBatch.y));
   }

   @Override
   public void render4() {
      this.width = 1.0F;
      this.height = this.componentHelper2.getHeightOffset() - 1.0F - this.heightOffset;
      this.api2Impl.render4();
   }

   public RenderableImpl6Sub3(ComponentHelper2 componentHelper2, EventBus eventBus) {
      super(componentHelper2, eventBus, Utils4.vector2i9.x, Utils4.vector2i9.y);
   }

   private static RoundedTextureBatch getRoundedTextureBatch() {
      return new RoundedTextureBatch()
         .setTexture(BThackClient.getInstance().getIcon().applyGlTex1())
         .rectSized(
            1 + Utils4.roundedTextureBatch.x,
            Utils4.roundedTextureBatch.y,
            56.0F,
            56.0F,
            10.0F,
            RectColors.oneColor(RenderColor.WHITE),
            new TextureBorder(0.0F, 0.0F, 1.0F, 1.0F)
         );
   }

   private static String getInt(int num) {
      return num < 10 ? "0" + num : num + "";
   }
}
