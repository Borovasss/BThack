package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class SettingElementSub7 extends SettingElement {
   @Override
   protected void close5() {
      this.api9.render4();
      super.close5();
   }

   @Override
   public void render(Api2Impl api2Impl) {
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(new RenderText(((ListSetting)this.setting).getName(), this.getWidth(), this.getHeight() + 3.0F).withFont(HudTextures.font));
      ((ColoredTextureBatch)api2Impl.getBatch(HudTextures.nameBatchCreator5))
         .rectSized(
            this.getWidth() + this.getWidthOffset() - 35.0F,
            this.getHeight(),
            35.0F,
            35.0F,
            RectColors.oneColor(this.helper35.getValue() ? Utils4.renderColor16 : Utils4.renderColor15),
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture9)
         );
   }

   public SettingElementSub7(EventBus eventBus, Api9 api9, ListSetting listSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, listSetting, flt, flt2, flt3);
   }

   @Override
   public float getHeightOffset() {
      return 35.0F;
   }

   @Override
   public void onClick(XYButton xYButton) {
      super.close2();
      this.api9.onSettingChanged((ListSetting)this.setting);
   }

   @Override
   public void close2() {
      this.api9.render4();
      super.close2();
   }
}
