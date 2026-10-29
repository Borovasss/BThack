package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.mojang.brigadier.context.CommandContext;
import java.nio.file.Path;
import net.minecraft.util.Util;

@CommandInfo(aliases = "openDirectory")
public class OpenDirectoryCommand extends CommandBase {
   private static void handlePath(Path path, CommandContext commandContext) {
      Util.getOperatingSystem().open(path);
   }

   private static void handlePath1(Path path, CommandContext commandContext) {
      Util.getOperatingSystem().open(path.resolve("configs"));
   }

   @Override
   public void build(Helper5<Object> helper5) {
      Path path = ClientLoader.getInstance().getResourceManager().mainFolder().toPath();
      helper5.then2(of("socials").executes((commandContext) -> OpenDirectoryCommand.handlePath2(path, commandContext)), of("configs").executes((commandContext) -> OpenDirectoryCommand.handlePath1(path, commandContext)))
         .executes((commandContext) -> OpenDirectoryCommand.handlePath(path, commandContext));
   }

   private static void handlePath2(Path path, CommandContext commandContext) {
      Util.getOperatingSystem().open(path.resolve("socials"));
   }
}
