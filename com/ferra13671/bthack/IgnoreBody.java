package com.ferra13671.bthack;

import java.util.List;
import java.util.function.BiConsumer;

public enum IgnoreBody {
   Ignore(false, (list, object) -> {}),
   ReplaceFirst(true, (list, object) -> list.set(0, object)),
   ReplaceLast(true, (list, object) -> list.set(list.size() - 1, object));

   public final boolean modify;
   public final BiConsumer<List<Object>, Object> onModifyConsumer;

   IgnoreBody(boolean bool, BiConsumer<List<Object>, Object> biConsumer) {
      this.modify = bool;
      this.onModifyConsumer = biConsumer;
   }

   private static IgnoreBody[] $values() {
      return new IgnoreBody[]{Ignore, ReplaceFirst, ReplaceLast};
   }
}
