package com.ferra13671.bthack;

import java.util.function.Function;

public enum ToggleBody {
   Toggle(boolean_ -> !boolean_, false, false),
   Hold(boolean_ -> true, true, false),
   HoldReversed(boolean_ -> false, true, true);

   public final Function<Boolean, Boolean> pressStateFunction;
   public final boolean useRelease;
   public final boolean releaseState;

   ToggleBody(Function<Boolean, Boolean> function, boolean bool, boolean bool2) {
      this.pressStateFunction = function;
      this.useRelease = bool;
      this.releaseState = bool2;
   }

   private static ToggleBody[] $values() {
      return new ToggleBody[]{Toggle, Hold, HoldReversed};
   }
}
