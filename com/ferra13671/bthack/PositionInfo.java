package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

@HudInfo(name = "Position", y = 98.0F, height = 34.0F, enabled = true)
public class PositionInfo extends HudElement implements MinecraftInstance {
   @Override
   public void getApi2Impl2() {
      String string = Integer.toString((int)mc.player.getEntityPos().x)
         .concat("  ")
         .concat(Integer.toString((int)mc.player.getEntityPos().y))
         .concat("  ")
         .concat(Integer.toString((int)mc.player.getEntityPos().z));
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      new TextBatch(tTFFont)
         .text(new RenderText(string, this.getWidth() + Utils4.offset.x + 26.0F + 5.0F, this.getHeight() + Utils4.offset.y))
         .build()
         .tryDraw()
         .close();
      float flt = this.widthOffset;
      this.widthOffset = Utils4.offset.x * 2 + 26 + 5 + tTFFont.getTextWidth(string);
      if (this.widthOffset != flt) {
         RendererInit.runLater(PositionInfo::getApi2Impl32);
      }
   }

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
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture12)
         );
   }

   private static void getApi2Impl32() {
      BThackClient.getInstance().getManagerHelper().render4();
   }
}
