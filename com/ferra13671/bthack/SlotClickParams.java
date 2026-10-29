package com.ferra13671.bthack;

public record SlotClickParams(SlotType fromslot, SlotType fromslot2, Api3 mode) {

   public SlotClickParams(SlotType slotType, SlotType slotType2) {
      this(slotType, slotType2, Api3.DEFAULT);
   }

   private void getFromslot() {
      if (this.getFromslot2().isSlot() || this.fromslot2.isSlot()) {
         throw new IllegalStateException("Slot has invalid.");
      }
   }

   public void runVoid() {
      this.getFromslot();
      this.mode
         .onSlotSwitch(
            this.fromslot.getSlotType1(this.fromslot2),
            this.fromslot.getType(),
            this.fromslot2.getSlotType1(this.fromslot),
            this.getFromslot3().getType(),
            true
         );
   }

   public SlotType getFromslot3() {
      return this.fromslot2;
   }

   public void runVoid1() {
      this.getFromslot();
      this.mode
         .onSlotSwitch(
            this.fromslot.getSlotType1(this.fromslot2),
            this.fromslot.getType(),
            this.fromslot2.getSlotType1(this.fromslot),
            this.getFromslot3().getType(),
            false
         );
   }

   public SlotType getFromslot2() {
      return this.fromslot;
   }

   public Api3 getMode() {
      return this.mode;
   }
}
