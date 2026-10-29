package com.ferra13671.bthack;

import com.google.gson.JsonObject;
import java.nio.ByteBuffer;
import java.util.UUID;

public interface Helper21Api extends Api5 {
   JsonObject getDiscordActivity2();

   static ByteBuffer withHelper21Api(Helper21Api helper21Api) {
      JsonObject jsonObject = helper21Api.getDiscordActivity2();
      jsonObject.addProperty("nonce", UUID.randomUUID().toString());
      byte[] bytArr = jsonObject.toString().getBytes();
      ByteBuffer byteBuffer = ByteBuffer.allocate(bytArr.length + 8);
      byteBuffer.putInt(Integer.reverseBytes(helper21Api.getHandshake().ordinal()));
      byteBuffer.putInt(Integer.reverseBytes(bytArr.length));
      byteBuffer.put(bytArr);
      byteBuffer.rewind();
      return byteBuffer;
   }
}
