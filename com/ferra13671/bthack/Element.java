package com.ferra13671.bthack;

import com.mojang.blaze3d.systems.RenderSystem;

public class Element extends Helper20 {
   protected transient float width;
   protected transient float height;
   protected transient float widthOffset;
   protected transient float heightOffset;
   protected transient Helper35<Boolean> helper35 = new Helper35<>(false, this::handleBoolean);

   public Helper35<Boolean> getHelper35() {
      return this.helper35;
   }

   public float getHeightOffset() {
      return this.heightOffset;
   }

   public float getHeight() {
      return this.height;
   }

   public void applyTransform() {
      this.applyTransform(0.0F, 0.0F);
   }

   @Override
   public void setPosition(int num, int num2) {
   }

   public void popMatrix() {
      RenderSystem.getModelViewStack().popMatrix();
   }

   @Override
   public boolean isHovered(int num, int num2) {
      return num >= this.getWidth()
         && num <= this.getWidth() + this.getWidthOffset()
         && num2 >= this.getHeight()
         && num2 <= this.getHeight() + this.getHeightOffset();
   }

   public float getWidth() {
      return this.width;
   }

   protected void close5() {
   }

   public float getWidthOffset() {
      return this.widthOffset;
   }

   private void handleBoolean(Boolean boolean_) {
      if (boolean_) {
         this.close5();
      } else {
         this.close2();
      }
   }

   public void onMouseRelease(XYButton xYButton) {
   }

   public void close2() {
   }

   public Element(EventBus eventBus, float flt, float flt2, float flt3, float flt4) {
      super(eventBus);
      this.width = flt;
      this.height = flt2;
      this.widthOffset = flt3;
      this.heightOffset = flt4;
   }

   public void applyTransform(float flt, float flt2) {
      RenderSystem.getModelViewStack().pushMatrix();
      RenderSystem.getModelViewMatrix().translate(this.width + flt, this.height + flt2, 0.0F);
   }
}
