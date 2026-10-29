package com.ferra13671.bthack;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper41 {
   public static Float getFloat(Float float_, int num) {
      return new BigDecimal(float_.floatValue()).setScale(num, RoundingMode.HALF_UP).floatValue();
   }
}
