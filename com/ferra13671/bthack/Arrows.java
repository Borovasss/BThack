package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.CustomVertexElementTypes;
import com.ferra13671.cometrenderer.minecraft.CustomVertexFormats;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.utils.blend.DstFactor;
import com.ferra13671.cometrenderer.utils.blend.SrcFactor;
import com.ferra13671.cometrenderer.vertex.DrawMode;
import com.ferra13671.cometrenderer.vertex.element.VertexElementType;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.ferra13671.cometrenderer.vertex.mesh.MeshBuilder;
import java.awt.Color;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@ModuleInfo(name = "Arrows", category = "render")
public class Arrows extends Module {
   public final transient ClassSetting texture = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(Arrow1Body.class)
            .name("Texture"))
         .value(Arrow1Body.Arrow1))
      .build();
   public final transient NumberSetting size = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder().name("Size"))
         .value(20.0F))
      .min(10.0F)
      .max(100.0F)
      .decimalPlaces(0)
      .build();
   public final transient NumberSetting radius = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Radius"))
         .value(50.0F))
      .min(10.0F)
      .max(400.0F)
      .decimalPlaces(0)
      .build();
   public final transient BooleanSetting smoothMoving = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Smooth Moving"))
         .value(true))
      .build();
   public final transient NumberSetting smoothness = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Smoothness"))
            .value(0.6F))
         .min(0.1F)
         .max(1.0F)
         .decimalPlaces(2)
         .visibleSupplier(this.smoothMoving::getValue))
      .build();
   public final transient BooleanSetting x3d = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("3D"))
         .value(true))
      .build();
   public final transient NumberSetting maxAngle = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Max Angle"))
            .value(60.0F))
         .min(10.0F)
         .max(80.0F)
         .decimalPlaces(1)
         .visibleSupplier(this.x3d::getValue))
      .build();
   public final transient ColorSetting color = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder().name("Color"))
         .value(Color.WHITE))
      .build();
   public final transient ClassSetting teamsColor = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(NoneBody2.class)
            .name("Teams Color"))
         .value(NoneBody2.None))
      .build();
   transient float float1;
   transient float float2;

   private void handleMeshBuilder(MeshBuilder[] meshBuilderArr, PlayerEntity playerEntity, float[] fltArr, float flt, RenderColor renderColor) {
      flt = (float)Math.toRadians(flt);
      Matrix4f matrix4f = new Matrix4f().translate(fltArr[0], fltArr[1], 0.0F);
      float[] fltArr2 = new float[]{(float)Math.sin(flt) * (Float)this.radius.getValue(), (float)(-Math.cos(flt)) * (Float)this.radius.getValue()};
      if ((Boolean)this.x3d.getValue()) {
         matrix4f.rotateX((float)Math.toRadians(Math.min((Float)this.maxAngle.getValue(), 90.0F - this.float2))).translate(fltArr2[0], fltArr2[1], 0.0F);
      }

      matrix4f.rotateZ(flt);
      float flt2 = (Float)this.size.getValue();
      Vector3f[] vector3fArr = new Vector3f[]{
         EntityUtils.withMatrix4f9(matrix4f, -flt2, -flt2, 0.0F),
         EntityUtils.withMatrix4f9(matrix4f, -flt2, flt2, 0.0F),
         EntityUtils.withMatrix4f9(matrix4f, flt2, flt2, 0.0F),
         EntityUtils.withMatrix4f9(matrix4f, flt2, -flt2, 0.0F)
      };
      RenderColor renderColor2 = (RenderColor)((NoneBody2)this.teamsColor.getValue()).colorFunction.apply(playerEntity, renderColor);
      this.handleMeshBuilder1(meshBuilderArr[0], vector3fArr, RenderColor.of(1.0F, 1.0F, 1.0F, 0.2F));
      this.handleMeshBuilder1(meshBuilderArr[1], vector3fArr, renderColor2);
   }

   private void handleMeshBuilder1(MeshBuilder meshBuilder, Vector3f[] vector3fArr, RenderColor renderColor) {
      meshBuilder.vertex(vector3fArr[0].x, vector3fArr[0].y, 0.0F)
         .element("Texture", VertexElementType.FLOAT, new Float[]{0.0F, 0.0F})
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
         .vertex(vector3fArr[1].x, vector3fArr[1].y, 0.0F)
         .element("Texture", VertexElementType.FLOAT, new Float[]{0.0F, 1.0F})
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
         .vertex(vector3fArr[2].x, vector3fArr[2].y, 0.0F)
         .element("Texture", VertexElementType.FLOAT, new Float[]{1.0F, 1.0F})
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
         .vertex(vector3fArr[3].x, vector3fArr[3].y, 0.0F)
         .element("Texture", VertexElementType.FLOAT, new Float[]{1.0F, 0.0F})
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor});
   }

   @Api8(event = EventSub18.class)
   public void getMc() {
      if ((Boolean)this.smoothMoving.getValue()) {
         this.float1 = MathHelper.lerp(0.3F * (1.1F - (Float)this.smoothness.getValue()), this.float1, mc.gameRenderer.getCamera().getYaw());
         this.float2 = MathHelper.lerp(0.1F * (1.1F - (Float)this.smoothness.getValue()), this.float2, mc.gameRenderer.getCamera().getPitch());
      } else {
         this.float1 = mc.gameRenderer.getCamera().getYaw();
         this.float2 = mc.gameRenderer.getCamera().getPitch();
      }

      float[] fltArr2 = new float[]{mc.getWindow().getFramebufferWidth() / 2.0F, mc.getWindow().getFramebufferHeight() / 2.0F};
      MeshBuilder[] meshBuilderArr = new MeshBuilder[]{
         Mesh.builder(DrawMode.QUADS, CustomVertexFormats.POSITION_TEXTURE_COLOR), Mesh.builder(DrawMode.QUADS, CustomVertexFormats.POSITION_TEXTURE_COLOR)
      };
      RenderColor renderColor = RenderColor.of((Color)this.color.getValue());

      for (PlayerEntity playerEntity : mc.world.getPlayers()) {
         if (playerEntity != mc.player) {
            float[] fltArr = EntityUtils.getVector3f10(
               mc.gameRenderer.getCamera().getCameraPos().toVector3f(), new float[]{this.float1, 0.0F}, EntityUtils.withClass_12973(playerEntity).toVector3f()
            );
            fltArr[0] -= this.float1;
            this.handleMeshBuilder(meshBuilderArr, playerEntity, fltArr2, fltArr[0], renderColor);
         }
      }

      Mesh[] meshArr = new Mesh[]{meshBuilderArr[0].buildNullable(), meshBuilderArr[1].buildNullable()};
      if (meshArr[0] != null) {
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION_TEXTURE_COLOR);
         CometRenderer.applyShaderColorUniform();
         CRM.applyMatrixUniform();
         CometRenderer.getCurrentProgram().getSampler(0).set(((Arrow1Body)this.texture.getValue()).texture);
         CometRenderer.setBlend(SrcFactor.SRC_ALPHA, DstFactor.ONE);
         CometRenderer.draw(meshArr[0]);
         CometRenderer.draw(meshArr[1]);
         CometRenderer.setDefaultBlend();
      }
   }
}
