package com.ferra13671.bthack;

public class RenderableImpl9Sub2 extends RenderableImpl9 {
   final transient CategoryInfoApi categoryInfoApi;

   public RenderableImpl9Sub2(EventBus eventBus, HudEditorScreen hudEditorScreen, CategoryInfoApi categoryInfoApi, int num) {
      super(eventBus, hudEditorScreen, categoryInfoApi.getName(), categoryInfoApi.getGeticon(), num);
      this.categoryInfoApi = categoryInfoApi;
   }

   @Override
   public void onClick(XYButton xYButton) {
      this.eventBus.register(new EventSub16(this.categoryInfoApi));
      this.hudEditorScreen.setRenderableImpl9(this);
   }
}
