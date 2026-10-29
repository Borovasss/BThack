package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.FormattedText;

public class ModuleComponent extends Element implements Renderable {
   final transient RenderableImpl6Sub1 colorField;
   final transient Api4 api4;
   final transient FormattedText formattedText1;
   final transient int int1;
   final transient int int2;

   public void handleColoredTextureBatch(ColoredTextureBatch coloredTextureBatch) {
      Utils4.TextColors textColors = this.getApi4();
      coloredTextureBatch.rectSized(
         this.getWidth() + this.widthOffset - 18.0F - 35.0F,
         this.getHeight() + this.heightOffset / 2.0F - 17.5F,
         35.0F,
         35.0F,
         RectColors.oneColor(textColors.getBackground3()),
         BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture9)
      );
   }

   public void handleArg1(AbstractPrimitiveBatchHelper obj) {
      Utils4.TextColors textColors = this.getApi4();
      obj.withFloat2(
         this.getWidth(),
         this.getHeight(),
         this.widthOffset,
         this.heightOffset,
         20.0F,
         2.0F,
         RectColors.oneColor(RenderColor.TRANSLUCENT),
         RectColors.oneColor(textColors.getBackground2())
      );
   }

   private Utils4.TextColors getApi4() {
      boolean bool = this.api4.isEnabled();
      boolean bool2 = (Boolean)this.helper35.getValue();
      return bool ? (bool2 ? Utils4.textColors4 : Utils4.textColors2) : (bool2 ? Utils4.textColors3 : Utils4.textColors1);
   }

   @Override
   public void close2() {
      this.colorField.getComponentHelper2().getScreenHelperSub1().getComponentHelper().handleString(null);
      this.eventBus.register(new EventSub20());
   }

   public ModuleComponent(EventBus eventBus, RenderableImpl6Sub1 renderableImpl6Sub1, Api4 api4, int num, int num2) {
      super(eventBus, 0.0F, 0.0F, Utils4.vector2i4.x, Utils4.vector2i4.y);
      this.colorField = renderableImpl6Sub1;
      this.api4 = api4;
      this.formattedText1 = new FormattedText(api4.getName());
      this.int1 = num;
      this.int2 = num2;
   }

   @Override
   public void onClick(XYButton xYButton) {
      switch (xYButton.getX3()) {
         case 0:
            this.api4.setEnabled(!this.api4.isEnabled(), false);
            this.eventBus.register(new EventSub20());
            break;
         case 1:
            this.eventBus.register(new EventSub1(new ComponentSub2(this.eventBus, this.api4, Utils11.getMouseX(), Utils11.getMouseY())));
      }
   }

   public void handleTextBatch2(TextBatch textBatch) {
      Utils4.TextColors textColors = this.getApi4();
      textBatch.text(
         new RenderText(this.formattedText1, this.getWidth() + Utils4.vector2i5.x, this.getHeight() + Utils4.vector2i5.y)
            .withColor(textColors.getBackground3())
      );
   }

   @Override
   public void render4() {
      this.width = 18.0F + (this.widthOffset + 18.0F) * this.int1;
      this.height = 18.0F + (this.heightOffset + 18.0F) * this.int2;
   }

   public void handleRoundedRectBatch3(RoundedRectBatch roundedRectBatch) {
      Utils4.TextColors textColors = this.getApi4();
      roundedRectBatch.rectSized(this.getWidth(), this.getHeight(), this.widthOffset, this.heightOffset, 20.0F, RectColors.oneColor(textColors.getBackground()));
   }

   @Override
   protected void close5() {
      this.colorField
         .getComponentHelper2()
         .getScreenHelperSub1()
         .getComponentHelper()
         .handleString(BThackClient.getInstance().getTranslationManager().translate("module." + this.api4.getGetname()));
      this.eventBus.register(new EventSub20());
   }
}
