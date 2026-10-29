package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.buffer.framebuffer.Framebuffer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.batch.AbstractPrimitiveBatch;
import com.ferra13671.cometrenderer.vertex.DrawMode;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import org.joml.Matrix4f;

public class AbstractPrimitiveBatchSub1 extends AbstractPrimitiveBatch {
   final transient Framebuffer framebuffer1;
   final transient boolean boolean1;

   public AbstractPrimitiveBatchSub1 withFloat4(float flt, float flt2, float flt3, float flt4, Matrix4f matrix4f) {
      return this.withFloat(flt, flt2, flt + flt3, flt2 + flt4, matrix4f);
   }

   public AbstractPrimitiveBatchSub1(Framebuffer framebuffer, boolean bool, Runnable runnable) {
      this(framebuffer, bool);
      this.preDrawRunnable = runnable;
   }

   public AbstractPrimitiveBatchSub1 withFloat(float flt, float flt2, float flt3, float flt4) {
      this.meshBuilder.vertex(flt, flt2, 0.0F);
      this.meshBuilder.vertex(flt, flt4, 0.0F);
      this.meshBuilder.vertex(flt3, flt4, 0.0F);
      this.meshBuilder.vertex(flt3, flt2, 0.0F);
      return this;
   }

   public AbstractPrimitiveBatchSub1(Framebuffer framebuffer, boolean bool) {
      super(Mesh.builder(DrawMode.QUADS, VertexFormat.POSITION));
      this.framebuffer1 = framebuffer;
      this.boolean1 = bool;
   }

   public AbstractPrimitiveBatchSub1 withFloat3(float flt, float flt2, float flt3, float flt4) {
      return this.withFloat(flt, flt2, flt + flt3, flt2 + flt4);
   }

   protected void draw() {
      CometRenderer.setCurrentProgram(this.boolean1 ? RendererInit.helper14.gLProgram3 : CRM.getPrograms().BLIT);
      CometRenderer.applyShaderColorUniform();
      CRM.applyMatrixUniform();
      CometRenderer.getCurrentProgram().getSampler(0).set(this.framebuffer1.getColorTextureId());
      CometRenderer.draw(this.mesh, false);
   }

   public AbstractPrimitiveBatchSub1 withFloat(float flt, float flt2, float flt3, float flt4, Matrix4f matrix4f) {
      this.meshBuilder.vertex(matrix4f, flt, flt2, 0.0F);
      this.meshBuilder.vertex(matrix4f, flt, flt4, 0.0F);
      this.meshBuilder.vertex(matrix4f, flt3, flt4, 0.0F);
      this.meshBuilder.vertex(matrix4f, flt3, flt2, 0.0F);
      return this;
   }
}
