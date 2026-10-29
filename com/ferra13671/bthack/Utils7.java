package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.CustomVertexElementTypes;
import com.ferra13671.cometrenderer.vertex.element.VertexElementType;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;

public final class Utils7 {
   public static transient VertexFormat vertexFormat1 = VertexFormat.builder()
      .element("Color", CustomVertexElementTypes.RENDER_COLOR, 1)
      .element("Outline Color", CustomVertexElementTypes.RENDER_COLOR, 1)
      .element("Outline Size", VertexElementType.FLOAT, 1)
      .element("Rect Position", VertexElementType.FLOAT, 2)
      .element("Half Size", VertexElementType.FLOAT, 2)
      .element("Radius", VertexElementType.FLOAT, 1)
      .build();

   Utils7() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
