package com.ferra13671.bthack;

public class Api2Impl2 implements Api2 {
   final transient Api2Impl3[] api2Impl31;

   @Override
   public void render4() {
      for (Api2Impl3<?> api2Impl3 : this.api2Impl31) {
         api2Impl3.render4();
      }
   }

   private void render() {
      for (Api2Impl3<?> api2Impl3 : this.api2Impl31) {
         api2Impl3.close();
      }
   }

   @Override
   public void close() {
      RendererInit.runLater(this::render);
   }

   @Override
   public void close2() {
      for (Api2Impl3<?> api2Impl3 : this.api2Impl31) {
         api2Impl3.close2();
      }
   }

   public Api2Impl2(Api2Impl3... api2Impl3Arr) {
      this.api2Impl31 = api2Impl3Arr;
   }
}
