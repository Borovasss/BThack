package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

class ConfigValidator implements ArgumentType {
   final transient CommandArgumentBaseSub3 commandArgumentBaseSub3;

   ConfigValidator(CommandArgumentBaseSub3 commandArgumentBaseSub3) {
      this.commandArgumentBaseSub3 = commandArgumentBaseSub3;
   }

   private static Message withString(String string, Object object) {
      return Text.literal(Formatting.RED + String.format("config cannot be named '%s'!", string));
   }

   @OverriddenMethod
   public CompletableFuture listSuggestions(CommandContext commandContext, SuggestionsBuilder suggestionsBuilder) {
      return CommandSource.suggestMatching(this.commandArgumentBaseSub3.getExamples(), suggestionsBuilder);
   }

   @OverriddenMethod
   public String parse(StringReader stringReader) throws CommandSyntaxException {
      String string = stringReader.getRemaining();
      if (string.equals("main")) {
         throw new DynamicCommandExceptionType((object) -> ConfigValidator.withString(string, object)).create(string);
      }

      stringReader.setCursor(stringReader.getTotalLength());
      return string;
   }
}
