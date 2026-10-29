package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import net.minecraft.command.CommandSource;

class Helper47 implements ArgumentType {
   final transient CommandArgumentBaseSub4 commandArgumentBaseSub4;

   Helper47(CommandArgumentBaseSub4 commandArgumentBaseSub4) {
      this.commandArgumentBaseSub4 = commandArgumentBaseSub4;
   }

   @OverriddenMethod
   public CompletableFuture listSuggestions(CommandContext commandContext, SuggestionsBuilder suggestionsBuilder) {
      return CommandSource.suggestMatching(this.commandArgumentBaseSub4.getExamples(), suggestionsBuilder);
   }

   @OverriddenMethod
   public String parse(StringReader stringReader) {
      String string = stringReader.getRemaining();
      stringReader.setCursor(stringReader.getTotalLength());
      return string;
   }
}
