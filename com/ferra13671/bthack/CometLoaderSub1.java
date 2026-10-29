package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.ClientLoader;
import com.ferra13671.bthack.loader.api.resource.Resource;
import com.ferra13671.cometrenderer.CometLoader;
import com.ferra13671.ferraguard.annotations.OverriddenMethod;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

class CometLoaderSub1 extends CometLoader {
   @OverriddenMethod
   public String load(Resource resource) throws Exception {
      InputStream inputStream = ClientLoader.getInstance().getResourceManager().getResource(resource);
      String string = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
      inputStream.close();
      return string;
   }

   @OverriddenMethod
   public String load(Object var1) throws Exception {
      return this.load((Resource)var1);
   }
}
