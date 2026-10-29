package com.ferra13671.bthack;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class Utils5 {
   Utils5() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Vector2f withMatrix4f(Matrix4f matrix4f, Vector2f vector2f) {
      Vector3f vector3f = matrix4f.transformPosition(new Vector3f(vector2f.x, vector2f.y, 0.0F));
      return new Vector2f(vector3f.x, vector3f.y);
   }

   public static Vector2f withMatrix4f(Matrix4f matrix4f, float flt, float flt2) {
      return withMatrix4f(matrix4f, new Vector2f(flt, flt2));
   }
}
