package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.IPrimitiveBatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class Api2Impl3<T> implements Api2 {
   final transient AtomicBoolean atomicBoolean1 = new AtomicBoolean(false);
   final transient Supplier<IPrimitiveBatch> supplier1;
   transient IPrimitiveBatch iPrimitiveBatch1;
   transient IPrimitiveBatch iPrimitiveBatch2;

   @Override
   public void render4() {
      RendererInit.runLater(this::render3);
   }

   public Api2Impl3(Supplier<IPrimitiveBatch> supplier) {
      this.supplier1 = supplier;
      this.render4();
   }

   @Override
   public void close() {
      if (this.iPrimitiveBatch1 != null) {
         RendererInit.runLater(this::render);
         this.atomicBoolean1.set(false);
      }
   }

   private void render3() {
      this.atomicBoolean1.set(false);
      this.iPrimitiveBatch2 = this.iPrimitiveBatch1;
      this.iPrimitiveBatch1 = null;
      this.iPrimitiveBatch1 = this.supplier1.get();
      this.iPrimitiveBatch1.build();
      if (this.iPrimitiveBatch2 != null) {
         this.iPrimitiveBatch2.close();
      }

      this.iPrimitiveBatch2 = null;
      this.atomicBoolean1.set(true);
   }

   @Override
   public void close2() {
      if (this.atomicBoolean1.get()) {
         this.iPrimitiveBatch1.tryDraw();
      } else if (this.iPrimitiveBatch2 != null) {
         this.iPrimitiveBatch2.tryDraw();
      }
   }

   private void render() {
      this.iPrimitiveBatch1.close();
   }
}
