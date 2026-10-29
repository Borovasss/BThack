package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.util.Formatting;

@CommandInfo(aliases = "auto2fa")
public class Auto2faCommand extends CommandBase {
   private static void execute(CommandContext commandContext) {
      BThackClient.getInstance().getAuthSecrets().getAuto2faSecrets().clear();
      BThackClient.getInstance().getClientPaths().save();
      error(BThackClient.getInstance().getTranslationManager().translate("command.auto-2fa.cleared"));
   }

   private static void run(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("name", String.class);
      BThackClient.getInstance().getAuthSecrets().getAuto2faSecrets().put(string, (String)commandContext.getArgument("key", String.class));
      BThackClient.getInstance().getClientPaths().save();
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.auto-2fa.added", Formatting.AQUA + string + Formatting.RESET));
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then2(
         of("add")
            .withHelper5(
               argument("name", CommandArgumentBaseUtils.commandArgumentBase5),
               argument("key", CommandArgumentBaseUtils.commandArgumentBase5).executes(Auto2faCommand::run)
            ),
         of("remove").then(argument("name", CommandArgumentBaseUtils.withSupplier(Auto2faCommand::getExamples2)).executes(Auto2faCommand::execute3)),
         of("clear").executes(Auto2faCommand::execute)
      );
   }

   private static void execute3(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("name", String.class);
      BThackClient.getInstance().getAuthSecrets().getAuto2faSecrets().remove(string);
      BThackClient.getInstance().getClientPaths().save();
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.auto-2fa.removed", Formatting.AQUA + string + Formatting.RESET));
   }

   private static Collection getExamples2() {
      return BThackClient.getInstance().getAuthSecrets().getAuto2faSecrets().keySet();
   }
}
