package com.ferra13671.bthack.mixins;

import com.ferra13671.bthack.BThackClient;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.screen.ChatInputSuggestor.SuggestionWindow;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatInputSuggestor.class)
public abstract class ChatInputSuggestorMixin {
   @Shadow
   transient @Nullable ParseResults parse;
   @Shadow
   @Final
   transient TextFieldWidget textField;
   @Shadow
   transient SuggestionWindow window;
   @Shadow
   transient boolean completingSuggestions;
   @Shadow
   transient @Nullable CompletableFuture pendingSuggestions;

   @Inject(method = "refresh", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/StringReader;canRead()Z", remap = false), cancellable = true)
   public void handleCallbackInfo(CallbackInfo callbackInfo, StringReader stringReader) {
      String string = (String)BThackClient.getInstance().getCommandPrefixSetting().getValue();
      if (stringReader.canRead(string.length()) && stringReader.getString().startsWith(string, stringReader.getCursor())) {
         stringReader.setCursor(stringReader.getCursor() + 1);
         if (this.parse == null) {
            this.parse = BThackClient.getInstance()
               .getCommandManager()
               .getCommandDispatcher()
               .parse(stringReader, BThackClient.getInstance().getCommandManager().getClientCommandSource());
         }

         int num = this.textField.getCursor();
         if (num >= 1 && (this.window == null || !this.completingSuggestions)) {
            this.pendingSuggestions = BThackClient.getInstance().getCommandManager().getCommandDispatcher().getCompletionSuggestions(this.parse, num);
            this.pendingSuggestions.thenRun(this::getField_21611);
         }

         callbackInfo.cancel();
      }
   }

   @Shadow
   protected abstract void showCommandSuggestions();

   private void getField_21611() {
      if (this.pendingSuggestions.isDone()) {
         this.showCommandSuggestions();
      }
   }
}
