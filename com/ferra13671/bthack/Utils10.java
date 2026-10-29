package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RenderColor;
import java.awt.Color;
import net.minecraft.util.math.MathHelper;

public final class Utils10 {
   public static RenderColor withRenderColor(RenderColor renderColor, RenderColor renderColor2, float flt) {
      return RenderColor.of(
         MathHelper.lerp(flt, renderColor.getColor()[0], renderColor2.getColor()[0]),
         MathHelper.lerp(flt, renderColor.getColor()[1], renderColor2.getColor()[1]),
         MathHelper.lerp(flt, renderColor.getColor()[2], renderColor2.getColor()[2]),
         MathHelper.lerp(flt, renderColor.getColor()[3], renderColor2.getColor()[3])
      );
   }

   Utils10() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Color withRenderColor1(RenderColor renderColor) {
      return new Color(renderColor.getColor()[0], renderColor.getColor()[1], renderColor.getColor()[2], renderColor.getColor()[3]);
   }

   public static int getRenderColor2(RenderColor renderColor) {
      return withRenderColor1(renderColor).getRGB();
   }

   public static RenderColor withFloat3(float flt) {
      return RenderColor.of(new Color(MathHelper.hsvToRgb(flt / 3.0F, 1.0F, 1.0F)));
   }
}
