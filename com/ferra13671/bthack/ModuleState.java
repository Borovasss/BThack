package com.ferra13671.bthack;

import com.google.gson.JsonObject;
import java.util.List;

public class ModuleState extends Api1Impl {
   final transient Module mcModule;
   final transient Api1ImplSub1 api1ImplSub11;
   final transient Api1ImplSub1 api1ImplSub1;

   protected void fromElement(JsonObject jsonObject) {
      new Helper27(jsonObject.get("enabled")).decode(ValueCodec.valueCodec3, this::handleBoolean);
      this.api1ImplSub11.read(new Helper27(jsonObject.get("keybind")));
      this.api1ImplSub1.read(new Helper27(jsonObject.get("settings")));
   }

   private void handleBoolean(Boolean boolean_) {
      this.mcModule.setEnabled(boolean_, true);
   }

   protected JsonObject toElement() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("enabled", this.mcModule.isEnabled());
      jsonObject.add("keybind", this.api1ImplSub11.toElement());
      jsonObject.add("settings", this.api1ImplSub1.toElement());
      return jsonObject;
   }

   @Override
   protected void set(Object var1) {
      this.fromElement((JsonObject)var1);
   }

   public ModuleState(Module module_) {
      super(ValueCodec.valueCodec1);
      this.mcModule = module_;
      this.api1ImplSub11 = new Api1ImplSub1(List.of(this.mcModule.getModuleKeybind(), this.mcModule.getModuleKeybind2()));
      this.api1ImplSub1 = new Api1ImplSub1(this.mcModule.getSettings());
   }

   @Override
   protected Object get() {
      return this.toElement();
   }
}
