package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.ferra13671.bthack.loader.api.resource.Resource;
import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.ferra13671.gltextureutils.ColorMode;
import com.ferra13671.gltextureutils.builder.GLTextureInfo;
import com.ferra13671.gltextureutils.loader.TextureLoader;

class Helper43 implements TextureLoader {
   @OverriddenMethod
   public GLTextureInfo load(Resource resource, ColorMode colorMode) throws Exception {
      return TextureLoader.INPUT_STREAM.load(ClientLoader.getInstance().getResourceManager().getResource(resource), colorMode);
   }

   @OverriddenMethod
   public GLTextureInfo load(Object var1, ColorMode var2) throws Exception {
      return this.load((Resource)var1, var2);
   }
}
