package com.ferra13671.bthack;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.function.Consumer;

public class DiscordIpcThread extends DiscordIpc {
   final transient Selector selector1 = Selector.open();
   final transient SocketChannel socketChannel1;

   @Override
   protected void handleByteBuffer2(ByteBuffer byteBuffer) {
      try {
         this.socketChannel1.write(byteBuffer);
      } catch (IOException iOException) {
         iOException.printStackTrace();
      }
   }

   public DiscordIpcThread(String string, Consumer<Api7> consumer) throws IOException {
      super(consumer);
      this.socketChannel1 = SocketChannel.open(UnixDomainSocketAddress.of(string));
      this.socketChannel1.configureBlocking(false);
      this.socketChannel1.register(this.selector1, 1);
      Thread thread = new Thread(this::runVoid2);
      thread.setName("Discord IPC - Read thread");
      thread.start();
   }

   private void runVoid2() {
      DiscordIpcThread.OpcodeBody opcodeBody = DiscordIpcThread.OpcodeBody.Opcode;
      ByteBuffer byteBuffer = ByteBuffer.allocate(4);
      ByteBuffer byteBuffer2 = null;
      HandshakeBody handshakeBody = null;

      try {
         while (true) {
            this.selector1.select();
            switch (opcodeBody) {
               case Opcode:
                  this.socketChannel1.read(byteBuffer);
                  if (!byteBuffer.hasRemaining()) {
                     handshakeBody = HandshakeBody.valueOf(Integer.reverseBytes(byteBuffer.getInt(0)));
                     opcodeBody = DiscordIpcThread.OpcodeBody.Length;
                     byteBuffer.rewind();
                  }
                  break;
               case Length:
                  this.socketChannel1.read(byteBuffer);
                  if (!byteBuffer.hasRemaining()) {
                     byteBuffer2 = ByteBuffer.allocate(Integer.reverseBytes(byteBuffer.getInt(0)));
                     opcodeBody = DiscordIpcThread.OpcodeBody.Data;
                     byteBuffer.rewind();
                  }
                  break;
               case Data:
                  this.socketChannel1.read(byteBuffer2);
                  if (!byteBuffer2.hasRemaining()) {
                     String string = Charset.defaultCharset().decode(byteBuffer2.rewind()).toString();
                     this.consumer1.accept((Api7)handshakeBody.toPacketFunction.apply(JsonParser.parseString(string).getAsJsonObject()));
                     byteBuffer2 = null;
                     opcodeBody = DiscordIpcThread.OpcodeBody.Opcode;
                  }
            }
         }
      } catch (Exception var6) {
      }
   }

   @Override
   public void runVoid1() {
      try {
         this.selector1.close();
         this.socketChannel1.close();
      } catch (IOException iOException) {
         iOException.printStackTrace();
      }
   }

   private enum OpcodeBody {
      Opcode,
      Length,
      Data;

      private static DiscordIpcThread.OpcodeBody[] $values() {
         return new DiscordIpcThread.OpcodeBody[]{Opcode, Length, Data};
      }
   }
}
