package com.ferra13671.bthack;

import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;

public class ListSetting extends Setting implements ListSettingApi {
   final transient Api1ImplSub1 api1ImplSub1 = new Api1ImplSub1((List)this.getValue());

   public static ListSetting.SettingBuilderSub4 builder() {
      return new ListSetting.SettingBuilderSub4();
   }

   protected ListSetting(Setting.SettingBuilder settingBuilder) {
      super(settingBuilder);
   }

   @Override
   public void fromJson(Helper27 helper27) {
      this.api1ImplSub1.read(helper27);
   }

   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementSub7(eventBus, api9, this, flt, flt2, flt3);
   }

   @Override
   public JsonElement toJson() {
      return this.api1ImplSub1.getValueCodec2().getJsonElement();
   }

   @Override
   public List getSettings() {
      return (List)this.value;
   }

   public static class SettingBuilderSub4 extends Setting.SettingBuilder {
      protected ListSetting.SettingBuilderSub4 toBuilder() {
         return this;
      }

      public ListSetting build() {
         this.value(new ArrayList());
         return new ListSetting(this);
      }
   }
}
