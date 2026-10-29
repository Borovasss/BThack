package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.ColoredTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedBlurBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedTextureBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;

public final class HudTextures {
   public static final transient TTFFont font = FontStyle.REGULAR.getOrLoad(24);
   public static final transient TTFFont tTFFont1 = FontStyle.REGULAR.getOrLoad(12);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator1 = new Api2Impl.NameBatchCreator(
      "plate.rounded-blur", HudTextures::getRoundedBlurBatch
   );
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator2 = new Api2Impl.NameBatchCreator("plate.rounded-rect", RoundedRectBatch::new);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator3 = new Api2Impl.NameBatchCreator(
      "plate.rounded-outlined", AbstractPrimitiveBatchHelper::new
   );
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator4 = new Api2Impl.NameBatchCreator("text", TextBatch::new);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator5 = new Api2Impl.NameBatchCreator("icons-atlas", HudTextures::getColoredTextureBatch);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator6 = new Api2Impl.NameBatchCreator("item", Helper6::new);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator7 = new Api2Impl.NameBatchCreator("rounded-rect", RoundedRectBatch::new);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator8 = new Api2Impl.NameBatchCreator(
      "color-alpha-background", HudTextures::getRoundedTextureBatch
   );
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator9 = new Api2Impl.NameBatchCreator("color-alpha-rounded-rect", RoundedRectBatch::new);
   public static final transient Api2Impl.NameBatchCreator nameBatchCreator10 = new Api2Impl.NameBatchCreator("colored-rect", ColoredRectBatch::new);

   private static RoundedBlurBatch getRoundedBlurBatch() {
      return new RoundedBlurBatch(RendererInit.roundedBlurBatch);
   }

   private static RoundedTextureBatch getRoundedTextureBatch() {
      return new RoundedTextureBatch().setTexture(RendererInit.config.gLTexture7);
   }

   HudTextures() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private static ColoredTextureBatch getColoredTextureBatch() {
      return new ColoredTextureBatch().setTexture(BThackClient.getInstance().getAtlasManager().getTextureAtlas());
   }
}
