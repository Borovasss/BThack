package com.ferra13671.bthack;

public record Id(String id) {

   @Override
   public boolean equals(Object object) {
      return object instanceof Id(String string) && string.equals(this.id);
   }

   public Id getId() {
      String[] stringArr2 = this.id.split("\\.");
      if (stringArr2.length > 1) {
         String[] stringArr = new String[stringArr2.length - 1];
         System.arraycopy(stringArr2, 0, stringArr, 0, stringArr.length);
         return new Id(String.join(".", stringArr));
      } else {
         return new Id("");
      }
   }
}
