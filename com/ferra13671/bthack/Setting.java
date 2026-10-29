package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import java.util.function.Supplier;
import lombok.NonNull;

public abstract class Setting<T> {
   protected final transient @NonNull String name;
   protected transient String id;
   protected transient @NonNull T value;
   protected transient @NonNull Supplier supplier1;
   protected transient ListSetting listSetting;
   protected transient String string1;

   public void setString(String string) {
      this.string1 = string;
   }

   public abstract void fromJson(Helper27 var1);

   private static Supplier applySupplier() {
      return Setting::isBoolean1;
   }

   public Supplier getSupplier() {
      return this.supplier1;
   }

   public abstract JsonElement toJson();

   private static String getGetname2() {
      return "";
   }

   public T getValue() {
      return this.value;
   }

   public final String getGetname() {
      if (this.id == null) {
         this.id = this.name.toLowerCase().replace(" ", "-");
      }

      return this.id;
   }

   public ListSetting getListSetting() {
      return this.listSetting;
   }

   private static Boolean isBoolean1() {
      return true;
   }

   public void setValue2(Object object) {
      this.value = (T)object;
      BThackClient.getInstance().getEventBus().register(new EventSub12(this));
   }

   public String getListSetting3() {
      return (this.isListSetting() ? this.listSetting.getListSetting3() + "." : "")
         .concat(this.string1 != null && !this.string1.isBlank() ? this.string1 + "." : "")
         .concat(this.getGetname());
   }

   private static String getGetname3() {
      return null;
   }

   public String getName() {
      return this.name;
   }

   protected Setting(Setting.SettingBuilder settingBuilder) {
      this.name = settingBuilder.name;
      if (this.name == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      if (settingBuilder.boolean1) {
         this.id = settingBuilder.string1;
      } else {
         this.id = getGetname3();
      }

      this.value = (T)settingBuilder.value;
      if (this.value == null) {
         throw new NullPointerException("value is marked non-null but is null");
      }

      if (settingBuilder.boolean2) {
         this.supplier1 = settingBuilder.supplier1;
      } else {
         this.supplier1 = applySupplier();
      }

      if (this.supplier1 == null) {
         throw new NullPointerException("visibleSupplier is marked non-null but is null");
      }

      if (settingBuilder.boolean3) {
         this.listSetting = settingBuilder.listSetting;
      } else {
         this.listSetting = getListSetting2();
      }

      if (settingBuilder.boolean4) {
         this.string1 = settingBuilder.string2;
      } else {
         this.string1 = getGetname2();
      }
   }

   public String getString() {
      return this.string1;
   }

   public boolean isListSetting() {
      return this.listSetting != null;
   }

   private static ListSetting getListSetting2() {
      return null;
   }

   public abstract SettingElement createSettingElement(EventBus var1, Api9 var2, float var3, float var4, float var5);

   public abstract static class SettingBuilder {
      transient String name;
      transient boolean boolean1;
      transient String string1;
      transient Object value;
      transient boolean boolean2;
      transient Supplier supplier1;
      transient boolean boolean3;
      transient ListSetting listSetting;
      transient boolean boolean4;
      transient String string2;

      public abstract Setting build();

      public Setting.SettingBuilder id(String string) {
         this.string1 = string;
         this.boolean1 = true;
         return this.toBuilder();
      }

      public Setting.SettingBuilder name(String string) {
         if (string == null) {
            throw new NullPointerException("name is marked non-null but is null");
         }

         this.name = string;
         return this.toBuilder();
      }

      public Setting.SettingBuilder descriptionPrefix(String string) {
         this.string2 = string;
         this.boolean4 = true;
         return this.toBuilder();
      }

      protected abstract Setting.SettingBuilder toBuilder();

      public Setting.SettingBuilder visibleSupplier(Supplier supplier) {
         if (supplier == null) {
            throw new NullPointerException("visibleSupplier is marked non-null but is null");
         }

         this.supplier1 = supplier;
         this.boolean2 = true;
         return this.toBuilder();
      }

      public Setting.SettingBuilder value(Object object) {
         if (object == null) {
            throw new NullPointerException("value is marked non-null but is null");
         }

         this.value = object;
         return this.toBuilder();
      }

      public Setting.SettingBuilder onlyIf(ListSetting listSetting) {
         this.listSetting = listSetting;
         this.boolean3 = true;
         return this.toBuilder();
      }

      @Override
      public String toString() {
         return "Setting.SettingBuilder(name="
            + this.name
            + ", id$value="
            + this.string1
            + ", value="
            + this.value
            + ", visibleSupplier$value="
            + this.supplier1
            + ", category$value="
            + this.listSetting
            + ", descriptionPrefix$value="
            + this.string2
            + ")";
      }
   }
}
