package com.ferra13671.bthack;

import com.ferra13671.gltextureutils.Pair;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum KeyboardEnum {
   Keyboard(new ArrayListSub1()),
   Mouse(new ArrayListSub2());

   final HashMap<Integer, String> keyNames = new HashMap<>();
   final HashMap<String, Integer> keyValues = new HashMap<>();

   KeyboardEnum(List<Pair<Integer, String>> list) {
      list.forEach(pair -> {
         this.keyNames.put((Integer)pair.getLeft(), (String)pair.getRight());
         this.keyValues.put((String)pair.getRight(), (Integer)pair.getLeft());
      });
   }

   public Map<String, Integer> getKeyValues() {
      return Collections.unmodifiableMap(this.keyValues);
   }

   private static KeyboardEnum[] $values() {
      return new KeyboardEnum[]{Keyboard, Mouse};
   }

   public String getKeyName(int num) {
      String string = this.keyNames.get(num);
      if (string == null) {
         string = "UNKNOWN";
      }

      return string;
   }

   public int getKeyValue(String string) {
      Integer integer = this.keyValues.get(string);
      return integer == null ? -1 : integer;
   }

   public Map<Integer, String> getKeyNames() {
      return Collections.unmodifiableMap(this.keyNames);
   }
}
