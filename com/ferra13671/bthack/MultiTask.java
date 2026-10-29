package com.ferra13671.bthack;

@ModuleInfo(name = "Multi Task", category = "misc")
public class MultiTask extends Module {
   public final transient BooleanSetting attacking = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Attacking"))
         .value(true))
      .build();
}
