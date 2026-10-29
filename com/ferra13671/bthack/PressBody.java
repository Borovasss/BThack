package com.ferra13671.bthack;

public enum PressBody {
   Press,
   Release;

   private static PressBody[] $values() {
      return new PressBody[]{Press, Release};
   }
}
