package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

@HudInfo(name = "Players", x = 126.0F, y = 139.0F, width = 94.0F, height = 34.0F)
public class PlayersInfo extends HudElement implements MinecraftInstance {
   transient float float1;
   transient int int1;
   final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getTextBatch);

   @Override
   public void getApi2Impl2() {
      this.api2Impl3.close2();
   }

   @Override
   public void setY(float flt) {
      super.setY(flt);
      this.api2Impl3.render4();
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      int num = mc.getNetworkHandler() != null ? mc.getNetworkHandler().getPlayerList().size() : 1;
      if (this.int1 != num) {
         this.int1 = num;
         this.api2Impl3.render4();
      }
   }

   @Override
   public void setX(float flt) {
      super.setX(flt);
      this.api2Impl3.render4();
   }

   public PlayersInfo() {
      float flt = Utils4.offset.x + 26;
      float flt2 = this.getWidthOffset() - flt - Utils4.offset.x;
      this.float1 = flt + flt2 / 2.0F;
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
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture16)
         );
   }

   private TextBatch getTextBatch() {
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      return new TextBatch()
         .text(
            new RenderText(
                  String.valueOf(this.int1),
                  this.getWidth() + this.float1 - tTFFont.getTextWidth(String.valueOf(this.int1)) / 2.0F,
                  this.getHeight() + Utils4.offset.y
               )
               .withFont(tTFFont)
         );
   }
}
