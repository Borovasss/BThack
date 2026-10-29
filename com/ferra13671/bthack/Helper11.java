package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.buffer.framebuffer.Framebuffer;
import com.ferra13671.cometrenderer.glsl.GLProgram;
import com.ferra13671.gltextureutils.Pair;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(status = Status.MAINTAINED)
public class Helper11 {
   final transient GLProgram gLProgram1;
   final transient List<Pair<Integer, Function<MeshFramebuffers, Framebuffer>>> list1;
   final transient Function<MeshFramebuffers, Framebuffer> function1;
   final transient Consumer<GLProgram> consumer1;

   public Helper11(GLProgram gLProgram, List<Pair<Integer, Function<MeshFramebuffers, Framebuffer>>> list, Function<MeshFramebuffers, Framebuffer> function, Consumer<GLProgram> consumer) {
      this.gLProgram1 = gLProgram;
      this.list1 = list;
      this.function1 = function;
      this.consumer1 = consumer;
   }

   public void handleMeshFramebuffers(MeshFramebuffers meshFramebuffers) {
      Framebuffer framebuffer = (Framebuffer)this.function1.apply(meshFramebuffers);
      framebuffer.bind(true);
      CometRenderer.setCurrentProgram(this.gLProgram1);

      for (Pair<Integer, Function<MeshFramebuffers, Framebuffer>> pair : this.list1) {
         this.gLProgram1.getSampler((Integer)pair.getLeft()).set(((Framebuffer)((Function)pair.getRight()).apply(meshFramebuffers)).getColorTextureId());
      }

      this.consumer1.accept(this.gLProgram1);
      CometRenderer.draw(meshFramebuffers.getMesh(), false);
   }

   public static BuilderSub1 builder() {
      return new BuilderSub1();
   }
}
