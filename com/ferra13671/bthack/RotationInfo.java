package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

@HudInfo(name = "Rotation", y = 262.0F, width = 222.0F, height = 34.0F)
public class RotationInfo extends HudElement implements MinecraftInstance {
   transient int int1 = 0;
   transient int int2 = 0;
   final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getTextBatch);

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized() && (this.int2 != (int)mc.player.getPitch() || this.int1 != (int)mc.player.getYaw())) {
         this.int2 = (int)mc.player.getPitch();
         this.int1 = (int)mc.player.getYaw();
         this.api2Impl3.render4();
      }
   }

   @Override
   public void setX(float flt) {
      super.setX(flt);
      this.api2Impl3.render4();
   }

   @Override
   public void getApi2Impl2() {
      this.api2Impl3.close2();
   }

   @Override
   public void render(Api2Impl api2Impl) {
      this.renderBackground(api2Impl);
      float flt = this.getHeight() + Utils4.offset.y;
      ((ColoredTextureBatch)api2Impl.getBatch(HudTextures.nameBatchCreator5))
         .rectSized(
            this.getWidth() + Utils4.offset.x,
            flt,
            26.0F,
            26.0F,
            RectColors.oneColor(RenderColor.WHITE),
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture19)
         );
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(new RenderText("Yaw:", this.getWidth() + 41.0F, flt).withColor(Utils4.roundedRectBatch).withFont(tTFFont))
         .text(new RenderText("Pitch:", this.getWidth() + 128.0F, flt).withColor(Utils4.roundedRectBatch).withFont(tTFFont));
   }

   private TextBatch getTextBatch() {
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      String string = String.valueOf(this.int1 % 360);
      String string2 = String.valueOf(this.int2);
      return new TextBatch(tTFFont)
         .text(new RenderText(string, this.getWidth() + 105.0F - tTFFont.getTextWidth(string) / 2.0F, this.getHeight() + Utils4.offset.y))
         .text(new RenderText(string2, this.getWidth() + 195.0F - tTFFont.getTextWidth(string2) / 2.0F, this.getHeight() + Utils4.offset.y));
   }

   @Override
   public void setY(float flt) {
      super.setY(flt);
      this.api2Impl3.render4();
   }
}
