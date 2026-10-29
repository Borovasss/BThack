package com.ferra13671.bthack;

public abstract class RenderableImpl6 extends Element implements Renderable {
   protected transient ComponentHelper2 componentHelper2;

   public RenderableImpl6(ComponentHelper2 componentHelper2, EventBus eventBus, int num, int num2) {
      super(eventBus, 0.0F, 0.0F, 0.0F, 0.0F);
      this.componentHelper2 = componentHelper2;
      this.widthOffset = num;
      this.heightOffset = num2;
   }

   public ComponentHelper2 getComponentHelper2() {
      return this.componentHelper2;
   }
}
