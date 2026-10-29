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
import java.awt.Color;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.joml.Vector4f;

@ModuleInfo(name = "Player ESP", category = "render")
public class PlayerESP extends Module {
   public final transient ClassSetting colormode = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(DefaultBody2.class)
            .name("Color Mode"))
         .value(DefaultBody2.Default))
      .build();
   public final transient ColorSetting color = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Color"))
            .value(Color.WHITE))
         .visibleSupplier(this::isColormode2))
      .build();
   public final transient ColorSetting firstColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("First Color"))
            .value(Utils10.withRenderColor1(Utils4.roundedRectBatch)))
         .visibleSupplier(this::isColormode3))
      .build();
   public final transient ColorSetting secondColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Second Color"))
            .value(Utils10.withRenderColor1(Utils4.roundedRectBatch.multiply(RenderColor.of(0.7F, 0.7F, 0.7F, 1.0F)))))
         .visibleSupplier(this::isColormode))
      .build();
   public final transient NumberSetting gradientSpeed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Gradient Speed"))
            .value(1.0F))
         .min(0.2F)
         .max(3.0F)
         .decimalPlaces(1)
         .visibleSupplier(this::isColormode4))
      .build();
   public final transient NumberSetting boxAlpha = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Box Alpha"))
         .value(0.4F))
      .min(0.0F)
      .max(1.0F)
      .build();
   public final transient BooleanSetting gradient = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Gradient"))
         .value(true))
      .build();
   public final transient BooleanSetting depthTest = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Depth Test"))
         .value(false))
      .build();
   protected final transient Helper45 helper45 = new Helper45();

   private Boolean isColormode2() {
      return this.colormode.getValue() == DefaultBody2.Default;
   }

   private Boolean isColormode4() {
      return this.colormode.getValue() == DefaultBody2.Gradient;
   }

   @Api8(event = EventSub7.class)
   public void runVoid() {
      if (mc.world.getPlayers().size() > 1) {
         if ((Boolean)this.depthTest.getValue()) {
            GlStateManager._enableDepthTest();
            GlStateManager._depthMask(false);
         } else {
            GlStateManager._disableDepthTest();
         }

         MeshBuilder meshBuilder = Mesh.builder(CustomDrawMode.CUBE, VertexFormat.POSITION_COLOR);
         MeshBuilder meshBuilder2 = this.gradient.getValue() ? Mesh.builder(CustomDrawMode.CUBE, VertexFormat.POSITION_COLOR) : null;

         for (PlayerEntity playerEntity : mc.world.getPlayers()) {
            if (playerEntity != mc.player) {
               RenderColor renderColor = (RenderColor)((DefaultBody2)this.colormode.getValue()).colorFunction.apply(this, playerEntity);
               RenderColor renderColor2 = RenderColor.of(
                  renderColor.getColor()[0] / 1.5F, renderColor.getColor()[1] / 1.5F, renderColor.getColor()[2] / 1.5F, renderColor.getColor()[3] * 0.0F
               );
               Box box = playerEntity.getBoundingBox()
                  .offset(playerEntity.getEntityPos().multiply(-1.0, -1.0, -1.0))
                  .offset(EntityUtils.withClass_12973(playerEntity))
                  .offset(mc.gameRenderer.getCamera().getCameraPos().multiply(-1.0, -1.0, -1.0));
               meshBuilder.vertex((float)box.maxX, (float)box.minY, (float)box.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.maxX, (float)box.minY, (float)box.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.minX, (float)box.minY, (float)box.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.minX, (float)box.minY, (float)box.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.minX, (float)box.maxY, (float)box.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.minX, (float)box.maxY, (float)box.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.maxX, (float)box.maxY, (float)box.maxZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex((float)box.maxX, (float)box.maxY, (float)box.minZ)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor});
               if ((Boolean)this.gradient.getValue()) {
                  meshBuilder2.vertex((float)box.maxX, (float)box.minY, (float)box.minZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                     .vertex((float)box.maxX, (float)box.minY, (float)box.maxZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                     .vertex((float)box.minX, (float)box.minY, (float)box.maxZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                     .vertex((float)box.minX, (float)box.minY, (float)box.minZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                     .vertex((float)box.minX, (float)box.maxY, (float)box.minZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor2})
                     .vertex((float)box.minX, (float)box.maxY, (float)box.maxZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor2})
                     .vertex((float)box.maxX, (float)box.maxY, (float)box.maxZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor2})
                     .vertex((float)box.maxX, (float)box.maxY, (float)box.minZ)
                     .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor2});
               }
            }
         }

         CometRenderer.getShaderColor().push();
         CometRenderer.getShaderColor().setColor(new Vector4f(1.0F, 1.0F, 1.0F, (Float)this.boxAlpha.getValue()));
         CometRenderer.setBlend(SrcFactor.SRC_ALPHA, DstFactor.ONE);
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION_COLOR);
         CRM.applyMatrixUniform();
         Mesh mesh = meshBuilder.buildNullable();
         if ((Boolean)this.gradient.getValue()) {
            CometRenderer.applyShaderColorUniform();
            CometRenderer.draw(meshBuilder2.buildNullable());
         } else {
            CometRenderer.applyShaderColorUniform();
            CometRenderer.draw(mesh, false);
         }

         CometRenderer.getShaderColor().resetColor();
         CometRenderer.applyShaderColorUniform();
         mesh.changeDrawMode(CustomDrawMode.CUBE_OUTLINE);
         CometRenderer.draw(mesh);
         CometRenderer.setDefaultBlend();
         CometRenderer.getShaderColor().pop();
      }
   }

   private Boolean isColormode() {
      return this.colormode.getValue() == DefaultBody2.Gradient;
   }

   private Boolean isColormode3() {
      return this.colormode.getValue() == DefaultBody2.Gradient;
   }

   @Override
   public void onEnable3() {
      super.onEnable3();
      this.helper45.reset();
   }
}
