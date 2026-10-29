package com.ferra13671.bthack;

import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.io.FilenameUtils;

public final class CommandArgumentBaseUtils {
   public static final transient CommandArgumentBase commandArgumentBase1 = new BooleanParser();
   public static final transient CommandArgumentBase commandArgumentBase2 = new IntegerParser();
   public static final transient CommandArgumentBase commandArgumentBase3 = new DoubleParser();
   public static final transient CommandArgumentBase commandArgumentBase4 = new FloatParser();
   public static final transient CommandArgumentBase commandArgumentBase5 = new CommandArgumentBaseSub5();
   public static final transient CommandArgumentBase commandArgumentBase6 = new CommandArgumentBaseSub4();
   public static final transient CommandArgumentBase commandArgumentBase7 = new CommandArgumentBaseSub7();
   public static final transient CommandArgumentBase commandArgumentBase8 = new FakePlayerArgument();
   public static final transient CommandArgumentBase commandArgumentBase9 = new ModuleArgument();
   public static final transient CommandArgumentBase commandArgumentBase10 = new CommandArgumentBaseSub3();

   public static CommandArgumentBase withSupplier(Supplier supplier) {
      class CommandArgumentBaseSub6 extends CommandArgumentBase {
         final transient Supplier examples;

         @Override
         public Collection getExamples() {
            return (Collection)this.examples.get();
         }

         @OverriddenMethod
         @Override
         public ArgumentType applyArgumentType() {
            class Helper16 implements ArgumentType {
               final transient CommandArgumentBaseSub6 commandArgumentBaseSub6;

               private static Message withString(String string, Object object) {
                  return Text.literal(Formatting.RED + String.format("'%s' does not exists!", string));
               }

               @OverriddenMethod
               public String parse(StringReader stringReader) throws CommandSyntaxException {
                  String string = stringReader.getRemaining();
                  stringReader.setCursor(stringReader.getTotalLength());
                  if (!((Collection)this.commandArgumentBaseSub6.examples.get()).contains(string)) {
                     throw new DynamicCommandExceptionType((object) -> Helper16.withString(string, object)).create(string);
                  } else {
                     return string;
                  }
               }

               Helper16(CommandArgumentBaseSub6 commandArgumentBaseSub6) {
                  this.commandArgumentBaseSub6 = commandArgumentBaseSub6;
               }

               @OverriddenMethod
               public CompletableFuture listSuggestions(CommandContext commandContext, SuggestionsBuilder suggestionsBuilder) {
                  return CommandSource.suggestMatching(this.commandArgumentBaseSub6.getExamples(), suggestionsBuilder);
               }
            }

            return new Helper16(this);
         }

         @OverriddenMethod
         public String parse(String string) {
            return null;
         }


         CommandArgumentBaseSub6(Supplier var1) {
            this.examples = var1;
         }
      }

      return new CommandArgumentBaseSub6(supplier);
   }

   public static CommandArgumentBase withSupplier1(Supplier supplier) {
      class CommandArgumentBaseSub1 extends CommandArgumentBase {
         final transient Supplier examples;

         @Override
         public Collection getExamples() {
            return (Collection)this.examples.get();
         }

         CommandArgumentBaseSub1(Supplier var1) {
            this.examples = var1;
         }

         public String parse(String string) throws ExceptionSub1 {
            if (!((Collection)this.examples.get()).contains(string)) {
               throw new ExceptionSub1(String.format("'%s' does not exists!", string));
            } else {
               return string;
            }
         }
      }

      return new CommandArgumentBaseSub1(supplier);
   }

   public static CommandArgumentBase withSupplier2(Supplier supplier, String string) {
      class NamedRegistry extends CommandArgumentBase {
         final transient Supplier<List<Helper13>> examples;
         final transient String string1;

         NamedRegistry(Supplier var1, String var2) {
            this.examples = var1;
            this.string1 = var2;
         }


         private static boolean isString(String string, Helper13 helper13) {
            return helper13.getName().equals(string);
         }

         @OverriddenMethod
         public Helper13 parse(String string) throws ExceptionSub1 {
            Helper13 helper13 = this.examples.get().stream().filter((helper131) -> NamedRegistry.isString(string, helper131)).findFirst().orElse(null);
            if (helper13 == null) {
               throw new ExceptionSub1(String.format("%s with name '%s' does not exists!", this.string1, string));
            } else {
               return helper13;
            }
         }

         @OverriddenMethod
         @Override
         public Collection getExamples() {
            return this.examples.get().stream().map(Helper13::getName).toList();
         }
      }

      return new NamedRegistry(supplier, string);
   }

   public static CommandArgumentBase withPath3(Path path) {
      class ConfigArgument extends CommandArgumentBase {
         final transient Path examples;

         private static boolean isFile(File file) {
            return file.isFile() && FilenameUtils.isExtension(file.getName(), "json") && !file.getName().replace(".json", "").equals("main");
         }

         @Override
         public Collection getExamples() {
            File[] fileArr = this.examples.toFile().listFiles();
            return fileArr != null ? Arrays.stream(fileArr).filter(ConfigArgument::isFile).map(ConfigArgument::getFile1).toList() : List.of();
         }

         private static String getFile1(File file) {
            return file.getName().replace(".json", "");
         }

         ConfigArgument(Path var1) {
            this.examples = var1;
         }

         public Path parse(String string) throws ExceptionSub1 {
            if (!this.getExamples().contains(string)) {
               throw new ExceptionSub1(String.format("config with name '%s' does not exists!", string));
            } else {
               return this.examples.resolve(string + ".json");
            }
         }
      }

      return new ConfigArgument(path);
   }

   CommandArgumentBaseUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static CommandArgumentBase withSupplier4(Supplier supplier, String string) {
      class NamedRegistry2 extends CommandArgumentBase {
         final transient Supplier<List<Helper13>> examples;
         final transient String string1;

         private static boolean isString(String string, Helper13 helper13) {
            return helper13.getName().equals(string);
         }

         NamedRegistry2(Supplier var1, String var2) {
            this.examples = var1;
            this.string1 = var2;
         }

         @OverriddenMethod
         public String parse(String string) throws ExceptionSub1 {
            Helper13 helper13 = this.examples.get().stream().filter((helper131) -> NamedRegistry2.isString(string, helper131)).findFirst().orElse(null);
            if (helper13 != null) {
               throw new ExceptionSub1(String.format("%s with name '%s' already exists!", this.string1, string));
            } else {
               return string;
            }
         }

      }

      return new NamedRegistry2(supplier, string);
   }

   public static CommandArgumentBase withClass5(Class class_) {
      class CommandArgumentBaseSub2 extends CommandArgumentBase {
         final transient Class examples;

         CommandArgumentBaseSub2(Class var1) {
            this.examples = var1;
         }

         public Enum parse(String string) throws ExceptionSub1 {
            Enum enum_ = (Enum)Utils3.withString1(string, this.examples);
            if (enum_ == null) {
               throw new ExceptionSub1(String.format("'%s' does not exists!", string));
            } else {
               return enum_;
            }
         }

         @Override
         public Collection getExamples() {
            return Arrays.stream((Enum[])this.examples.getEnumConstants()).map(Enum::name).toList();
         }
      }

      return new CommandArgumentBaseSub2(class_);
   }
}
