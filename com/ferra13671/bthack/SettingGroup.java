package com.ferra13671.bthack;

public class SettingGroup extends SubSettingContainer {
   public static SettingGroup.SettingBuilderSub1Sub3 builder() {
      return new SettingGroup.SettingBuilderSub1Sub3();
   }

   protected SettingGroup(SettingGroup.SettingBuilderSub1Sub3 settingBuilderSub1Sub3) {
      super(settingBuilderSub1Sub3);
   }

   public static class SettingBuilderSub1Sub3 extends SubSettingContainer.SettingBuilderSub1 {
      public SettingBuilderSub1Sub3() {
         this.withHashMap(NamedObjectUtils.hashMap2);
      }

      public SettingGroup build() {
         return new SettingGroup(this);
      }
   }
}
