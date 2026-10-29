package com.ferra13671.bthack;

import net.minecraft.item.BlockItem;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.ShearsItem;

@ModuleInfo(name = "Fast Use", category = "misc")
public class FastUse extends Module {
   public final transient NumberSetting delay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder().name("Delay"))
         .value(0.0F))
      .min(0.0F)
      .max(4.0F)
      .decimalPlaces(0)
      .build();
   public final transient BooleanSetting all = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("All"))
         .value(false))
      .build();
   public final transient BooleanSetting blocks = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Blocks"))
            .value(false))
         .visibleSupplier(this::isAll2))
      .build();
   public final transient BooleanSetting projectiles = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Projectiles"))
            .value(false))
         .visibleSupplier(this::isAll3))
      .build();
   public final transient BooleanSetting fishingRod = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Fishing rod"))
            .value(false))
         .visibleSupplier(this::isAll))
      .build();
   public final transient BooleanSetting endCrystals = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("End crystals"))
            .value(false))
         .visibleSupplier(this::isAll5))
      .build();
   public final transient BooleanSetting scissors = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Scissors"))
            .value(false))
         .visibleSupplier(this::isAll4))
      .build();

   private Boolean isAll5() {
      return !(Boolean)this.all.getValue();
   }

   private Boolean isAll3() {
      return !(Boolean)this.all.getValue();
   }

   private Boolean isAll() {
      return !(Boolean)this.all.getValue();
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized()) {
         ItemStack itemStack = mc.player.getActiveOrMainHandStack();
         if ((
               (Boolean)this.all.getValue()
                  || (Boolean)this.blocks.getValue() && itemStack.getItem() instanceof BlockItem
                  || (Boolean)this.projectiles.getValue() && itemStack.getItem() instanceof ProjectileItem
                  || (Boolean)this.fishingRod.getValue() && itemStack.getItem() instanceof FishingRodItem
                  || (Boolean)this.endCrystals.getValue() && itemStack.getItem() instanceof EndCrystalItem
                  || (Boolean)this.scissors.getValue() && itemStack.getItem() instanceof ShearsItem
            )
            && Utils9.getClass_31015(mc) > ((Float)this.delay.getValue()).intValue()) {
            Utils9.handleClass_3103(mc, ((Float)this.delay.getValue()).intValue());
         }
      }
   }

   private Boolean isAll2() {
      return !(Boolean)this.all.getValue();
   }

   private Boolean isAll4() {
      return !(Boolean)this.all.getValue();
   }
}
