package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.gltextureutils.GLTexture;
import java.util.ArrayList;
import java.util.List;

public class HudEditorScreen extends RenderableImpl6 implements MinecraftInstance {
   final transient List<RenderableImpl9> textBatch = new ArrayList();
   final transient Api2Impl2 api2Impl2;
   transient RenderableImpl9 renderableImpl9;

   private TextBatch getTextBatch() {
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(29));
      this.textBatch.forEach((renderableImpl9) -> HudEditorScreen.handleTextBatch(textBatch, renderableImpl9));
      return textBatch;
   }

   @Override
   public void setPosition(int num, int num2) {
      this.applyTransform();
      this.api2Impl2.close2();
      num -= (int)this.width;
      num2 -= (int)this.height;

      for (RenderableImpl9 renderableImpl9 : this.textBatch) {
         renderableImpl9.setPosition(num, num2);
         if ((Boolean)this.helper35.getValue()) {
            renderableImpl9.getHelper35().setValue2(renderableImpl9.isHovered(num, num2));
         } else {
            renderableImpl9.getHelper35().setValue2(false);
         }
      }

      this.popMatrix();
   }

   private ColoredTextureBatch getColoredTextureBatch() {
      ColoredTextureBatch coloredTextureBatch = new ColoredTextureBatch().setTexture(BThackClient.getInstance().getAtlasManager().getHelper9());
      this.textBatch.forEach((renderableImpl9) -> HudEditorScreen.handleColoredTextureBatch1(coloredTextureBatch, renderableImpl9));
      return coloredTextureBatch;
   }

   public void setRenderableImpl9(RenderableImpl9 renderableImpl9) {
      this.renderableImpl9 = renderableImpl9;
   }

   private static void handleTextBatch(TextBatch textBatch, RenderableImpl9 renderableImpl9) {
      renderableImpl9.handleTextBatch1(textBatch);
   }

   private static void handleColoredTextureBatch1(ColoredTextureBatch coloredTextureBatch, RenderableImpl9 renderableImpl9) {
      renderableImpl9.handleColoredTextureBatch(coloredTextureBatch);
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight());

      for (RenderableImpl9 renderableImpl9 : this.textBatch) {
         if (renderableImpl9.isHovered(xYButton2.getX2(), xYButton2.getX())) {
            renderableImpl9.onClick(xYButton2);
         }
      }
   }

   @Override
   public void close6() {
      this.textBatch.forEach(Helper20::close6);
      super.close6();
   }

   public RenderableImpl9 getRenderableImpl9() {
      return this.renderableImpl9;
   }

   public HudEditorScreen(ComponentHelper2 componentHelper2, EventBus eventBus) {
      super(componentHelper2, eventBus, Utils4.vector2i7.x, Utils4.vector2i7.y);

      class RenderableImpl9Sub1 extends RenderableImpl9 {
         RenderableImpl9Sub1(EventBus eventBus, HudEditorScreen hudEditorScreen2, String string, GLTexture gLTexture, int num) {
            super(eventBus, hudEditorScreen2, string, gLTexture, num);
         }

         @Override
         public void onClick(XYButton xYButton) {
            MinecraftInstance.mc.execute(RenderableImpl9Sub1::runVoid);
         }

         private static void runVoid() {
            MinecraftInstance.mc.setScreen(BThackClient.getInstance().getHudEditorScreen());
         }
      }

      this.api2Impl2 = new Api2Impl2(new Api2Impl3<>(this::getColoredTextureBatch), new Api2Impl3<>(this::getTextBatch));
      this.renderableImpl9 = null;
      int num = 0;

      for (CategoryInfoApi categoryInfoApi : BThackClient.getInstance().getModuleManager().getCategories()) {
         this.textBatch.add(new RenderableImpl9Sub2(eventBus, this, categoryInfoApi, num));
         num++;
      }

      this.textBatch.add(new RenderableImpl9Sub1(eventBus, this, "Hud editor", RendererInit.config.gLTexture5, num));
   }

   @Override
   public void render4() {
      this.width = 1.0F;
      this.height = this.componentHelper2.getRenderableImpl6Sub2().getHeight() + this.componentHelper2.getRenderableImpl6Sub2().getHeightOffset();
      this.textBatch.forEach(RenderableImpl9::render4);
      this.api2Impl2.render4();
   }
}
