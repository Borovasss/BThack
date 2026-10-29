package com.ferra13671.bthack;

import com.ferra13671.gltextureutils.GLTexture;

public record CategoryInfo(String getname2, String getname, GLTexture geticon) implements CategoryInfoApi {

   @Override
   public String getGetname() {
      return this.getname;
   }

   @Override
   public String getName() {
      return this.getname2;
   }

   @Override
   public GLTexture getGeticon() {
      return this.geticon;
   }
}
