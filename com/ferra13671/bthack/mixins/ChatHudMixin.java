package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.ChatHudMixinApi;
import java.util.List;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChatHud.class)
public class ChatHudMixin implements ChatHudMixinApi {
   @Shadow
   @Final
   transient List visibleMessages;

   @Override
   public void getField_2064() {
      this.visibleMessages.clear();
   }
}
