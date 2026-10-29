package com.ferra13671.bthack;

public class Helper45 {
   transient long timestamp;

   public void reset() {
      this.setTimestamp(System.currentTimeMillis());
   }

   public boolean passed(int num) {
      return this.timestamp + num <= System.currentTimeMillis();
   }

   public Helper45(long lng) {
      this.timestamp = lng;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(long lng) {
      this.timestamp = lng;
   }

   public long getPassedTime() {
      return System.currentTimeMillis() - this.timestamp;
   }

   public Helper45() {
      this(System.currentTimeMillis());
   }
}
