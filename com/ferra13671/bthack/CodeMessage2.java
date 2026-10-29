package com.ferra13671.bthack;

public record CodeMessage2(int code, String message) implements Api7 {

   public String getMessage() {
      return this.message;
   }

   public int getCode() {
      return this.code;
   }

   @Override
   public HandshakeBody getHandshake() {
      return HandshakeBody.Close;
   }
}
