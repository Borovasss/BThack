package com.ferra13671.bthack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public abstract class DiscordIpc {
   static final transient String[] string1 = new String[]{"XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP"};
   protected final transient Consumer<Api7> consumer1;

   public void handleHelper21Api(Helper21Api helper21Api) {
      this.handleByteBuffer2(Helper21Api.withHelper21Api(helper21Api));
   }

   public static DiscordIpc withConsumer(Consumer<Api7> consumer) {
      String string3 = System.getProperty("os.name").toLowerCase();
      if (string3.contains("win")) {
         for (int num = 0; num < 10; num++) {
            try {
               return new DiscordIpcThread2("\\\\.\\pipe\\discord-ipc-" + num, consumer);
            } catch (Exception exception) {
               exception.printStackTrace();
            }
         }
      } else {
         String string2 = null;

         for (String string : string1) {
            string2 = System.getenv(string);
            if (string2 != null) {
               break;
            }
         }

         if (string2 == null) {
            string2 = "/tmp";
         }

         string2 = string2 + "/discord-ipc-";

         for (int num = 0; num < 10; num++) {
            try {
               return new DiscordIpcThread(string2 + num, consumer);
            } catch (IOException var8) {
            }
         }
      }

      return null;
   }

   public abstract void runVoid1();

   protected abstract void handleByteBuffer2(ByteBuffer var1);

   public DiscordIpc(Consumer<Api7> consumer) {
      this.consumer1 = consumer;
   }
}
