package com.ferra13671.bthack;

import java.lang.management.ManagementFactory;
import java.util.function.BiConsumer;
import java.util.function.Function;
import lombok.NonNull;

public class Helper17 {
   transient DiscordIpc discordIpc;
   transient @NonNull Runnable runnable1 = Helper17::runVoid2;
   transient @NonNull BiConsumer<Integer, String> biConsumer1 = Helper17::handleInteger4;
   final transient Helper35<DiscordActivity> helper35 = new Helper35<>(
      new DiscordActivity(null, null, null, null, null, null, System.currentTimeMillis() / 1000L, null, null, null), this::handleDiscordActivity8
   );
   transient boolean boolean1 = false;
   transient DiscordProfile discordProfile;

   private void handleCodeMessage(CodeMessage codeMessage) {
      this.biConsumer1.accept(codeMessage.getCode(), codeMessage.getMessage());
   }

   private void handleCodeMessage21(CodeMessage2 codeMessage2) {
      this.biConsumer1.accept(codeMessage2.getCode(), codeMessage2.getMessage());
      this.getHelper17();
   }

   private static void runVoid2() {
   }

   public boolean isDiscordIpc() {
      return this.discordIpc != null;
   }

   public DiscordProfile getDiscordProfile() {
      return this.discordProfile;
   }

   public void runVoid3() {
      if (this.getBoolean()) {
         this.discordIpc.handleHelper21Api(new Helper21ApiImpl(getInt9(), (DiscordActivity)this.helper35.getValue()));
      }
   }

   private static void handleInteger4(Integer integer, String string) {
      System.err.println("Discord IPC error " + integer + " with message: " + string);
   }

   public void setRunnable(Runnable runnable) {
      if (runnable == null) {
         throw new NullPointerException("onReady is marked non-null but is null");
      }

      this.runnable1 = runnable;
   }

   public void setBiConsumer(BiConsumer<Integer, String> biConsumer) {
      if (biConsumer == null) {
         throw new NullPointerException("onError is marked non-null but is null");
      }

      this.biConsumer1 = biConsumer;
   }

   public void runVoid5() {
      while (!this.boolean1) {
         Thread.yield();
      }
   }

   private void handleApi76(Api7 api7) {
      if (api7 instanceof CodeMessage2 codeMessage2) {
         this.handleCodeMessage21(codeMessage2);
      }

      if (api7 instanceof CodeMessage codeMessage) {
         this.handleCodeMessage(codeMessage);
      }

      if (api7 instanceof DiscordUser discordUser) {
         this.handleDiscordUser11(discordUser);
      }
   }

   public void handleFunction7(Function<DiscordActivity, DiscordActivity> function) {
      this.helper35.setValue2(function.apply((DiscordActivity)this.helper35.getValue()));
   }

   public boolean getBoolean() {
      return this.boolean1;
   }

   public void getHelper17() {
      if (this.discordIpc != null) {
         this.discordIpc.runVoid1();
         this.boolean1 = false;
         this.discordIpc = null;
         this.discordProfile = null;
      }
   }

   private void handleDiscordActivity8(DiscordActivity discordActivity) {
      this.runVoid3();
   }

   private static int getInt9() {
      String string = ManagementFactory.getRuntimeMXBean().getName();
      return Integer.parseInt(string.substring(0, string.indexOf(64)));
   }

   public boolean isLong10(long lng) {
      this.discordIpc = DiscordIpc.withConsumer(this::handleApi76);
      if (this.discordIpc == null) {
         return false;
      }

      this.discordIpc.handleHelper21Api(new Helper21(lng, 1));
      return true;
   }

   private void handleDiscordUser11(DiscordUser discordUser) {
      this.boolean1 = true;
      this.discordProfile = discordUser.getDiscorduser();
      this.runnable1.run();
      this.runVoid3();
   }
}
