package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class ConfigManagerSub1 extends ConfigManager {
   final transient Api1 api1;

   @Override
   public void fromJson(InputStream inputStream) {
      JsonElement jsonElement = this.withInputStream3(inputStream).getJsonElement();
      if (jsonElement != null && (Boolean)this.api1.getValueCodec().getEqualsfunction2().apply(jsonElement)) {
         this.api1.read(new Helper27(jsonElement));
      }
   }

   @Override
   public void handleOutputStream4(OutputStream outputStream) {
      JsonElement jsonElement = this.api1.getValueCodec2().getJsonElement();
      if (jsonElement != null) {
         try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            outputStreamWriter.write(gson1.toJson(jsonElement));
         } catch (Exception exception) {
            new Exception("Cannot save config. Reason: " + exception.getMessage()).printStackTrace();
         }
      }
   }

   public ConfigManagerSub1(Api1 api1) {
      super(null);
      this.api1 = api1;
   }

   public ConfigManagerSub1(Path path, Api1 api1) {
      super(path);
      this.api1 = api1;
   }
}
