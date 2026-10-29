package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.CustomDrawMode;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.ferra13671.gltextureutils.Pair;
import com.mojang.blaze3d.opengl.GlStateManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

@ModuleInfo(name = "Block Outline", category = "render")
public class BlockOutline extends Module {
   public final transient BooleanSetting useShape = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Use shape"))
         .value(true))
      .build();
   public final transient ColorSetting sideColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
            .name("Side color"))
         .value(new Color(1.0F, 1.0F, 1.0F, 0.3F)))
      .build();
   public final transient ColorSetting outlineColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
            .name("Outline color"))
         .value(new Color(1.0F, 1.0F, 1.0F, 1.0F)))
      .build();
   public final transient BooleanSetting depthTest = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Depth test"))
         .value(true))
      .build();

   @Api8(event = EventSub7.class)
   public void runVoid() {
      Pair<BlockPos, BlockState> pair = this.applyPair1();
      if (pair != null) {
         if ((Boolean)this.depthTest.getValue()) {
            GlStateManager._enableDepthTest();
            GlStateManager._depthMask(false);
         } else {
            GlStateManager._disableDepthTest();
         }

         List<Box> list;
         if ((Boolean)this.useShape.getValue()) {
            list = new ArrayList<>();

            for (Box box : ((BlockState)pair.getRight()).getOutlineShape(mc.world, (BlockPos)pair.getLeft()).getBoundingBoxes()) {
               list.add(box.offset((BlockPos)pair.getLeft()).expand(0.001));
            }
         } else {
            list = List.of(
               new Box(
                  ((BlockPos)pair.getLeft()).toCenterPos().add(-0.5001, -0.5001, -0.5001), ((BlockPos)pair.getLeft()).toCenterPos().add(0.5001, 0.5001, 0.5001)
               )
            );
         }

         Mesh mesh = RendererInit.withList4(list, mc.gameRenderer.getCamera().getCameraPos());
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION);
         CRM.applyMatrixUniform();
         CometRenderer.getShaderColor().setColor(RenderColor.of((Color)this.sideColor.getValue()).toVector4f());
         CometRenderer.applyShaderColorUniform();
         CometRenderer.draw(mesh, false);
         mesh.changeDrawMode(CustomDrawMode.CUBE_OUTLINE);
         CometRenderer.getShaderColor().setColor(RenderColor.of((Color)this.outlineColor.getValue()).toVector4f());
         CometRenderer.applyShaderColorUniform();
         CometRenderer.draw(mesh);
      }
   }

   private Pair applyPair1() {
      if (mc.crosshairTarget instanceof BlockHitResult blockHitResult) {
         if (blockHitResult.getType() == Type.MISS) {
            return null;
         }

         BlockPos blockPos = blockHitResult.getBlockPos();
         BlockState blockState = mc.world.getBlockState(blockPos);
         return blockState.isAir() ? null : new Pair(blockPos, blockState);
      } else {
         return null;
      }
   }
}
