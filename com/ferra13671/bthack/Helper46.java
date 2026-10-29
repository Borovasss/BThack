package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public class Helper46 implements MinecraftInstance {
   final transient List<KeybindBinding> keybinds = new ArrayList<>();
   final transient List<KeybindBinding> mouseBinds = new ArrayList<>();

   public void unregisterKeybind(KeybindBinding keybindBinding) {
      if (keybindBinding.getBind().getHelper36().getKeyboardEnum() == KeyboardEnum.Keyboard) {
         this.keybinds.remove(keybindBinding);
      } else {
         this.mouseBinds.remove(keybindBinding);
      }
   }

   @Api8(event = OnMouseButtonEvent.class)
   public void onMouseButton(OnMouseButtonEvent onMouseButtonEvent) {
      if (!BThackClient.isInitialized() && mc.currentScreen == null) {
         if (!this.mouseBinds.isEmpty()) {
            for (KeybindBinding keybindBinding : this.mouseBinds) {
               if (keybindBinding.getBind().getHelper36().getInt() == onMouseButtonEvent.x) {
                  keybindBinding.onOnKey(onMouseButtonEvent.pressBody);
               }
            }
         }
      }
   }

   public void registerKeybind(KeybindBinding keybindBinding) {
      if (keybindBinding.getBind().getHelper36().getKeyboardEnum() == KeyboardEnum.Keyboard) {
         this.keybinds.add(keybindBinding);
      } else {
         this.mouseBinds.add(keybindBinding);
      }

      if (keybindBinding.getBind().getToggleBody() == ToggleBody.HoldReversed) {
         keybindBinding.getActivateconsumer().accept(true);
      }
   }

   @Api8(event = OnKeyEvent.class)
   public void onKey(OnKeyEvent onKeyEvent) {
      if (!BThackClient.isInitialized() && mc.currentScreen == null) {
         if (!this.keybinds.isEmpty()) {
            for (KeybindBinding keybindBinding : this.keybinds) {
               if (keybindBinding.getBind().getHelper36().getInt() == onKeyEvent.int1) {
                  keybindBinding.onOnKey(onKeyEvent.pressBody);
               }
            }
         }
      }
   }
}
