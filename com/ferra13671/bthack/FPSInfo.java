package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

@HudInfo(name = "FPS", y = 139.0F, width = 112.0F, height = 34.0F, enabled = true)
public class FPSInfo extends HudElement implements MinecraftInstance {
   final transient float float1;

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
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture13)
         );
   }

   @Override
   public void getApi2Impl2() {
      String string = Integer.toString(mc.getCurrentFps());
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      new TextBatch(tTFFont)
         .text(new RenderText(string, this.getWidth() + this.float1 - tTFFont.getTextWidth(string) / 2.0F, this.getHeight() + Utils4.offset.y))
         .build()
         .tryDraw()
         .close();
   }

   public FPSInfo() {
      float flt = Utils4.offset.x + 26;
      float flt2 = this.getWidthOffset() - flt - Utils4.offset.x;
      this.float1 = flt + flt2 / 2.0F;
   }
}
