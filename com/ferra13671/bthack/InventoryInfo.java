package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import net.minecraft.item.ItemStack;

@HudInfo(name = "Inventory", x = -999.0F, y = -999.0F)
public class InventoryInfo extends HudElement implements MinecraftInstance {
   final transient ItemStack[] class_17991 = new ItemStack[27];

   public InventoryInfo() {
      this.widthOffset = Utils4.vector2i24.x;
      this.heightOffset = Utils4.vector2i24.y;

      for (int num = 0; num < 27; num++) {
         this.class_17991[num] = ItemStack.EMPTY;
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized()) {
         boolean bool = false;

         for (int num = 9; num < 36; num++) {
            ItemStack itemStack = mc.player.getInventory().getStack(num);
            if (this.class_17991[num - 9] != itemStack) {
               this.class_17991[num - 9] = itemStack;
               bool = true;
            }
         }

         if (bool) {
            BThackClient.getInstance().getManagerHelper().render4();
         }
      }
   }

   @Override
   protected void onEnable3() {
      super.onEnable3();
      if (this.getWidth() == -999.0F || this.getHeight() == -999.0F) {
         this.setX(mc.getWindow().getFramebufferWidth() / 2.0F);
         this.setY(mc.getWindow().getFramebufferHeight() / 2.0F);
      }
   }

   @Override
   public void render(Api2Impl api2Impl) {
      this.renderBackground(api2Impl);
      ((ColoredTextureBatch)api2Impl.getBatch(HudTextures.nameBatchCreator5))
         .rectSized(
            this.getWidth() + Utils4.offset.x,
            this.getHeight() + Utils4.offset.y,
            26.0F,
            26.0F,
            RectColors.oneColor(RenderColor.WHITE),
            BThackClient.getInstance().getAtlasManager().getTextureAtlas().getBorder(RendererInit.config.gLTexture18)
         );
      ((TextBatch)api2Impl.getBatch(HudTextures.nameBatchCreator4))
         .text(
            new RenderText("Inventory", this.getWidth() + Utils4.offset.x + 26.0F + 4.0F, this.getHeight() + Utils4.offset.y)
               .withFont(FontStyle.REGULAR.getOrLoad(20))
         );
      RoundedRectBatch roundedRectBatch = (RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator2);
      Helper6 helper6 = (Helper6)api2Impl.getBatch(HudTextures.nameBatchCreator6);

      for (int num3 = 9; num3 < 36; num3++) {
         int num = num3 % 9;
         int num2 = 4 - num3 / 9;
         float flt = this.getWidth() + 8.0F + num * 38 + num * 3;
         float flt2 = this.getHeight() + this.getHeightOffset() - 8.0F - num2 * 38 - (num2 - 1) * 3;
         roundedRectBatch.rectSized(flt, flt2, 38.0F, 38.0F, 10.0F, RectColors.oneColor(Utils4.renderColor19));
         helper6.withClass_1799(this.class_17991[num3 - 9], flt + 3.0F, flt2 + 3.0F, true);
      }
   }
}
