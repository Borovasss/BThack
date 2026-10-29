package com.ferra13671.bthack;

public class Helper38 {
   transient Helper36 helper36;
   transient ToggleBody toggleBody;
   public static transient Helper38 helper38 = new Helper38(new Helper36(0, KeyboardEnum.Keyboard), ToggleBody.Toggle);

   public Helper38 withToggleBody(ToggleBody toggleBody) {
      return this.toggleBody == toggleBody ? this : new Helper38(this.helper36, toggleBody);
   }

   public Helper38(Helper36 helper36, ToggleBody toggleBody) {
      this.helper36 = helper36;
      this.toggleBody = toggleBody;
   }

   public Helper36 getHelper36() {
      return this.helper36;
   }

   public Helper38 withHelper36(Helper36 helper36) {
      return this.helper36 == helper36 ? this : new Helper38(helper36, this.toggleBody);
   }

   public ToggleBody getToggleBody() {
      return this.toggleBody;
   }
}
