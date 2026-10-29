package com.ferra13671.bthack;

public class Helper36 {
   transient int int1;
   final transient KeyboardEnum keyboardEnum;

   public int getInt() {
      return this.int1;
   }

   public Helper36 withInt(int num) {
      return this.int1 == num ? this : new Helper36(num, this.keyboardEnum);
   }

   public Helper36(int num, KeyboardEnum keyboardEnum) {
      this.int1 = num;
      this.keyboardEnum = keyboardEnum;
   }

   public Helper36 withKeyboardEnum(KeyboardEnum keyboardEnum) {
      return this.keyboardEnum == keyboardEnum ? this : new Helper36(this.int1, keyboardEnum);
   }

   public KeyboardEnum getKeyboardEnum() {
      return this.keyboardEnum;
   }
}
