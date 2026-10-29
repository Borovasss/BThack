package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;

public class ElementSub3 extends Element {
   final transient Enum value;
   final transient Api9 api9;
   final transient SettingElementSub5 settingElementSub5;
   final transient ComponentSub6 componentSub6;

   @Override
   protected void close5() {
      this.componentSub6.render4();
   }

   public ElementSub3(EventBus eventBus, Enum enum_, Api9 api9, SettingElementSub5 settingElementSub5, ComponentSub6 componentSub6, float flt, float flt2) {
      super(eventBus, flt, flt2, 223.0F, 29.0F);
      this.value = enum_;
      this.api9 = api9;
      this.settingElementSub5 = settingElementSub5;
      this.componentSub6 = componentSub6;
   }

   public void handleTextBatch(TextBatch textBatch) {
      textBatch.text(
         new RenderText(
               this.value.name(),
               this.getWidth() + this.getWidthOffset() / 2.0F - textBatch.getFontOrNull().getTextWidth(this.value.name()) / 2.0F,
               this.getHeight()
            )
            .withColor(
               ((Enum)((ClassSetting)this.settingElementSub5.getSetting()).getValue()).equals(this.value)
                  ? (this.helper35.getValue() ? Utils4.defaultColors9.getDefaultcolor3() : Utils4.defaultColors9.getDefaultcolor2())
                  : (this.helper35.getValue() ? Utils4.defaultColors9.getDefaultcolor() : Utils4.defaultColors9.getDefaultcolor4())
            )
      );
   }

   @Override
   public void close2() {
      this.componentSub6.render4();
   }

   @Override
   public void onClick(XYButton xYButton) {
      ((ClassSetting)this.settingElementSub5.getSetting()).setValue2(this.value);
      this.api9.render4();
      this.componentSub6.render4();
   }
}
