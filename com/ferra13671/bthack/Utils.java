package com.ferra13671.bthack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class Utils {
   public static List getSettings(Class class_, Object object) {
      return getSettings(class_, object, new ArrayList());
   }

   public static Enum withClass(Class class_, String string) {
      Enum<?>[] enum_Arr = (Enum<?>[])class_.getEnumConstants();

      for (Enum<?> enum_ : enum_Arr) {
         if (enum_.name().equals(string)) {
            return enum_;
         }
      }

      return null;
   }

   Utils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static List getSettings(Class class_, Object object, List list) {
      for (Field field : class_.getDeclaredFields()) {
         try {
            field.setAccessible(true);
            if (field.get(object) instanceof Setting<?> setting) {
               if (!setting.isListSetting()) {
                  list.add(setting);
               } else {
                  ((List)setting.getListSetting().getValue()).add(setting);
               }
            }
         } catch (Exception exception) {
            exception.printStackTrace(BThackClient.getInstance().getLogger().getExceptionWriter());
         }
      }

      Class<?> class_2 = class_.getSuperclass();
      if (!class_2.equals(Object.class)) {
         getSettings(class_2, object, list);
      }

      return list;
   }
}
