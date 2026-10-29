package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import lombok.NonNull;

public class PrioritySorted {
   protected final transient List subscribers = new ArrayList();
   protected transient Object current;
   protected final transient @NonNull Function<Object, Integer> priorityExtractor;

   public boolean subscribe(Object object) {
      boolean bool = !this.subscribers.contains(object);
      if (bool) {
         this.subscribers.add(object);
         this.updateCurrent();
      }

      return bool;
   }

   public boolean isHigherPriority(Object object) {
      return this.subscribers.contains(object);
   }

   public boolean unsubscribe(Object object) {
      boolean bool = this.subscribers.remove(object);
      if (bool) {
         this.updateCurrent();
      }

      return bool;
   }

   public Object getCurrent() {
      return this.current;
   }

   public PrioritySorted(Function<Object, Integer> function) {
      if (function == null) {
         throw new NullPointerException("sortPriorityFunction is marked non-null but is null");
      }

      this.priorityExtractor = function;
   }

   public boolean hasSubscribers() {
      return this.subscribers.isEmpty();
   }

   protected void updateCurrent() {
      this.subscribers.sort(Comparator.comparing(this.priorityExtractor));
      if (!this.subscribers.isEmpty()) {
         this.current = this.subscribers.getFirst();
      } else {
         this.current = null;
      }
   }
}
