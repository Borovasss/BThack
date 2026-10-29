package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class TextFieldElement extends SettingElement {
   final transient ElementHelper elementHelper;

   @Override
   public void render(Api2Impl api2Impl) {
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(new RenderText(((CustomSetting)this.setting).getName(), this.getWidth(), this.getHeight()).withFont(HudTextures.font));
   }

   @Override
   public float getHeightOffset() {
      return 65.0F;
   }

   @Override
   public void onClick(XYButton xYButton) {
      this.elementHelper.onClick(xYButton);
   }

   private void handleElementHelper(ElementHelper elementHelper) {
      ((CustomSetting)this.setting).setValue2(elementHelper.getStringBuilder());
   }

   @Override
   public void close() {
      super.close();
      this.elementHelper.close();
   }

   public TextFieldElement(EventBus eventBus, Api9 api9, CustomSetting customSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, customSetting, flt, flt2, flt3);
      this.elementHelper = new ElementHelper(
         eventBus,
         this.getWidth(),
         this.getHeight() + HudTextures.font.getTextHeight() + 12.0F,
         this.getWidthOffset(),
         FontStyle.REGULAR.getOrLoad(20).getTextHeight(),
         "Write text...",
         20,
         this::handleElementHelper
      );
      this.elementHelper.handleString((String)((CustomSetting)this.setting).getValue());
   }

   @Override
   public void onCharTyped(int num) {
      this.elementHelper.onCharTyped(num);
   }

   @Override
   public void onKeyPress(int num) {
      this.elementHelper.onKeyPress(num);
   }

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      ElementUtils.updateHover(this.elementHelper, this, num, num2);
      this.popMatrix();
   }

   @Override
   public void onMouseRelease(XYButton xYButton) {
      this.elementHelper.onMouseRelease(xYButton);
   }
}
