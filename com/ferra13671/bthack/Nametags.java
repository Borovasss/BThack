package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedBlurBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.RenderText;
import com.ferra13671.cometrenderer.minecraft.batch.impl.text.TextBatch;
import com.ferra13671.cometrenderer.minecraft.font.TTFFont;
import java.awt.Color;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;

@ModuleInfo(name = "Nametags", category = "render")
public class Nametags extends Module {
   public final transient NumberSetting height = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Height"))
         .value(0.3F))
      .min(0.0F)
      .max(1.0F)
      .decimalPlaces(1)
      .build();
   public final transient BooleanSetting blur = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Blur"))
         .value(false))
      .build();
   public final transient BooleanSetting displayHealth = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Display Health"))
         .value(false))
      .build();
   public final transient BooleanSetting displayPing = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Display Ping"))
         .value(false))
      .build();
   public final transient BooleanSetting teamColor = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Team Color"))
         .value(true))
      .build();

   @Api8(event = EventSub18.class)
   public void getMc() {
      TTFFont tTFFont = FontStyle.REGULAR.getOrLoad(26);
      float flt11 = tTFFont.getTextWidth("|") + 4.0F;

      for (PlayerEntity playerEntity : mc.world.getPlayers()) {
         if (playerEntity != mc.player) {
            float[] fltArr = RendererInit.getClass_2437(
               EntityUtils.withClass_12973(playerEntity)
                  .add(0.0, playerEntity.getBoundingBox().getLengthY() + ((Float)this.height.getValue()).floatValue(), 0.0),
               false
            );
            if (fltArr != null) {
               String string = PlayerEntityUtils.isClass_16573(playerEntity)
                  ? PlayerEntityUtils.getClass_1657(playerEntity)
                  : (PlayerEntityUtils.isClass_16571(playerEntity) ? PlayerEntityUtils.getClass_16572(playerEntity) : playerEntity.getName().getString());
               float flt = tTFFont.getTextWidth(string);
               String string2 = EntityUtils.decimalPlaces(playerEntity.getHealth(), 1).toString().concat("hp");
               float flt2 = tTFFont.getTextWidth(string2);
               PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(playerEntity.getUuid());
               int num = playerListEntry != null ? playerListEntry.getLatency() : 0;
               String string3 = String.valueOf(num).concat("ms.");
               float flt3 = tTFFont.getTextWidth(string3);
               float flt4 = 7.0F;
               float flt5 = flt + flt4 * 2.0F;
               if ((Boolean)this.displayHealth.getValue()) {
                  flt5 += flt2 + flt11;
               }

               if ((Boolean)this.displayPing.getValue()) {
                  flt5 += flt3 + flt11;
               }

               float flt6 = fltArr[0] - flt5 / 2.0F;
               float flt7 = fltArr[0] + flt5 / 2.0F;
               float flt8 = fltArr[1] - 16.0F;
               float flt9 = fltArr[1] + 16.0F;
               float flt10 = fltArr[1] - tTFFont.getTextHeight() / 2.0F;
               if ((Boolean)this.blur.getValue()) {
                  new RoundedBlurBatch(RendererInit.roundedBlurBatch).rectPositioned(flt6, flt8, flt7, flt9, 10.0F).build().tryDraw().close();
               }

               new RoundedRectBatch().rectPositioned(flt6, flt8, flt7, flt9, 10.0F, RectColors.oneColor(Utils4.renderColor1)).build().tryDraw().close();
               TextBatch textBatch = new TextBatch(tTFFont)
                  .text(
                     new RenderText(string, this.displayPing.getValue() ? flt7 - flt4 - flt3 - flt11 - flt : flt7 - flt4 - flt, flt10)
                        .withColor(
                           PlayerEntityUtils.isClass_16573(playerEntity)
                              ? Utils6.renderColor1
                              : (
                                 PlayerEntityUtils.isClass_16571(playerEntity)
                                    ? Utils6.renderColor2
                                    : (
                                       this.teamColor.getValue()
                                          ? Utils10.withRenderColor(RenderColor.of(new Color(playerEntity.getTeamColorValue())), RenderColor.WHITE, 0.3F)
                                          : RenderColor.WHITE
                                    )
                              )
                        )
                  );
               if ((Boolean)this.displayHealth.getValue()) {
                  textBatch.text(new RenderText("|", flt6 + flt4 + flt2, flt10).withColor(Utils4.renderColor18))
                     .text(new RenderText(string2, flt6 + flt4, flt10).withColor(Utils10.withFloat3(playerEntity.getHealth() / playerEntity.getMaxHealth())));
               }

               if ((Boolean)this.displayPing.getValue()) {
                  textBatch.text(new RenderText("|", flt7 - flt4 - flt3 - flt11, flt10).withColor(Utils4.renderColor18))
                     .text(new RenderText(string3, flt7 - flt4 - flt3, flt10).withColor(Utils10.withFloat3(1.0F - num / 500.0F)));
               }

               textBatch.build().tryDraw().close();
            }
         }
      }
   }
}
