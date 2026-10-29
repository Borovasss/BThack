package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class SettingElementSub2 extends SettingElement {
   transient boolean boolean1 = false;

   public SettingElementSub2(EventBus eventBus, Api9 api9, KeybindSetting keybindSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, keybindSetting, flt, flt2, flt3);
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (!this.boolean1) {
         if (xYButton.getX3() == 0) {
            this.boolean1 = true;
            this.api9.render4();
         }
      } else {
         this.setBoolean(((Helper36)((KeybindSetting)this.setting).getValue()).withInt(xYButton.getX3()).withKeyboardEnum(KeyboardEnum.Mouse));
      }
   }

   @Override
   public void render(Api2Impl api2Impl) {
      TextBatch textBatch = (TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4);
      String string = this.boolean1
         ? "<listening>"
         : ((Helper36)((KeybindSetting)this.setting).getValue()).getKeyboardEnum().getKeyName(((Helper36)((KeybindSetting)this.setting).getValue()).getInt());
      textBatch.text(new RenderText(((KeybindSetting)this.setting).getName(), this.getWidth(), this.getHeight()).withFont(HudTextures.font))
         .text(
            new RenderText(string, this.getWidth() + this.getWidthOffset() - HudTextures.font.getTextWidth(string), this.getHeight())
               .withFont(HudTextures.font)
         );
   }

   @Override
   public float getHeightOffset() {
      return 29.0F;
   }

   private void setBoolean(Helper36 helper36) {
      ((KeybindSetting)this.setting).setValue2(helper36);
      this.boolean1 = false;
      this.api9.render4();
   }

   @Override
   public void onKeyPress(int num) {
      if (this.boolean1) {
         this.setBoolean(((Helper36)((KeybindSetting)this.setting).getValue()).withInt(num == 261 ? 0 : num).withKeyboardEnum(KeyboardEnum.Keyboard));
      }
   }
}
