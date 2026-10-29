package com.ferra13671.bthack;

import com.ferra13671.gltextureutils.GLTexture;

public enum Arrow1Body {
   Arrow1(RendererInit.config.gLTexture24),
   Arrow2(RendererInit.config.gLTexture25),
   Arrow3(RendererInit.config.gLTexture26),
   Arrow4(RendererInit.config.gLTexture27),
   Penis(RendererInit.config.gLTexture28);

   public final GLTexture texture;

   private static Arrow1Body[] $values() {
      return new Arrow1Body[]{Arrow1, Arrow2, Arrow3, Arrow4, Penis};
   }

   Arrow1Body(GLTexture gLTexture) {
      this.texture = gLTexture;
   }
}
