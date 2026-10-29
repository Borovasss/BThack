package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.font.FormattedText;
import com.ferra13671.gltextureutils.GlTex;

public class IconImpl implements Icon, MinecraftInstance {
   @Override
   public GlTex applyGlTex1() {
      return RendererInit.config.gLTexture23;
   }

   @Override
   public String getName() {
      return mc.getSession().getUsername();
   }

   @Override
   public FormattedText applyFormattedText() {
      return new FormattedText(this.getName());
   }
}
