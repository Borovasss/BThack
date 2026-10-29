package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.CustomDrawMode;
import com.ferra13671.cometrenderer.minecraft.CustomVertexElementTypes;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.utils.blend.DstFactor;
import com.ferra13671.cometrenderer.utils.blend.SrcFactor;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.ferra13671.cometrenderer.vertex.mesh.MeshBuilder;
import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.chunk.WorldChunk;
import org.joml.Vector4f;

@ModuleInfo(name = "Container ESP", category = "render")
public class ContainerESP extends Module {
   public final transient NumberSetting boxAlpha = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Box Alpha"))
         .value(0.4F))
      .min(0.0F)
      .max(1.0F)
      .build();
   public final transient BooleanSetting brightBlend = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Bright Blend"))
         .value(false))
      .build();
   public final transient BooleanSetting chests = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Chests"))
         .value(true))
      .build();
   public final transient ColorSetting chestColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Chest Color"))
            .value(new Color(255, 135, 0)))
         .visibleSupplier(this.chests::getValue))
      .build();
   public final transient BooleanSetting enderChests = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Ender Chests"))
         .value(true))
      .build();
   public final transient ColorSetting enderChestColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Ender Chest Color"))
            .value(new Color(146, 0, 146)))
         .visibleSupplier(this.enderChests::getValue))
      .build();
   public final transient BooleanSetting shulkers = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Shulkers"))
         .value(true))
      .build();
   public final transient ColorSetting shulkerColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Shulker Color"))
            .value(new Color(255, 76, 255)))
         .visibleSupplier(this.shulkers::getValue))
      .build();
   public final transient BooleanSetting barrels = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Barrels"))
         .value(false))
      .build();
   public final transient ColorSetting barrelColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Barrel Color"))
            .value(new Color(165, 64, 0)))
         .visibleSupplier(this.barrels::getValue))
      .build();
   final transient Map<BlockPos, BlockEntity> map1 = new ConcurrentHashMap();
   transient Mesh mesh;
   transient boolean boolean1 = false;

   @Api8(event = EventSub7.class)
   public void runVoid() {
      if (this.boolean1) {
         this.render4();
      }

      if (this.mesh != null) {
         GlStateManager._disableDepthTest();
         if ((Boolean)this.brightBlend.getValue()) {
            CometRenderer.setBlend(SrcFactor.SRC_ALPHA, DstFactor.ONE);
         } else {
            CometRenderer.setDefaultBlend();
         }

         RenderSystem.getModelViewStack().pushMatrix();
         RenderSystem.getModelViewStack().translate(mc.gameRenderer.getCamera().getCameraPos().toVector3f().negate());
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION_COLOR);
         CRM.applyMatrixUniform();
         CometRenderer.getShaderColor().push();
         CometRenderer.getShaderColor().setColor(new Vector4f(1.0F, 1.0F, 1.0F, (Float)this.boxAlpha.getValue()));
         this.mesh.changeDrawMode(CustomDrawMode.CUBE);
         CometRenderer.applyShaderColorUniform();
         CometRenderer.draw(this.mesh, false);
         this.mesh.changeDrawMode(CustomDrawMode.CUBE_OUTLINE);
         CometRenderer.getShaderColor().resetColor();
         CometRenderer.applyShaderColorUniform();
         CometRenderer.draw(this.mesh, false);
         CometRenderer.getShaderColor().pop();
         RenderSystem.getModelViewStack().popMatrix();
         if ((Boolean)this.brightBlend.getValue()) {
            CometRenderer.setDefaultBlend();
         }
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized()) {
         if (!this.map1.isEmpty()) {
            this.map1.clear();
         }

         this.setMesh();
         this.boolean1 = false;
      }
   }

   private void setBoolean3(WorldChunk worldChunk) {
      this.map1.putAll(worldChunk.getBlockEntities());
      this.boolean1 = true;
   }

   private void setMesh() {
      if (this.mesh != null) {
         this.mesh.close();
         this.mesh = null;
      }
   }

   @Api8(event = EventSub5.class)
   public void setBoolean4(EventSub5 eventSub5) {
      eventSub5.worldChunk.getBlockEntities().keySet().forEach(this.map1::remove);
      this.boolean1 = true;
   }

   private void render4() {
      RendererInit.runLater(this::render2);
   }

   private void render2() {
      this.setMesh();
      MeshBuilder meshBuilder = Mesh.builder(CustomDrawMode.CUBE, VertexFormat.POSITION_COLOR);

      for (Entry<BlockPos, BlockEntity> map$Entry : this.map1.entrySet()) {
         RenderColor renderColor = this.withClass_25861(map$Entry.getValue());
         if (renderColor != null) {
            List<Box> list = new ArrayList<>();

            for (Box box : map$Entry.getValue().getCachedState().getOutlineShape(mc.world, map$Entry.getKey()).getBoundingBoxes()) {
               list.add(box.offset(map$Entry.getValue().getPos()).expand(0.001));
            }

            for (Box box2 : list) {
               meshBuilder.vertex((float)box2.maxX, (float)box2.minY, (float)box2.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.maxX, (float)box2.minY, (float)box2.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.minX, (float)box2.minY, (float)box2.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.minX, (float)box2.minY, (float)box2.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.minX, (float)box2.maxY, (float)box2.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.minX, (float)box2.maxY, (float)box2.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.maxX, (float)box2.maxY, (float)box2.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box2.maxX, (float)box2.maxY, (float)box2.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor});
            }
         }
      }

      this.mesh = meshBuilder.buildNullable();
      this.boolean1 = false;
   }

   @Override
   public void onDisable2() {
      super.onDisable2();
      RendererInit.runLater(this::setMesh);
   }

   @Api8(event = EventSub12.class)
   public void setBoolean(EventSub12 eventSub12) {
      if (this.getSettings().contains(eventSub12.setting)) {
         this.boolean1 = true;
      }
   }

   private RenderColor withClass_25861(BlockEntity blockEntity) {
      Color color = this.chests.getValue() && blockEntity instanceof ChestBlockEntity
         ? (Color)this.chestColor.getValue()
         : (
            this.enderChests.getValue() && blockEntity instanceof EnderChestBlockEntity
               ? (Color)this.enderChestColor.getValue()
               : (
                  this.shulkers.getValue() && blockEntity instanceof ShulkerBoxBlockEntity
                     ? (Color)this.shulkerColor.getValue()
                     : (this.barrels.getValue() && blockEntity instanceof BarrelBlockEntity ? (Color)this.barrelColor.getValue() : null)
               )
         );
      return color == null ? null : RenderColor.of(color);
   }

   @Api8(event = EventSub2.class)
   public void setBoolean2(EventSub2 eventSub2) {
      if (this.map1.containsKey(eventSub2.blockPos)) {
         this.map1.remove(eventSub2.blockPos);
         this.boolean1 = true;
      }

      BlockEntity blockEntity = mc.world.getBlockEntity(eventSub2.blockPos);
      if (blockEntity != null && !this.map1.containsKey(eventSub2.blockPos)) {
         this.boolean1 = true;
         this.map1.put(eventSub2.blockPos, blockEntity);
      }
   }

   @Override
   public void onEnable3() {
      super.onEnable3();
      this.map1.clear();
      this.boolean1 = true;
      if (!BThackClient.isInitialized()) {
         for (WorldChunk worldChunk : BThackClient.getInstance().getChunkTracker().getLoadedChunks()) {
            this.setBoolean3(worldChunk);
         }
      }
   }

   @Api8(event = EventSub14.class)
   public void handleEventSub142(EventSub14 eventSub14) {
      this.setBoolean3(eventSub14.worldChunk);
   }
}
