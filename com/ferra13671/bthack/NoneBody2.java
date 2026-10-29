package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RenderColor;
import java.awt.Color;
import java.util.function.BiFunction;
import net.minecraft.entity.player.PlayerEntity;

public enum NoneBody2 implements MinecraftInstance {
   None((playerEntity, renderColor) -> renderColor),
   YourTeam((playerEntity, renderColor) -> mc.player.isTeammate(playerEntity) ? RenderColor.of(new Color(playerEntity.getTeamColorValue())) : renderColor),
   AllTeams((playerEntity, renderColor) -> RenderColor.of(new Color(playerEntity.getTeamColorValue())));

   public final BiFunction<PlayerEntity, RenderColor, RenderColor> colorFunction;

   private static NoneBody2[] $values() {
      return new NoneBody2[]{None, YourTeam, AllTeams};
   }

   NoneBody2(BiFunction<PlayerEntity, RenderColor, RenderColor> biFunction) {
      this.colorFunction = biFunction;
   }
}
