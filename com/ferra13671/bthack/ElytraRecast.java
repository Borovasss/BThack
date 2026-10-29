package com.ferra13671.bthack;

import com.ferra13671.bthack.mixins.LivingEntityMixin;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;

@ModuleInfo(name = "Elytra Recast", category = "movement")
public class ElytraRecast extends Module {
   public final transient BooleanSetting legitRecast = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Legit Recast"))
         .value(false))
      .build();
   public final transient BooleanSetting blink = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Blink"))
         .value(true))
      .build();
   public final transient NumberSetting blinkTicks = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Blink Ticks"))
            .value(3.0F))
         .min(1.0F)
         .max(10.0F)
         .decimalPlaces(0)
         .visibleSupplier(this.blink::getValue))
      .build();
   public final transient BooleanSetting changepitch = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Change pitch"))
         .value(false))
      .build();
   public final transient NumberSetting pitch = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pitch"))
            .value(74.4F))
         .min(0.0F)
         .max(90.0F)
         .visibleSupplier(this.changepitch::getValue))
      .build();
   transient boolean boolean1 = false;
   transient int int1 = 0;
   final transient RotationOptions rotationOptions = new RotationOptions(0, this::getPitch, ElytraRecast::isBoolean1, this::isChangepitch);
   final transient MoveOptions moveOptions = new MoveOptions(100, ElytraRecast::isBoolean2);

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (!BThackClient.isInitialized()) {
         if (this.isMc()) {
            if (mc.player.isOnGround()) {
               ((LivingEntityMixin)mc.player).handleInt1(0);
               if ((Boolean)this.blink.getValue()) {
                  BThackClient.getInstance().getPrioritySortedHelper2().subscribe(this.moveOptions);
               }
            } else {
               this.int1++;
               if ((Boolean)this.blink.getValue()
                  && this.int1 > (Float)this.blinkTicks.getValue()
                  && BThackClient.getInstance().getPrioritySortedHelper2().isHigherPriority(this.moveOptions)) {
                  BThackClient.getInstance().getPrioritySortedHelper2().unregister(this.moveOptions);
               }
            }

            if (!mc.player.isGliding() && mc.player.fallDistance > 0.0) {
               this.isLegitrecast();
            }
         }
      }
   }

   private float[] getPitch() {
      return new float[]{mc.player.input instanceof KeyboardInput ? mc.gameRenderer.getCamera().getYaw() : mc.player.getYaw(), (Float)this.pitch.getValue()};
   }

   public boolean isMc() {
      return mc.player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA;
   }

   @Api8(event = EventSub12.class)
   public void handleEventSub12(EventSub12 eventSub12) {
      if (eventSub12.setting == this.blink) {
         BThackClient.getInstance().getPrioritySortedHelper2().unregister(this.moveOptions);
      }
   }

   @Override
   public void onEnable3() {
      super.onEnable3();
      if ((Boolean)this.changepitch.getValue()) {
         BThackClient.getInstance().getPrioritySortedHelper().register(this.rotationOptions);
      }
   }

   @Api8(event = TickMovementEvent.class)
   public void onTickMovement() {
      if (!BThackClient.isInitialized()) {
         if (this.isMc()) {
            boolean bool = mc.player.isOnGround() || this.boolean1;
            if (this.boolean1) {
               this.boolean1 = false;
            }

            InputUtils.withClass_7447(
               InputUtils.withClass_7444(
                  InputUtils.withClass_7442(InputUtils.withClass_7448(InputUtils.withClass_744(mc.player.input, true), 1.0F), bool),
                  !mc.player.isGliding() && ToggleableUtils.autoSprint.isBoolean()
               ),
               false
            );
         }
      }
   }

   private static Boolean isBoolean1() {
      return true;
   }

   private static Boolean isBoolean2() {
      return false;
   }

   private Boolean isChangepitch() {
      return !this.isEnabled() || !(Boolean)this.changepitch.getValue();
   }

   public boolean isLegitrecast() {
      if (!this.isMc()) {
         return false;
      } else if ((Boolean)this.legitRecast.getValue() && !mc.player.input.playerInput.jump()) {
         this.boolean1 = true;
         return false;
      } else {
         mc.getNetworkHandler().sendPacket(new ClientCommandC2SPacket(mc.player, Mode.START_FALL_FLYING));
         mc.player.startGliding();
         return true;
      }
   }
}
