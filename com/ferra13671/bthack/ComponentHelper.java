package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.FormattedText;

public class ComponentHelper extends Component implements MinecraftInstance {
   transient String text2;
   transient FormattedText textBatch;
   final transient Api2Impl3 api2Impl3 = new Api2Impl3<>(this::getTextBatch);

   @Override
   public void render4() {
      if (this.text2 != null) {
         this.render2();
         this.widthOffset = 18.0F + FontStyle.REGULAR.getOrLoad(20).getTextWidth(this.text2);
         this.heightOffset = 18.0F + FontStyle.REGULAR.getOrLoad(20).getTextHeight(this.text2);
         this.api2Impl3.render4();
         super.render4();
      }
   }

   private void render2() {
      this.width = Utils11.getMouseX() + 12;
      this.height = Utils11.getMouseY() + 12;
   }

   @Override
   public void setSize(int num, int num2) {
      if (this.text2 != null) {
         this.render2();
      }
   }

   public void handleString(String string) {
      if (string != null) {
         if (this.text2 == null || !this.text2.equals(string)) {
            this.text2 = string;
            this.textBatch = new FormattedText(this.text2);
            this.render4();
         }
      } else if (this.text2 != null) {
         this.text2 = null;
         this.textBatch = null;
      }
   }

   private TextBatch getTextBatch() {
      TextBatch textBatch = new TextBatch(FontStyle.REGULAR.getOrLoad(20));
      if (this.textBatch != null) {
         textBatch.text(new RenderText(this.textBatch, 9.0F, 9.0F));
      }

      return textBatch;
   }

   public ComponentHelper(EventBus eventBus) {
      super(eventBus, 0.0F, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public void setPosition(int num, int num2) {
      if (this.text2 != null) {
         super.setPosition(num, num2);
         this.applyTransform();
         this.api2Impl3.close2();
         this.popMatrix();
      }
   }
}
