package com.ferra13671.bthack;

public class RenderableImpl8 extends Element implements Renderable {
   final transient ComponentSub4 componentSub4;
   transient RenderableImpl7 renderableImpl7;

   @Override
   public void render4() {
      if (this.componentSub4.isStack()) {
         if (this.renderableImpl7 == null) {
            this.renderableImpl7 = new RenderableImpl7(this.eventBus, this.componentSub4);
            this.renderableImpl7.render4();
         }
      } else if (this.renderableImpl7 != null) {
         this.renderableImpl7.close();
         this.renderableImpl7 = null;
      }
   }

   @Override
   public void setPosition(int num, int num2) {
      if (this.renderableImpl7 != null) {
         ElementUtils.updateHover(this.renderableImpl7, this, num, num2);
      }
   }

   @Override
   public void close() {
      if (this.renderableImpl7 != null) {
         this.renderableImpl7.close();
      }

      super.close();
   }

   public RenderableImpl8(EventBus eventBus, ComponentSub4 componentSub4) {
      super(eventBus, 0.0F, 0.0F, Utils4.vector2i13.x, Utils4.vector2i13.y);
      this.componentSub4 = componentSub4;
   }

   @Override
   public void onClick(XYButton xYButton) {
      if (this.renderableImpl7 != null) {
         ElementUtils.handleClick(this.renderableImpl7, xYButton);
      }
   }
}
