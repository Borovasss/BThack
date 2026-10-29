package com.ferra13671.bthack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public abstract class ConfigManager {
   public static final transient Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
   protected transient Optional<Path> optional1 = Optional.empty();

   public void fromJson(Path path) {
      InputStream inputStream = null;

      try {
         inputStream = Files.newInputStream(path);
      } catch (Exception exception) {
         new Exception("Cannot load config'" + path + "'. Reason: " + exception.getMessage()).printStackTrace();
      }

      if (inputStream != null) {
         this.fromJson(inputStream);

         try {
            inputStream.close();
         } catch (Exception var4) {
         }
      }
   }

   public void save() {
      this.handlePath1((Path)this.optional1.orElseThrow(ConfigManager::applyOp2));
   }

   private static UnsupportedOperationException applyOp() {
      return new UnsupportedOperationException("Default config path is null");
   }

   public void handlePath1(Path path) {
      OutputStream outputStream = null;

      try {
         Files.deleteIfExists(path);
         Files.createDirectories(path.getParent());
         Files.createFile(path);
         outputStream = Files.newOutputStream(path);
      } catch (IOException iOException) {
         new Exception("Cannot save config'" + path + "'. Reason: " + iOException.getMessage()).printStackTrace();
      }

      if (outputStream != null) {
         this.handleOutputStream4(outputStream);

         try {
            outputStream.close();
         } catch (Exception var4) {
         }
      }
   }

   private static UnsupportedOperationException applyOp2() {
      return new UnsupportedOperationException("Default config path is null");
   }

   public ConfigManager(Path path) {
      this.optional1 = Optional.ofNullable(path);
   }

   public void load() {
      this.fromJson((Path)this.optional1.orElseThrow(ConfigManager::applyOp));
   }

   protected Helper27 withInputStream3(InputStream inputStream) {
      JsonElement jsonElement = null;

      try {
         jsonElement = JsonParser.parseReader(new InputStreamReader(inputStream));
      } catch (Exception exception) {
         new Exception("Cannot load jsonElement from input stream. Reason: " + exception.getMessage()).printStackTrace();
      }

      return new Helper27(jsonElement);
   }

   public ConfigManager() {
   }

   public abstract void fromJson(InputStream var1);

   public abstract void handleOutputStream4(OutputStream var1);
}
