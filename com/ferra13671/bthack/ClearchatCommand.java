package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;

@CommandInfo(aliases = "clearchat")
public class ClearchatCommand extends CommandBase {
   @Override
   public void build(Helper5<Object> helper5) {
      helper5.executes(ClearchatCommand::run);
   }

   private static void run(CommandContext commandContext) {
      ((ChatHudMixinApi)mc.inGameHud.getChatHud()).getField_2064();
   }
}
