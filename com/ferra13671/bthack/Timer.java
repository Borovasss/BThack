package com.ferra13671.bthack;

@ModuleInfo(name = "Timer", category = "movement")
public class Timer extends Module {
   public final transient NumberSetting speed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder().name("Speed"))
         .value(1.0F))
      .min(0.05F)
      .max(12.0F)
      .build();
   final transient PriorityTimer priorityTimer = new PriorityTimer(0, this.speed::getValue);

   @Override
   public void onEnable3() {
      BThackClient.getInstance().getPrioritySortedSub2().subscribe(this.priorityTimer);
   }

   @Override
   public void onDisable2() {
      BThackClient.getInstance().getPrioritySortedSub2().unsubscribe(this.priorityTimer);
   }
}
