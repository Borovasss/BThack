package com.ferra13671.bthack;

public class ElementSub5 extends Element {
   final transient Hud height;
   final transient ScreenHelperSub2 screenHelperSub2;
   transient boolean hovered = false;
   transient float float1;
   transient float float2;

   @Override
   public void onClick(XYButton xYButton) {
      switch (xYButton.getX3()) {
         case 0:
            this.hovered = true;
            this.float1 = xYButton.getX2() - this.getWidth();
            this.float2 = xYButton.getX() - this.getHeight();
            break;
         case 1:
            if (this.screenHelperSub2.componentSub4 != null) {
               this.screenHelperSub2.componentSub4.close();
            }

            this.screenHelperSub2.componentSub4 = new ComponentSub4(this.eventBus, this.height, xYButton.getX2(), xYButton.getX());
      }
   }

   @Override
   public float getHeight() {
      return this.height.getHeight();
   }

   @Override
   public float getHeightOffset() {
      return this.height.getHeightOffset();
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      this.hovered = false;
   }

   @Override
   public float getWidthOffset() {
      return this.height.getWidthOffset();
   }

   public ElementSub5(EventBus eventBus, Hud hud, ScreenHelperSub2 screenHelperSub2) {
      super(eventBus, hud.getWidth(), hud.getHeight(), hud.getWidthOffset(), hud.getHeightOffset());
      this.height = hud;
      this.screenHelperSub2 = screenHelperSub2;
   }

   @Override
   public void setSize(int num, int num2) {
      if (this.hovered) {
         this.height.setX(num - this.float1);
         this.height.setY(num2 - this.float2);
         BThackClient.getInstance().getManagerHelper().render4();
      }
   }

   @Override
   public float getWidth() {
      return this.height.getWidth();
   }
}
