package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import java.nio.file.Path;
import net.minecraft.util.Formatting;

@CommandInfo(aliases = "config")
public class ConfigCommand extends CommandBase {
   private static void execute3(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("name", String.class);
      BThackClient.getInstance().getClientPaths().getHudConfig().handlePath1(ClientPaths.hudDir.resolve(string + ".json"));
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.config.hud.saved", Formatting.AQUA + string + Formatting.RESET));
   }

   private static void execute(CommandContext commandContext) {
      Path path = (Path)commandContext.getArgument("config", Path.class);
      BThackClient.getInstance().getClientPaths().getHudConfig().fromJson(path);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.config.hud.loaded", Formatting.AQUA + path.toFile().getName().replace(".json", "") + Formatting.RESET)
      );
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then2(
         of("modules")
            .then2(
               of("save").then(argument("name", CommandArgumentBaseUtils.commandArgumentBase10).executes(ConfigCommand::run)),
               of("load").then(argument("config", CommandArgumentBaseUtils.withPath3(ClientPaths.modulesDir)).executes(ConfigCommand::execute2))
            ),
         of("hud")
            .then2(
               of("save").then(argument("name", CommandArgumentBaseUtils.commandArgumentBase10).executes(ConfigCommand::execute3)),
               of("load").then(argument("config", CommandArgumentBaseUtils.withPath3(ClientPaths.hudDir)).executes(ConfigCommand::execute))
            )
      );
   }

   private static void run(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("name", String.class);
      BThackClient.getInstance().getClientPaths().getModuleConfig().handlePath1(ClientPaths.modulesDir.resolve(string + ".json"));
      error(BThackClient.getInstance().getTranslationManager().translateFormat("command.config.modules.saved", Formatting.AQUA + string + Formatting.RESET));
   }

   private static void execute2(CommandContext commandContext) {
      Path path = (Path)commandContext.getArgument("config", Path.class);
      BThackClient.getInstance().getClientPaths().getModuleConfig().fromJson(path);
      error(
         BThackClient.getInstance()
            .getTranslationManager()
            .translateFormat("command.config.modules.loaded", Formatting.AQUA + path.toFile().getName().replace(".json", "") + Formatting.RESET)
      );
   }
}
