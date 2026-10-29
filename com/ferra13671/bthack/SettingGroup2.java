package com.ferra13671.bthack;

public class SettingGroup2 extends SubSettingContainer {
   public static SettingGroup2.SettingBuilderSub1Sub2 builder() {
      return new SettingGroup2.SettingBuilderSub1Sub2();
   }

   protected SettingGroup2(SettingGroup2.SettingBuilderSub1Sub2 settingBuilderSub1Sub2) {
      super(settingBuilderSub1Sub2);
   }

   public static class SettingBuilderSub1Sub2 extends SubSettingContainer.SettingBuilderSub1 {
      public SettingBuilderSub1Sub2() {
         this.withHashMap(NamedObjectUtils.hashMap3);
      }

      public SettingGroup2 build() {
         return new SettingGroup2(this);
      }
   }
}
