package com.ferra13671.bthack;

import net.minecraft.util.math.Vec3d;

@ModuleInfo(name = "Freeze", category = "movement")
public class Freeze extends Blink {
   @Api8(event = SetVelocityEvent.class)
   public void onSetVelocity(SetVelocityEvent setVelocityEvent) {
      setVelocityEvent.runVoid();
      setVelocityEvent.vec3d = new Vec3d(0.0, 0.0, 0.0);
   }
}
