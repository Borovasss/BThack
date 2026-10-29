package com.ferra13671.bthack;

import java.util.function.Function;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class PrioritySortedHelper extends PrioritySorted implements MinecraftInstance {
   final transient Helper3 helper3 = new Helper3();

   private static Integer getRotationOptions(RotationOptions rotationOptions) {
      return -rotationOptions.getPriority();
   }

   @Override
   public boolean unsubscribe(Object var1) {
      return this.unregister((RotationOptions)var1);
   }

   @Api8(priority = -1, event = EventSub8.class)
   public void handleEventSub81(EventSub8 eventSub8) {
      if (this.current != null) {
         eventSub8.float1 = this.helper3.getFloat4();
         eventSub8.float2 = this.helper3.getFloat();
      }
   }

   public Helper3 getHelper3() {
      return this.helper3;
   }

   public PrioritySortedHelper() {
      super((Function<Object, Integer>)(Object o) -> -((RotationOptions)o).getPriority());
      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   public boolean register(RotationOptions rotationOptions) {
      boolean bool = super.subscribe(rotationOptions);
      if (bool) {
         this.helper3.handleFloat1(mc.gameRenderer.getCamera().getYaw(), mc.gameRenderer.getCamera().getPitch());
      }

      return bool;
   }

   @Override
   public boolean subscribe(Object var1) {
      return this.register((RotationOptions)var1);
   }

   public void handleBoolean2(boolean bool) {
      float flt = mc.player.input.playerInput.forward() ? 1 : (mc.player.input.playerInput.backward() ? -1 : 0);
      float flt2 = mc.player.input.playerInput.left() ? 1 : (mc.player.input.playerInput.right() ? -1 : 0);
      Matrix4f matrix4f = new Matrix4f();
      matrix4f.rotate((float)Math.toRadians(mc.player.getYaw() - mc.gameRenderer.getCamera().getYaw()), 0.0F, 1.0F, 0.0F);
      Vec3d vec3d = EntityUtils.withMatrix4f2(matrix4f, flt2, 0.0F, flt);
      flt = (float)Math.round(vec3d.getZ()) * (bool ? (float)mc.player.getAttributeValue(EntityAttributes.SNEAKING_SPEED) : 1.0F);
      flt2 = (float)Math.round(vec3d.getX()) * (bool ? (float)mc.player.getAttributeValue(EntityAttributes.SNEAKING_SPEED) : 1.0F);
      InputUtils.withClass_7441(
         InputUtils.withClass_7449(
            InputUtils.withClass_7446(InputUtils.withClass_74411(InputUtils.withClass_744(mc.player.input, flt > 0.0F), flt < 0.0F), flt2 > 0.0F), flt2 < 0.0F
         ),
         flt,
         flt2
      );
   }

   @Api8(event = EventSub6.ChangeLookDirectionEvent.class)
   public void onChangeLookDirection(EventSub6.ChangeLookDirectionEvent obj) {
      if (this.current != null) {
         obj.runVoid();
         this.helper3.handleFloat2((float)obj.double1, (float)obj.double2);
      }
   }

   @Api8(event = EventSub6.MouseTickEvent.class)
   public void onTick() {
      if (!BThackClient.isInitialized()) {
         if (this.current != null) {
            if ((Boolean)((RotationOptions)this.current).getRotategetter().get()) {
               this.unregister((RotationOptions)this.current);
               return;
            }

            float[] fltArr = (float[])((RotationOptions)this.current).getRotategetter3().get();
            mc.player.setYaw(fltArr[0]);
            mc.player.setPitch(fltArr[1]);
         }
      }
   }

   @Api8(event = TickMovementEvent.class)
   public void onTickMovement() {
      if (this.current != null && (Boolean)((RotationOptions)this.current).getRotategetter2().get()) {
         this.handleBoolean2(mc.player.isSneaking());
      }
   }

   public boolean unregister(RotationOptions rotationOptions) {
      boolean bool = super.unsubscribe(rotationOptions);
      if (bool && !BThackClient.isInitialized()) {
         mc.player.setYaw(mc.gameRenderer.getCamera().getYaw());
         mc.player.setPitch(mc.gameRenderer.getCamera().getPitch());
      }

      return bool;
   }
}
