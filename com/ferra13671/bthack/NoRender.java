package com.ferra13671.bthack;

@ModuleInfo(name = "No Render", category = "render")
public class NoRender extends Module {
   public final transient ListSetting overlayCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("Overlay"))
         .id("overlay-category"))
      .build();
   public final transient BooleanSetting hurtCamera = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Hurt Camera"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting headOverlay = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Head Overlay"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting powderSnow = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Powder Snow"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting fire = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Fire"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting water = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Water"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting inWallOverlay = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("In Wall Overlay"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting bossBar = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Boss Bar"))
            .value(false))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting sleep = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Sleep"))
            .value(false))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting portal = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Portal"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting vignette = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Vignette"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting spyglass = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Spyglass"))
            .value(false))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient BooleanSetting nausea = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Nausea"))
            .value(true))
         .onlyIf(this.overlayCategory))
      .build();
   public final transient ListSetting worldCategory = ((ListSetting.SettingBuilderSub4)((ListSetting.SettingBuilderSub4)ListSetting.builder().name("World"))
         .id("world-category"))
      .build();
   public final transient BooleanSetting explosions = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Explosions"))
            .value(false))
         .onlyIf(this.worldCategory))
      .build();
   public final transient BooleanSetting armor = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Armor"))
            .value(false))
         .onlyIf(this.worldCategory))
      .build();
   public final transient BooleanSetting totemAnimation = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Totem Animation"))
            .value(false))
         .onlyIf(this.worldCategory))
      .build();
   public final transient BooleanSetting fallingBlocks = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Falling Blocks"))
            .value(false))
         .onlyIf(this.worldCategory))
      .build();
   public final transient BooleanSetting armorStands = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Armor Stands"))
            .value(false))
         .onlyIf(this.worldCategory))
      .build();
   public final transient BooleanSetting textureRotation = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Texture Rotation"))
            .value(false))
         .onlyIf(this.worldCategory))
      .build();

   @Api8(event = EventSub12.class)
   public void handleEventSub12(EventSub12 eventSub12) {
      if (eventSub12.setting == this.textureRotation && !BThackClient.isInitialized()) {
         RendererInit.runLater(mc.worldRenderer::reload);
      }
   }
}
