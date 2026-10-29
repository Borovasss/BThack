package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.FormattedText;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;
import net.minecraft.util.math.Vec3d;

@HudInfo(name = "Speed", y = 180.0F, width = 124.0F, height = 34.0F)
public class SpeedInfo extends HudElement implements MinecraftInstance {
   static final transient String string1 = "-";
   public final transient NumberSetting numberSetting = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Update Delay"))
         .value(250.0F))
      .min(100.0F)
      .max(2500.0F)
      .decimalPlaces(0)
      .build();
   public final transient BooleanSetting booleanSetting = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Consider Y"))
         .value(true))
      .build();
   transient float float1;
   transient String string2 = "-";
   final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getTextBatch);
   final transient Helper45 helper45 = new Helper45();
   transient Vec3d vec3d = null;

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
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture14)
         );
      FormattedText formattedText = new FormattedText("m/s");
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

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized()) {
         if (this.helper45.passed(((Float)this.numberSetting.getValue()).intValue())) {
            String string = this.string2;
            if (this.vec3d != null) {
               float flt = (float)this.vec3d.distanceTo(this.getBooleanSetting()) * (1000.0F / ((Float)this.numberSetting.getValue()).intValue());
               this.string2 = (int)flt > 99 ? String.valueOf((int)flt) : String.valueOf(EntityUtils.decimalPlaces(flt, 1));
            }

            this.vec3d = this.getBooleanSetting();
            if (!this.string2.equals(string)) {
               this.api2Impl3.render4();
            }

            this.helper45.reset();
         }
      } else {
         if (!this.string2.equals("-")) {
            this.api2Impl3.render4();
         }

         this.string2 = "-";
         this.vec3d = null;
         this.helper45.reset();
      }
   }

   @Override
   public void setX(float flt) {
      super.setX(flt);
      this.api2Impl3.render4();
   }

   private Vec3d getBooleanSetting() {
      return new Vec3d(mc.player.getX(), this.booleanSetting.getValue() ? mc.player.getY() : 0.0, mc.player.getZ());
   }

   @Override
   public void getApi2Impl2() {
      this.api2Impl3.close2();
   }

   private TextBatch getTextBatch() {
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(20);
      return new TextBatch()
         .text(
            new RenderText(this.string2, this.getWidth() + this.float1 - tTFFont.getTextWidth(this.string2) / 2.0F, this.getHeight() + Utils4.offset.y)
               .withFont(tTFFont)
         );
   }

   @Override
   public void setY(float flt) {
      super.setY(flt);
      this.api2Impl3.render4();
   }
}
