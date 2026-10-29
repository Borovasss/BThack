package com.ferra13671.bthack;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.function.Consumer;

public class DiscordIpcThread2 extends DiscordIpc {
   final transient RandomAccessFile randomAccessFile1;

   @Override
   public void runVoid1() {
      try {
         this.randomAccessFile1.close();
      } catch (IOException iOException) {
         iOException.printStackTrace();
      }
   }

   private void runVoid2() {
      ByteBuffer byteBuffer2 = ByteBuffer.allocate(4);

      try {
         while (true) {
            this.handleByteBuffer3(byteBuffer2);
            HandshakeBody handshakeBody = HandshakeBody.valueOf(Integer.reverseBytes(byteBuffer2.getInt(0)));
            this.handleByteBuffer3(byteBuffer2);
            int num = Integer.reverseBytes(byteBuffer2.getInt(0));
            ByteBuffer byteBuffer = ByteBuffer.allocate(num);
            this.handleByteBuffer3(byteBuffer);
            String string = Charset.defaultCharset().decode(byteBuffer.rewind()).toString();
            this.consumer1.accept((Api7)handshakeBody.toPacketFunction.apply(JsonParser.parseString(string).getAsJsonObject()));
         }
      } catch (Exception var6) {
      }
   }

   public DiscordIpcThread2(String string, Consumer<Api7> consumer) throws IOException {
      super(consumer);
      this.randomAccessFile1 = new RandomAccessFile(string, "rw");
      Thread thread = new Thread(this::runVoid2);
      thread.setName("Discord IPC - Read thread");
      thread.start();
   }

   @Override
   protected void handleByteBuffer2(ByteBuffer byteBuffer) {
      try {
         this.randomAccessFile1.write(byteBuffer.array());
      } catch (IOException iOException) {
         iOException.printStackTrace();
      }
   }

   private void handleByteBuffer3(ByteBuffer byteBuffer) throws IOException {
      byteBuffer.rewind();

      while (this.randomAccessFile1.length() < byteBuffer.remaining()) {
         Thread.onSpinWait();

         try {
            Thread.sleep(100L);
         } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
         }
      }

      while (byteBuffer.hasRemaining()) {
         this.randomAccessFile1.getChannel().read(byteBuffer);
      }
   }
}
