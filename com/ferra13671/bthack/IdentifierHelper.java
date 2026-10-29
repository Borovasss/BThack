package com.ferra13671.bthack;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import net.minecraft.util.Identifier;

public class IdentifierHelper implements MinecraftInstance {
   public static final transient String string1 = "sound-manager/sounds/";
   final transient HashMap<Identifier, byte[]> map = new HashMap<>();

   public void handleClass_2960(Identifier identifier, float flt) {
      mc.getSoundManager().play(new PositionedSoundInstanceSub1(identifier, flt));
   }

   private static byte[] withInputStream1(InputStream inputStream, Identifier identifier) {
      try {
         return inputStream.readAllBytes();
      } catch (IOException iOException) {
         iOException.printStackTrace(BThackClient.getInstance().getLogger().getExceptionWriter());
         return new byte[0];
      }
   }

   public byte[] withClass_29602(Identifier identifier) {
      return this.map.get(identifier);
   }

   public Identifier withString3(String string, InputStream inputStream, boolean bool) {
      Identifier identifier = Identifier.of("bthack", "sound-manager/sounds/" + string);
      this.map.computeIfAbsent(identifier, (identifier1) -> IdentifierHelper.withInputStream1(inputStream, identifier1));
      if (bool) {
         try {
            inputStream.close();
         } catch (IOException iOException) {
            iOException.printStackTrace(BThackClient.getInstance().getLogger().getExceptionWriter());
         }
      }

      return identifier;
   }

   public void handleClass_29604(Identifier identifier) {
      this.handleClass_2960(identifier, 1.0F);
   }
}
