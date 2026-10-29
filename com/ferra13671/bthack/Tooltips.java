package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedBlurBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.item.ItemStack;

@ModuleInfo(name = "Tooltips", category = "render")
public class Tooltips extends Module {
   transient ItemStackCount itemStackCount;
   final transient Api2Impl api2Impl = new Api2Impl(this::setItemStackCount2);

   private void setItemStackCount2(Api2Impl api2Impl) {
      if (this.itemStackCount != null) {
         ((RoundedBlurBatch)api2Impl.getBatch(HudTextures.nameBatchCreator1)).rectSized(0.0F, 0.0F, Utils4.vector2i24.x, Utils4.vector2i24.y, 20.0F);
         ((RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator2))
            .rectSized(0.0F, 0.0F, Utils4.vector2i24.x, Utils4.vector2i24.y, 20.0F, RectColors.oneColor(Utils4.renderColor1));
         ((AbstractPrimitiveBatchHelper)api2Impl.getBatch(HudTextures.nameBatchCreator3))
            .withFloat2(
               0.0F,
               0.0F,
               Utils4.vector2i24.x,
               Utils4.vector2i24.y,
               20.0F,
               2.0F,
               RectColors.oneColor(RenderColor.TRANSLUCENT),
               RectColors.oneColor(Utils4.api2Impl2)
            );
         ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
            .text(
               new RenderText(this.itemStackCount.getItemstack().getName().getString(), Utils4.offset.x, Utils4.offset.y)
                  .withFont(FontStyle.REGULAR.getOrLoad(20))
            );
         RoundedRectBatch roundedRectBatch = (RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator2);
         Helper6 helper6 = (Helper6)api2Impl.getBatch(HudTextures.nameBatchCreator6);

         for (int num3 = 9; num3 < 36; num3++) {
            int num = num3 % 9;
            int num2 = 4 - num3 / 9;
            float flt = 8 + num * 38 + num * 3;
            float flt2 = Utils4.vector2i24.y - 8 - num2 * 38 - (num2 - 1) * 3;
            roundedRectBatch.rectSized(flt, flt2, 38.0F, 38.0F, 10.0F, RectColors.oneColor(Utils4.renderColor19));
            if (num3 - 9 < this.itemStackCount.getStacks().size()) {
               helper6.withClass_1799((ItemStack)this.itemStackCount.getStacks().get(num3 - 9), flt + 3.0F, flt2 + 3.0F, true);
            }
         }

         this.itemStackCount = null;
      }
   }

   public ItemStackCount getItemStackCount() {
      return this.itemStackCount;
   }

   public void setPosition(int num, int num2) {
      RenderSystem.getModelViewStack().pushMatrix();
      RenderSystem.getModelViewStack().translate(num + 6, num2 + 33, 0.0F);
      this.api2Impl.render4();
      this.api2Impl.close2();
      RenderSystem.getModelViewStack().popMatrix();
   }

   @Override
   protected void onEnable3() {
      super.onEnable3();
      this.setItemStackCount(null);
   }

   public void setItemStackCount(ItemStackCount itemStackCount) {
      this.itemStackCount = itemStackCount;
   }
}
