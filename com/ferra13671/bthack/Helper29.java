package com.ferra13671.bthack;

public class Helper29 implements Cloneable {
   transient Easing easing;
   transient int int1;
   transient long long1;

   public long getLong() {
      return this.long1;
   }

   public Helper29(Easing easing, int num) {
      this.easing = easing;
      this.int1 = num;
      this.long1 = System.currentTimeMillis();
   }

   public double getEasing2() {
      long lng = this.getLong2();
      return lng >= this.int1 ? 1.0 : this.easing.ease((float)lng / this.int1);
   }

   public int getInt() {
      return this.int1;
   }

   public void setLong() {
      this.long1 = System.currentTimeMillis();
   }

   public void setEasing(Easing easing) {
      this.easing = easing;
   }

   public Helper29 clone() {
      return new Helper29(this.easing, this.int1);
   }

   public long getLong2() {
      return System.currentTimeMillis() - this.long1;
   }

   public void setInt(int num) {
      this.int1 = num;
   }

   public Easing getEasing() {
      return this.easing;
   }

   public void setLong2(long lng) {
      this.long1 = lng;
   }
}
