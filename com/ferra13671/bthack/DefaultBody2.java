package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RenderColor;
import java.awt.Color;
import java.util.function.BiFunction;
import net.minecraft.entity.player.PlayerEntity;

public enum DefaultBody2 {
   Default((playerESP, playerEntity) -> RenderColor.of((Color)playerESP.color.getValue())),
   Teams((playerESP, playerEntity) -> RenderColor.of(new Color(playerEntity.getTeamColorValue()))),
   Gradient(
      (playerESP, playerEntity) -> {
         float flt = 0.5F
            + (float)(
               Math.sin(Math.toRadians(360.0F * ((float)playerESP.helper45.getPassedTime() / 4000.0F) * (Float)playerESP.gradientSpeed.getValue())) / 2.0
            );
         return Utils10.withRenderColor(RenderColor.of((Color)playerESP.firstColor.getValue()), RenderColor.of((Color)playerESP.secondColor.getValue()), flt);
      }
   );

   public final BiFunction<PlayerESP, PlayerEntity, RenderColor> colorFunction;

   DefaultBody2(BiFunction<PlayerESP, PlayerEntity, RenderColor> biFunction) {
      this.colorFunction = biFunction;
   }

   private static DefaultBody2[] $values() {
      return new DefaultBody2[]{Default, Teams, Gradient};
   }
}
