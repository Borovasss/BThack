package com.ferra13671.bthack;

import java.io.Closeable;

public class SettingElement<T> extends Element implements Closeable {
   protected final transient Api9 api9;
   protected final transient Setting setting;

   @Override
   public void close2() {
      this.eventBus.register(new EventSub3(this.setting));
   }

   public void setX(float flt) {
      this.width = flt;
   }

   @Override
   public void close() {
      super.close();
   }

   public SettingElement(EventBus eventBus, Api9 api9, Setting setting, float flt, float flt2, float flt3) {
      super(eventBus, flt, flt2, flt3, 0.0F);
      this.api9 = api9;
      this.setting = setting;
   }

   public void setY(float flt) {
      this.height = flt;
   }

   public Api9 getApi9() {
      return this.api9;
   }

   public void render(Api2Impl api2Impl) {
   }

   @Override
   protected void close5() {
      this.eventBus.register(new EventSub21(this.setting));
   }

   public Setting getSetting() {
      return this.setting;
   }
}
