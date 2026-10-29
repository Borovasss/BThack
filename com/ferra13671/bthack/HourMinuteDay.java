package com.ferra13671.bthack;

import java.util.Calendar;

public record HourMinuteDay(int hour3, int hour5, int hour2, int hour, int hour4) {

   public int getHour3() {
      return this.hour;
   }

   public int getHour4() {
      return this.hour5;
   }

   public int getHour5() {
      return this.hour2;
   }

   public int getHour2() {
      return this.hour4;
   }

   public static HourMinuteDay withValue() {
      Calendar calendar = Calendar.getInstance();
      return new HourMinuteDay(calendar.get(11), calendar.get(12), calendar.get(5), calendar.get(2), calendar.get(1));
   }

   public int getHour() {
      return this.hour3;
   }
}
