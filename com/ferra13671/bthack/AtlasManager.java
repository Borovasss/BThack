package com.ferra13671.bthack;

import com.ferra13671.gltextureutils.GLTexture;
import com.ferra13671.gltextureutils.atlas.TextureAtlas;
import java.util.ArrayList;
import java.util.List;

public class AtlasManager extends Manager {
   final transient AtlasManager.Helper9 helper91 = new AtlasManager.Helper9("atlas.categories");
   final transient AtlasManager.Helper9 atlas = new AtlasManager.Helper9("atlas.icons");

   public TextureAtlas getTextureAtlas() {
      this.getAtlas();
      return this.atlas.getTextureAtlas();
   }

   public TextureAtlas getHelper9() {
      this.getAtlas();
      return this.helper91.getTextureAtlas();
   }

   public void handleGLTexture(GLTexture... gLTextureArr) {
      this.getAtlas3();

      for (GLTexture gLTexture : gLTextureArr) {
         this.atlas.handleGLTexture(gLTexture);
      }
   }

   @Override
   public void getAtlas2() {
      super.getAtlas2();
      this.helper91.getName();
      this.atlas.getName();
   }

   public void handleGLTexture1(GLTexture... gLTextureArr) {
      this.getAtlas3();

      for (GLTexture gLTexture : gLTextureArr) {
         this.helper91.handleGLTexture(gLTexture);
      }
   }

   public static class Helper9 {
      final transient String name;
      final transient List list1 = new ArrayList();
      transient TextureAtlas textureAtlas1;

      public void handleGLTexture(GLTexture gLTexture) {
         if (!this.list1.contains(gLTexture)) {
            this.list1.add(gLTexture);
         }
      }

      Helper9(String string) {
         this.name = string;
      }

      public TextureAtlas getTextureAtlas() {
         return this.textureAtlas1;
      }

      public void getName() {
         this.textureAtlas1 = new TextureAtlas(this.name, this.list1);
      }
   }
}
