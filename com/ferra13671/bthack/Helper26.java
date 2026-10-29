package com.ferra13671.bthack;

public class Helper26 implements MinecraftInstance {
   final transient Aura mcModule;
   protected transient float[] float1 = null;

   public Helper26(Aura aura) {
      this.mcModule = aura;
   }

   private float[] getFloat(float[] fltArr) {
      fltArr[0] = Math.clamp(fltArr[0], -(Float)this.mcModule.angleStep.getValue(), (Float)this.mcModule.angleStep.getValue());
      fltArr[1] = Math.clamp(fltArr[1], -(Float)this.mcModule.angleStep.getValue(), (Float)this.mcModule.angleStep.getValue());
      float flt = (float)Math.pow((Double)mc.options.getMouseSensitivity().getValue() * 0.6 + 0.2, 3.0) * 1.2F;
      fltArr[0] += fltArr[0] % flt;
      fltArr[1] += fltArr[1] % flt;
      fltArr[0] = EntityUtils.getFloat(fltArr[0], flt);
      fltArr[1] = EntityUtils.getFloat(fltArr[1], flt);
      return fltArr;
   }

   public void setFloat() {
      if (this.mcModule.livingEntity == null) {
         this.mcModule.runVoid2();
         this.float1 = new float[]{mc.player.getYaw(), mc.player.getPitch()};
      } else {
         if ((Boolean)this.mcModule.smartRotate.getValue() && this.mcModule.isFloat((Float)this.mcModule.targetRange.getValue())) {
            return;
         }

         if (this.float1 == null) {
            this.float1 = new float[]{mc.player.getYaw(), mc.player.getPitch()};
         }

         float[] fltArr = this.getModule();
         fltArr[0] -= this.float1[0];
         fltArr[1] -= this.float1[1];
         fltArr = this.getFloat(fltArr);
         this.float1[0] = this.float1[0] + fltArr[0];
         this.float1[1] = this.float1[1] + fltArr[1];
         if (!BThackClient.getInstance().getPrioritySortedHelper().isHigherPriority(this.mcModule.rotationOptions)) {
            BThackClient.getInstance().getPrioritySortedHelper().register(this.mcModule.rotationOptions);
         }
      }
   }

   private float[] getModule() {
      return EntityUtils.getClass_12978(mc.player, this.mcModule.livingEntity.getBoundingBox().getCenter().toVector3f());
   }
}
