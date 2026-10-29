package com.ferra13671.bthack;

public enum ImageBody {
   Image,
   Gif;

   private static ImageBody[] $values() {
      return new ImageBody[]{Image, Gif};
   }
}
