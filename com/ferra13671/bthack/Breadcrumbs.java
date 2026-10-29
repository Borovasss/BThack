package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.vertex.DrawMode;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.ferra13671.cometrenderer.vertex.mesh.MeshBuilder;
import com.mojang.blaze3d.opengl.GlStateManager;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.joml.Vector3f;

@ModuleInfo(name = "Breadcrumbs", category = "render")
public class Breadcrumbs extends Module {
   public final transient NumberSetting maxVertices = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Max vertices"))
         .value(1000.0F))
      .min(200.0F)
      .max(15000.0F)
      .decimalPlaces(0)
      .build();
   public final transient NumberSetting updateDelay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Update delay"))
         .value(0.0F))
      .min(0.0F)
      .max(20.0F)
      .build();
   public final transient ColorSetting color = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder().name("Color"))
         .value(new Color(1.0F, 1.0F, 1.0F, 1.0F)))
      .build();
   public final transient NumberSetting height = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Height"))
         .value(0.0F))
      .min(0.0F)
      .max(2.0F)
      .build();
   public final transient BooleanSetting depthTest = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Depth test"))
         .value(true))
      .build();
   transient String string1 = "";
   final transient List<Vector3f> list1 = new CopyOnWriteArrayList<>();
   transient int int1 = 0;

   @Api8(event = EventSub7.class)
   public void runVoid() {
      MeshBuilder meshBuilder = Mesh.builder(DrawMode.LINE_STRIP, VertexFormat.POSITION);
      Vector3f vector3f2 = mc.gameRenderer.getCamera().getCameraPos().toVector3f();

      for (Vector3f vector3f : this.list1) {
         meshBuilder.vertex(vector3f.x - vector3f2.x, vector3f.y - vector3f2.y + (Float)this.height.getValue(), vector3f.z - vector3f2.z);
      }

      Mesh mesh = meshBuilder.buildNullable();
      if (mesh != null) {
         if ((Boolean)this.depthTest.getValue()) {
            GlStateManager._enableDepthTest();
            GlStateManager._depthMask(false);
         } else {
            GlStateManager._disableDepthTest();
         }

         CometRenderer.getShaderColor().push();
         CometRenderer.getShaderColor().setColor(RenderColor.of((Color)this.color.getValue()).toVector4f());
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION);
         CometRenderer.applyShaderColorUniform();
         CRM.applyMatrixUniform();
         CometRenderer.draw(mesh);
         CometRenderer.getShaderColor().pop();
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized()) {
         this.list1.clear();
      } else {
         if (!this.string1.equals(mc.world.getRegistryKey().getValue().getPath())) {
            this.string1 = mc.world.getRegistryKey().getValue().getPath();
            this.list1.clear();
         }

         Vector3f vector3f = new Vector3f((float)mc.player.getX(), (float)mc.player.getY() + 0.01F, (float)mc.player.getZ());
         if (this.list1.isEmpty() || !((Vector3f)this.list1.getLast()).equals(vector3f)) {
            if (this.int1 <= 0) {
               this.list1.addLast(vector3f);
               this.int1 = ((Float)this.updateDelay.getValue()).intValue();
            }

            this.int1--;
         }

         if (this.list1.size() > (Float)this.maxVertices.getValue()) {
            this.list1.removeFirst();
         }
      }
   }

   @Override
   public void onEnable3() {
      this.list1.clear();
      super.onEnable3();
   }
}
