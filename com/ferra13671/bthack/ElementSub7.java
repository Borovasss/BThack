package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.gltextureutils.atlas.TextureBorder;
import java.awt.Color;

public class ElementSub7 extends Element {
   final transient ComponentSub5 componentSub5;

   public ElementSub7(EventBus eventBus, ComponentSub5 componentSub5, float flt, float flt2) {
      super(eventBus, flt, flt2, Utils4.vector2i17.x, 43.0F);
      this.componentSub5 = componentSub5;
   }

   public void handleRoundedRectBatch(RoundedRectBatch roundedRectBatch) {
      roundedRectBatch.rectSized(
         this.getWidth() + Utils4.vector2i18.x,
         this.getHeight() + Utils4.vector2i18.y + 10.0F,
         Utils4.vector2i19.x,
         Utils4.vector2i19.y,
         Utils4.vector2i19.y / 2.0F,
         RectColors.oneColor(RenderColor.of((Color)this.componentSub5.getColorSetting().getValue()))
      );
   }

   public void handleRoundedTextureBatch1(RoundedTextureBatch roundedTextureBatch) {
      roundedTextureBatch.rectSized(
         this.getWidth() + Utils4.vector2i18.x,
         this.getHeight() + Utils4.vector2i18.y + 10.0F,
         Utils4.vector2i19.x,
         Utils4.vector2i19.y,
         Utils4.vector2i19.y / 2.0F,
         RectColors.oneColor(RenderColor.WHITE),
         new TextureBorder(0.0F, 0.0F, Utils4.vector2i19.x / Utils4.vector2i19.y * 4.0F, 2.0F)
      );
   }
}
