package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.SoundManager;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.client.sound.SoundLoader;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundLoader.class)
public class SoundLoaderMixin {
   @Redirect(
      method = "method_19745",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceFactory;open(Lnet/minecraft/util/Identifier;)Ljava/io/InputStream;")
   )
   public InputStream withClass_5912(ResourceFactory resourceFactory, Identifier identifier) throws IOException {
      return SoundManager.withClass_29605(identifier, resourceFactory);
   }

   @Redirect(
      method = "method_19747",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceFactory;open(Lnet/minecraft/util/Identifier;)Ljava/io/InputStream;")
   )
   public InputStream withClass_59121(ResourceFactory resourceFactory, Identifier identifier) throws IOException {
      return SoundManager.withClass_29605(identifier, resourceFactory);
   }
}
