package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.buffer.framebuffer.Framebuffer;
import com.ferra13671.cometrenderer.vertex.mesh.IMesh;
import java.util.HashMap;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(status = Status.MAINTAINED)
public record MeshFramebuffers(IMesh mesh, HashMap<String, Framebuffer> framebuffers) {

   public HashMap<String, Framebuffer> getFramebuffers() {
      return this.framebuffers;
   }

   public IMesh getMesh() {
      return this.mesh;
   }
}
