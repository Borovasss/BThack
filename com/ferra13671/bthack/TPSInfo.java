package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.FormattedText;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

@HudInfo(name = "TPS", x = 138.0F, y = 180.0F, width = 124.0F, height = 34.0F)
public class TPSInfo extends HudElement {
   transient float float1;
   transient float float2 = 0.0F;
   final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getTextBatch);

   @Override
   public void render(Api2Impl api2Impl) {
      this.renderBackground(api2Impl);
      ((ColoredTextureBatch)api2Impl.getBatch(HudTextures.nameBatchCreator5))
         .rectSized(
            this.getWidth() + Utils4.offset.x,
            this.getHeight() + Utils4.offset.y,
            26.0F,
            26.0F,
            RectColors.oneColor(RenderColor.WHITE),
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture17)
         );
      FormattedText formattedText = new FormattedText("TPS");
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(
            new RenderText(
                  formattedText,
                  this.getWidth() + this.getWidthOffset() - Utils4.offset.x - tTFFont.getTextWidth(formattedText),
                  this.getHeight() + Utils4.offset.y
               )
               .withFont(tTFFont)
               .withColor(Utils4.roundedRectBatch)
         );
      float flt = Utils4.offset.x + 26;
      float flt2 = this.getWidthOffset() - flt - Utils4.offset.x - tTFFont.getTextWidth(formattedText);
      this.float1 = flt + flt2 / 2.0F;
   }

   @Override
   public void setY(float flt) {
      super.setY(flt);
      this.api2Impl3.render4();
   }

   private TextBatch getTextBatch() {
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      String string = String.valueOf(EntityUtils.decimalPlaces(EntityUtils.getFloat7(this.float2), 1));
      return new TextBatch()
         .text(
            new RenderText(string, this.getWidth() + this.float1 - tTFFont.getTextWidth(string) / 2.0F, this.getHeight() + Utils4.offset.y).withFont(tTFFont)
         );
   }

   @Override
   public void getApi2Impl2() {
      this.api2Impl3.close2();
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      float flt = this.float2;
      this.float2 = BThackClient.getInstance().getTpsTracker().getTps();
      if (this.float2 != flt) {
         this.api2Impl3.render4();
      }
   }

   @Override
   public void setX(float flt) {
      super.setX(flt);
      this.api2Impl3.render4();
   }
}
