package com.ferra13671.bthack;

import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

@ModuleInfo(name = "Free Camera", category = "misc")
public class FreeCamera extends Module {
   public final transient NumberSetting verticalSpeed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Vertical Speed"))
         .value(10.0F))
      .min(1.0F)
      .max(100.0F)
      .build();
   public final transient NumberSetting horizontalSpeed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Horizontal Speed"))
         .value(10.0F))
      .min(1.0F)
      .max(100.0F)
      .build();
   public final transient BooleanSetting playerMoveSync = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Player Move Sync"))
         .value(false))
      .build();
   public final transient BooleanSetting smoothMovement = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Smooth movement"))
         .value(true))
      .build();
   public final transient NumberSetting smoothStrength = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Smooth Strength"))
            .value(0.35F))
         .min(0.1F)
         .max(1.0F)
         .visibleSupplier(this.smoothMovement::getValue))
      .build();
   public final transient BooleanSetting raycast = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Raycast"))
         .value(true))
      .build();
   public final transient BooleanSetting rotate = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Rotate"))
            .value(false))
         .visibleSupplier(this.raycast::getValue))
      .build();
   public final transient Helper3 helper3 = new Helper3();
   protected transient Vec3d vec3d;
   protected transient Vec3d class_2431;
   protected transient Vec3d class_2432;

   @Api8(event = TickMovementEvent.class)
   public void onTickMovement() {
      if (mc.player.input instanceof KeyboardInput) {
         mc.player.input = new FreeCamera.InputSub1();
      }

      this.helper3.runVoid();
      float flt2 = (Float)this.horizontalSpeed.getValue() / 10.0F;
      float flt3 = (Float)this.verticalSpeed.getValue() / 10.0F;
      float flt4 = this.getBoolean(mc.options.forwardKey.isPressed(), mc.options.backKey.isPressed());
      float flt5 = this.getBoolean(mc.options.leftKey.isPressed(), mc.options.rightKey.isPressed());
      Vec2f vec2f = this.withFloat2(flt2, flt4, flt5);
      float flt6 = 0.0F;
      if (mc.options.jumpKey.isPressed()) {
         flt6 += flt3;
      } else if (mc.options.sneakKey.isPressed()) {
         flt6 -= flt3;
      }

      Vec3d vec3d = new Vec3d(vec2f.x, flt6, vec2f.y);
      if ((Boolean)this.smoothMovement.getValue()) {
         float flt = 0.1F / (Float)this.smoothStrength.getValue();
         this.vec3d = this.vec3d.lerp(vec3d, flt);
      } else {
         this.vec3d = vec3d;
      }

      this.class_2432 = this.class_2431;
      this.class_2431 = this.class_2431.add(this.vec3d);
      if ((Boolean)this.playerMoveSync.getValue()) {
         this.class_2431 = this.class_2431.add(EntityUtils.withClass_12971(mc.player));
      }
   }

   @Api8(event = UpdateEvent.class)
   public void onUpdate(UpdateEvent updateEvent) {
      updateEvent.handleClass_243(this.class_2432.lerp(this.class_2431, updateEvent.getFloat()));
   }

   private float getBoolean(boolean bool, boolean bool2) {
      if (bool == bool2) {
         return 0.0F;
      } else {
         return bool ? 1.0F : -1.0F;
      }
   }

   @Api8(event = EventSub8.class)
   public void handleEventSub81(EventSub8 eventSub8) {
      eventSub8.float1 = this.helper3.getFloat4();
      eventSub8.float2 = this.helper3.getFloat();
   }

   @Override
   public void onEnable3() {
      if (BThackClient.isInitialized()) {
         this.setEnabled(false, true);
      } else {
         this.helper3.handleFloat1(mc.gameRenderer.getCamera().getYaw(), mc.gameRenderer.getCamera().getPitch());
         this.vec3d = new Vec3d(0.0, 0.0, 0.0);
         this.class_2431 = this.class_2432 = mc.player.getEyePos();
         RendererInit.runLater(mc.worldRenderer::reload);
         super.onEnable3();
      }
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick(MinecraftClientTickEvent obj) {
      if (BThackClient.isInitialized()) {
         this.setEnabled(false, false);
      }
   }

   public void getRotate() {
      if (!BThackClient.isInitialized()) {
         BlockHitResult blockHitResult = Utils8.withClass_2432(
            mc.gameRenderer.getCamera().getCameraPos(),
            mc.gameRenderer
               .getCamera()
               .getCameraPos()
               .add(
                  mc.player
                     .getRotationVector(mc.gameRenderer.getCamera().getPitch(), mc.gameRenderer.getCamera().getYaw())
                     .multiply(mc.player.getBlockInteractionRange(), mc.player.getBlockInteractionRange(), mc.player.getBlockInteractionRange())
               )
         );
         if (blockHitResult.getType() != Type.MISS) {
            mc.crosshairTarget = blockHitResult;
            mc.targetedEntity = null;
            if ((Boolean)this.rotate.getValue()) {
               float[] fltArr = EntityUtils.getClass_12978(mc.player, blockHitResult.getPos().toVector3f());
               mc.player.setYaw(fltArr[0]);
               mc.player.setPitch(fltArr[1]);
            }
         }
      }
   }

   @Api8(event = EventSub6.ChangeLookDirectionEvent.class)
   public void onChangeLookDirection(EventSub6.ChangeLookDirectionEvent obj) {
      obj.runVoid();
      this.helper3.handleFloat2((float)obj.double1, (float)obj.double2);
   }

   private Vec2f withFloat2(float flt, float flt2, float flt3) {
      if (flt2 == 0.0F && flt3 == 0.0F) {
         return Vec2f.ZERO;
      }

      if (flt2 != 0.0F && flt3 != 0.0F) {
         flt2 *= (float)Math.sin(Math.PI / 4);
         flt3 *= (float)Math.cos(Math.PI / 4);
      }

      return new Vec2f(
         (float)(flt2 * flt * -Math.sin(Math.toRadians(this.helper3.getFloat4())) + flt3 * flt * Math.cos(Math.toRadians(this.helper3.getFloat4()))),
         (float)(flt2 * flt * Math.cos(Math.toRadians(this.helper3.getFloat4())) - flt3 * flt * -Math.sin(Math.toRadians(this.helper3.getFloat4())))
      );
   }

   @Override
   public void onDisable2() {
      super.onDisable2();
      if (!BThackClient.isInitialized()) {
         mc.player.input = new KeyboardInput(mc.options);
         RendererInit.runLater(mc.worldRenderer::reload);
      }
   }

   private static class InputSub1 extends Input {
      public void tick() {
         this.playerInput = new PlayerInput(false, false, false, false, false, false, false);
         this.movementVector = new Vec2f(0.0F, 0.0F);
      }

      InputSub1() {
      }
   }
}
