package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.font.FormattedText;
import com.ferra13671.gltextureutils.GlTex;
import com.ferra13671.gltextureutils.TextureFiltering;
import com.ferra13671.gltextureutils.TextureWrapping;
import com.ferra13671.gltextureutils.loader.GifLoader;
import com.ferra13671.gltextureutils.loader.TextureLoader;

public class IconImpl2 implements Icon {
   final transient String id4;
   final transient FormattedText formattedText1;
   final transient GlTex glTex1;

   @Override
   public FormattedText applyFormattedText() {
      return this.formattedText1;
   }

   public IconImpl2(DiscordProfile discordProfile) {
      this.id4 = discordProfile.getId4();
      this.formattedText1 = new FormattedText(this.id4);
      InputStreamAvatarType inputStreamAvatarType = discordProfile.getId22();
      this.glTex1 = (GlTex)(inputStreamAvatarType != null
         ? (
            inputStreamAvatarType.getAvatartype() == ImageBody.Gif
               ? GifLoader.INPUT_STREAM.createGifBuilder().name(this.id4.concat("_avatar")).info(inputStreamAvatarType.getInputstream()).build()
               : TextureLoader.INPUT_STREAM
                  .createTextureBuilder()
                  .name(this.id4.concat("_avatar"))
                  .info(inputStreamAvatarType.getInputstream())
                  .filtering(TextureFiltering.SMOOTH)
                  .wrapping(TextureWrapping.DEFAULT)
                  .build()
         )
         : RendererInit.config.gLTexture23);
   }

   @Override
   public GlTex applyGlTex1() {
      return this.glTex1;
   }

   @Override
   public String getName() {
      return this.id4;
   }
}
