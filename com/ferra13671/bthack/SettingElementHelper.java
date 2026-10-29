package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class SettingElementHelper extends SettingElement implements MinecraftInstance {
   transient float float1 = 0.0F;
   transient boolean boolean1 = false;

   public void setFloat() {
      float flt = ((NumberSetting)this.setting).getValue2() - ((NumberSetting)this.setting).getMin();
      float flt2 = (Float)((NumberSetting)this.setting).getValue() - ((NumberSetting)this.setting).getMin();
      this.float1 = Math.clamp(this.getWidthOffset() * Math.clamp(flt2 / flt, 0.0F, 1.0F), 8.0F, this.getWidthOffset() - 8.0F);
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      if (xYButton.getX3() == 0) {
         this.boolean1 = false;
      }
   }

   public SettingElementHelper(EventBus eventBus, Api9 api9, NumberSetting numberSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, numberSetting, flt, flt2, flt3);
   }

   @Override
   public float getHeightOffset() {
      return 72.0F;
   }

   private boolean isInt(int num, int num2) {
      return num >= this.getWidth()
         && num <= this.getWidth() + this.getWidthOffset()
         && num2 >= this.getHeight() + 41.0F
         && num2 <= this.getHeight() + 41.0F + 16.0F;
   }

   @Override
   public void render(Api2Impl api2Impl) {
      this.setFloat();
      TextBatch textBatch = (TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4);
      textBatch.text(new RenderText(((NumberSetting)this.setting).getName(), this.getWidth(), this.getHeight()).withFont(HudTextures.font))
         .text(
            new RenderText(
                  ((NumberSetting)this.setting).getAsString(),
                  this.getWidth() + this.getWidthOffset() - HudTextures.font.getTextWidth(((NumberSetting)this.setting).getAsString()),
                  this.getHeight()
               )
               .withFont(HudTextures.font)
         );
      textBatch.text(
            new RenderText(((NumberSetting)this.setting).getMaxAsString(), this.getWidth(), this.getHeight() + 41.0F + 16.0F + 1.0F)
               .withColor(Utils4.renderColor13)
               .withFont(HudTextures.tTFFont1)
         )
         .text(
            new RenderText(
                  ((NumberSetting)this.setting).getMinAsString(),
                  this.getWidth() + this.getWidthOffset() - HudTextures.tTFFont1.getTextWidth(((NumberSetting)this.setting).getMinAsString()),
                  this.getHeight() + 41.0F + 16.0F + 1.0F
               )
               .withColor(Utils4.renderColor13)
               .withFont(HudTextures.tTFFont1)
         );
      RoundedRectBatch roundedRectBatch = (RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator7);
      roundedRectBatch.rectSized(
            this.getWidth() + this.float1, this.getHeight() + 44.0F, this.getWidthOffset() - this.float1, 10.0F, 5.0F, RectColors.oneColor(Utils4.renderColor8)
         )
         .rectSized(this.getWidth(), this.getHeight() + 44.0F, this.float1, 10.0F, 5.0F, RectColors.oneColor(Utils4.roundedRectBatch))
         .rectSized(this.getWidth() + this.float1 - 8.0F, this.getHeight() + 44.0F - 3.0F, 16.0F, 16.0F, 8.0F, RectColors.oneColor(Utils4.renderColor12));
   }

   public void handleInt1(int num) {
      int num2 = (int)Math.clamp(num - this.getWidth(), 0.0F, this.getWidthOffset());
      float flt = ((NumberSetting)this.setting).getMin()
         + (((NumberSetting)this.setting).getValue2() - ((NumberSetting)this.setting).getMin()) * (num2 / this.getWidthOffset());
      if ((Float)((NumberSetting)this.setting).getValue() != flt) {
         ((NumberSetting)this.setting).setValue(flt);
         this.api9.render4();
      }
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 0 && this.isInt(xYButton.getX2(), xYButton.getX())) {
         this.boolean1 = true;
         this.handleInt1(xYButton.getX2());
      }
   }

   @Override
   public void setSize(int num, int num2) {
      if (this.boolean1) {
         this.handleInt1(num);
      }
   }
}
