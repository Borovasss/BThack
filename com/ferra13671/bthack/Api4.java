package com.ferra13671.bthack;

public interface Api4 extends ListSettingApi {
   CategoryInfoApi getCategoryInfoApi();

   String getName();

   String getGetname();

   Api1Impl getModuleState();

   void getEnabled();

   boolean isEnabled();

   void setEnabled(boolean var1, boolean var2);
}
