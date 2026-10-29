package com.ferra13671.bthack;

import com.ferra13671.gltextureutils.Pair;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.lwjgl.glfw.GLFW;

class ArrayListSub1 extends ArrayList {
   ArrayListSub1() throws RuntimeException, Error {
      this.add(new Pair(0, "NONE"));

      for (Field field : GLFW.class.getDeclaredFields()) {
         String string = field.getName();
         if (string.startsWith("GLFW_KEY_")
            && Modifier.isStatic(field.getModifiers())
            && Modifier.isFinal(field.getModifiers())
            && Modifier.isPublic(field.getModifiers())) {
            try {
               int num = (Integer)field.get(null);
               this.add(new Pair(num, string.substring("GLFW_KEY_".length())));
            } catch (IllegalAccessException var7) {
            }
         }
      }
   }
}
