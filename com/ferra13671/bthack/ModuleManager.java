package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.logger.LoggerLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModuleManager extends Manager {
   final transient Map<String, CategoryInfoApi> modulesById = new HashMap<>();
   final transient Map<CategoryInfoApi, List<Toggleable>> modulesByCategory = new HashMap();
   final transient Set<Toggleable> allModules = new HashSet();
   final transient List<SettingButtonConfig> categories = new ArrayList<>();

   public List<SettingButtonConfig> getModules() {
      return new ArrayList<>(this.categories);
   }

   private static List<Toggleable> registerCategory(CategoryInfoApi categoryInfoApi) {
      return new ArrayList<>();
   }

   public Toggleable getCategoryOrThrow(CategoryInfoApi categoryInfoApi, String string) {
      if (!this.modulesById.containsValue(categoryInfoApi)) {
         BThackClient.getInstance().getLogger().log(LoggerLevel.ERROR, String.format("Category '%s' was not registered in ModuleManager", categoryInfoApi));
      }

      for (Toggleable toggleable : this.modulesByCategory.computeIfAbsent(categoryInfoApi, ModuleManager::removeCategory)) {
         if (toggleable.getGetname().equals(string)) {
            return toggleable;
         }
      }

      return null;
   }

   public List<CategoryInfoApi> getCategories() {
      return new ArrayList(this.modulesById.values().stream().toList());
   }

   public SettingButtonConfig registerContextButton(SettingButtonConfig settingButtonConfig) {
      this.getAtlas3();
      this.categories.add(settingButtonConfig);
      BThackClient.getInstance()
         .getAtlasManager()
         .handleGLTexture(
            settingButtonConfig.getButtonpack().getDefaultpack().getIcon(),
            settingButtonConfig.getButtonpack().getDefaultpack4().getIcon(),
            settingButtonConfig.getButtonpack().getDefaultpack2().getIcon(),
            settingButtonConfig.getButtonpack().getDefaultpack3().getIcon()
         );
      return settingButtonConfig;
   }

   public CategoryInfoApi getModulesInCategory(String string) {
      return (CategoryInfoApi)this.modulesById.get(string);
   }

   public Toggleable registerModule(Toggleable toggleable) {
      this.getAtlas3();
      if (!this.modulesById.containsValue(toggleable.getCategoryInfoApi())) {
         BThackClient.getInstance()
            .getLogger()
            .log(
               LoggerLevel.ERROR,
               String.format(
                  "Category '%s', which is used in module '%s', was not registered in ModuleManager",
                  toggleable.getCategoryInfoApi().getGetname(),
                  toggleable.getGetname()
               )
            );
      }

      this.modulesByCategory.computeIfAbsent(toggleable.getCategoryInfoApi(), ModuleManager::registerCategory).add(toggleable);
      this.allModules.add(toggleable);
      return toggleable;
   }

   private static List<Toggleable> getCategory(CategoryInfoApi categoryInfoApi) {
      return new ArrayList<>();
   }

   public CategoryInfoApi addCategory(CategoryInfoApi categoryInfoApi) {
      this.getAtlas3();
      this.modulesById.put(categoryInfoApi.getGetname(), categoryInfoApi);
      BThackClient.getInstance().getAtlasManager().handleGLTexture1(categoryInfoApi.getGeticon());
      return categoryInfoApi;
   }

   public Set<Toggleable> getModuleSet() {
      return this.allModules;
   }

   private static List<Toggleable> removeCategory(CategoryInfoApi categoryInfoApi) {
      return new ArrayList<>();
   }

   public Toggleable replaceCategory(CategoryInfoApi categoryInfoApi, String string, Class class_) {
      return this.getCategoryOrThrow(categoryInfoApi, string);
   }

   public List<Api4> containsCategory(CategoryInfoApi categoryInfoApi) {
      return new ArrayList(this.modulesByCategory.computeIfAbsent(categoryInfoApi, ModuleManager::getCategory));
   }

   public CategoryInfoApi getModulesInCategoryOfType(String string, Class class_) {
      return this.getModulesInCategory(string);
   }
}
