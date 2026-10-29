package com.ferra13671.bthack;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

public abstract class Helper23 {
   protected final transient Helper23.SixBody sixBody;
   protected final transient Helper23.Sha1Body sha1Body;
   final transient int int1;

   private static byte[] withLong(long lng) {
      ByteBuffer byteBuffer = ByteBuffer.allocate(8);
      byteBuffer.putLong(0, lng);
      return byteBuffer.array();
   }

   protected Helper23(Helper23.SixBody sixBody, Helper23.Sha1Body sha1Body) {
      this.sixBody = sixBody;
      this.sha1Body = sha1Body;
      this.int1 = (int)Math.pow(10.0, sixBody.getValue());
   }

   protected String getByte1(byte[] bytArr, long lng) {
      byte[] bytArr2 = HmacUtils.getInitializedMac(this.sha1Body.value, bytArr).doFinal(withLong(lng));
      int num = this.getByte2(bytArr2) % this.int1;
      return String.format("%0" + this.sixBody.getValue() + "d", num);
   }

   private int getByte2(byte[] bytArr) {
      int num = bytArr[bytArr.length - 1] & 15;
      byte[] bytArr2 = Arrays.copyOfRange(bytArr, num, num + 4);
      bytArr2[0] = (byte)(bytArr2[0] & 127);
      return ByteBuffer.wrap(bytArr2).getInt();
   }

   public enum Sha1Body {
      SHA1(HmacAlgorithms.HMAC_SHA_1),
      SHA256(HmacAlgorithms.HMAC_SHA_256),
      SHA512(HmacAlgorithms.HMAC_SHA_512);

      public final HmacAlgorithms value;

      private static Helper23.Sha1Body[] $values() {
         return new Helper23.Sha1Body[]{SHA1, SHA256, SHA512};
      }

      Sha1Body(HmacAlgorithms hmacAlgorithms) {
         this.value = hmacAlgorithms;
      }
   }

   public enum SixBody {
      SIX(6),
      SEVEN(7),
      EIGHT(8),
      NINE(9),
      TEN(10);

      final int value;

      SixBody(int num) {
         this.value = num;
      }

      public int getValue() {
         return this.value;
      }

      private static Helper23.SixBody[] $values() {
         return new Helper23.SixBody[]{SIX, SEVEN, EIGHT, NINE, TEN};
      }
   }
}
