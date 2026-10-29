package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.util.Formatting;

@CommandInfo(aliases = "autoauth")
public class AutoauthCommand extends CommandBase {
   private static void execute3(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("name", String.class);
      BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets().remove(string);
      BThackClient.getInstance().getClientPaths().save();
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.auto-auth.removed", Formatting.AQUA + string + Formatting.RESET));
   }

   private static void execute(CommandContext commandContext) {
      BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets().clear();
      BThackClient.getInstance().getClientPaths().save();
      error(BThackClient.getInstance().getTranslationManager().translate("command.auto-auth.cleared"));
   }

   private static void run(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("name", String.class);
      BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets().put(string, (String)commandContext.getArgument("password", String.class));
      BThackClient.getInstance().getClientPaths().save();
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.auto-auth.added", Formatting.AQUA + string + Formatting.RESET));
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then2(
         of("add")
            .withHelper5(
               argument("name", CommandArgumentBaseUtils.commandArgumentBase5),
               argument("password", CommandArgumentBaseUtils.commandArgumentBase5).executes(AutoauthCommand::run)
            ),
         of("remove").then(argument("name", CommandArgumentBaseUtils.withSupplier(AutoauthCommand::getExamples2)).executes(AutoauthCommand::execute3)),
         of("clear").executes(AutoauthCommand::execute)
      );
   }

   private static Collection getExamples2() {
      return BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets().keySet();
   }
}
