package com.ferra13671.bthack;

import net.minecraft.client.gui.screen.Screen;

public class SetScreenEvent extends Event {
   public transient Screen screen;

   public SetScreenEvent(Screen screen) {
      this.screen = screen;
   }
}
