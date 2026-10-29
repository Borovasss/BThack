package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;
import java.awt.Color;

public class SettingElementSub3 extends SettingElement {
   @Override
   public float getHeightOffset() {
      return 29.0F;
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (xYButton.getX3() == 1) {
         ComponentSub5 componentSub5 = new ComponentSub5(this.eventBus, (ColorSetting)this.getSetting(), this.api9, Utils11.getMouseX(), Utils11.getMouseY());
         componentSub5.runVoid();
         this.eventBus.register(new EventSub1(componentSub5));
         this.api9.addComponent(componentSub5);
      }
   }

   @Override
   public void render(Api2Impl api2Impl) {
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(new RenderText(((ColorSetting)this.setting).getName(), this.getWidth(), this.getHeight()).withFont(HudTextures.font));
      ((RoundedTextureBatch)api2Impl.getBatch(HudTextures.nameBatchCreator8))
         .rectSized(
            this.getWidth() + this.getWidthOffset() - Utils4.vector2i16.x,
            this.getHeight() + this.getHeightOffset() / 2.0F - Utils4.vector2i16.y / 2.0F,
            Utils4.vector2i16.x,
            Utils4.vector2i16.y,
            Utils4.vector2i16.y / 2.0F,
            RectColors.oneColor(RenderColor.WHITE),
            new TextureBorder(0.0F, 0.0F, Utils4.vector2i16.x / Utils4.vector2i16.y * 4.0F, 2.0F)
         );
      ((RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator9))
         .rectSized(
            this.getWidth() + this.getWidthOffset() - Utils4.vector2i16.x,
            this.getHeight() + this.getHeightOffset() / 2.0F - Utils4.vector2i16.y / 2.0F,
            Utils4.vector2i16.x,
            Utils4.vector2i16.y,
            Utils4.vector2i16.y / 2.0F,
            RectColors.oneColor(RenderColor.of((Color)((ColorSetting)this.setting).getValue()))
         );
   }

   public SettingElementSub3(EventBus eventBus, Api9 api9, ColorSetting colorSetting, float flt, float flt2, float flt3) {
      super(eventBus, api9, colorSetting, flt, flt2, flt3);
   }
}
