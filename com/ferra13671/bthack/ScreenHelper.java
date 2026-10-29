package com.ferra13671.bthack;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public abstract class ScreenHelper extends Screen implements MinecraftInstance {
   protected final transient EventBus eventBus = new EventBusImpl();
   transient ScheduledExecutorService value1;

   public EventBus getEventBus() {
      return this.eventBus;
   }

   protected ScreenHelper() {
      super(Text.empty());
      this.eventBus.subscribe(this);
   }

   public final void removed() {
      this.eventBus.register(new EventSub19());
      this.value1.shutdown();
   }

   public abstract void setPosition(int var1, int var2);

   public void close6() {
   }

   public final void onDisplayed() {
      this.eventBus.register(new EventSub17());
      this.eventBus.register(new EventSub15());
      this.value1 = Executors.newSingleThreadScheduledExecutor();
      this.value1.scheduleAtFixedRate(this::close6, 0L, 100L, TimeUnit.MILLISECONDS);
   }

   public final void resize(int num, int num2) {
      super.resize(num, num2);
      this.eventBus.register(new EventSub10(num, num2));
      this.eventBus.register(new EventSub15());
   }
}
