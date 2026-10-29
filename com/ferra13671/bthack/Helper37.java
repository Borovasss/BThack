package com.ferra13671.bthack;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;

public class Helper37 implements MinecraftInstance {
   final transient Set<WorldChunk> loadedChunks = new HashSet<>();

   private static void onChunkData(ChunkDataS2CPacket chunkDataS2CPacket) {
      WorldChunk worldChunk = new WorldChunk(mc.world, chunkDataS2CPacket.getChunkData().getSectionsDataBuf().readChunkPos());
      worldChunk.loadFromPacket(
         chunkDataS2CPacket.getChunkData().getSectionsDataBuf(),
         new HashMap(),
         chunkDataS2CPacket.getChunkData().getBlockEntities(chunkDataS2CPacket.getChunkX(), chunkDataS2CPacket.getChunkZ())
      );
      BThackClient.getInstance().getEventBus().register(new EventSub14(worldChunk));
   }

   public Set<WorldChunk> getLoadedChunks() {
      return Collections.unmodifiableSet(this.loadedChunks);
   }

   private static void onBlockUpdate(BlockPos blockPos, BlockState blockState) {
      BThackClient.getInstance().getEventBus().register(new EventSub2(blockPos, blockState));
   }

   @Api8(event = EventSub4.EventSub4Sub2.class)
   public void onWorldPacket(EventSub4.EventSub4Sub2 eventSub4Sub2) {
      if (eventSub4Sub2.getPacket() instanceof ChunkDataS2CPacket chunkDataS2CPacket) {
         RendererInit.runLater(() -> Helper37.onChunkData(chunkDataS2CPacket));
      }

      if (eventSub4Sub2.getPacket() instanceof UnloadChunkS2CPacket var7) {
         UnloadChunkS2CPacket var10000 = var7;
         ChunkPos var13;

         try {
            var13 = var10000.pos();
         } catch (Throwable var6) {
            throw new MatchException(var6.toString(), var6);
         }

         ChunkPos var5 = var13;
         ChunkPos chunkPos = var5;
         BThackClient.getInstance().getEventBus().register(new EventSub5(mc.world.getChunk(chunkPos.x, chunkPos.z)));
      }

      if (eventSub4Sub2.getPacket() instanceof BlockUpdateS2CPacket blockUpdateS2CPacket) {
         BThackClient.getInstance().getEventBus().register(new EventSub2(blockUpdateS2CPacket.getPos(), blockUpdateS2CPacket.getState()));
      }

      if (eventSub4Sub2.getPacket() instanceof ChunkDeltaUpdateS2CPacket obj) {
         obj.visitUpdates(Helper37::onBlockUpdate);
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized() && !this.loadedChunks.isEmpty()) {
         this.loadedChunks.clear();
      }
   }

   public void onChunkLoad(WorldChunk worldChunk) {
      this.loadedChunks.add(worldChunk);
   }

   public void onChunkUnload(WorldChunk worldChunk) {
      this.loadedChunks.remove(worldChunk);
   }

   public Helper37() {
      BThackClient.getInstance().getEventBus().subscribe(this);
   }
}
