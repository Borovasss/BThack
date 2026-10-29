package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.resource.Resource;
import com.ferra13671.cometrenderer.glsl.shader.GLShader;
import com.ferra13671.cometrenderer.glsl.shader.ShaderType;

public class ShaderManager {
   public final transient GLShader gLShader1 = RendererInit.cometLoader1
      .createShaderBuilder()
      .info("rounded-outlined.vertex", new Resource("bthack-client", "shaders/impl/rounded-outlined.vert"), ShaderType.Vertex)
      .build();
   public final transient GLShader gLShader2 = RendererInit.cometLoader1
      .createShaderBuilder()
      .info("rounded-outlined.fragment", new Resource("bthack-client", "shaders/impl/rounded-outlined.frag"), ShaderType.Fragment)
      .build();
   public final transient GLShader gLShader3 = RendererInit.cometLoader1
      .createShaderBuilder()
      .info("rounded-shadow.fragment", new Resource("bthack-client", "shaders/impl/rounded-shadow.frag"), ShaderType.Fragment)
      .build();
   public final transient GLShader gLShader4 = RendererInit.cometLoader1
      .createShaderBuilder()
      .info("blit-with-alpha.fragment", new Resource("bthack-client", "shaders/impl/blit-with-alpha.frag"), ShaderType.Fragment)
      .sampler("u_Texture")
      .build();
}
