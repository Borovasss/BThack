package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import java.util.UUID;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;

@CommandInfo(aliases = "fakeplayer")
public class FakeplayerCommand extends CommandBase {
   private static void handleCommandContext(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      float flt = ((Double)commandContext.getArgument("value", Double.class)).floatValue();
      obj.handleFloat2(flt);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.updated.max-health", "" + Formatting.AQUA + flt + Formatting.RESET)
      );
   }

   private static void execute3(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      BThackClient.getInstance().getAttackTracker().onEntityRemoved(obj);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.successfully.removed", Formatting.AQUA + obj.getString() + Formatting.RESET)
      );
   }

   private static void handleCommandContext1(CommandContext commandContext) {
      ((OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class)).setHealth(mc.player.getHealth());
      error(BThackClient.getInstance().getTranslationManager().translate("command.fake-player.copied.health"));
   }

   private static void handleCommandContext2(CommandContext commandContext) {
      ((OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class)).runVoid6();
      error(BThackClient.getInstance().getTranslationManager().translate("command.fake-player.copied.inventory"));
   }

   private static void handleCommandContext3(CommandContext commandContext) {
      ((OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class))
         .handleFloat1(mc.player.getYaw(), mc.player.getPitch());
      error(BThackClient.getInstance().getTranslationManager().translate("command.fake-player.copied.rotation"));
   }

   private static void handleCommandContext10(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      double dbl = (Double)commandContext.getArgument("x", Double.class);
      double dbl2 = (Double)commandContext.getArgument("y", Double.class);
      double dbl3 = (Double)commandContext.getArgument("z", Double.class);
      obj.handleClass_2435(new Vec3d(dbl, dbl2, dbl3));
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.updated.position", Formatting.AQUA + String.join(" ", dbl + "", dbl2 + "", dbl3 + "") + Formatting.RESET)
      );
   }

   private static void handleCommandContext5(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      obj.setBoolean3((Boolean)commandContext.getArgument("value", Boolean.class));
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.updated.invulnerability", "" + Formatting.AQUA + obj.getBoolean() + Formatting.RESET)
      );
   }

   private void execute2(CommandContext commandContext) {
      this.handleArg9(new OtherClientPlayerEntityHelper());
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then2(
         of("spawn").then(argument("name", CommandArgumentBaseUtils.commandArgumentBase7).executes(this::run)).executes(this::execute2),
         of("remove").then(argument("fake-player", CommandArgumentBaseUtils.commandArgumentBase8).executes(FakeplayerCommand::execute3)),
         of("set")
            .then(
               argument("fake-player", CommandArgumentBaseUtils.commandArgumentBase8)
                  .then2(
                     of("name").then(argument("value", CommandArgumentBaseUtils.commandArgumentBase5).executes(FakeplayerCommand::execute)),
                     of("max-health").then(argument("value", CommandArgumentBaseUtils.commandArgumentBase3).executes(FakeplayerCommand::handleCommandContext)),
                     of("health").then(argument("value", CommandArgumentBaseUtils.commandArgumentBase3).executes(FakeplayerCommand::handleCommandContext6)),
                     of("position")
                        .withHelper5(
                           argument("x", CommandArgumentBaseUtils.commandArgumentBase3),
                           argument("y", CommandArgumentBaseUtils.commandArgumentBase3),
                           argument("z", CommandArgumentBaseUtils.commandArgumentBase3).executes(FakeplayerCommand::handleCommandContext10)
                        ),
                     of("invulnerability")
                        .then(argument("value", CommandArgumentBaseUtils.commandArgumentBase1).executes(FakeplayerCommand::handleCommandContext5)),
                     of("auto-totem").then(argument("value", CommandArgumentBaseUtils.commandArgumentBase1).executes(FakeplayerCommand::handleCommandContext8))
                  )
            ),
         of("copy")
            .then(
               argument("fake-player", CommandArgumentBaseUtils.commandArgumentBase8)
                  .then2(
                     of("inventory").executes(FakeplayerCommand::handleCommandContext2),
                     of("rotation").executes(FakeplayerCommand::handleCommandContext3),
                     of("position").executes(FakeplayerCommand::handleCommandContext7),
                     of("health").executes(FakeplayerCommand::handleCommandContext1)
                  )
            )
      );
   }

   private static void execute(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      obj.setString((String)commandContext.getArgument("value", String.class));
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.updated.name", Formatting.AQUA + obj.getString() + Formatting.RESET)
      );
   }

   private static void handleCommandContext6(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      float flt = ((Double)commandContext.getArgument("value", Double.class)).floatValue();
      obj.setHealth(flt);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.updated.health", "" + Formatting.AQUA + flt + Formatting.RESET)
      );
   }

   private static void handleCommandContext7(CommandContext commandContext) {
      ((OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class))
         .handleClass_2435(mc.player.getEntityPos());
      error(BThackClient.getInstance().getTranslationManager().translate("command.fake-player.copied.position"));
   }

   private static void handleCommandContext8(CommandContext commandContext) {
      OtherClientPlayerEntityHelper obj = (OtherClientPlayerEntityHelper)commandContext.getArgument("fake-player", OtherClientPlayerEntityHelper.class);
      obj.setBoolean2((Boolean)commandContext.getArgument("value", Boolean.class));
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.updated.auto-totem", "" + Formatting.AQUA + obj.getBoolean2() + Formatting.RESET)
      );
   }

   private void run(CommandContext commandContext) {
      this.handleArg9(new OtherClientPlayerEntityHelper(UUID.randomUUID(), (String)commandContext.getArgument("name", String.class)));
   }

   private void handleArg9(OtherClientPlayerEntityHelper obj) {
      BThackClient.getInstance().getAttackTracker().onEntityAdded(obj);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.fake-player.successfully.spawned", Formatting.AQUA + obj.getString() + Formatting.RESET)
      );
   }
}
