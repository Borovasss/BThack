package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.ferra13671.bthack.loader.api.logger.Logger;
import com.ferra13671.bthack.loader.api.logger.LoggerLevel;
import com.ferra13671.bthack.loader.api.metadata.ClientEntrypoint;
import com.ferra13671.bthack.loader.api.resource.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BThackClient implements ClientEntrypoint, MinecraftInstance {
   public static final transient String CONFIGS_PATH = "configs";
   public static final transient String SOCIALS_PATH = "socials";
   public static final transient String DEFAULT_COMMAND_PREFIX = "*";
   static transient BThackClient instance;
   transient boolean initialized = false;
   final transient Api1Impl2 api1Impl2 = new Api1Impl2<>("command-prefix", String.class, ValueCodec.valueCodec4, "*");
   final transient Logger logger = ClientLoader.getInstance().getOrCreateLogger("BThack");
   final transient EventBus eventBus = new EventBusImpl();
   final transient StageManager stageManager = StageManager.of("BThack main stage");
   transient ExecutorService shutdownExecutor;
   transient Icon icon = new IconImpl();
   transient Helper15 shutdownManager;
   transient IdentifierHelper identifierHelper;
   transient PrioritySortedSub2 rotationHelper;
   transient PrioritySortedHelper2 movementPacketBuffer;
   transient Helper44 attackTracker;
   transient Helper34 socialManager;
   transient Helper37 chunkTracker;
   transient Helper28 tpsTracker;
   transient Helper42 authSecrets;
   transient AtlasManager atlasManager;
   transient Helper18 translationManager;
   transient ModuleManager moduleManager;
   transient Helper46 keybindDispatcher;
   transient PrioritySortedHelper packetSendHelper;
   transient Helper40 commandManager;
   transient ManagerHelper hudManager;
   transient ClientPaths clientPaths;
   transient DiscordRpc discordRpc;
   transient ScreenHelper clickUiScreen;
   transient ScreenHelper hudEditorScreen;

   private static Boolean isToggleable(Api4 api4) {
      return api4 instanceof Toggleable;
   }

   private void openKeybindButton(Api4 api4, ComponentSub2 componentSub2, XYButton xYButton) {
      this.clickUiScreen
         .getEventBus()
         .register(new EventSub1(new ComponentSub3(this.clickUiScreen.getEventBus(), (Toggleable)api4, xYButton.getX2(), xYButton.getX())));
   }

   public Helper46 getKeybindDispatcher() {
      return this.keybindDispatcher;
   }

   public Icon getIcon() {
      return this.icon;
   }

   private void createClientIcon() {
      RendererInit.runLater(this::initIconWithDiscord);
   }

   private void loadLanguage(String string, String string2) {
      Map<String, String> map = new HashMap<>();
      map.putAll(this.loadTranslationMap("translations/modules_" + string + ".json"));
      map.putAll(this.loadTranslationMap("translations/commands_" + string + ".json"));
      this.translationManager.load(string2, map);
   }

   private void initIconWithDiscord() {
      this.icon = new IconImpl2(this.discordRpc.getProfile());
   }

   private void createIdentifierHelper() {
      this.identifierHelper = new IdentifierHelper();
   }

   public ClientPaths getClientPaths() {
      return this.clientPaths;
   }

   public Helper40 getCommandManager() {
      return this.commandManager;
   }

   public Api1Impl2 getCommandPrefixSetting() {
      return this.api1Impl2;
   }

   private void registerBootStages() {
      this.logger.log(LoggerLevel.BOOT, "Register main task provider stages...");
      this.stageManager.push(StageManager.of("Create executor", this::createShutdownExecutor));
      this.stageManager.push(StageManager.of("Init shutdown manager", this::initShutdownManager));
      this.stageManager.push(this.initSoundsStage());
      this.stageManager.push(StageManager.of("Create other managers", this::createOtherManagers));
      this.stageManager.push(RendererInit.applyStageManager5());
      this.stageManager.push(this.initAtlasStage());
      this.stageManager.push(this.initLanguagesStage());
      this.stageManager.push(this.initModulesStage());
      this.stageManager.push(StageManager.of("Init HUD", this::createHudManager));
      this.stageManager.push(StageManager.of("Init commands", this::createCommandManager));
      this.stageManager.push(this.initConfigsStage());
      this.stageManager.push(StageManager.of("Startup discord RPC", this::startupDiscordRpc));
      this.stageManager.push(StageManager.of("Freeze managers", this::freezeManagers));
      this.stageManager.push(StageManager.of("Init UI screen", this::createClickUiScreen));
      this.stageManager.push(StageManager.of("Init Hud editor screen", this::createHudEditorScreen));
   }

   private void loadEnglishLanguage() {
      this.loadLanguage("en", "en_us");
   }

   private void registerAllModules() {
      ToggleableUtils.getList().forEach(this.moduleManager::registerModule);
   }

   public Helper28 getTpsTracker() {
      return this.tpsTracker;
   }

   private static String getCurrentLanguage() {
      return mc.getLanguageManager().getLanguage();
   }

   private void createCommandManager() {
      this.commandManager = new Helper40();
   }

   private void createHudManager() {
      this.hudManager = new ManagerHelper();
   }

   private void openSettingsButton(Api4 api4, ComponentSub2 componentSub2, XYButton xYButton) {
      this.clickUiScreen.getEventBus().register(new EventSub1(new ComponentSub4(this.clickUiScreen.getEventBus(), api4, xYButton.getX2(), xYButton.getX())));
   }

   public PrioritySortedHelper2 getPrioritySortedHelper2() {
      return this.movementPacketBuffer;
   }

   private void createTranslationManager() {
      this.translationManager = new Helper18(BThackClient::getCurrentLanguage, "en_us");
   }

   private void addClientLocalParams() {
      this.clientPaths.registerLocalParam(this.api1Impl2);
   }

   public boolean isClientInitialized() {
      return this.initialized;
   }

   public ManagerHelper getManagerHelper() {
      return this.hudManager;
   }

   private StageManager initAtlasStage() {
      StageManager stageManager = StageManager.of("Init atlas manager");
      stageManager.push(StageManager.of("Create manager", this::createAtlasManager));
      stageManager.push(StageManager.of("Add default client icons", this::addDefaultClientIcons));
      return stageManager;
   }

   public ExecutorService getShutdownExecutor() {
      return this.shutdownExecutor;
   }

   public static File getOrCreateConfigFile(String string, boolean bool) {
      return ClientLoader.getInstance().getResourceManager().getOrCreateFileNullable(string, bool);
   }

   public void init() {
      this.logger.log(LoggerLevel.BOOT, "Start BThack main init...");
      long lng = System.currentTimeMillis();
      this.stageManager.runVoid1();
      this.logger.log(LoggerLevel.BOOT, String.format("BThack main initialization completed in %s ms.", System.currentTimeMillis() - lng));
      this.initialized = true;
   }

   public DiscordRpc getDiscordRpc() {
      return this.discordRpc;
   }

   public Helper42 getAuthSecrets() {
      return this.authSecrets;
   }

   private static void loadModuleSounds() {
      ModuleSounds.stageManager.getNamespace();
   }

   private void createClickUiScreen() {
      this.clickUiScreen = new ScreenHelperSub1();
   }

   private StageManager initConfigsStage() {
      StageManager stageManager = StageManager.of("Init configs");
      stageManager.push(StageManager.of("Create manager", this::createClientPaths));
      stageManager.push(StageManager.of("Add client local parameters", this::addClientLocalParams));
      stageManager.push(StageManager.of("Load configs", this::loadConfigs));
      return stageManager;
   }

   public void earlyInit() {
      instance = (BThackClient)ClientLoader.getInstance().getClientEntrypoint();
      this.logger.log(LoggerLevel.BOOT, "Start BThack preLaunch init...");
      long lng = System.currentTimeMillis();
      this.registerBootStages();
      this.logger.log(LoggerLevel.BOOT, String.format("BThack preLaunch initialization completed in %s ms.", System.currentTimeMillis() - lng));
   }

   private static Boolean alwaysTrue(Api4 api4) {
      return true;
   }

   private void freezeManagers() {
      this.moduleManager.getAtlas2();
      this.hudManager.getAtlas2();
      this.atlasManager.getAtlas2();
   }

   public ScreenHelper getHudEditorScreen() {
      return this.hudEditorScreen;
   }

   public PrioritySortedSub2 getPrioritySortedSub2() {
      return this.rotationHelper;
   }

   private StageManager initSoundsStage() {
      StageManager stageManager = StageManager.of("Init sounds");
      stageManager.push(StageManager.of("Create manager", this::createIdentifierHelper));
      stageManager.push(StageManager.of("Load sounds", BThackClient::loadModuleSounds));
      return stageManager;
   }

   private void onClientShutdown() {
      this.logger.log(LoggerLevel.INFO, "BThack shutdown!");
      this.shutdownExecutor.shutdownNow();
      this.discordRpc.shutdown();
      this.clientPaths.save();
   }

   public IdentifierHelper getIdentifierHelper() {
      return this.identifierHelper;
   }

   public Helper15 getShutdownManager() {
      return this.shutdownManager;
   }

   private void registerModuleContextButtons() {
      this.moduleManager
         .registerContextButton(
            new SettingButtonConfig("Open settings", Utils4.packButtons1, BThackClient::alwaysTrue, BThackClient::alwaysFalse2, this::openSettingsButton)
         );
      this.moduleManager
         .registerContextButton(
            new SettingButtonConfig("Change keybind", Utils4.packButtons2, BThackClient::isToggleable, BThackClient::alwaysFalse, this::openKeybindButton)
         );
   }

   private void registerAllCategories() {
      CategoryUtils.getList().forEach(this.moduleManager::addCategory);
   }

   private void createAtlasManager() {
      this.atlasManager = new AtlasManager();
   }

   private void addDefaultClientIcons() {
      this.atlasManager.handleGLTexture(RendererInit.config.getGLTexture());
   }

   private static Boolean alwaysFalse(Api4 api4) {
      return false;
   }

   private Map loadTranslationMap(String string) {
      try (InputStream inputStream = ClientLoader.getInstance().getResourceManager().getResource(new Resource("bthack-client", string))) {
         return IdUtils.parse(inputStream);
      } catch (Exception exception) {
         exception.printStackTrace(this.logger.getExceptionWriter());
         return new HashMap();
      }
   }

   private void loadRussianLanguage() {
      this.loadLanguage("ru", "ru_ru");
   }

   public Logger getLogger() {
      return this.logger;
   }

   public Helper34 getSocialManager() {
      return this.socialManager;
   }

   public Helper37 getChunkTracker() {
      return this.chunkTracker;
   }

   private void initShutdownManager() {
      this.shutdownManager = new Helper15();
      this.shutdownManager.addHook(this::onClientShutdown);
   }

   public static BThackClient getInstance() {
      return instance;
   }

   public Helper44 getAttackTracker() {
      return this.attackTracker;
   }

   private void initModuleManagers() {
      this.moduleManager = new ModuleManager();
      this.keybindDispatcher = new Helper46();
      this.eventBus.subscribe(this.keybindDispatcher);
      this.packetSendHelper = new PrioritySortedHelper();
   }

   private void createClientPaths() {
      this.clientPaths = new ClientPaths();
   }

   private void createOtherManagers() {
      this.rotationHelper = new PrioritySortedSub2();
      this.movementPacketBuffer = new PrioritySortedHelper2();
      this.attackTracker = new Helper44();
      this.socialManager = new Helper34();
      this.chunkTracker = new Helper37();
      this.tpsTracker = new Helper28();
      this.authSecrets = new Helper42();
   }

   private void createHudEditorScreen() {
      this.hudEditorScreen = new ScreenHelperSub2();
   }

   public PrioritySortedHelper getPrioritySortedHelper() {
      return this.packetSendHelper;
   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   private void loadConfigs() {
      this.clientPaths.load();
   }

   private StageManager initLanguagesStage() {
      StageManager stageManager = StageManager.of("Init languages");
      stageManager.push(StageManager.of("Create manager", this::createTranslationManager));
      stageManager.push(StageManager.of("Load English language", this::loadEnglishLanguage));
      stageManager.push(StageManager.of("Load Russian language", this::loadRussianLanguage));
      return stageManager;
   }

   private static Boolean alwaysFalse2(Api4 api4) {
      return false;
   }

   public EventBus getEventBus() {
      return this.eventBus;
   }

   public StageManager getMainStage() {
      return this.stageManager;
   }

   private StageManager initModulesStage() {
      StageManager stageManager = StageManager.of("Init modules");
      stageManager.push(StageManager.of("Create required managers", this::initModuleManagers));
      stageManager.push(StageManager.of("Add categories", this::registerAllCategories));
      stageManager.push(StageManager.of("Add modules", this::registerAllModules));
      stageManager.push(StageManager.of("Add module context buttons", this::registerModuleContextButtons));
      return stageManager;
   }

   public ScreenHelper getClickUiScreen() {
      return this.clickUiScreen;
   }

   public static boolean isInitialized() {
      return mc.player == null || mc.world == null;
   }

   public Helper18 getTranslationManager() {
      return this.translationManager;
   }

   private void startupDiscordRpc() {
      this.discordRpc = new DiscordRpc();
      this.discordRpc.startAsync(this::createClientIcon);
   }

   private void createShutdownExecutor() {
      this.shutdownExecutor = Executors.newSingleThreadExecutor();
   }

   public AtlasManager getAtlasManager() {
      return this.atlasManager;
   }
}
