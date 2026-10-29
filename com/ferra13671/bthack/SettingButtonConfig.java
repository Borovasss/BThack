package com.ferra13671.bthack;

import java.util.function.Function;
import org.apache.logging.log4j.util.TriConsumer;

public record SettingButtonConfig(
   String name, Utils4.PackButtons buttonpack, Function<Api4, Boolean> shouldworkformodulefunction2, Function<Api4, Boolean> shouldworkformodulefunction, TriConsumer<Api4, ComponentSub2, XYButton> clickconsumer
) {

   public Utils4.PackButtons getButtonpack() {
      return this.buttonpack;
   }

   public Function<Api4, Boolean> getShouldworkformodulefunction2() {
      return this.shouldworkformodulefunction;
   }

   public TriConsumer<Api4, ComponentSub2, XYButton> getClickconsumer() {
      return this.clickconsumer;
   }

   public Function<Api4, Boolean> getShouldworkformodulefunction() {
      return this.shouldworkformodulefunction2;
   }
}
