package com.ferra13671.bthack;

import net.minecraft.util.math.Vec3d;

public class SetVelocityEvent extends Event {
   public transient Vec3d vec3d;

   public SetVelocityEvent(Vec3d vec3d) {
      this.vec3d = vec3d;
   }
}
