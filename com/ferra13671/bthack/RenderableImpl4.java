package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class RenderableImpl4 extends Element implements Renderable {
   final transient SettingButtonConfig settingButtonConfig;
   final transient ComponentSub2 componentSub2;
   final transient int int1;
   transient boolean enabled = false;

   private Utils4.IconIconColorTextColor getSettingButtonConfig() {
      return this.settingButtonConfig.getButtonpack().withBoolean(this.enabled, (Boolean)this.helper35.getValue());
   }

   @Override
   public void render4() {
      this.width = this.componentSub2.getWidth() + 15.0F;
      this.height = this.componentSub2.getHeight() + 15.0F + (this.heightOffset + 6.0F) * this.int1;
   }

   @Override
   public void onClick(XYButton xYButton) {
      this.enabled = !this.enabled;
      this.settingButtonConfig.getClickconsumer().accept(this.componentSub2.getApi4(), this.componentSub2, xYButton);
      this.componentSub2.render4();
   }

   public void handleColoredTextureBatch(ColoredTextureBatch coloredTextureBatch) {
      Utils4.IconIconColorTextColor iconIconColorTextColor = this.getSettingButtonConfig();
      coloredTextureBatch.rectSized(
         this.width,
         this.height + this.heightOffset / 2.0F - 17.5F,
         35.0F,
         35.0F,
         RectColors.oneColor(iconIconColorTextColor.getIconcolor2()),
         BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(iconIconColorTextColor.getIcon())
      );
   }

   public RenderableImpl4(EventBus eventBus, SettingButtonConfig settingButtonConfig, ComponentSub2 componentSub2, int num) {
      super(eventBus, 0.0F, 0.0F, Utils4.vector2i11.x, Utils4.vector2i11.y);
      this.settingButtonConfig = settingButtonConfig;
      this.componentSub2 = componentSub2;
      this.int1 = num;
      this.enabled = (Boolean)settingButtonConfig.getShouldworkformodulefunction2().apply(componentSub2.getApi4());
   }

   @Override
   protected void close5() {
      this.componentSub2.render4();
   }

   @Override
   public void close2() {
      this.componentSub2.render4();
   }

   public void handleTextBatch(TextBatch textBatch) {
      Utils4.IconIconColorTextColor iconIconColorTextColor = this.getSettingButtonConfig();
      textBatch.text(
         new RenderText(this.settingButtonConfig.name(), this.width + 35.0F + Utils4.vector2i12.x, this.height + Utils4.vector2i12.y)
            .withColor(iconIconColorTextColor.getIconcolor())
      );
   }
}
