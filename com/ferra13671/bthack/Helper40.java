package com.ferra13671.bthack;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.permission.Permission;

public class Helper40 {
   final transient CommandDispatcher dispatcher = new CommandDispatcher();
   final transient ClientCommandSource commandSource = new ClientCommandSource(null, MinecraftClient.getInstance(), Helper40::isCustomCommand);

   public CommandDispatcher getCommandDispatcher() {
      return this.dispatcher;
   }

   public Helper40() {
      Helper10.getList().forEach(this::registerCommand);
   }

   public ClientCommandSource getClientCommandSource() {
      return this.commandSource;
   }

   private static boolean isCustomCommand(Permission permission) {
      return false;
   }

   private void registerCommand(Command command) {
      for (String string : command.getAliases()) {
         Helper5<Object> helper5 = new Helper5<>(string);
         command.build(helper5);
         ArgumentBuilder<ClientCommandSource, ?> argumentBuilder = helper5.build(null);
         this.dispatcher.register((LiteralArgumentBuilder)argumentBuilder);
      }
   }
}
