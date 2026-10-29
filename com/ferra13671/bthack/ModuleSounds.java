package com.ferra13671.bthack;

import net.minecraft.util.Identifier;

public final class ModuleSounds {
   public static final transient Identifier stageManager = BThackClient.getInstance()
      .getIdentifierHelper()
      .withString3("module-enable", ModuleSounds.class.getClassLoader().getResourceAsStream("assets/bthack-client/sounds/module-enable.ogg"), true);
   public static final transient Identifier class_29601 = BThackClient.getInstance()
      .getIdentifierHelper()
      .withString3("module-disable", ModuleSounds.class.getClassLoader().getResourceAsStream("assets/bthack-client/sounds/module-disable.ogg"), true);

   ModuleSounds() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
