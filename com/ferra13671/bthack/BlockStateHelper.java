package com.ferra13671.bthack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;

public class BlockStateHelper {
   final transient BlockState blockState;

   public static BlockStateHelper withClass_2680(BlockState blockState) {
      return new BlockStateHelper(blockState);
   }

   public BlockStateHelper(BlockState blockState) {
      this.blockState = blockState;
   }

   public boolean getBlockState2() {
      return this.blockState.isIn(BlockTags.COAL_ORES)
         || this.blockState.isIn(BlockTags.COPPER_ORES)
         || this.blockState.isIn(BlockTags.IRON_ORES)
         || this.blockState.isIn(BlockTags.REDSTONE_ORES)
         || this.blockState.isIn(BlockTags.LAPIS_ORES)
         || this.blockState.isIn(BlockTags.GOLD_ORES)
         || this.blockState.isIn(BlockTags.DIAMOND_ORES)
         || this.blockState.isIn(BlockTags.EMERALD_ORES)
         || this.blockState.getBlock() == Blocks.ANCIENT_DEBRIS;
   }

   public BlockState getBlockState() {
      return this.blockState;
   }
}
