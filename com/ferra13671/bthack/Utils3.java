package com.ferra13671.bthack;

public final class Utils3 {
   public static Enum withEnum(Enum enum_) {
      Enum<?>[] enum_Arr = (Enum<?>[])enum_.getClass().getEnumConstants();
      return enum_Arr[(enum_.ordinal() + 1) % enum_Arr.length];
   }

   Utils3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Enum withString1(String string, Class class_) {
      for (Enum<?> enum_ : (Enum[])class_.getEnumConstants()) {
         if (enum_.name().equals(string)) {
            return enum_;
         }
      }

      return null;
   }
}
