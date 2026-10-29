package com.ferra13671.bthack;

public class Helper13 {
   final transient String name;
   transient String string1;

   public String getString() {
      return this.string1;
   }

   public void setString(String string) {
      this.string1 = string;
   }

   public String getName() {
      return this.name;
   }

   public Helper13(String string, String string2) {
      this.name = string;
      this.string1 = string2;
   }

   public Helper13(String string) {
      this(string, string);
   }
}
