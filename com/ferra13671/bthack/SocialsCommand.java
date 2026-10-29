package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import java.util.List;
import net.minecraft.util.Formatting;

@CommandInfo(aliases = "socials")
public class SocialsCommand extends CommandBase {
   private static void handleCommandContext10(CommandContext commandContext) {
      Helper13 helper13 = (Helper13)commandContext.getArgument("friend", Helper13.class);
      String string = (String)commandContext.getArgument("alias", String.class);
      helper13.setString(string);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat(
               "command.socials.friend.changed-alias",
               Formatting.AQUA + helper13.getName() + Formatting.RESET,
               Formatting.AQUA + helper13.getString() + Formatting.RESET
            )
      );
   }

   private static List applyList1() {
      return BThackClient.getInstance().getSocialManager().getFriends();
   }

   private static void handleCommandContext2(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("enemy", String.class);
      String string2 = (String)commandContext.getArgument("alias", String.class);
      BThackClient.getInstance().getSocialManager().addEnemy(new Helper13(string, string2));
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat(
               "command.socials.enemy.added.with-alias", Formatting.AQUA + string + Formatting.RESET, Formatting.AQUA + string2 + Formatting.RESET
            )
      );
   }

   private static List getExamples() {
      return BThackClient.getInstance().getSocialManager().getFriends();
   }

   private static List getSuggestions() {
      return BThackClient.getInstance().getSocialManager().getFriends();
   }

   private static void handleCommandContext(CommandContext commandContext) {
      Helper13 helper13 = (Helper13)commandContext.getArgument("friend", Helper13.class);
      BThackClient.getInstance().getSocialManager().removeFriend(helper13.getName());
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.socials.friend.removed", Formatting.AQUA + helper13.getName() + Formatting.RESET)
      );
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then2(
         of("friends")
            .then2(
               of("add")
                  .withHelper5(
                     argument("friend", CommandArgumentBaseUtils.withSupplier4(SocialsCommand::getSuggestions, "friend")).executes(SocialsCommand::execute2),
                     argument("alias", CommandArgumentBaseUtils.commandArgumentBase5).executes(SocialsCommand::execute3)
                  ),
               of("remove")
                  .then(
                     argument("friend", CommandArgumentBaseUtils.withSupplier2(SocialsCommand::getExamples, "friend"))
                        .executes(SocialsCommand::handleCommandContext)
                  ),
               of("alias")
                  .withHelper5(
                     argument("friend", CommandArgumentBaseUtils.withSupplier2(SocialsCommand::applyList1, "friend")),
                     argument("alias", CommandArgumentBaseUtils.commandArgumentBase5).executes(SocialsCommand::handleCommandContext10)
                  )
            ),
         of("enemies")
            .then2(
               of("add")
                  .withHelper5(
                     argument("enemy", CommandArgumentBaseUtils.withSupplier4(SocialsCommand::applyList6, "enemy"))
                        .executes(SocialsCommand::handleCommandContext9),
                     argument("alias", CommandArgumentBaseUtils.commandArgumentBase5).executes(SocialsCommand::handleCommandContext2)
                  ),
               of("remove")
                  .then(
                     argument("enemy", CommandArgumentBaseUtils.withSupplier2(SocialsCommand::applyList7, "enemy"))
                        .executes(SocialsCommand::handleCommandContext5)
                  ),
               of("alias")
                  .withHelper5(
                     argument("enemy", CommandArgumentBaseUtils.withSupplier2(SocialsCommand::applyList8, "enemy")),
                     argument("alias", CommandArgumentBaseUtils.commandArgumentBase5).executes(SocialsCommand::handleCommandContext4)
                  )
            )
      );
   }

   private static void handleCommandContext4(CommandContext commandContext) {
      Helper13 helper13 = (Helper13)commandContext.getArgument("enemy", Helper13.class);
      String string = (String)commandContext.getArgument("alias", String.class);
      helper13.setString(string);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat(
               "command.socials.enemy.changed-alias",
               Formatting.AQUA + helper13.getName() + Formatting.RESET,
               Formatting.AQUA + helper13.getString() + Formatting.RESET
            )
      );
   }

   private static void execute2(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("friend", String.class);
      BThackClient.getInstance().getSocialManager().addFriend(new Helper13(string));
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.socials.friend.added", Formatting.AQUA + string + Formatting.RESET));
   }

   private static void handleCommandContext5(CommandContext commandContext) {
      Helper13 helper13 = (Helper13)commandContext.getArgument("enemy", Helper13.class);
      BThackClient.getInstance().getSocialManager().removeEnemy(helper13.getName());
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.socials.enemy.removed", Formatting.AQUA + helper13.getName() + Formatting.RESET)
      );
   }

   private static List applyList6() {
      return BThackClient.getInstance().getSocialManager().getEnemies();
   }

   private static List applyList7() {
      return BThackClient.getInstance().getSocialManager().getEnemies();
   }

   private static List applyList8() {
      return BThackClient.getInstance().getSocialManager().getEnemies();
   }

   private static void handleCommandContext9(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("enemy", String.class);
      BThackClient.getInstance().getSocialManager().addEnemy(new Helper13(string));
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.socials.enemy.added", Formatting.AQUA + string + Formatting.RESET));
   }

   private static void execute3(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("friend", String.class);
      String string2 = (String)commandContext.getArgument("alias", String.class);
      BThackClient.getInstance().getSocialManager().addFriend(new Helper13(string, string2));
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat(
               "command.socials.friend.added.with-alias", Formatting.AQUA + string + Formatting.RESET, Formatting.AQUA + string2 + Formatting.RESET
            )
      );
   }
}
