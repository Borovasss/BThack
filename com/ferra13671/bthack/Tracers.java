package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.CustomVertexElementTypes;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.vertex.DrawMode;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.ferra13671.cometrenderer.vertex.mesh.MeshBuilder;
import com.mojang.blaze3d.opengl.GlStateManager;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

@ModuleInfo(name = "Tracers", category = "render")
public class Tracers extends Module {
   public final transient BooleanSetting players = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Players"))
         .value(true))
      .build();
   public final transient ColorSetting playersColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Players Color"))
            .value(Color.WHITE))
         .visibleSupplier(this.players::getValue))
      .build();
   public final transient BooleanSetting items = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Items"))
         .value(true))
      .build();
   public final transient ColorSetting itemsColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
               .name("Items Color"))
            .value(new Color(150, 150, 255)))
         .visibleSupplier(this.items::getValue))
      .build();
   public final transient ListSetting mobsCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Mobs"))
         .id("mobs-category"))
      .build();
   public final transient BooleanSetting hostiles = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Hostiles"))
            .value(true))
         .onlyIf(this.mobsCategory))
      .build();
   public final transient ColorSetting hostilesColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
                  .name("Hostiles Color"))
               .value(new Color(212, 235, 43)))
            .visibleSupplier(this.hostiles::getValue))
         .onlyIf(this.mobsCategory))
      .build();
   public final transient BooleanSetting passives = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Passives"))
            .value(true))
         .onlyIf(this.mobsCategory))
      .build();
   public final transient ColorSetting passivesColor = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
                  .name("Passives Color"))
               .value(new Color(176, 255, 87)))
            .visibleSupplier(this.passives::getValue))
         .onlyIf(this.mobsCategory))
      .build();

   private RenderColor withClass_1297(Entity entity) {
      if ((Boolean)this.players.getValue() && entity instanceof PlayerEntity) {
         return RenderColor.of((Color)this.playersColor.getValue());
      } else if ((Boolean)this.items.getValue() && entity instanceof ItemEntity) {
         return RenderColor.of((Color)this.itemsColor.getValue());
      } else if ((Boolean)this.hostiles.getValue() && entity instanceof HostileEntity) {
         return RenderColor.of((Color)this.hostilesColor.getValue());
      } else {
         return this.passives.getValue() && entity instanceof PassiveEntity ? RenderColor.of((Color)this.passivesColor.getValue()) : null;
      }
   }

   @Api8(event = EventSub7.class)
   public void runVoid() {
      MeshBuilder meshBuilder = Mesh.builder(DrawMode.LINES, VertexFormat.POSITION_COLOR);
      Vector3f vector3f2 = this.applyVector3f2();
      Vector3f vector3f3 = mc.gameRenderer.getCamera().getCameraPos().toVector3f();

      for (Entity entity : mc.world.getEntities()) {
         if (entity != mc.player) {
            RenderColor renderColor = this.withClass_1297(entity);
            Vector3f vector3f = EntityUtils.withClass_12973(entity).add(0.0, entity.getBoundingBox().getLengthY() / 2.0, 0.0).toVector3f().sub(vector3f3);
            if (renderColor != null) {
               meshBuilder.vertex(vector3f2.x, vector3f2.y, vector3f2.z)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor})
                  .vertex(vector3f.x, vector3f.y, vector3f.z)
                  .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{renderColor});
            }
         }
      }

      Mesh mesh = meshBuilder.buildNullable();
      if (mesh != null) {
         GlStateManager._disableDepthTest();
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION_COLOR);
         CometRenderer.applyShaderColorUniform();
         CRM.applyMatrixUniform();
         CometRenderer.draw(mesh);
      }
   }

   private Vector3f applyVector3f2() {
      Vector4f vector4f = new Vector4f(0.0F, 0.0F, -1.0F, 1.0F);
      Vector4f vector4f2 = new Matrix4f(RendererInit.matrix4f3).invert().transform(vector4f);
      return new Vector3f(vector4f2.x, vector4f2.y, vector4f2.z);
   }
}
