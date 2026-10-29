package com.ferra13671.bthack;

import java.util.HashMap;
import java.util.function.Supplier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.PlayerScreenHandler;

@ModuleInfo(name = "Auto Armor", category = "combat")
public class AutoArmor extends Module {
   public final transient ListSetting scoreCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Score"))
         .id("score-category"))
      .build();
   public final transient NumberSetting armor = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Armor"))
            .value(1.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting armorToughness = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Armor Toughness"))
            .value(0.5F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting knockbackResistance = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Knockback Resistance"))
            .value(0.5F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting protection = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Protection"))
            .value(3.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting blastProtection = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Blast Protection"))
            .value(2.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting thorns = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Thorns"))
            .value(1.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting unbreaking = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Unbreaking"))
            .value(1.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting mending = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Mending"))
            .value(4.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting depthStrider = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Depth Strider"))
            .value(0.5F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting respiration = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Respiration"))
            .value(0.5F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting featherFalling = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Feather Falling"))
            .value(1.0F))
         .min(-10.0F)
         .max(10.0F)
         .decimalPlaces(1)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting bindingCurse = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Binding Curse"))
            .value(-50.0F))
         .min(-200.0F)
         .max(10.0F)
         .decimalPlaces(0)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient NumberSetting vanishingCurse = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Vanishing Curse"))
            .value(-1.0F))
         .min(-200.0F)
         .max(10.0F)
         .decimalPlaces(0)
         .onlyIf(this.scoreCategory))
      .build();
   public final transient BooleanSetting antiBindingCurse = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Anti Binding Curse"))
         .value(true))
      .build();
   public final transient BooleanSetting keepElytra = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Keep Elytra"))
         .value(true))
      .build();
   final transient HashMap<RegistryKey<Enchantment>, Supplier<Float>> map = new HashMapSub2(this);

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized() && mc.player.currentScreenHandler instanceof PlayerScreenHandler) {
         this.handleClass_6862(ItemTags.HEAD_ARMOR, Helper7.slotType1, EquipmentSlot.HEAD);
         if (!(Boolean)this.keepElytra.getValue() || mc.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
            this.handleClass_6862(ItemTags.CHEST_ARMOR, Helper7.slotType2, EquipmentSlot.CHEST);
         }

         this.handleClass_6862(ItemTags.LEG_ARMOR, Helper7.slotType3, EquipmentSlot.LEGS);
         this.handleClass_6862(ItemTags.FOOT_ARMOR, Helper7.slotType4, EquipmentSlot.FEET);
      }
   }

   private void handleClass_6862(TagKey tagKey, SlotType slotType2, EquipmentSlot equipmentSlot) {
      SlotType slotType3 = slotType2;
      int num3 = this.getClass_17991(mc.player.getEquippedStack(equipmentSlot));

      for (int num2 = 0; num2 < 36; num2++) {
         SlotType slotType = SlotType.of(num2);
         ItemStack itemStack = mc.player.getInventory().getStack(slotType.getSlot());
         if (itemStack.isIn(tagKey)) {
            int num = this.getClass_17991(itemStack);
            if (num > num3) {
               slotType3 = slotType;
               num3 = num;
            }
         }
      }

      if (slotType3 != slotType2) {
         slotType3.withSlotType(slotType2, Api3.DEFAULT).runVoid1();
      }
   }

   private int getClass_17991(ItemStack itemStack) {
      EquippableComponent equippableComponent = (EquippableComponent)itemStack.getItem().getComponents().get(DataComponentTypes.EQUIPPABLE);
      float flt = 0.0F;
      if (equippableComponent != null) {
         AttributeModifiersComponent obj = (AttributeModifiersComponent)itemStack.getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
         flt += this.armor.getValue() * (float)obj.applyOperations(EntityAttributes.ARMOR, 0.0, equippableComponent.slot());
         flt += this.armorToughness.getValue() * (float)obj.applyOperations(EntityAttributes.ARMOR_TOUGHNESS, 0.0, equippableComponent.slot());
         flt += this.knockbackResistance.getValue() * (float)obj.applyOperations(EntityAttributes.KNOCKBACK_RESISTANCE, 0.0, equippableComponent.slot());

         for (EnchantmentLevel enchantmentLevel : Helper32.withClass_1799(itemStack).getEnchantmentLevels()) {
            if ((Boolean)this.antiBindingCurse.getValue() && enchantmentLevel.getEnchantment().matchesKey(Enchantments.BINDING_CURSE)) {
               flt = -2.1474836E9F;
            }

            for (RegistryKey<Enchantment> registryKey : this.map.keySet()) {
               if (enchantmentLevel.getEnchantment().matchesKey(registryKey)) {
                  flt += this.map.get(registryKey).get() * enchantmentLevel.getLevel();
               }
            }
         }
      }

      return (int)(flt * 100.0F);
   }
}
