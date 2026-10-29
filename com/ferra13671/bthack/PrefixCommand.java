package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.util.Formatting;

@CommandInfo(aliases = "prefix")
public class PrefixCommand extends CommandBase {
   private void handleString1(String string) {
      BThackClient.getInstance().getCommandPrefixSetting().setValue2(string);
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.prefix.changed", Formatting.AQUA + string + Formatting.RESET));
   }

   private void run(CommandContext commandContext) {
      this.handleString1((String)commandContext.getArgument("prefix", String.class));
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then2(
         of("set").then(argument("prefix", CommandArgumentBaseUtils.commandArgumentBase6).executes(this::run)), of("default").executes(this::execute2)
      );
   }

   private void execute2(CommandContext commandContext) {
      this.handleString1("*");
   }
}
