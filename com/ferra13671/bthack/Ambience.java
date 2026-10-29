package com.ferra13671.bthack;

import com.ferra13671.gltextureutils.Pair;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat.DrawMode;
import java.awt.Color;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix3f;
import org.joml.Vector3f;

@ModuleInfo(name = "Ambience", category = "render")
public class Ambience extends Module {
   public final transient ListSetting fogCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Fog"))
         .id("fog-category"))
      .build();
   public final transient ListSetting distanceCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
               .name("Distance"))
            .id("distance-category"))
         .onlyIf(this.fogCategory))
      .build();
   public final transient BooleanSetting custom6 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.distanceCategory))
      .build();
   public final transient NumberSetting start = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
                  .name("Start"))
               .value(0.0F))
            .min(0.0F)
            .max(600.0F)
            .visibleSupplier(this.custom6::getValue))
         .decimalPlaces(1)
         .onlyIf(this.distanceCategory))
      .build();
   public final transient NumberSetting end = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
                  .name("End"))
               .value(150.0F))
            .min(0.0F)
            .max(600.0F)
            .visibleSupplier(this.custom6::getValue))
         .decimalPlaces(1)
         .onlyIf(this.distanceCategory))
      .build();
   public final transient ListSetting colorCategory3 = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
               .name("Color"))
            .id("color-category"))
         .onlyIf(this.fogCategory))
      .build();
   public final transient BooleanSetting custom5 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.colorCategory3))
      .build();
   public final transient ColorSetting color2 = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
                  .name("Color"))
               .value(new Color(62, 62, 125)))
            .visibleSupplier(this.custom5::getValue))
         .onlyIf(this.colorCategory3))
      .build();
   public final transient ListSetting skyCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Sky"))
         .id("sky-category"))
      .build();
   public final transient ListSetting colorCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
               .name("Color"))
            .id("color-category"))
         .onlyIf(this.skyCategory))
      .build();
   public final transient BooleanSetting custom3 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.colorCategory))
      .build();
   public final transient ColorSetting color3 = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
                  .name("Color"))
               .value(new Color(15, 15, 31)))
            .visibleSupplier(this.custom3::getValue))
         .onlyIf(this.colorCategory))
      .build();
   public final transient ListSetting starsCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
               .name("Stars"))
            .id("stars-category"))
         .onlyIf(this.skyCategory))
      .build();
   public final transient BooleanSetting custom7 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.starsCategory))
      .build();
   public final transient ListSetting colorCategory2 = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
                  .name("Color"))
               .id("color-category"))
            .visibleSupplier(this.custom7::getValue))
         .onlyIf(this.starsCategory))
      .build();
   public final transient BooleanSetting custom8 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.colorCategory2))
      .build();
   public final transient ColorSetting color = ((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)((ColorSetting.ColorSettingBuilder)ColorSetting.builder()
                  .name("Color"))
               .value(new Color(255, 255, 255, 36)))
            .visibleSupplier(this.custom8::getValue))
         .onlyIf(this.colorCategory2))
      .build();
   public final transient ListSetting amountCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
                  .name("Amount"))
               .id("amount-category"))
            .visibleSupplier(this.custom7::getValue))
         .onlyIf(this.starsCategory))
      .build();
   public final transient BooleanSetting custom4 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.amountCategory))
      .build();
   public final transient NumberSetting amount = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
                  .name("Amount"))
               .value(20000.0F))
            .min(200.0F)
            .max(20000.0F)
            .visibleSupplier(this.custom4::getValue))
         .decimalPlaces(0)
         .onlyIf(this.amountCategory))
      .build();
   public final transient ListSetting seedCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
                  .name("Seed"))
               .id("seed-category"))
            .visibleSupplier(this.custom7::getValue))
         .onlyIf(this.starsCategory))
      .build();
   public final transient BooleanSetting custom2 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(false))
         .onlyIf(this.seedCategory))
      .build();
   public final transient NumberSetting seed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
                  .name("Seed"))
               .value(10842.0F))
            .min(1.0F)
            .max(20000.0F)
            .visibleSupplier(this.custom2::getValue))
         .decimalPlaces(0)
         .onlyIf(this.seedCategory))
      .build();
   public final transient ListSetting worldTimeCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder()
            .name("World Time"))
         .id("world-time-category"))
      .build();
   public final transient BooleanSetting custom9 = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(true))
         .onlyIf(this.worldTimeCategory))
      .build();
   public final transient ClassSetting worldTime = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(
                     DayBody.class
                  )
                  .name("World Time"))
               .value(DayBody.Night))
            .visibleSupplier(this.custom9::getValue))
         .onlyIf(this.worldTimeCategory))
      .build();
   public final transient NumberSetting time = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
                  .name("Time"))
               .value(10000.0F))
            .min(1.0F)
            .max(24000.0F)
            .visibleSupplier(this::getCustom9))
         .decimalPlaces(0)
         .onlyIf(this.worldTimeCategory))
      .build();
   public final transient NumberSetting spinSpeed = ((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)((NumberSetting.NumberSettingBuilder)NumberSetting.builder()
                  .name("Spin Speed"))
               .value(1.0F))
            .min(0.3F)
            .max(10.0F)
            .visibleSupplier(this::getCustom92))
         .onlyIf(this.worldTimeCategory))
      .build();
   public final transient ListSetting weatherCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Weather"))
         .id("weather-category"))
      .build();
   public final transient BooleanSetting custom = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Custom"))
            .value(false))
         .onlyIf(this.weatherCategory))
      .build();
   public final transient ClassSetting weather = ((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)((ClassSetting.SettingBuilderSub2)ClassSetting.builder(
                     ClearBody.class
                  )
                  .name("Weather"))
               .value(ClearBody.Clear))
            .visibleSupplier(this.custom::getValue))
         .onlyIf(this.weatherCategory))
      .build();
   protected final transient Helper45 helper45 = new Helper45();

   private void getCustom() {
      if (Utils9.withClass_76117(mc.worldRenderer) == null) {
         this.runVoid();
      } else {
         if ((Boolean)this.custom7.getValue()) {
            Pair<GpuBuffer, Integer> pair = this.getCustom2();
            Utils9.withClass_99755(Utils9.withClass_76117(mc.worldRenderer)).close();
            Utils9.handleClass_997516(Utils9.withClass_76117(mc.worldRenderer), (GpuBuffer)pair.getLeft());
            Utils9.handleClass_997511(Utils9.withClass_76117(mc.worldRenderer), (Integer)pair.getRight());
         } else {
            Utils9.handleClass_997516(Utils9.withClass_76117(mc.worldRenderer), Utils9.withClass_99751(Utils9.withClass_76117(mc.worldRenderer)));
         }
      }
   }

   private Boolean getCustom9() {
      return (Boolean)this.custom9.getValue() && ((DayBody)this.worldTime.getValue()).equals(DayBody.Custom);
   }

   public void runVoid() {
      RendererInit.runLater(this::getCustom);
   }

   public Pair getCustom2() {
      Random random = Random.create(this.custom2.getValue() ? ((Float)this.seed.getValue()).longValue() : 10842L);
      int num3 = this.custom4.getValue() ? ((Float)this.amount.getValue()).intValue() : 1500;
      BufferAllocator bufferAllocator = BufferAllocator.fixedSized(VertexFormats.POSITION.getVertexSize() * num3 * 4);

      int num2;
      GpuBuffer gpuBuffer;
      try {
         BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, DrawMode.QUADS, VertexFormats.POSITION);

         for (int num = 0; num < num3; num++) {
            float flt2 = random.nextFloat() * 2.0F - 1.0F;
            float flt3 = random.nextFloat() * 2.0F - 1.0F;
            float flt4 = random.nextFloat() * 2.0F - 1.0F;
            float flt5 = 0.15F + random.nextFloat() * 0.1F;
            float flt6 = MathHelper.magnitude(flt2, flt3, flt4);
            if (!(flt6 <= 0.010000001F) && !(flt6 >= 1.0F)) {
               Vector3f vector3f = new Vector3f(flt2, flt3, flt4).normalize(100.0F);
               float flt = (float)(random.nextDouble() * (float) Math.PI * 2.0);
               Matrix3f matrix3f = new Matrix3f().rotateTowards(new Vector3f(vector3f).negate(), new Vector3f(0.0F, 1.0F, 0.0F)).rotateZ(-flt);
               bufferBuilder.vertex(new Vector3f(flt5, -flt5, 0.0F).mul(matrix3f).add(vector3f));
               bufferBuilder.vertex(new Vector3f(flt5, flt5, 0.0F).mul(matrix3f).add(vector3f));
               bufferBuilder.vertex(new Vector3f(-flt5, flt5, 0.0F).mul(matrix3f).add(vector3f));
               bufferBuilder.vertex(new Vector3f(-flt5, -flt5, 0.0F).mul(matrix3f).add(vector3f));
            }
         }

         BuiltBuffer builtBuffer = bufferBuilder.end();

         try {
            num2 = builtBuffer.getDrawParameters().indexCount();
            gpuBuffer = RenderSystem.getDevice().createBuffer(Ambience::getString1, 40, builtBuffer.getBuffer());
         } catch (Throwable var18) {
            if (builtBuffer != null) {
               try {
                  builtBuffer.close();
               } catch (Throwable var17) {
                  var18.addSuppressed(var17);
               }
            }

            throw var18;
         }

         if (builtBuffer != null) {
            builtBuffer.close();
         }
      } catch (Throwable var19) {
         if (bufferAllocator != null) {
            try {
               bufferAllocator.close();
            } catch (Throwable var16) {
               var19.addSuppressed(var16);
            }
         }

         throw var19;
      }

      if (bufferAllocator != null) {
         bufferAllocator.close();
      }

      return new Pair(gpuBuffer, num2);
   }

   private static String getString1() {
      return "Stars vertex buffer";
   }

   @Api8(event = EventSub12.class)
   public void handleEventSub122(EventSub12 eventSub12) {
      if (eventSub12.setting == this.worldTime) {
         this.helper45.reset();
      }

      if (eventSub12.setting == this.custom7
         || eventSub12.setting == this.custom4
         || eventSub12.setting == this.amount
         || eventSub12.setting == this.custom2
         || eventSub12.setting == this.seed) {
         this.runVoid();
      }
   }

   @Override
   public void onEnable3() {
      super.onEnable3();
      if ((Boolean)this.custom7.getValue()) {
         this.runVoid();
      }

      this.helper45.reset();
   }

   @Override
   public void onDisable2() {
      super.onDisable2();
      if ((Boolean)this.custom7.getValue()) {
         this.runVoid();
      }

      this.helper45.reset();
   }

   private Boolean getCustom92() {
      return (Boolean)this.custom9.getValue() && ((DayBody)this.worldTime.getValue()).equals(DayBody.Spin);
   }
}
