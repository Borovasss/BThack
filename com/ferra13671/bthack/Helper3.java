package com.ferra13671.bthack;

public class Helper3 implements MinecraftInstance {
   transient boolean boolean1 = true;
   transient float float1;
   transient float float2;
   transient float float3;
   transient float float4;
   transient float float5;
   transient float float6;
   transient float float7;
   transient float float8;

   public float getFloat3() {
      return this.float6;
   }

   public float getFloat8() {
      return this.float7;
   }

   public void runVoid() {
      this.float7 = this.float5;
      this.float8 = this.float6;
      this.float6 = this.float6 + (this.float2 - this.float6) * 0.5F;
      this.float5 = this.float5 + (this.float1 - this.float5) * 0.5F;
   }

   public void handleFloat1(float flt, float flt2) {
      if (this.boolean1) {
         this.float5 = this.float7 = flt;
         this.float6 = this.float8 = flt2;
         this.boolean1 = false;
      }

      this.float1 = flt;
      this.float2 = flt2;
   }

   public float getFloat4() {
      return this.float1;
   }

   public float getFloat5() {
      return this.float8;
   }

   public void handleFloat2(float flt, float flt2) {
      float flt3 = flt2 * 0.15F;
      float flt4 = flt * 0.15F;
      this.float3 = this.float1;
      this.float4 = this.float2;
      this.float2 += flt3;
      this.float1 += flt4;
      this.float2 = Math.clamp(this.float2, -90.0F, 90.0F);
   }

   public float getFloat2() {
      return this.float4;
   }

   public float getFloat7() {
      return this.float5;
   }

   public float getFloat6() {
      return this.float3;
   }

   public float getFloat() {
      return this.float2;
   }
}
