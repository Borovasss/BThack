package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public abstract class CommandArgumentBase implements CommandArgument {
   @Override
   public ArgumentType applyArgumentType() {
      class Helper19 implements ArgumentType {
         final transient CommandArgumentBase commandArgumentBase;

         @OverriddenMethod
         public CompletableFuture listSuggestions(CommandContext commandContext, SuggestionsBuilder suggestionsBuilder) {
            return CommandSource.suggestMatching(this.commandArgumentBase.getExamples(), suggestionsBuilder);
         }

         @OverriddenMethod
         public Object parse(StringReader stringReader) throws CommandSyntaxException {
            String string = stringReader.readString();

            try {
               return this.commandArgumentBase.parse(string);
            } catch (ExceptionSub1 exceptionSub1) {
               throw new DynamicCommandExceptionType(unused -> Text.literal(Formatting.RED + exceptionSub1.getMessage())).create(string);
            }
         }

         Helper19() {
            this.commandArgumentBase = CommandArgumentBase.this;
         }
      }

      return new Helper19();
   }

   public Collection getExamples() {
      return List.of();
   }

   public abstract Object parse(String var1) throws ExceptionSub1;
}
