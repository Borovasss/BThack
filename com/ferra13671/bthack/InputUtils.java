package com.ferra13671.bthack;

import net.minecraft.client.input.Input;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Vec2f;

public final class InputUtils {
   public static Input withClass_744(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, bool, playerInput.backward(), playerInput.left(), playerInput.right(), playerInput.jump(), playerInput.sneak(), playerInput.sprint()
      );
   }

   public static Input withClass_7441(Input input, float flt, float flt2) {
      Utils9.handleClass_7448(input, new Vec2f(flt2, flt));
      return input;
   }

   public static Input withClass_7442(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, playerInput.forward(), playerInput.backward(), playerInput.left(), playerInput.right(), bool, playerInput.sneak(), playerInput.sprint()
      );
   }

   public static boolean isClass_7443(Input input) {
      PlayerInput playerInput = input.playerInput;
      return playerInput.forward() || playerInput.backward() || playerInput.left() || playerInput.right() || playerInput.jump();
   }

   public static Input withClass_7444(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, playerInput.forward(), playerInput.backward(), playerInput.left(), playerInput.right(), playerInput.jump(), playerInput.sneak(), bool
      );
   }

   public static Input withClass_7445(Input input, float flt) {
      Utils9.handleClass_7448(input, new Vec2f(flt, input.getMovementInput().y));
      return input;
   }

   public static Input withClass_7446(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, playerInput.forward(), playerInput.backward(), bool, playerInput.right(), playerInput.jump(), playerInput.sneak(), playerInput.sprint()
      );
   }

   InputUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Input withClass_7447(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, playerInput.forward(), playerInput.backward(), playerInput.left(), playerInput.right(), playerInput.jump(), bool, playerInput.sprint()
      );
   }

   public static Input withClass_7448(Input input, float flt) {
      Utils9.handleClass_7448(input, new Vec2f(input.getMovementInput().x, flt));
      return input;
   }

   public static Input withClass_7449(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, playerInput.forward(), playerInput.backward(), playerInput.left(), bool, playerInput.jump(), playerInput.sneak(), playerInput.sprint()
      );
   }

   public static Input withClass_74410(Input input, boolean bool, boolean bool2, boolean bool3, boolean bool4, boolean bool5, boolean bool6, boolean bool7) {
      input.playerInput = new PlayerInput(bool, bool2, bool3, bool4, bool5, bool6, bool7);
      return input;
   }

   public static Input withClass_74411(Input input, boolean bool) {
      PlayerInput playerInput = input.playerInput;
      return withClass_74410(
         input, playerInput.forward(), bool, playerInput.left(), playerInput.right(), playerInput.jump(), playerInput.sneak(), playerInput.sprint()
      );
   }
}
