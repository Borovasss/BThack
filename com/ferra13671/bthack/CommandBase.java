package com.ferra13671.bthack;

import net.minecraft.util.Formatting;

public abstract class CommandBase implements Command, MinecraftInstance {
   final transient String[] aliases = this.getClass().getAnnotation(CommandInfo.class).aliases();

   public static void error(String string) {
      ChatUtils.handleString(string);
   }

   public static Helper5<Object> of(String string) {
      return new Helper5<>(string);
   }

   public static Helper5<Object> argument(String string, CommandArgument commandArgument) {
      return new Helper5<>(string, commandArgument);
   }

   @Override
   public String[] getAliases() {
      return this.aliases;
   }

   public static void handleString(String string) {
      error(Formatting.RED + string);
   }
}
