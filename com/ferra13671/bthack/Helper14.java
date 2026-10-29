package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.glsl.GLProgram;
import com.ferra13671.cometrenderer.glsl.GLProgramBuilder;
import com.ferra13671.cometrenderer.glsl.GLProgramSnippet;
import com.ferra13671.cometrenderer.minecraft.program.DefaultShaders;

public class Helper14 {
   public final transient GLProgram gLProgram1 = new GLProgramBuilder(new GLProgramSnippet[0])
      .name("rounded-outlined")
      .shader(RendererInit.shaderManager.gLShader1)
      .shader(RendererInit.shaderManager.gLShader2)
      .build();
   public final transient GLProgram gLProgram2 = new GLProgramBuilder(new GLProgramSnippet[0])
      .name("rounded-outlined")
      .shader(DefaultShaders.ROUNDED_RECT_VERTEX)
      .shader(RendererInit.shaderManager.gLShader3)
      .build();
   public final transient GLProgram gLProgram3 = new GLProgramBuilder(new GLProgramSnippet[0])
      .name("blit-with-alpha")
      .shader(DefaultShaders.POSITION_VERTEX)
      .shader(RendererInit.shaderManager.gLShader4)
      .build();
}
