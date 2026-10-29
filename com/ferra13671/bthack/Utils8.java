package com.ferra13671.bthack;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import org.joml.Vector3f;

public final class Utils8 implements MinecraftInstance {
   public static boolean isClass_1297(Entity entity, Box box, float flt) {
      Vector3f vector3f = entity.getEyePos().toVector3f();
      Vector3f vector3f2 = entity.getRotationVec(1.0F).toVector3f().mul(0.04F);
      Vector3f vector3f3 = new Vector3f(vector3f);

      while (vector3f.distance(vector3f3) < flt) {
         vector3f3.add(vector3f2);
         if (box.contains(vector3f3.x, vector3f3.y, vector3f3.z)) {
            return true;
         }
      }

      return false;
   }

   public static BlockHitResult withClass_2431(Vec3d vec3d, Vec3d vec3d2, FluidHandling obj) {
      return mc.world.raycast(new RaycastContext(vec3d, vec3d2, ShapeType.COLLIDER, obj, mc.player));
   }

   public static BlockHitResult withClass_2432(Vec3d vec3d, Vec3d vec3d2) {
      return withClass_2431(vec3d, vec3d2, FluidHandling.NONE);
   }

   Utils8() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
