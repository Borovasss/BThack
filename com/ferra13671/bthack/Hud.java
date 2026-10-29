package com.ferra13671.bthack;

import java.util.function.BiConsumer;

public interface Hud extends Api4 {
   float getHeight();

   void setY(float var1);

   void setX(float var1);

   void render(Api2Impl var1);

   float getWidth();

   float getWidthOffset();

   void setHeight(float var1);

   void setWidth(float var1);

   float getHeightOffset();

   void handleBiConsumer(BiConsumer<Hud, Boolean> var1);

   void getApi2Impl2();
}
