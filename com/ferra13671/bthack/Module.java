package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public class Module implements Toggleable, MinecraftInstance {
   final transient ModuleInfo moduleInfo = this.getClass().getAnnotation(ModuleInfo.class);
   final transient String name = this.moduleInfo.name();
   final transient CategoryInfoApi categoryInfoApi = BThackClient.getInstance().getModuleManager().getModulesInCategory(this.moduleInfo.category());
   final transient String getname = this.name.toLowerCase().replace(" ", "-");
   transient List settings = null;
   transient boolean enabled = this.moduleInfo.enabled();
   protected transient boolean quitKeybindToggling = this.moduleInfo.quitKeybindToggling();
   transient Helper38 settingFactory = Helper38.helper38;
   transient KeybindBinding keybindBinding = null;
   final transient KeybindSetting moduleKeybind = ((KeybindSetting.KeybindSettingBuilder)((KeybindSetting.KeybindSettingBuilder)((KeybindSetting.KeybindSettingBuilder)KeybindSetting.builder()
               .name("Keybind"))
            .value(this.settingFactory.getHelper36()))
         .descriptionPrefix("module.keybind"))
      .build();
   final transient ClassSetting moduleKeybind2 = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(
                  ToggleBody.class
               )
               .name("Bind type"))
            .value(this.settingFactory.getToggleBody()))
         .descriptionPrefix("module.keybind"))
      .build();
   transient ModuleState moduleState;

   @Override
   public CategoryInfoApi getCategoryInfoApi() {
      return this.categoryInfoApi;
   }

   private Boolean getEnabled2() {
      return this.enabled;
   }

   protected void onEnable3() {
      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   private void onEnabledChanged(Boolean boolean_) {
      this.setEnabled(boolean_, this.quitKeybindToggling);
   }

   protected void onDisable2() {
      BThackClient.getInstance().getEventBus().unsubscribe(this);
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public List getSettings() {
      if (this.settings == null) {
         this.settings = this.buildSettings();
      }

      return this.settings;
   }

   @Override
   public KeybindSetting getModuleKeybind() {
      return this.moduleKeybind;
   }

   @Override
   public void setEnabled(boolean bool, boolean bool2) {
      if (this.isEnabled() && !bool) {
         this.enabled = false;
         this.onDisable2();
         if (!bool2 && !BThackClient.isInitialized()) {
            ChatUtils.handleString(BThackClient.getInstance().getTranslationManager().translateFormat("module.disabled", this.getName()));
            BThackClient.getInstance().getIdentifierHelper().handleClass_29604(ModuleSounds.class_29601);
         }
      }

      if (!this.isEnabled() && bool) {
         this.enabled = true;
         this.onEnable3();
         if (!bool2 && !BThackClient.isInitialized()) {
            ChatUtils.handleString(BThackClient.getInstance().getTranslationManager().translateFormat("module.enabled", this.getName()));
            BThackClient.getInstance().getIdentifierHelper().handleClass_29604(ModuleSounds.stageManager);
         }
      }
   }

   @Override
   public ClassSetting getModuleKeybind2() {
      return this.moduleKeybind2;
   }

   private void onSettingAdded(Helper38 helper38) {
      if (this.keybindBinding != null) {
         BThackClient.getInstance().getKeybindDispatcher().unregisterKeybind(this.keybindBinding);
      }

      this.settingFactory = helper38;
      this.keybindBinding = new KeybindBinding(this.settingFactory, this::onEnabledChanged, this::getEnabled2);
      BThackClient.getInstance().getKeybindDispatcher().registerKeybind(this.keybindBinding);
   }

   public Helper38 getHelper38() {
      return this.settingFactory;
   }

   private void onSettingValueChanged(Setting setting) {
      if (!this.hasSetting(setting)) {
         setting.setString("module.".concat(this.getGetname()));
      }
   }

   @Override
   public String getGetname() {
      return this.getname;
   }

   @Override
   public Api1Impl getModuleState() {
      if (this.moduleState == null) {
         this.moduleState = new ModuleState(this);
      }

      return this.moduleState;
   }

   private boolean hasSetting(Setting setting) {
      return setting == this.getModuleKeybind() || setting == this.getModuleKeybind2();
   }

   private void onKeyEvent(EventSub12 eventSub12) {
      if (eventSub12.setting == this.moduleKeybind) {
         this.onSettingAdded(this.settingFactory.withHelper36((Helper36)this.moduleKeybind.getValue()));
      }

      if (eventSub12.setting == this.moduleKeybind2) {
         this.onSettingAdded(this.settingFactory.withToggleBody((ToggleBody)this.moduleKeybind2.getValue()));
      }
   }

   @Override
   public void getEnabled() {
      if (this.enabled) {
         this.onEnable3();
      } else {
         this.onDisable2();
      }
   }

   public void onSettingRemoved(Helper38 helper38) {
      this.moduleKeybind.setValue2(helper38.getHelper36());
      this.moduleKeybind2.setValue2(helper38.getToggleBody());
   }

   private List buildSettings() {
      List<Setting<?>> list = new ArrayList<>();
      Utils.getSettings(this.getClass(), this, list);
      list.forEach(this::onSettingValueChanged);
      list.removeIf(this::hasSetting);
      return list;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   public Module() {
      BThackClient.getInstance().getEventBus().subscribe(new Helper8<>(EventSub12.class, this::onKeyEvent));
   }
}
