package com.ferra13671.bthack;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;

public abstract class ModuleSub1 extends Module {
   public final transient NumberSetting numberSetting = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Delay"))
         .value(3.0F))
      .min(0.0F)
      .max(10.0F)
      .build();
   public final transient BooleanSetting booleanSetting = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Auto Disable"))
         .value(false))
      .build();
   final transient List<TriggerOptions> list1 = this.applyList();

   protected abstract List applyList();

   void handleString1(String string) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleString2(string));
   }

   private void handleString2(String string) {
      if ((Float)this.numberSetting.getValue() > 0.0F) {
         try {
            Thread.sleep((int)((Float)this.numberSetting.getValue() * 1000.0F));
         } catch (InterruptedException var3) {
         }
      }

      ChatUtils.handleString1(string);
      if ((Boolean)this.booleanSetting.getValue()) {
         this.setEnabled(false, false);
      }
   }

   @Api8(event = EventSub4.EventSub4Sub2.class)
   public void handleEventSub4Sub23(EventSub4.EventSub4Sub2 eventSub4Sub2) {
      if (eventSub4Sub2.getPacket() instanceof GameMessageS2CPacket var2) {
         GameMessageS2CPacket var10000 = var2;
         Text var14;

         try {
            var14 = var10000.content();
         } catch (Throwable var11) {
            throw new MatchException(var11.toString(), var11);
         }

         Text var6 = var14;
         Text text = var6;
         var10000 = var2;

         try {
            var10000.overlay();
         } catch (Throwable var10) {
            throw new MatchException(var10.toString(), var10);
         }

         String string2 = text.getString();

         for (ModuleSub1.TriggerOptions triggerOptions : this.list1) {
            for (String string : triggerOptions.triggers) {
               if (string2.contains(string)) {
                  this.handleString1((String)triggerOptions.commandgetter.get());
                  return;
               }
            }
         }
      }
   }

   public record TriggerOptions(List<String> triggers, Supplier<String> commandgetter) {

      public Supplier<String> getCommandgetter() {
         return this.commandgetter;
      }

      public List getTriggers() {
         return this.triggers;
      }
   }
}
