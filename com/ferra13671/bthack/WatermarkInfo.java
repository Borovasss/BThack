package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

@HudInfo(name = "Watermark", height = 80.0F, enabled = true)
public class WatermarkInfo extends HudElement implements MinecraftInstance {
   transient String string1;
   transient String id4;
   transient float float1;
   transient float float2;
   transient float float3;

   @Override
   public void getApi2Impl2() {
      if (this.string1 == null || this.id4 == null) {
         this.getApi2Impl3();
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized()) {
         this.string1 = null;
         this.id4 = null;
      }
   }

   @Override
   public void render(Api2Impl api2Impl) {
      if (this.string1 == null || this.id4 == null) {
         this.getApi2Impl3();
      }

      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(28);
      TTFFont tTFFont2 = FontStyle.REGULAR.getOrLoad(20);
      TTFFont tTFFont3 = FontStyle.REGULAR.getOrLoad(15);
      String string = ClientLoader.getInstance().getClientMetadata().getVersion();
      if (this.float3 == 0.0F) {
         this.float3 = Utils4.offset.x * 2 + 35 + tTFFont.getTextWidth("BThack") + tTFFont3.getTextWidth(string);
      }

      this.renderBackground(api2Impl, this.getWidth(), this.getHeight(), this.float3, 44.0F);
      float flt = Utils4.offset.x * 2 + this.float2;
      float flt2 = this.getHeight() + 44.0F + 4.0F;
      this.renderBackground(api2Impl, this.getWidth(), flt2, flt, 34.0F);
      this.renderBackground(api2Impl, this.getWidth() + flt + 4.0F, flt2, Utils4.offset.x * 2 + this.float1, 34.0F);
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(new RenderText("BThack", this.getWidth() + Utils4.offset.x + 35.0F, this.getHeight() + 5.0F).withFont(tTFFont))
         .text(
            new RenderText(
                  string,
                  this.getWidth() + this.float3 - Utils4.offset.x - tTFFont3.getTextWidth(string),
                  this.getHeight() + 44.0F - 5.0F - tTFFont3.getTextHeight(string)
               )
               .withFont(tTFFont3)
               .withColor(Utils4.roundedRectBatch)
         )
         .text(new RenderText(this.id4, this.getWidth() + Utils4.offset.x, flt2 + 5.0F).withFont(tTFFont2))
         .text(new RenderText(this.string1, this.getWidth() + flt + 4.0F + Utils4.offset.x, flt2 + 5.0F).withFont(tTFFont2));
      ((ColoredTextureBatch)api2Impl.getBatch(HudTextures.nameBatchCreator5))
         .rectSized(
            this.getWidth() + Utils4.offset.x,
            this.getHeight() + 5.0F,
            35.0F,
            35.0F,
            RectColors.oneColor(RenderColor.WHITE),
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture6)
         );
   }

   @Override
   protected void onEnable3() {
      if (!BThackClient.isInitialized()) {
         this.getApi2Impl3();
      }

      BThackClient.getInstance().getEventBus().subscribe(this);
      super.onEnable3();
   }

   private void getApi2Impl3() {
      this.string1 = mc.getCurrentServerEntry() != null ? mc.getCurrentServerEntry().address : "";
      if (this.string1.isBlank()) {
         this.string1 = "Singleplayer";
      }

      this.id4 = mc.getGameProfile().name();
      this.float1 = FontStyle.REGULAR.getOrLoad(20).getTextWidth(this.string1);
      this.float2 = FontStyle.REGULAR.getOrLoad(20).getTextWidth(this.id4);
      this.setWidth(Utils4.offset.x * 4 + this.float2 + 4.0F + this.float1);
      BThackClient.getInstance().getManagerHelper().render4();
   }
}
