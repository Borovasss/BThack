package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.ferra13671.bthack.loader.api.resource.Resource;
import com.ferra13671.cometrenderer.minecraft.font.FontInfo;
import com.ferra13671.cometrenderer.minecraft.font.FontType;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;
import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;

public enum FontStyle {
   LIGHT(ClientLoader.getInstance().getResourceManager().getResource(new Resource("bthack-client", "SF-Pro-Rounded-Light.otf"))),
   REGULAR(ClientLoader.getInstance().getResourceManager().getResource(new Resource("bthack-client", "SF-Pro-Rounded-Regular.otf"))),
   SEMIBOLD(ClientLoader.getInstance().getResourceManager().getResource(new Resource("bthack-client", "SF-Pro-Rounded-Semibold.otf")));

   public final Font font;
   final HashMap<Integer, TTFFont> fonts = new HashMap();

   public TTFFont getOrLoad(int num) {
      return this.fonts.computeIfAbsent(num, integer -> new TTFFont(FontInfo.builder().font(this.font).fontSize(integer).fontType(FontType.Plain).build()));
   }

   FontStyle(InputStream inputStream) {
      try {
         this.font = Font.createFont(0, inputStream);
      } catch (Throwable throwable) {
         throw throwable;
      }
   }

   private static FontStyle[] $values() {
      return new FontStyle[]{LIGHT, REGULAR, SEMIBOLD};
   }
}
