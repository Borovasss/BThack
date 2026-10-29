package com.ferra13671.bthack;

public class SettingGroup3 extends SubSettingContainer {
   protected SettingGroup3(SettingGroup3.SettingBuilderSub1Sub1 settingBuilderSub1Sub1) {
      super(settingBuilderSub1Sub1);
   }

   public static SettingGroup3.SettingBuilderSub1Sub1 builder() {
      return new SettingGroup3.SettingBuilderSub1Sub1();
   }

   public static class SettingBuilderSub1Sub1 extends SubSettingContainer.SettingBuilderSub1 {
      public SettingGroup3 build() {
         return new SettingGroup3(this);
      }

      public SettingBuilderSub1Sub1() {
         this.withHashMap(NamedObjectUtils.hashMap1);
      }
   }
}
