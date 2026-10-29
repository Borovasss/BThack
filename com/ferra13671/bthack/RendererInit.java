package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometLoader;
import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.State;
import com.ferra13671.cometrenderer.State.BooleanState;
import com.ferra13671.cometrenderer.State.TextureState;
import com.ferra13671.cometrenderer.minecraft.CRMInstance;
import com.ferra13671.cometrenderer.minecraft.CustomDrawMode;
import com.ferra13671.cometrenderer.minecraft.blur.BlurConfig;
import com.ferra13671.cometrenderer.minecraft.blur.BlurProvider;
import com.ferra13671.cometrenderer.minecraft.event.RenderHudCallback;
import com.ferra13671.cometrenderer.minecraft.event.RenderWorldCallback;
import com.ferra13671.cometrenderer.scissor.ScissorRect;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.ferra13671.cometrenderer.vertex.mesh.MeshBuilder;
import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.ferra13671.gltextureutils.loader.TextureLoader;
import com.mojang.blaze3d.opengl.GlStateManager;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public final class RendererInit implements MinecraftInstance {
   static final transient List list1 = new CopyOnWriteArrayList();
   public static final transient CometLoader cometLoader1 = new CometLoaderSub1();
   public static final transient TextureLoader textureLoader = new Helper43();
   public static transient CRMInstance cRMInstance1;
   public static transient ShaderManager shaderManager;
   public static transient Helper14 helper14;
   public static transient CategoryIcons config;
   public static transient BlurProvider roundedBlurBatch;
   public static transient GuiRenderState guiRenderState;
   public static transient GuiRenderer guiRenderer;
   public static transient Matrix4f matrix4f1;
   public static transient Matrix4f matrix4f2;
   public static transient Matrix4f matrix4f3;
   public static transient Framebuffer framebuffer;
   public static transient boolean boolean1 = false;

   private static void setShaderManager() {
      shaderManager = new ShaderManager();
   }

   RendererInit() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private static Integer getInteger() {
      return 1;
   }

   static final class Helper22 implements BooleanState {
      @OverriddenMethod
      public void disable() {
         GlStateManager._disableScissorTest();
      }

      @OverriddenMethod
      public void enable() {
         GlStateManager._enableScissorTest();
      }
   }

   static final class Helper25 implements TextureState {
      @OverriddenMethod
      public void activeTexture(int num) {
         GlStateManager._activeTexture(num);
      }

      @OverriddenMethod
      public void bindTexture(int num) {
         GlStateManager._bindTexture(num);
      }
   }

   static final class Helper30 implements BooleanState {
      @OverriddenMethod
      public void disable() {
         GlStateManager._disableBlend();
      }

      @OverriddenMethod
      public void enable() {
         GlStateManager._enableBlend();
      }
   }

   public static void handleInt1(int num, int num2, int num3, int num4) {
      cRMInstance1.pushScissor(new ScissorRect(num, num2, num3, num4));
   }

   private static void runVoid2() {
      cRMInstance1 = new CRMInstance(RendererInit::getInteger);
      State.BLEND = new Helper30();
      State.SCISSOR = new Helper22();
      State.TEXTURE = new Helper25();
   }

   private static void setHelper14() {
      helper14 = new Helper14();
   }

   private static void setConfig() {
      config = new CategoryIcons();
   }

   private static void runVoid3() {
      RenderHudCallback.EVENT.register(RendererInit::getRoundedBlurBatch);
      RenderWorldCallback.EVENT.register(RendererInit::runVoid10);
   }

   public static Mesh withList4(List<Box> list, Vec3d vec3d) {
      MeshBuilder meshBuilder = Mesh.builder(CustomDrawMode.CUBE, VertexFormat.POSITION);

      for (Box box2 : list) {
         Box box = box2.offset(vec3d.multiply(-1.0, -1.0, -1.0));
         meshBuilder.vertex((float)box.maxX, (float)box.minY, (float)box.minZ)
            .vertex((float)box.maxX, (float)box.minY, (float)box.maxZ)
            .vertex((float)box.minX, (float)box.minY, (float)box.maxZ)
            .vertex((float)box.minX, (float)box.minY, (float)box.minZ)
            .vertex((float)box.minX, (float)box.maxY, (float)box.minZ)
            .vertex((float)box.minX, (float)box.maxY, (float)box.maxZ)
            .vertex((float)box.maxX, (float)box.maxY, (float)box.maxZ)
            .vertex((float)box.maxX, (float)box.maxY, (float)box.minZ);
      }

      return meshBuilder.buildNullable();
   }

   public static StageManager applyStageManager5() {
      StageManager stageManager = StageManager.of("Init Renderer", RendererInit::runVoid2);
      stageManager.push(StageManager.of("Compile shaders", RendererInit::setShaderManager));
      stageManager.push(StageManager.of("Compile programs", RendererInit::setHelper14));
      stageManager.push(StageManager.of("Load textures", RendererInit::setConfig));
      stageManager.push(StageManager.of("Load blur provider", RendererInit::setRoundedBlurBatch));
      stageManager.push(StageManager.of("Register render callbacks", RendererInit::runVoid3));
      return stageManager;
   }

   public static void runLater(Runnable runnable) {
      list1.add(runnable);
   }

   public static void runVoid6() {
      synchronized (list1) {
         if (!list1.isEmpty()) {
            List<Runnable> list = List.copyOf(list1);
            list1.clear();

            for (Runnable runnable : list) {
               runnable.run();
            }
         }
      }
   }

   public static float[] getClass_2437(Vec3d vec3d, boolean bool) {
      Vec3d vec3d2 = withClass_24311(vec3d);
      return !bool && isClass_2439(vec3d2) ? null : new float[]{(float)vec3d2.x, (float)vec3d2.y};
   }

   public static void runVoid8() {
      CometRenderer.getScissorStack().pop();
   }

   public static boolean isClass_2439(Vec3d vec3d) {
      return !(vec3d.z > 0.0) || !(vec3d.z < 1.0);
   }

   private static void runVoid10() {
      GL11.glEnable(2848);
      BThackClient.getInstance().getEventBus().register(new EventSub7());
   }

   private static Vec3d withClass_24311(Vec3d vec3d) {
      Camera camera = mc.getEntityRenderDispatcher().camera;
      int num = mc.getWindow().getFramebufferHeight();
      int[] numArr = new int[4];
      GL11.glGetIntegerv(2978, numArr);
      Vector3f vector3f = new Vector3f();
      double dbl = vec3d.x - camera.getCameraPos().x;
      double dbl2 = vec3d.y - camera.getCameraPos().y;

      double dbl3;
      for (dbl3 = vec3d.z - camera.getCameraPos().z; Math.abs(dbl) > 600.0 || Math.abs(dbl2) > 600.0 || Math.abs(dbl3) > 600.0; dbl3 /= 1.5) {
         dbl /= 1.5;
         dbl2 /= 1.5;
      }

      Vector4f vector4f = new Vector4f((float)dbl, (float)dbl2, (float)dbl3, 1.0F).mul(matrix4f1);
      Matrix4f matrix4f = new Matrix4f(RendererInit.matrix4f2);
      Matrix4f matrix4f2 = new Matrix4f(matrix4f3);
      matrix4f.mul(matrix4f2).project(vector4f.x(), vector4f.y(), vector4f.z(), numArr, vector3f);
      return new Vec3d(vector3f.x, num - vector3f.y, vector3f.z);
   }

   private static void setRoundedBlurBatch() {
      roundedBlurBatch = new BlurProvider(BlurConfig.DEFAULT);
   }

   private static void getRoundedBlurBatch() {
      boolean1 = false;
      cRMInstance1.setupUIMatrix();
      roundedBlurBatch.blurFrame();
      BThackClient.getInstance().getEventBus().register(new EventSub18());
   }
}
