package com.ferra13671.bthack;

import net.minecraft.entity.player.PlayerEntity;

public final class PlayerEntityUtils {
   PlayerEntityUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static String getClass_1657(PlayerEntity playerEntity) {
      return BThackClient.getInstance().getSocialManager().getEnemy(playerEntity.getName().getString()).getString();
   }

   public static boolean isClass_16571(PlayerEntity playerEntity) {
      return BThackClient.getInstance().getSocialManager().isFriend(playerEntity.getName().getString());
   }

   public static String getClass_16572(PlayerEntity playerEntity) {
      return BThackClient.getInstance().getSocialManager().getFriend(playerEntity.getName().getString()).getString();
   }

   public static boolean isClass_16573(PlayerEntity playerEntity) {
      return BThackClient.getInstance().getSocialManager().isEnemy(playerEntity.getName().getString());
   }
}
