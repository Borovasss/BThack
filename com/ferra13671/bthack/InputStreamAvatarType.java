package com.ferra13671.bthack;

import java.io.InputStream;

public record InputStreamAvatarType(InputStream inputstream, ImageBody avatartype) {

   public InputStream getInputstream() {
      return this.inputstream;
   }

   public ImageBody getAvatartype() {
      return this.avatartype;
   }
}
