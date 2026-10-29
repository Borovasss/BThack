package com.ferra13671.bthack;

import java.awt.Color;

public class GetGlTextureViewEvent extends Event {
   transient Color color = null;

   public Color getColor() {
      return this.color;
   }

   public void setColor(Color color) {
      this.runVoid();
      this.color = color;
   }
}
