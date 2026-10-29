package com.ferra13671.bthack;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import lombok.NonNull;
import net.minecraft.client.network.ClientCommandSource;

public class Helper5<T> {
   final transient @NonNull String name;
   final transient CommandArgument commandArgument;
   final transient List<Helper5<T>> list1 = new ArrayList<>();
   transient Consumer consumer1 = null;

   public Helper5<T> then(Helper5<T> helper5) {
      this.list1.add(helper5);
      return this;
   }

   public Helper5<T> then2(Helper5<T>... helper5Arr) {
      this.list1.addAll(Arrays.asList(helper5Arr));
      return this;
   }

   private int run(CommandContext commandContext) throws CommandSyntaxException {
      this.consumer1.accept(commandContext);
      return 0;
   }

   public ArgumentBuilder build(ArgumentBuilder argumentBuilder) {
      ArgumentBuilder<ClientCommandSource, ?> argumentBuilder2 = (ArgumentBuilder<ClientCommandSource, ?>)(this.commandArgument == null
         ? LiteralArgumentBuilder.literal(this.name)
         : RequiredArgumentBuilder.argument(this.name, this.commandArgument.applyArgumentType()));
      if (this.consumer1 != null) {
         argumentBuilder2.executes(this::run);
      }

      for (Helper5<T> helper5 : this.list1) {
         helper5.build(argumentBuilder2);
      }

      if (argumentBuilder != null) {
         argumentBuilder.then(argumentBuilder2);
         return argumentBuilder;
      } else {
         return argumentBuilder2;
      }
   }

   public Helper5<T> withHelper5(Helper5<T>... helper5Arr) {
      if (helper5Arr.length > 0) {
         this.then(helper5Arr[0]);
         if (helper5Arr.length > 1) {
            for (int num = 1; num < helper5Arr.length; num++) {
               helper5Arr[num - 1].then(helper5Arr[num]);
            }
         }
      }

      return this;
   }

   public Helper5(String string) {
      this(string, null);
      if (string == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }
   }

   public Helper5<T> executes(Consumer<CommandContext<ClientCommandSource>> consumer) {
      this.consumer1 = consumer;
      return this;
   }

   public Helper5(String string, CommandArgument commandArgument) {
      if (string == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      this.name = string;
      this.commandArgument = commandArgument;
   }
}
