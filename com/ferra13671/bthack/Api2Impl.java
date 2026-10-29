package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.batch.IPrimitiveBatch;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Api2Impl implements Api2 {
   final transient Consumer<Api2Impl> consumer1;
   final transient Map<String, IPrimitiveBatch> map1 = new Object2ObjectArrayMap();

   public Api2Impl(Consumer<Api2Impl> consumer) {
      this.consumer1 = consumer;
      this.render4();
   }

   @Override
   public void render4() {
      RendererInit.runLater(this::render);
   }

   private void render2() {
      this.map1.values().forEach(IPrimitiveBatch::close);
   }

   @Override
   public void close() {
      RendererInit.runLater(this::render2);
   }

   private static IPrimitiveBatch withNameBatchCreator(Api2Impl.NameBatchCreator nameBatchCreator, String string) {
      return nameBatchCreator.getBatchcreator().get();
   }

   public IPrimitiveBatch getBatch(Api2Impl.NameBatchCreator nameBatchCreator) {
      return this.map1.computeIfAbsent(nameBatchCreator.name(), (string) -> Api2Impl.withNameBatchCreator(nameBatchCreator, string));
   }

   private void render() {
      this.map1.values().forEach(IPrimitiveBatch::close);
      this.map1.clear();
      this.consumer1.accept(this);
      this.map1.values().forEach(IPrimitiveBatch::build);
   }

   @Override
   public void close2() {
      for (IPrimitiveBatch iPrimitiveBatch : this.map1.values()) {
         iPrimitiveBatch.tryDraw();
      }
   }

   public record NameBatchCreator(String name, Supplier<IPrimitiveBatch> batchcreator) {

      public Supplier<IPrimitiveBatch> getBatchcreator() {
         return this.batchcreator;
      }
   }
}
