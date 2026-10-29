package com.ferra13671.bthack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubSettingContainer extends Setting {
   final transient HashMap<Object, NamedObject> map;
   final transient int currentsize2;
   final transient IgnoreBody ignoreBody;

   public List<NamedObject> applyList() {
      return ((List)this.getValue()).stream().map(SubSettingContainer::withObject2).toList();
   }

   public List<NamedObject> getMap() {
      List<NamedObject> list = this.applyList();
      return this.map.values().stream().filter((namedObject) -> SubSettingContainer.isList3(list, namedObject)).toList();
   }

   private static void handleJsonArray1(JsonArray jsonArray, NamedObject namedObject) {
      jsonArray.add(namedObject.getString1());
   }

   public HashMap<Object, NamedObject> getMap2() {
      return this.map;
   }

   public IgnoreBody getIgnoreBody() {
      return this.ignoreBody;
   }

   private static NamedObject withObject2(Object object) {
      return NamedObjectUtils.set(object);
   }

   public int getCurrentsize() {
      return this.currentsize2;
   }

   @Override
   public JsonElement toJson() {
      JsonArray jsonArray = new JsonArray();
      this.applyList().forEach((namedObject) -> SubSettingContainer.handleJsonArray1(jsonArray, namedObject));
      return jsonArray;
   }

   private static boolean isList3(List list, NamedObject namedObject) {
      return !list.contains(namedObject);
   }

   @Override
   public SettingElement createSettingElement(EventBus eventBus, Api9 api9, float flt, float flt2, float flt3) {
      return new SettingElementSub6(eventBus, api9, this, flt, flt2, flt3);
   }

   private void handleJsonArray4(JsonArray jsonArray) {
      jsonArray.forEach(this::handleJsonElement6);
   }

   private void handleJsonElement5(JsonElement jsonElement, NamedObject namedObject) {
      if (namedObject.getString1().equals(jsonElement.getAsString())) {
         ((List)this.getValue()).add(namedObject.getObject());
      }
   }

   private void handleJsonElement6(JsonElement jsonElement) {
      this.map.values().forEach((namedObject) -> this.handleJsonElement5(jsonElement, namedObject));
   }

   @Override
   public void fromJson(Helper27 helper27) {
      helper27.decode(ValueCodec.valueCodec2, this::handleJsonArray4);
   }

   protected SubSettingContainer(SubSettingContainer.SettingBuilderSub1 settingBuilderSub1) {
      super(settingBuilderSub1);
      this.map = settingBuilderSub1.getMap2();
      this.currentsize2 = settingBuilderSub1.getCurrentsize();
      this.ignoreBody = settingBuilderSub1.getIgnoreBody();
   }

   public abstract static class SettingBuilderSub1 extends Setting.SettingBuilder {
      transient HashMap<Object, NamedObject> map = new HashMap<>();
      transient int currentsize2 = -1;
      transient IgnoreBody ignoreBody = IgnoreBody.Ignore;

      protected SubSettingContainer.SettingBuilderSub1 toBuilder() {
         return this;
      }

      protected int getCurrentsize() {
         return this.currentsize2;
      }

      protected HashMap getMap2() {
         return this.map;
      }

      public abstract SubSettingContainer build();

      public SubSettingContainer.SettingBuilderSub1 withIgnoreBody(IgnoreBody ignoreBody) {
         if (ignoreBody == null) {
            throw new NullPointerException("overflowAction is marked non-null but is null");
         }

         this.ignoreBody = ignoreBody;
         return this.toBuilder();
      }

      protected IgnoreBody getIgnoreBody() {
         return this.ignoreBody;
      }

      public SubSettingContainer.SettingBuilderSub1 withHashMap(HashMap<Object, NamedObject> hashMap) {
         this.map = hashMap;
         return this.toBuilder();
      }

      public SubSettingContainer.SettingBuilderSub1 withInt(int num) {
         if (num < 0) {
            this.currentsize2 = -1;
         } else {
            this.currentsize2 = num;
         }

         return this.toBuilder();
      }

      public SettingBuilderSub1() {
         this.value(new ArrayList<NamedObject>());
      }
   }
}
