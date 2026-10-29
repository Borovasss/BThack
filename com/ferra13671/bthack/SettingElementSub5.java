package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class SettingElementSub5 extends SettingElement {
   @Override
   public void onClick(XYButton xYButton) {
      switch (xYButton.getX3()) {
         case 0:
            ((ClassSetting)this.setting).setValue2(Utils3.withEnum((Enum)((ClassSetting)this.setting).getValue()));
            this.api9.render4();
            break;
         case 1:
            ComponentSub6 componentSub6 = new ComponentSub6(this.eventBus, this.api9, this, Utils11.getMouseX(), Utils11.getMouseY());
            componentSub6.runVoid();
            this.eventBus.register(new EventSub1(componentSub6));
            this.api9.addComponent(componentSub6);
      }
   }

   public SettingElementSub5(EventBus eventBus, Api9 api9, ClassSetting classSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, classSetting, flt, flt2, flt3);
   }

   @Override
   public float getHeightOffset() {
      return 29.0F;
   }

   @Override
   public void render(Api2Impl api2Impl) {
      TextBatch textBatch = (TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4);
      textBatch.text(new RenderText(((ClassSetting)this.setting).getName(), this.getWidth(), this.getHeight()).withFont(HudTextures.font))
         .text(
            new RenderText(
                  ((Enum)((ClassSetting)this.setting).getValue()).name(),
                  this.getWidth() + this.getWidthOffset() - HudTextures.font.getTextWidth(((Enum)((ClassSetting)this.setting).getValue()).name()),
                  this.getHeight()
               )
               .withFont(HudTextures.font)
         );
   }
}
