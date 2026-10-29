package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.CustomVertexElementTypes;
import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.AbstractPrimitiveBatch;
import com.ferra13671.cometrenderer.vertex.DrawMode;
import com.ferra13671.cometrenderer.vertex.element.VertexElementType;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;

public class AbstractPrimitiveBatchHelper extends AbstractPrimitiveBatch implements MinecraftInstance {
   public AbstractPrimitiveBatchHelper withFloat2(
      float flt, float flt2, float flt3, float flt4, float flt5, float flt6, RectColors rectColors, RectColors rectColors2
   ) {
      return this.withFloat(flt, flt2, flt + flt3, flt2 + flt4, flt5, flt6, rectColors, rectColors2);
   }

   protected void draw() {
      CometRenderer.setCurrentProgram(RendererInit.helper14.gLProgram1);
      CometRenderer.applyShaderColorUniform();
      CRM.applyMatrixUniform();
      CometRenderer.draw(this.mesh, false);
   }

   public AbstractPrimitiveBatchHelper withFloat(
      float flt, float flt2, float flt3, float flt4, float flt5, float flt6, RectColors rectColors, RectColors rectColors2
   ) {
      float[] fltArr = new float[]{(flt3 - flt) / 2.0F, (flt4 - flt2) / 2.0F};
      float[] fltArr2 = new float[]{flt + fltArr[0], flt2 + fltArr[1]};
      flt -= 2.0F;
      flt3 += 2.0F;
      flt2 -= 2.0F;
      flt4 += 2.0F;
      this.meshBuilder
         .vertex(flt, flt2, 0.0F)
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors.x1y1Color()})
         .element("Outline Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors2.x1y1Color()})
         .element("Outline Size", VertexElementType.FLOAT, new Float[]{flt6})
         .element("Rect Position", VertexElementType.FLOAT, new Float[]{fltArr2[0], fltArr2[1]})
         .element("Half Size", VertexElementType.FLOAT, new Float[]{fltArr[0], fltArr[1]})
         .element("Radius", VertexElementType.FLOAT, new Float[]{flt5});
      this.meshBuilder
         .vertex(flt, flt4, 0.0F)
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors.x1y2Color()})
         .element("Outline Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors2.x1y2Color()})
         .element("Outline Size", VertexElementType.FLOAT, new Float[]{flt6})
         .element("Rect Position", VertexElementType.FLOAT, new Float[]{fltArr2[0], fltArr2[1]})
         .element("Half Size", VertexElementType.FLOAT, new Float[]{fltArr[0], fltArr[1]})
         .element("Radius", VertexElementType.FLOAT, new Float[]{flt5});
      this.meshBuilder
         .vertex(flt3, flt4, 0.0F)
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors.x2y2Color()})
         .element("Outline Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors2.x2y2Color()})
         .element("Outline Size", VertexElementType.FLOAT, new Float[]{flt6})
         .element("Rect Position", VertexElementType.FLOAT, new Float[]{fltArr2[0], fltArr2[1]})
         .element("Half Size", VertexElementType.FLOAT, new Float[]{fltArr[0], fltArr[1]})
         .element("Radius", VertexElementType.FLOAT, new Float[]{flt5});
      this.meshBuilder
         .vertex(flt3, flt2, 0.0F)
         .element("Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors.x2y1Color()})
         .element("Outline Color", CustomVertexElementTypes.RENDER_COLOR, new RenderColor[]{rectColors2.x2y1Color()})
         .element("Outline Size", VertexElementType.FLOAT, new Float[]{flt6})
         .element("Rect Position", VertexElementType.FLOAT, new Float[]{fltArr2[0], fltArr2[1]})
         .element("Half Size", VertexElementType.FLOAT, new Float[]{fltArr[0], fltArr[1]})
         .element("Radius", VertexElementType.FLOAT, new Float[]{flt5});
      return this;
   }

   public AbstractPrimitiveBatchHelper(Runnable runnable) {
      this();
      this.preDrawRunnable = runnable;
   }

   public AbstractPrimitiveBatchHelper() {
      super(Mesh.builder(DrawMode.QUADS, Utils7.vertexFormat1));
   }
}
