package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.FormattedText;
import com.ferra13671.gltextureutils.GLTexture;

public abstract class RenderableImpl9 extends Element implements Renderable {
   protected final transient HudEditorScreen hudEditorScreen;
   protected final transient FormattedText formattedText1;
   protected final transient int int1;
   protected final transient GLTexture gLTexture1;
   protected final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getColoredRectBatch);

   @Override
   public void render4() {
      this.width = 25.0F;
      this.height = Utils4.vector2i3.y + (Utils4.vector2i3.y + this.heightOffset) * this.int1;
      this.api2Impl3.render4();
   }

   @Override
   public abstract void onClick(XYButton var1);

   public RenderableImpl9(EventBus eventBus, HudEditorScreen hudEditorScreen, String string, GLTexture gLTexture, int num) {
      super(eventBus, 0.0F, 0.0F, Utils4.vector2i7.x - Utils4.vector2i3.x, Utils4.vector2i2.y);
      this.hudEditorScreen = hudEditorScreen;
      this.formattedText1 = new FormattedText(string);
      this.gLTexture1 = gLTexture;
      this.int1 = num;
   }

   @Override
   public void setPosition(int num, int num2) {
      if ((Boolean)this.helper35.getValue()) {
         this.api2Impl3.close2();
      }
   }

   public void handleColoredTextureBatch(ColoredTextureBatch coloredTextureBatch) {
      coloredTextureBatch.rectSized(
         this.getWidth(),
         this.getHeight() + this.heightOffset / 2.0F - 17.5F,
         35.0F,
         35.0F,
         RectColors.oneColor(
            this.hudEditorScreen.getRenderableImpl9() == this ? Utils4.defaultColors1.getDefaultcolor2() : Utils4.defaultColors1.getDefaultcolor4()
         ),
         BThackClient.getInstance().getAtlasManager().getHelper9().getBorder(this.gLTexture1)
      );
   }

   private ColoredRectBatch getColoredRectBatch() {
      return new ColoredRectBatch(CometRenderer.getShaderColor()::resetColor)
         .rectSized(
            this.getWidth(), this.getHeight() + this.heightOffset, 50.0F, 2.0F, RectColors.horizontalGradient(RenderColor.TRANSLUCENT, RenderColor.WHITE)
         )
         .rectSized(this.getWidth() + 50.0F, this.getHeight() + this.heightOffset, 70.0F, 2.0F, RectColors.oneColor(RenderColor.WHITE))
         .rectSized(
            this.getWidth() + 50.0F + 70.0F,
            this.getHeight() + this.heightOffset,
            50.0F,
            2.0F,
            RectColors.horizontalGradient(RenderColor.WHITE, RenderColor.TRANSLUCENT)
         );
   }

   public void handleTextBatch1(TextBatch textBatch) {
      textBatch.text(
         new RenderText(this.formattedText1, this.getWidth() + 35.0F + 12.0F, this.getHeight() + this.heightOffset / 2.0F - 17.5F)
            .withColor(this.hudEditorScreen.getRenderableImpl9() == this ? Utils4.defaultColors1.getDefaultcolor2() : Utils4.defaultColors1.getDefaultcolor4())
      );
   }
}
