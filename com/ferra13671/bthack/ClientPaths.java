package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ClientPaths {
   public static final transient Path modulesDir = ClientLoader.getInstance().getResourceManager().resolveFile("configs/module");
   public static final transient Path hudDir = ClientLoader.getInstance().getResourceManager().resolveFile("configs/hud");
   public static final transient Path autoAuthDir = ClientLoader.getInstance().getResourceManager().resolveFile("configs/autoauth");
   public static final transient Path auto2faDir = ClientLoader.getInstance().getResourceManager().resolveFile("configs/auto2fa");
   final transient ConfigManager moduleConfig = new ConfigManagerSub1(modulesDir.resolve("main.json"), new Api1ImplSub6());
   final transient ConfigManager hudConfig = new ConfigManagerSub1(hudDir.resolve("main.json"), new Api1ImplSub5());
   final transient ConfigManager friendsConfig = new ConfigManagerSub1(
      BThackClient.getOrCreateConfigFile("socials/friends.json", false).toPath(), new Api1ImplSub2(ClientPaths::forEachFriend, ClientPaths::addFriend)
   );
   final transient ConfigManager enemiesConfig = new ConfigManagerSub1(
      BThackClient.getOrCreateConfigFile("socials/enemies.json", false).toPath(), new Api1ImplSub2(ClientPaths::forEachEnemy, ClientPaths::addEnemy)
   );
   final transient ConfigManager localConfig = new ConfigManagerSub1(
      BThackClient.getOrCreateConfigFile("configs/client-local.json", false).toPath(), new Api1ImplSub3(this::getSaveCallbacks)
   );
   final transient ConfigManager autoAuthConfig = new ConfigManagerSub1(
      autoAuthDir.resolve("main.json"), new Api1ImplSub4(BThackClient.getInstance().getAuthSecrets().getAutoAuthSecrets())
   );
   final transient ConfigManager auto2faConfig = new ConfigManagerSub1(
      auto2faDir.resolve("main.json"), new Api1ImplSub4(BThackClient.getInstance().getAuthSecrets().getAuto2faSecrets())
   );
   final transient List<Api1Impl2<?>> saveCallbacks = new ArrayList<>();

   public ConfigManager getAutoAuthConfig() {
      return this.autoAuthConfig;
   }

   public ConfigManager getHudConfig() {
      return this.hudConfig;
   }

   private static void addFriend(Helper13 helper13) {
      BThackClient.getInstance().getSocialManager().addFriend(helper13);
   }

   public ConfigManager getEnemiesConfig() {
      return this.enemiesConfig;
   }

   public ConfigManager getFriendsConfig() {
      return this.friendsConfig;
   }

   public void load() {
      this.moduleConfig.load();
      this.hudConfig.load();
      this.friendsConfig.load();
      this.enemiesConfig.load();
      this.localConfig.load();
      this.autoAuthConfig.load();
      this.auto2faConfig.load();
   }

   public ConfigManager getLocalConfig() {
      return this.localConfig;
   }

   private static void forEachFriend(Consumer<Helper13> consumer) {
      BThackClient.getInstance().getSocialManager().forEachFriend(consumer);
   }

   private static void addEnemy(Helper13 helper13) {
      BThackClient.getInstance().getSocialManager().addEnemy(helper13);
   }

   public ConfigManager getAuto2faConfig() {
      return this.auto2faConfig;
   }

   public void save() {
      this.moduleConfig.save();
      this.hudConfig.save();
      this.friendsConfig.save();
      this.enemiesConfig.save();
      this.localConfig.save();
      this.autoAuthConfig.save();
      this.auto2faConfig.save();
   }

   private static void forEachEnemy(Consumer<Helper13> consumer) {
      BThackClient.getInstance().getSocialManager().forEachEnemy(consumer);
   }

   public void registerLocalParam(Api1Impl2 api1Impl2) {
      this.saveCallbacks.add(api1Impl2);
   }

   public void unregisterLocalParam(Api1Impl2 api1Impl2) {
      this.saveCallbacks.remove(api1Impl2);
   }

   private List<Api1Impl2<?>> getSaveCallbacks() {
      return this.saveCallbacks;
   }

   public ConfigManager getModuleConfig() {
      return this.moduleConfig;
   }

   public boolean isLocalParam(Api1Impl2 api1Impl2) {
      return this.saveCallbacks.contains(api1Impl2);
   }
}
