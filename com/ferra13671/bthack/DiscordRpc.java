package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.ferra13671.bthack.loader.api.logger.LoggerLevel;

public class DiscordRpc {
   final transient Helper17 ipc = new Helper17();

   public DiscordProfile getProfile() {
      return this.ipc.getDiscordProfile();
   }

   private static void onRpcError(Integer integer, String string) {
      BThackClient.getInstance().getLogger().log(LoggerLevel.ERROR, String.format("Discord RPC error %s with message: %s", integer, string));
   }

   public void startAsync(Runnable runnable) {
      this.ipc.setRunnable(runnable);
      this.ipc.setBiConsumer(DiscordRpc::onRpcError);
      if (!this.ipc.isLong10(1456661594326044692L)) {
         BThackClient.getInstance().getLogger().log(LoggerLevel.WARN, "Cannot start discord RPC");
      }

      this.ipc.handleFunction7(DiscordRpc::updateActivity);
   }

   private static DiscordActivity updateActivity(DiscordActivity discordActivity) {
      return discordActivity.withString(ClientLoader.getInstance().getClientMetadata().getVersion())
         .withString6("bthack-logo")
         .withString5("BThack client")
         .withTextUrl2(new TextUrl("Discord", "https://discord.gg/vmX26ByYEX"));
   }

   public void shutdown() {
      this.ipc.getHelper17();
   }
}
