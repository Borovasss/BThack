package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public final class CategoryUtils {
   static final transient List<CategoryInfoApi> list1 = new ArrayList();
   public static final transient CategoryInfoApi categoryInfoApi = new CategoryInfo("Hud", "hud", null);

   CategoryUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      subscribe(
         new CategoryInfo("Combat", "combat", RendererInit.config.gLTexture1),
         new CategoryInfo("Misc", "misc", RendererInit.config.gLTexture2),
         new CategoryInfo("Move", "movement", RendererInit.config.gLTexture3),
         new CategoryInfo("Render", "render", RendererInit.config.gLTexture4),
         new CategoryInfo("Client", "client", RendererInit.config.gLTexture22)
      );
   }

   public static void subscribe(CategoryInfoApi... categoryInfoApiArr) {
      list1.addAll(List.of(categoryInfoApiArr));
   }

   public static List<CategoryInfoApi> getList() {
      return list1;
   }
}
