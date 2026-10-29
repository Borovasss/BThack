package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.buffer.framebuffer.Framebuffer;
import com.ferra13671.cometrenderer.buffer.framebuffer.FramebufferImpl;
import com.ferra13671.cometrenderer.buffer.framebuffer.FramebufferInfo;
import com.ferra13671.cometrenderer.vertex.DrawMode;
import com.ferra13671.cometrenderer.vertex.format.VertexFormat;
import com.ferra13671.cometrenderer.vertex.mesh.IMeshBuilder;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(status = Status.MAINTAINED)
public class Helper39 {
   final transient List<ClearColor> list1;
   final transient List<Helper11> list2;

   public void handleInt(int num, int num2) {
      HashMap<String, Framebuffer> hashMap = new HashMap<>();

      for (ClearColor clearColor : this.list1) {
         FramebufferImpl framebufferImpl = new FramebufferImpl(
            FramebufferInfo.builder().name(clearColor.name()).useDepth(false).width(num).height(num2).clearColor(new Color(clearColor.getClearcolor())).build()
         );
         framebufferImpl.clearColor();
         hashMap.put(clearColor.name(), framebufferImpl);
      }

      MeshFramebuffers meshFramebuffers = new MeshFramebuffers(CometRenderer.createMesh(DrawMode.QUADS, VertexFormat.POSITION, (iMeshBuilder) -> Helper39.handleInt1(num, num2, iMeshBuilder)), hashMap);

      for (Helper11 helper11 : this.list2) {
         helper11.handleMeshFramebuffers(meshFramebuffers);
      }

      meshFramebuffers.getMesh().close();

      for (Framebuffer framebuffer : meshFramebuffers.getFramebuffers().values()) {
         framebuffer.delete();
      }
   }

   public static PostEffectRenderer builder() {
      return new PostEffectRenderer();
   }

   public Helper39(List<ClearColor> list, List<Helper11> list2) {
      this.list1 = list;
      this.list2 = list2;
   }

   private static void handleInt1(int num, int num2, IMeshBuilder iMeshBuilder) {
      iMeshBuilder.vertex(0.0F, num, 0.0F);
      iMeshBuilder.vertex(num2, num, 0.0F);
      iMeshBuilder.vertex(num2, 0.0F, 0.0F);
      iMeshBuilder.vertex(0.0F, 0.0F, 0.0F);
   }
}
