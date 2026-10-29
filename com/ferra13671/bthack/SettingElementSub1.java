package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class SettingElementSub1 extends SettingElement {
   @Override
   public float getHeightOffset() {
      return 29.0F;
   }

   private boolean isInt(int num, int num2) {
      return num >= this.getWidth() + this.getWidthOffset() - Utils4.vector2i15.x
         && num <= this.getWidth() + this.getWidthOffset()
         && num2 >= this.getHeight() + this.getHeightOffset() / 2.0F - 11.0F
         && num2 <= this.getHeight() + this.getHeightOffset() / 2.0F + 11.0F;
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0 && this.isInt(xYButton.getX2(), xYButton.getX())) {
         ((BooleanSetting)this.setting).setValue2(!(Boolean)((BooleanSetting)this.setting).getValue());
         this.api9.render4();
      }
   }

   public SettingElementSub1(EventBus eventBus, Api9 api9, BooleanSetting booleanSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, booleanSetting, flt, flt2, flt3);
   }

   @Override
   public void render(Api2Impl api2Impl) {
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(new RenderText(((BooleanSetting)this.setting).getName(), this.getWidth(), this.getHeight()).withFont(HudTextures.font));
      RoundedRectBatch roundedRectBatch = (RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator7);
      roundedRectBatch.rectSized(
            this.getWidth() + this.getWidthOffset() - Utils4.vector2i15.x,
            this.getHeight() + this.getHeightOffset() / 2.0F - Utils4.vector2i15.y / 2.0F,
            Utils4.vector2i15.x,
            Utils4.vector2i15.y,
            Utils4.vector2i15.y / 2.0F,
            RectColors.oneColor(((BooleanSetting)this.setting).getValue() ? Utils4.renderColor10 : Utils4.renderColor9)
         )
         .rectSized(
            this.getWidth() + this.getWidthOffset() - (((BooleanSetting)this.setting).getValue() ? 22 : Utils4.vector2i15.x),
            this.getHeight() + this.getHeightOffset() / 2.0F - 11.0F,
            22.0F,
            22.0F,
            11.0F,
            RectColors.oneColor(Utils4.renderColor11)
         );
   }
}
