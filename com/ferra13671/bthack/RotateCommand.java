package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;

@CommandInfo(aliases = "rotate")
public class RotateCommand extends CommandBase {
   private static void run(CommandContext commandContext) {
      mc.player.setYaw((Float)commandContext.getArgument("yaw", Float.class));
      mc.player.setPitch((Float)commandContext.getArgument("pitch", Float.class));
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.withHelper5(
         argument("yaw", CommandArgumentBaseUtils.commandArgumentBase4),
         argument("pitch", CommandArgumentBaseUtils.commandArgumentBase4).executes(RotateCommand::run)
      );
   }
}
