package com.ferra13671.bthack;

import java.util.Arrays;
import org.apache.commons.codec.binary.Base32;

public class Helper2 {
   public final transient byte[] value;
   public final transient String string1;

   @Override
   public int hashCode() {
      return Arrays.hashCode(this.value);
   }

   public static Helper2 withString(String string) {
      return new Helper2(new Base32().decode(string));
   }

   @Override
   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else {
         return object instanceof Helper2 helper2 ? Arrays.equals(this.value, helper2.value) : false;
      }
   }

   public Helper2(byte[] bytArr) {
      this.value = bytArr;
      this.string1 = new Base32().encodeToString(bytArr);
   }
}
