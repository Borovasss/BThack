package com.ferra13671.bthack;

import java.util.function.Function;

public enum DayBody {
   Day(ambience -> 5000L),
   Night(ambience -> 17000L),
   Morning(ambience -> 0L),
   Sunset(ambience -> 13000L),
   Custom(ambience -> ((Float)ambience.time.getValue()).longValue()),
   Spin(ambience -> (long)((float)ambience.helper45.getPassedTime() * (Float)ambience.spinSpeed.getValue() % 10000.0F / 10000.0F * 24000.0F));

   public final Function<Ambience, Long> timeSupplier;

   private static DayBody[] $values() {
      return new DayBody[]{Day, Night, Morning, Sunset, Custom, Spin};
   }

   DayBody(Function<Ambience, Long> function) {
      this.timeSupplier = function;
   }
}
