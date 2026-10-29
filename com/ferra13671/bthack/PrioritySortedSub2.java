package com.ferra13671.bthack;

import java.util.function.Function;

public class PrioritySortedSub2 extends PrioritySorted {
   public PrioritySortedSub2() {
      super((Function<Object, Integer>)(Object o) -> ((PriorityTimer)o).getPriority());
   }
}
