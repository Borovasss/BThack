package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import java.util.ArrayList;
import java.util.List;

public class ComponentSub2 extends Component {
   final transient Api4 api4;
   final transient List<RenderableImpl4> coloredTextureBatch = new ArrayList();
   final transient Api2Impl2 api2Impl2 = new Api2Impl2(new Api2Impl3<>(this::getColoredTextureBatch), new Api2Impl3<>(this::getTextBatch));

   private void handleInt(int num, int num2, RenderableImpl4 renderableImpl4) {
      ElementUtils.updateHover(renderableImpl4, this, num, num2);
   }

   private static void handleTextBatch(TextBatch textBatch, RenderableImpl4 renderableImpl4) {
      renderableImpl4.handleTextBatch(textBatch);
   }

   @Override
   public void setPosition(int num, int num2) {
      super.setPosition(num, num2);
      this.coloredTextureBatch.forEach((renderableImpl4) -> this.handleInt(num, num2, renderableImpl4));
      this.api2Impl2.close2();
   }

   @Override
   public void render4() {
      super.render4();
      this.coloredTextureBatch.forEach(RenderableImpl4::render4);
      this.api2Impl2.render4();
   }

   private static void handleApi42(Api4 api4, List list, SettingButtonConfig settingButtonConfig) {
      if ((Boolean)settingButtonConfig.getShouldworkformodulefunction().apply(api4)) {
         list.add(settingButtonConfig);
      }
   }

   private static void handleColoredTextureBatch3(ColoredTextureBatch coloredTextureBatch, RenderableImpl4 renderableImpl4) {
      renderableImpl4.handleColoredTextureBatch(coloredTextureBatch);
   }

   public ComponentSub2(EventBus eventBus, Api4 api4, float flt, float flt2) {
      super(eventBus, flt, flt2, 250.0F, 0.0F);
      List<SettingButtonConfig> list = new ArrayList<>();
      BThackClient.getInstance().getModuleManager().getModules().forEach((settingButtonConfig1) -> ComponentSub2.handleApi42(api4, list, settingButtonConfig1));
      this.heightOffset = 30 + Utils4.vector2i11.y * list.size() + 6 * (list.size() - 1);
      this.api4 = api4;
      int num = 0;

      for (SettingButtonConfig settingButtonConfig : list) {
         this.coloredTextureBatch.add(new RenderableImpl4(eventBus, settingButtonConfig, this, num));
         num++;
      }

      this.render4();
   }

   private ColoredTextureBatch getColoredTextureBatch() {
      ColoredTextureBatch coloredTextureBatch = new ColoredTextureBatch().setTexture(BThackClient.getInstance().getAtlasManager().getTextureAtlas());
      this.coloredTextureBatch.forEach((renderableImpl4) -> ComponentSub2.handleColoredTextureBatch3(coloredTextureBatch, renderableImpl4));
      return coloredTextureBatch;
   }

   @Override
   public void onMouseRelease(XYButton xYButton) {
      if (!this.isHovered(xYButton.getX2(), xYButton.getX())) {
         this.eventBus.register(new EventSub13(this));
      }
   }

   @Override
   public void onClick(XYButton xYButton) {
      this.coloredTextureBatch.forEach((renderableImpl4) -> ComponentSub2.handleXYButton4(xYButton, renderableImpl4));
   }

   public Api4 getApi4() {
      return this.api4;
   }

   private TextBatch getTextBatch() {
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(24));
      this.coloredTextureBatch.forEach((renderableImpl4) -> ComponentSub2.handleTextBatch(textBatch, renderableImpl4));
      return textBatch;
   }

   @Override
   public void close() {
      super.close();
      this.api2Impl2.close();
   }

   private static void handleXYButton4(XYButton xYButton, RenderableImpl4 renderableImpl4) {
      ElementUtils.handleClick(renderableImpl4, xYButton);
   }
}
