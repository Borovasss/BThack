package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.CometRenderer;
import com.ferra13671.cometrenderer.minecraft.CRM;
import com.ferra13671.cometrenderer.minecraft.CustomDrawMode;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.vertex.mesh.Mesh;
import com.mojang.blaze3d.opengl.GlStateManager;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

@ModuleInfo(name = "Aura", category = "combat")
public class Aura extends Module {
   public final transient NumberSetting targetRange = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Target Range"))
         .value(3.0F))
      .min(1.0F)
      .max(7.0F)
      .decimalPlaces(1)
      .build();
   public final transient NumberSetting attackRange = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
            .name("Attack Range"))
         .value(3.0F))
      .min(1.0F)
      .max(7.0F)
      .decimalPlaces(1)
      .build();
   public final transient NumberSetting fov = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder().name("Fov"))
         .value(360.0F))
      .min(5.0F)
      .max(360.0F)
      .decimalPlaces(0)
      .build();
   public final transient ClassSetting criticalsMode = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(
                  NoneBody.class
               )
               .name("Crit Mode"))
            .id("criticals-mode"))
         .value(NoneBody.None))
      .build();
   public final transient ClassSetting delaymode = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(CooldownBody.class)
            .name("Delay Mode"))
         .value(CooldownBody.Cooldown))
      .build();
   public final transient NumberSetting delay = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Delay"))
            .value(1.0F))
         .min(0.01F)
         .max(5.0F)
         .visibleSupplier(this::isDelaymode))
      .build();
   public final transient BooleanSetting sprintReset = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Sprint Reset"))
         .value(true))
      .build();
   public final transient ListSetting rotationCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
            .name("Rotation"))
         .id("rotation-category"))
      .build();
   public final transient BooleanSetting smartRotate = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Smart Rotate"))
            .value(true))
         .onlyIf(this.rotationCategory))
      .build();
   public final transient NumberSetting angleStep = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Angle Step"))
            .value(20.0F))
         .min(0.1F)
         .max(90.0F)
         .decimalPlaces(1)
         .onlyIf(this.rotationCategory))
      .build();
   public final transient BooleanSetting moveFix = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Move Fix"))
            .value(true))
         .onlyIf(this.rotationCategory))
      .build();
   public final transient ListSetting targetsCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Targets"))
         .id("targets-category"))
      .build();
   public final transient ClassSetting priority = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(
                  DistanceBody.class
               )
               .name("Priority"))
            .value(DistanceBody.Distance))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting enemyPriority = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Enemy Priority"))
            .value(true))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting lockTarget = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Lock Target"))
            .value(false))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting players = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Players"))
            .value(true))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting friends = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name("Friends"))
               .value(false))
            .visibleSupplier(this.players::getValue))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting teammates = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name("Teammates"))
               .value(false))
            .visibleSupplier(this.players::getValue))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting invisibles = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name("Invisibles"))
               .value(true))
            .visibleSupplier(this.players::getValue))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting mobs = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Mobs"))
            .value(false))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting hostiles = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name("Hostiles"))
               .value(true))
            .visibleSupplier(this.mobs::getValue))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting passives = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name("Passives"))
               .value(false))
            .visibleSupplier(this.mobs::getValue))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient BooleanSetting golems = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name("Golems"))
               .value(true))
            .visibleSupplier(this.mobs::getValue))
         .onlyIf(this.targetsCategory))
      .build();
   public final transient ListSetting pauseIfCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
            .name("Pause If"))
         .id("pause-if-category"))
      .build();
   public final transient BooleanSetting eating = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Eating"))
            .value(true))
         .onlyIf(this.pauseIfCategory))
      .build();
   public final transient BooleanSetting notWeaponInHand = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Not Weapon In Hand"))
            .value(false))
         .onlyIf(this.pauseIfCategory))
      .build();
   protected transient LivingEntity livingEntity = null;
   protected final transient Helper45 helper45 = new Helper45();
   final transient LivingEntityHelper livingEntityHelper = new LivingEntityHelper(this);
   final transient Helper26 helper26 = new Helper26(this);
   final transient Helper24 helper24 = new Helper24(this);
   protected final transient RotationOptions rotationOptions = new RotationOptions(1000, this::getHelper26, this.moveFix::getValue, this::getLivingEntity);
   public transient boolean boolean1 = false;

   public boolean isFloat(float flt) {
      return Utils8.isClass_1297(mc.player, this.livingEntity.getBoundingBox(), flt);
   }

   public boolean isBoolean1() {
      return mc.player.getVelocity().y < 0.0 && !mc.player.isOnGround() && mc.player.fallDistance > 0.0 && mc.player.fallDistance < 0.3;
   }

   public void runVoid2() {
      this.livingEntity = null;
      BThackClient.getInstance().getPrioritySortedHelper().unregister(this.rotationOptions);
      this.boolean1 = true;
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if (BThackClient.isInitialized()) {
         this.runVoid2();
      } else {
         this.livingEntityHelper.runVoid();
         this.helper26.setFloat();
         this.helper24.runVoid();
      }
   }

   @Override
   public void onEnable3() {
      this.livingEntity = null;
      super.onEnable3();
   }

   public boolean getEating() {
      return (Boolean)this.eating.getValue() && mc.player.isUsingItem() && Helper32.withClass_1799(mc.player.getActiveOrMainHandStack()).isItemStack()
         || (Boolean)this.notWeaponInHand.getValue() && !Helper32.withClass_1799(mc.player.getStackInHand(Hand.MAIN_HAND)).getItemStack4();
   }

   private Boolean isDelaymode() {
      return this.delaymode.getValue() == CooldownBody.Timer;
   }

   private float[] getHelper26() {
      return this.helper26.float1;
   }

   private Boolean getLivingEntity() {
      return !this.isEnabled() || this.livingEntity == null;
   }

   public boolean isClass_16573(PlayerEntity playerEntity) {
      return playerEntity.isInvisible() || playerEntity.isSpectator() || playerEntity.hasStatusEffect(StatusEffects.INVISIBILITY);
   }

   @Api8(event = EventSub7.class)
   public void setBoolean() {
      if (this.livingEntity != null) {
         GlStateManager._disableDepthTest();
         Mesh mesh = RendererInit.withList4(List.of(this.livingEntity.getBoundingBox()), mc.gameRenderer.getCamera().getCameraPos());
         CometRenderer.setCurrentProgram(CRM.getPrograms().POSITION);
         CRM.applyMatrixUniform();
         CometRenderer.getShaderColor().setColor(RenderColor.of(1.0F, 1.0F, 1.0F, 0.3F).toVector4f());
         CometRenderer.applyShaderColorUniform();
         CometRenderer.draw(mesh, false);
         mesh.changeDrawMode(CustomDrawMode.CUBE_OUTLINE);
         CometRenderer.getShaderColor().setColor(RenderColor.WHITE.toVector4f());
         CometRenderer.applyShaderColorUniform();
         CometRenderer.draw(mesh);
      } else {
         this.boolean1 = true;
      }
   }
}
