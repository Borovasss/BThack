package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import java.util.ArrayList;
import java.util.List;

public class RenderableImpl6Sub1 extends RenderableImpl6 {
   final transient CategoryInfoApi categoryInfoApi;
   final transient List<ModuleComponent> textBatch = new ArrayList();
   final transient Api2Impl2 api2Impl2 = new Api2Impl2(
      new Api2Impl3<>(this::getRoundedRectBatch),
      new Api2Impl3<>(this::getTextBatch),
      new Api2Impl3<>(this::getTextBatch2),
      new Api2Impl3<>(this::getColoredTextureBatch)
   );

   public RenderableImpl6Sub1(ComponentHelper2 componentHelper2, EventBus eventBus, CategoryInfoApi categoryInfoApi) {
      super(componentHelper2, eventBus, Utils4.vector2i8.x, Utils4.vector2i8.y);
      this.categoryInfoApi = categoryInfoApi;
      int num = 0;
      int num2 = 0;

      for (Api4 api4 : BThackClient.getInstance().getModuleManager().containsCategory(categoryInfoApi)) {
         this.textBatch.add(new ModuleComponent(eventBus, this, api4, num, num2));
         if (num >= 1) {
            num = 0;
            num2++;
         } else {
            num++;
         }
      }
   }

   private static void handleTextBatch(TextBatch textBatch, ModuleComponent moduleComponent) {
      moduleComponent.handleTextBatch2(textBatch);
   }

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      this.api2Impl2.close2();
      num -= (int)this.width;
      num2 -= (int)this.height;

      for (ModuleComponent moduleComponent : this.textBatch) {
         moduleComponent.setPosition(num, num2);
         if ((Boolean)this.helper35.getValue()) {
            moduleComponent.getHelper35().setValue2(moduleComponent.isHovered(num, num2));
         } else {
            moduleComponent.getHelper35().setValue2(false);
         }
      }

      this.popMatrix();
   }

   private static void handleRoundedRectBatch1(RoundedRectBatch roundedRectBatch, ModuleComponent moduleComponent) {
      moduleComponent.handleRoundedRectBatch3(roundedRectBatch);
   }

   public CategoryInfoApi getCategoryInfoApi() {
      return this.categoryInfoApi;
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());

      for (ModuleComponent moduleComponent : this.textBatch) {
         if (moduleComponent.isHovered(xYButton2.getX2(), xYButton2.getX())) {
            moduleComponent.onClick(xYButton2);
         }
      }
   }

   private AbstractPrimitiveBatchHelper getTextBatch() {
      AbstractPrimitiveBatchHelper obj = new AbstractPrimitiveBatchHelper();
      this.textBatch.forEach((moduleComponent) -> RenderableImpl6Sub1.handleArg2(obj, moduleComponent));
      return obj;
   }

   private static void handleArg2(AbstractPrimitiveBatchHelper obj, ModuleComponent moduleComponent) {
      moduleComponent.handleArg1(obj);
   }

   private TextBatch getTextBatch2() {
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(26));
      this.textBatch.forEach((moduleComponent) -> RenderableImpl6Sub1.handleTextBatch(textBatch, moduleComponent));
      return textBatch;
   }

   @Override
   public void render4() {
      this.width = this.componentHelper2.getHudEditorScreen().getWidth() + this.componentHelper2.getHudEditorScreen().getWidthOffset() + 2.0F;
      this.height = this.componentHelper2.getRenderableImpl6Sub2().getHeightOffset() + 2.0F;
      this.textBatch.forEach(ModuleComponent::render4);
      this.api2Impl2.render4();
   }

   private RoundedRectBatch getRoundedRectBatch() {
      RoundedRectBatch roundedRectBatch = new RoundedRectBatch();
      this.textBatch.forEach((moduleComponent) -> RenderableImpl6Sub1.handleRoundedRectBatch1(roundedRectBatch, moduleComponent));
      return roundedRectBatch;
   }

   private static void handleColoredTextureBatch3(ColoredTextureBatch coloredTextureBatch, ModuleComponent moduleComponent) {
      moduleComponent.handleColoredTextureBatch(coloredTextureBatch);
   }

   @Override
   public void close6() {
      super.close6();
      this.textBatch.forEach(Helper20::close6);
   }

   private ColoredTextureBatch getColoredTextureBatch() {
      ColoredTextureBatch coloredTextureBatch = new ColoredTextureBatch().setTexture(BThackClient.getInstance().getAtlasManager().getTextureAtlas());
      this.textBatch.forEach((moduleComponent) -> RenderableImpl6Sub1.handleColoredTextureBatch3(coloredTextureBatch, moduleComponent));
      return coloredTextureBatch;
   }

   @Override
   public void onMouseMove(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());

      for (ModuleComponent moduleComponent : this.textBatch) {
         moduleComponent.onMouseMove(xYButton2);
      }
   }
}
