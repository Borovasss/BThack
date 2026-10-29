package com.ferra13671.bthack;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;

@ModuleInfo(name = "Hand View", category = "render")
public class HandView extends Module {
   public final transient ListSetting mainHandCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
            .name("Main Hand"))
         .id("main-hand-category"))
      .build();
   public final transient NumberSetting posX2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pos X"))
            .value(0.0F))
         .min(-2.0F)
         .max(2.0F)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting posY2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pos y"))
            .value(0.0F))
         .min(-2.0F)
         .max(2.0F)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting posZ = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pos Z"))
            .value(0.0F))
         .min(-2.0F)
         .max(2.0F)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting rotX = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Rot X"))
            .value(0.0F))
         .min(-360.0F)
         .max(360.0F)
         .decimalPlaces(0)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting rotY = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Rot Y"))
            .value(0.0F))
         .min(-360.0F)
         .max(360.0F)
         .decimalPlaces(0)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting rotZ = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Rot Z"))
            .value(0.0F))
         .min(-360.0F)
         .max(360.0F)
         .decimalPlaces(0)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting scaleX2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Scale X"))
            .value(1.0F))
         .min(0.0F)
         .max(4.0F)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting scaleY2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Scale Y"))
            .value(1.0F))
         .min(0.0F)
         .max(4.0F)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient NumberSetting scaleZ2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Scale Z"))
            .value(1.0F))
         .min(0.0F)
         .max(4.0F)
         .onlyIf(this.mainHandCategory))
      .build();
   public final transient ListSetting offHandCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
            .name("Off Hand"))
         .id("off-hand-category"))
      .build();
   public final transient NumberSetting posX = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pos X"))
            .value(0.0F))
         .min(-2.0F)
         .max(2.0F)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting posY = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pos y"))
            .value(0.0F))
         .min(-2.0F)
         .max(2.0F)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting posZ2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Pos Z"))
            .value(0.0F))
         .min(-2.0F)
         .max(2.0F)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting rotX2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Rot X"))
            .value(0.0F))
         .min(-360.0F)
         .max(360.0F)
         .decimalPlaces(0)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting rotY2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Rot Y"))
            .value(0.0F))
         .min(-360.0F)
         .max(360.0F)
         .decimalPlaces(0)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting rotZ2 = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Rot Z"))
            .value(0.0F))
         .min(-360.0F)
         .max(360.0F)
         .decimalPlaces(0)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting scaleX = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Scale X"))
            .value(1.0F))
         .min(0.0F)
         .max(4.0F)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting scaleY = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Scale Y"))
            .value(1.0F))
         .min(0.0F)
         .max(4.0F)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient NumberSetting scaleZ = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Scale Z"))
            .value(1.0F))
         .min(0.0F)
         .max(4.0F)
         .onlyIf(this.offHandCategory))
      .build();
   public final transient BooleanSetting customFov = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Custom Fov"))
         .value(false))
      .build();
   public final transient NumberSetting fov = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
               .name("Fov"))
            .value(90.0F))
         .min(10.0F)
         .max(180.0F)
         .decimalPlaces(1)
         .visibleSupplier(this.customFov::getValue))
      .build();

   public void applyHandTransform(MatrixStack matrixStack, Hand hand) {
      if (hand == Hand.MAIN_HAND) {
         matrixStack.translate((Float)this.posX2.getValue(), (Float)this.posY2.getValue(), (Float)this.posZ.getValue());
         matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees((Float)this.rotX.getValue()));
         matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((Float)this.rotY.getValue()));
         matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((Float)this.rotZ.getValue()));
         matrixStack.scale((Float)this.scaleX2.getValue(), (Float)this.scaleY2.getValue(), (Float)this.scaleZ2.getValue());
      } else {
         matrixStack.translate((Float)this.posX.getValue(), (Float)this.posY.getValue(), (Float)this.posZ2.getValue());
         matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees((Float)this.rotX2.getValue()));
         matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((Float)this.rotY2.getValue()));
         matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((Float)this.rotZ2.getValue()));
         matrixStack.scale((Float)this.scaleX.getValue(), (Float)this.scaleY.getValue(), (Float)this.scaleZ.getValue());
      }
   }
}
