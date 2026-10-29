package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
   @Inject(method = "unloadBlockEntities", at = @At("TAIL"))
   public void handleClass_2818(WorldChunk worldChunk, CallbackInfo callbackInfo) {
      BThackClient.getInstance().getChunkTracker().onChunkUnload(worldChunk);
   }

   @Inject(method = "resetChunkColor", at = @At("TAIL"))
   public void handleClass_19231(ChunkPos chunkPos, CallbackInfo callbackInfo) {
      BThackClient.getInstance().getChunkTracker().onChunkLoad(((World)(Object)this).getChunk(chunkPos.x, chunkPos.z));
   }
}
