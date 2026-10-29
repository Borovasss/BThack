package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import java.util.ArrayList;
import java.util.List;

public class ComponentSub6 extends Component {
   final transient List<ElementSub3> children = new ArrayList();
   final transient Api2Impl2 api2Impl2;

   private static void handleXYButton(XYButton xYButton, ElementSub3 elementSub3) {
      ElementUtils.handleClick(elementSub3, xYButton);
   }

   private TextBatch getTextBatch() {
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(24));
      this.children.forEach((elementSub3) -> ComponentSub6.handleTextBatch1(textBatch, elementSub3));
      return textBatch;
   }

   @Override
   public void onMouseRelease(XYButton xYButton) {
      if (!this.isHovered(xYButton.getX2(), xYButton.getX())) {
         this.eventBus.register(new EventSub13(this));
      }
   }

   @Override
   public void onClick(XYButton xYButton) {
      this.children.forEach((elementSub3) -> ComponentSub6.handleXYButton(xYButton, elementSub3));
   }

   @Override
   public void setPosition(int num, int num2) {
      super.setPosition(num, num2);
      this.api2Impl2.close2();

      for (ElementSub3 elementSub3 : this.children) {
         ElementUtils.updateHover(elementSub3, this, num, num2);
      }
   }

   private static void handleTextBatch1(TextBatch textBatch, ElementSub3 elementSub3) {
      elementSub3.handleTextBatch(textBatch);
   }

   @Override
   public void render4() {
      super.render4();
      this.api2Impl2.render4();
   }

   public ComponentSub6(EventBus eventBus, Api9 api9, SettingElementSub5 settingElementSub5, float flt, float flt2) {
      super(eventBus, flt, flt2, 223.0F, 0.0F);
      float flt3 = 12.0F;
      Enum<?>[] enum_Arr = (Enum<?>[])((Enum)((ClassSetting)settingElementSub5.getSetting()).getValue()).getClass().getEnumConstants();

      for (int num = 0; num < enum_Arr.length; num++) {
         Enum<?> enum_ = enum_Arr[num];
         ElementSub3 elementSub3 = new ElementSub3(eventBus, enum_, api9, settingElementSub5, this, this.getWidth(), this.getHeight() + flt3);
         this.children.add(elementSub3);
         flt3 += elementSub3.getHeightOffset();
         if (num < enum_Arr.length - 1) {
            flt3 += 10.0F;
         } else {
            flt3 += 12.0F;
         }
      }

      this.heightOffset = flt3;
      this.api2Impl2 = new Api2Impl2(new Api2Impl3<>(this::getTextBatch), new Api2Impl3<>(this::getColoredRectBatch));
   }

   private ColoredRectBatch getColoredRectBatch() {
      ColoredRectBatch coloredRectBatch = new ColoredRectBatch();

      for (int num = 0; num < this.children.size(); num++) {
         if (num < this.children.size() - 1) {
            ElementSub3 elementSub3 = (ElementSub3)this.children.get(num);
            float flt = elementSub3.getHeight() + elementSub3.getHeightOffset() + 4.0F;
            coloredRectBatch.rectSized(this.getWidth(), flt, 67.0F, 2.0F, RectColors.horizontalGradient(RenderColor.TRANSLUCENT, Utils4.renderColor7))
               .rectSized(this.getWidth() + 67.0F, flt, 61.0F, 2.0F, RectColors.oneColor(Utils4.renderColor7))
               .rectSized(this.getWidth() + 67.0F + 61.0F, flt, 67.0F, 2.0F, RectColors.horizontalGradient(Utils4.renderColor7, RenderColor.TRANSLUCENT));
         }
      }

      return coloredRectBatch;
   }

   public void runVoid() {
      this.width = Math.min(this.getWidth() + this.getWidthOffset(), CRM.getMainFramebuffer().getWidth()) - this.getWidthOffset();
      this.height = Math.min(this.getHeight() + this.getHeightOffset(), CRM.getMainFramebuffer().getHeight()) - this.getHeightOffset();
   }
}
