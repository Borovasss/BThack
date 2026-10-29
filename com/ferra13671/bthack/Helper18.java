package com.ferra13671.bthack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.NonNull;

public class Helper18 {
   final transient HashMap<String, HashMap<String, String>> translations = new HashMap();
   final transient @NonNull Supplier languageSupplier;
   final transient @NonNull String fallbackLanguage;

   public Helper18(Supplier supplier, String string) {
      if (supplier == null) {
         throw new NullPointerException("languageGetter is marked non-null but is null");
      }

      if (string == null) {
         throw new NullPointerException("defaultLanguage is marked non-null but is null");
      }

      this.languageSupplier = supplier;
      this.fallbackLanguage = string;
   }

   private static HashMap getTranslations(String string) {
      return new HashMap();
   }

   public String translateFormat(String string, String... stringArr) {
      return String.format(this.translate(string), stringArr);
   }

   public void load(String string, Map map) {
      this.translations.computeIfAbsent(string, Helper18::getTranslations).putAll(map);
   }

   private static HashMap loadLanguagePack(String string) {
      return new HashMap();
   }

   public String translate(String string) {
      return ((HashMap<String, String>)this.translations
            .getOrDefault(this.languageSupplier.get(), this.translations.computeIfAbsent(this.fallbackLanguage, Helper18::loadLanguagePack)))
         .getOrDefault(string, string);
   }
}
