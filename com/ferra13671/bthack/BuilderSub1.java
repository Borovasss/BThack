package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.buffer.framebuffer.Framebuffer;
import com.ferra13671.cometrenderer.glsl.GLProgram;
import com.ferra13671.cometrenderer.utils.Builder;
import com.ferra13671.gltextureutils.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(status = Status.MAINTAINED)
public class BuilderSub1 extends Builder {
   final transient List<Pair<Integer, Function<MeshFramebuffers, Framebuffer>>> list1 = new ArrayList<>();
   transient Function<MeshFramebuffers, Framebuffer> function1;
   transient GLProgram gLProgram1;
   transient Consumer<GLProgram> consumer1 = BuilderSub1::handleGLProgram1;

   public BuilderSub1 withFramebuffer2(Framebuffer framebuffer) {
      this.function1 = (meshFramebuffers) -> BuilderSub1.withFramebuffer4(framebuffer, meshFramebuffers);
      return this;
   }

   private static Framebuffer withFramebuffer(Framebuffer framebuffer, MeshFramebuffers meshFramebuffers) {
      return framebuffer;
   }

   public Helper11 build() {
      this.assertNotNull(this.function1, "output");
      this.assertNotNull(this.gLProgram1, "program");
      return new Helper11(this.gLProgram1, this.list1, this.function1, this.consumer1);
   }

   private static void handleGLProgram1(GLProgram gLProgram) {
   }

   private static Framebuffer withString2(String string, MeshFramebuffers meshFramebuffers) {
      return (Framebuffer)meshFramebuffers.getFramebuffers().get(string);
   }

   public BuilderSub1 withGLProgram(GLProgram gLProgram) {
      this.gLProgram1 = gLProgram;
      return this;
   }

   public BuilderSub1 withInt(int num, String string) {
      this.list1.add(new Pair<>(num, (meshFramebuffers) -> BuilderSub1.withString3(string, meshFramebuffers)));
      return this;
   }

   public BuilderSub1 withInt(int num, Framebuffer framebuffer) {
      this.list1.add(new Pair<>(num, (meshFramebuffers) -> BuilderSub1.withFramebuffer(framebuffer, meshFramebuffers)));
      return this;
   }

   public BuilderSub1 withConsumer(Consumer<GLProgram> consumer) {
      if (consumer != null) {
         this.consumer1 = consumer;
      }

      return this;
   }

   public BuilderSub1 withString(String string) {
      this.function1 = (meshFramebuffers) -> BuilderSub1.withString2(string, meshFramebuffers);
      return this;
   }

   public BuilderSub1() {
      super("program pass");
   }

   private static Framebuffer withString3(String string, MeshFramebuffers meshFramebuffers) {
      return (Framebuffer)meshFramebuffers.getFramebuffers().get(string);
   }


   private static Framebuffer withFramebuffer4(Framebuffer framebuffer, MeshFramebuffers meshFramebuffers) {
      return framebuffer;
   }
}
