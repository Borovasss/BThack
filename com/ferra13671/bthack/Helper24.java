package com.ferra13671.bthack;

import net.minecraft.util.Hand;

public class Helper24 implements MinecraftInstance {
   final transient Aura mcModule;
   transient boolean boolean1 = false;

   private boolean getModule() {
      if ((Boolean)this.mcModule.sprintReset.getValue() && this.mcModule.isBoolean1()) {
         if (this.mcModule.boolean1) {
            this.boolean1 = mc.options.sprintKey.isPressed();
         }

         mc.options.sprintKey.setPressed(false);
         mc.player.setSprinting(false);
         InputUtils.withClass_7444(mc.player.input, false);
         if (this.mcModule.boolean1) {
            this.mcModule.boolean1 = false;
            return true;
         }
      }

      return false;
   }

   private boolean getModule2() {
      return (Float)((CooldownBody)this.mcModule.delaymode.getValue()).delayFunction.apply(this.mcModule)
            >= (this.mcModule.criticalsMode.getValue() != NoneBody.None && this.mcModule.isBoolean1() ? 0.95F : 1.0F)
         && this.mcModule.isFloat((Float)this.mcModule.attackRange.getValue())
         && (Boolean)((NoneBody)this.mcModule.criticalsMode.getValue()).canAttackFunction.apply(this.mcModule);
   }

   public Helper24(Aura aura) {
      this.mcModule = aura;
   }

   public void runVoid() {
      if (this.mcModule.livingEntity != null && (this.getModule2() || !this.mcModule.boolean1 && !this.getModule())) {
         mc.interactionManager.attackEntity(mc.player, this.mcModule.livingEntity);
         mc.player.swingHand(Hand.MAIN_HAND);
         this.mcModule.helper45.reset();
         this.mcModule.boolean1 = true;
         if (this.boolean1) {
            mc.options.sprintKey.setPressed(true);
         }
      }
   }
}
