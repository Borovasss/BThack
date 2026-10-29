package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public final class ChatUtils implements MinecraftInstance {
   static final transient RenderColor renderColor1 = Utils10.withRenderColor(Utils4.roundedRectBatch, RenderColor.WHITE, 0.65F);

   public static void handleString(String string, boolean bool) {
      handleString("BThack", string, Utils4.roundedRectBatch, renderColor1, bool);
   }

   public static void handleString1(String string) {
      if (!BThackClient.isInitialized()) {
         mc.getNetworkHandler().sendChatCommand(string);
      }
   }

   public static void handleClass_25612(Text text, boolean bool) {
      mc.getMessageHandler().onGameMessage(text, bool);
   }

   public static void handleString(String string) {
      handleString(string, false);
   }

   private static void handleString4(String string, String string2, RenderColor renderColor, RenderColor renderColor2, boolean bool) {
      handleString(string, string2, renderColor, renderColor2, bool);
   }

   ChatUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static void handleString(String string, String string2, RenderColor renderColor, RenderColor renderColor2, boolean bool) {
      if (!BThackClient.isInitialized()) {
         if (!RenderSystem.isOnRenderThread()) {
            RendererInit.runLater(() -> ChatUtils.handleString4(string, string2, renderColor, renderColor2, bool));
         } else {
            MutableText mutableText = Text.literal("");
            mutableText.append(Formatting.GRAY + "[");

            for (int num = 0; num < string.length(); num++) {
               float flt = (num + 1.0F) / string.length();
               TextColor textColor = TextColor.fromRgb(Utils10.getRenderColor2(Utils10.withRenderColor(renderColor, renderColor2, flt)));
               Text text = Text.literal(Character.toString(string.charAt(num))).setStyle(Style.EMPTY.withBold(true).withColor(textColor));
               mutableText.append(text);
            }

            mutableText.append(Formatting.GRAY + "] " + Formatting.WHITE + string2);
            handleClass_25612(mutableText, bool);
         }
      }
   }
}
