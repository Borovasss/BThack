package com.ferra13671.bthack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class EntityUtils implements MinecraftInstance {
   EntityUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static float getFloat(float flt, float flt2) {
      return (float)Math.ceil(flt / flt2) * flt2;
   }

   public static Vec3d withClass_12971(Entity entity) {
      return entity.verticalCollision ? entity.getVelocity().multiply(1.0, 0.0, 1.0) : entity.getVelocity();
   }

   public static Float decimalPlaces(Float float_, int num) {
      return new BigDecimal(float_.floatValue()).setScale(num, RoundingMode.HALF_UP).floatValue();
   }

   public static Vec3d withMatrix4f2(Matrix4f matrix4f, float flt, float flt2, float flt3) {
      Vector3f vector3f = withMatrix4f9(matrix4f, flt, flt2, flt3);
      return new Vec3d(vector3f.x(), vector3f.y(), vector3f.z());
   }

   public static Vec3d withClass_12973(Entity entity) {
      float flt = mc.getRenderTickCounter().getTickProgress(false);
      double dbl = entity.lastRenderX + (entity.getX() - entity.lastRenderX) * flt;
      double dbl2 = entity.lastRenderY + (entity.getY() - entity.lastRenderY) * flt;
      double dbl3 = entity.lastRenderZ + (entity.getZ() - entity.lastRenderZ) * flt;
      return new Vec3d(dbl, dbl2, dbl3);
   }

   public static float getVector3f4(Vector3f vector3f, float[] fltArr, Vector3f vector3f2) {
      float[] fltArr2 = getVector3f10(vector3f, fltArr, vector3f2);
      return new Vector2f(MathHelper.wrapDegrees(fltArr2[0] - fltArr[0]), fltArr2[1] - fltArr[1]).length();
   }

   public static float getClass_12975(Entity entity, Vector3f vector3f) {
      return entity.getEyePos().toVector3f().distance(vector3f);
   }

   public static Float getFloat6(Float float_, float flt, float flt2) {
      float flt3 = -(flt - flt2);
      if (flt3 > 0.0F) {
         float_ = 0.0F;
      } else {
         float_ = Math.clamp(float_, flt3, 0.0F);
      }

      return float_;
   }

   public static Float getFloat7(Float float_) {
      return !float_.isInfinite() && !float_.isNaN() ? float_ : 0.0F;
   }

   public static float[] getClass_12978(Entity entity, Vector3f vector3f) {
      return getVector3f10(entity.getEyePos().toVector3f(), new float[]{entity.getYaw(), entity.getPitch()}, vector3f);
   }

   public static Vector3f withMatrix4f9(Matrix4f matrix4f, float flt, float flt2, float flt3) {
      return matrix4f.transformPosition(flt, flt2, flt3, new Vector3f());
   }

   public static XYButton relativize(XYButton xYButton, int num, int num2) {
      return new XYButton(xYButton.getX2() - num, xYButton.getX() - num2, xYButton.getX3());
   }

   public static float[] getVector3f10(Vector3f vector3f, float[] fltArr, Vector3f vector3f2) {
      double dbl = vector3f2.x - vector3f.x;
      double dbl2 = vector3f2.y - vector3f.y;
      double dbl3 = vector3f2.z - vector3f.z;
      double dbl4 = Math.sqrt(dbl * dbl + dbl3 * dbl3);
      float flt = fltArr[1] + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(dbl2, dbl4))) - fltArr[1]);
      float flt2 = fltArr[0] + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(dbl3, dbl)) - 90.0F - fltArr[0]);
      return new float[]{flt2, flt};
   }

   public static PointAmount withPointAmount11(PointAmount pointAmount, int num, int num2) {
      return new PointAmount(pointAmount.getX2() - num, pointAmount.getX() - num2, pointAmount.getAmount());
   }
}
