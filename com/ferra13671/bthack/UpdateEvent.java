package com.ferra13671.bthack;

import net.minecraft.util.math.Vec3d;

public class UpdateEvent extends Event {
   transient double width;
   transient double height;
   transient double double1;
   final transient float float1;

   public float getFloat() {
      return this.float1;
   }

   public double getDouble() {
      return this.double1;
   }

   public UpdateEvent(double dbl, double dbl2, double dbl3, float flt) {
      this.width = dbl;
      this.height = dbl2;
      this.double1 = dbl3;
      this.float1 = flt;
   }

   public double getWidth() {
      return this.width;
   }

   public double getHeight() {
      return this.height;
   }

   public void handleClass_243(Vec3d vec3d) {
      this.width = vec3d.getX();
      this.height = vec3d.getY();
      this.double1 = vec3d.getZ();
   }
}
