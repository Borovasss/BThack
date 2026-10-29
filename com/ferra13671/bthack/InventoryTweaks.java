package com.ferra13671.bthack;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen.CreativeScreenHandler;
import net.minecraft.client.util.InputUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;

@ModuleInfo(name = "Inventory Tweaks", category = "misc")
public class InventoryTweaks extends Module {
   public final transient BooleanSetting shiftDrag = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Shift Drag"))
         .value(true))
      .build();
   public final transient BooleanSetting groupMove = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Group Move"))
         .value(false))
      .build();
   public final transient BooleanSetting autoTool = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Auto Tool"))
         .value(false))
      .build();
   public final transient ClassSetting orePriority = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(
                  NoneBody3.class
               )
               .name("Ore Priority"))
            .value(NoneBody3.Fortune))
         .visibleSupplier(this.autoTool::getValue))
      .build();
   public final transient BooleanSetting safeEnderChests = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Safe Ender Chests"))
            .value(false))
         .visibleSupplier(this.autoTool::getValue))
      .build();
   public final transient BooleanSetting hotbarReplenish = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Hotbar Replenish"))
         .value(false))
      .build();
   public final transient NumberSetting minItems = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Min Items"))
            .value(10.0F))
         .min(1.0F)
         .max(63.0F)
         .decimalPlaces(0)
         .visibleSupplier(this.hotbarReplenish::getValue))
      .build();
   public final transient BooleanSetting inventoryCleaner = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Inventory Cleaner"))
         .value(false))
      .build();
   public final transient SettingGroup3 trash = ((SettingGroup3.SettingBuilderSub1Sub1)((SettingGroup3.SettingBuilderSub1Sub1)SettingGroup3.builder()
            .name("Trash"))
         .visibleSupplier(this.inventoryCleaner::getValue))
      .build();
   public final transient NumberSetting throwDelay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Throw Delay"))
            .value(0.0F))
         .min(0.0F)
         .max(500.0F)
         .decimalPlaces(0)
         .visibleSupplier(this.inventoryCleaner::getValue))
      .build();
   transient SlotClickParams slotClickParams = null;
   final transient Helper45 helper451 = new Helper45();
   final transient Helper45 helper452 = new Helper45();

   private void handleClass_1735(Slot slot, ScreenHandler screenHandler) {
      MinecraftInstance.mc.interactionManager.clickSlot(screenHandler.syncId, slot.id, 0, SlotActionType.QUICK_MOVE, MinecraftInstance.mc.player);
   }

   public boolean isClass_17351(Slot slot, ScreenHandler screenHandler) {
      if (this.isClass_17355(slot)) {
         if (this.isClass_17032(screenHandler)) {
            this.isClass_17353(slot, screenHandler);
         } else {
            this.handleClass_1735(slot, screenHandler);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isClass_17032(ScreenHandler screenHandler) {
      return (Boolean)this.groupMove.getValue()
         && !(screenHandler instanceof CreativeScreenHandler)
         && (InputUtil.isKeyPressed(MinecraftInstance.mc.getWindow(), 341) || InputUtil.isKeyPressed(MinecraftInstance.mc.getWindow(), 345));
   }

   public boolean isClass_17353(Slot slot2, ScreenHandler screenHandler) {
      if (this.isClass_17355(slot2)) {
         ItemStack itemStack = slot2.getStack();

         for (Slot slot : screenHandler.slots) {
            if (slot != slot2 && slot.inventory == slot2.inventory && Helper32.withClass_1799(slot.getStack()).isHelper325(Helper32.withClass_1799(itemStack))) {
               this.handleClass_1735(slot, screenHandler);
            }
         }

         this.handleClass_1735(slot2, screenHandler);
         return true;
      } else {
         return false;
      }
   }

   public boolean isClass_17034(ScreenHandler screenHandler) {
      return (Boolean)this.shiftDrag.getValue()
         && !(screenHandler instanceof CreativeScreenHandler)
         && (InputUtil.isKeyPressed(MinecraftInstance.mc.getWindow(), 340) || InputUtil.isKeyPressed(MinecraftInstance.mc.getWindow(), 344));
   }

   public boolean isClass_17355(Slot slot) {
      return slot != null && slot.hasStack();
   }

   private void onTick() {
      if (this.slotClickParams != null) {
         this.slotClickParams.runVoid();
         this.slotClickParams = null;
      }
   }

   @Api8(event = CancelBlockBreakingEvent.class)
   public void onCancelBlockBreaking(CancelBlockBreakingEvent obj) {
      if ((Boolean)this.autoTool.getValue() && !obj.boolean1) {
         this.onTick();
      }
   }

   @Api8(event = ClientPlayerInteractionManagerAttackBlockEvent.class)
   public void onAttackBlock(ClientPlayerInteractionManagerAttackBlockEvent obj) {
      if ((Boolean)this.autoTool.getValue()) {
         if (!this.helper451.passed(100)) {
            return;
         }

         BlockStateHelper blockStateHelper = BlockStateHelper.withClass_2680(mc.world.getBlockState(obj.blockPos));
         SlotType slotType = SlotType.withClass_1268(Hand.MAIN_HAND);
         double dbl2 = 0.0;
         int num2 = -1;

         for (int num = 0; num < 36; num++) {
            Helper32 helper32 = Helper32.withClass_1799(mc.player.getInventory().getStack(num));
            double dbl = helper32.getClass_26802(blockStateHelper.getBlockState());
            if (dbl != -1.0) {
               if (blockStateHelper.getBlockState2()) {
                  dbl += ((NoneBody3)this.orePriority.getValue()).getExtraPriority(helper32);
               }

               if ((Boolean)this.safeEnderChests.getValue()) {
                  dbl += this.getClass_26807(blockStateHelper.getBlockState(), helper32);
               }

               if (dbl > dbl2) {
                  dbl2 = dbl;
                  num2 = num;
               }
            }
         }

         if (num2 != -1 && slotType.getSlot() != num2) {
            this.onTick();
            this.slotClickParams = slotType.withSlotType(SlotType.of(num2), Api3.DEFAULT);
            this.slotClickParams.runVoid1();
            this.helper451.reset();
         }
      } else {
         this.slotClickParams = null;
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized() && mc.player.currentScreenHandler instanceof PlayerScreenHandler) {
         if ((Boolean)this.hotbarReplenish.getValue()) {
            for (int num = 0; num < 9; num++) {
               Helper32 helper32 = Helper32.withClass_1799(mc.player.getInventory().getStack(num));
               if (!helper32.getItemStack().isEmpty()
                  && helper32.getItemStack().getCount() < Math.min(helper32.getItemStack().getMaxCount(), (Float)this.minItems.getValue() + 1.0F)) {
                  SlotType slotType = SlotTypeUtils.of(helper32.getItemStack(), 9, 36);
                  if (slotType != null && !slotType.isSlot()) {
                     slotType.withSlotType(SlotType.of(num), Api3.DEFAULT).runVoid1();
                  }
               }
            }
         }

         if ((Boolean)this.inventoryCleaner.getValue() && this.helper452.passed(((Float)this.throwDelay.getValue()).intValue())) {
            mc.player.playerScreenHandler.slots.forEach(this::handleClass_17356);
         }
      }
   }

   private void handleClass_17356(Slot slot) {
      if (this.helper452.passed(((Float)this.throwDelay.getValue()).intValue()) && ((List)this.trash.getValue()).contains(slot.getStack().getItem())) {
         SlotType slotType = SlotType.of(slot.id);
         slotType.withSlotType(new SlotType(-999, HotbarBody.Inventory), Api3.DEFAULT).runVoid1();
         if ((Float)this.throwDelay.getValue() > 0.0F) {
            this.helper452.reset();
         }
      }
   }

   private double getClass_26807(BlockState blockState, Helper32 helper32) {
      return helper32.getItemStack().isIn(ItemTags.PICKAXES) && blockState.isOf(Blocks.ENDER_CHEST) && helper32.isClass_53211(Enchantments.SILK_TOUCH)
         ? 10000.0
         : 0.0;
   }
}
