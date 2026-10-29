package com.ferra13671.bthack;

import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.option.ChatOptionsScreen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.CreditsAndAttributionScreen;
import net.minecraft.client.gui.screen.option.FontOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OnlineOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.TelemetryInfoScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.PlayerInput;

@ModuleInfo(name = "Gui Move", category = "movement")
public class GuiMove extends Module {
   public final transient BooleanSetting grim = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("Grim"))
         .value(true))
      .build();
   public final transient BooleanSetting all = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
            .name("All"))
         .value(false))
      .build();
   public final transient BooleanSetting chat = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Chat"))
            .value(false))
         .visibleSupplier(this::isAll2))
      .build();
   public final transient BooleanSetting ui = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("UI"))
            .value(false))
         .visibleSupplier(this::isAll3))
      .build();
   public final transient BooleanSetting settings = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
               .name("Settings"))
            .visibleSupplier(this::isAll))
         .value(false))
      .build();
   final transient KeyBinding[] class_3041 = new KeyBinding[]{
      mc.options.sneakKey, mc.options.forwardKey, mc.options.backKey, mc.options.leftKey, mc.options.rightKey, mc.options.jumpKey
   };
   transient int int1 = 0;
   transient boolean all2 = false;

   private boolean isChat() {
      return (Boolean)this.chat.getValue() && mc.currentScreen instanceof ChatScreen;
   }

   private Boolean isAll3() {
      return !(Boolean)this.all.getValue();
   }

   private boolean isMc() {
      return mc.currentScreen instanceof HandledScreen;
   }

   @Api8(event = CloseHandledScreenEvent.class)
   public void onCloseHandledScreen(CloseHandledScreenEvent closeHandledScreenEvent) {
      if (!BThackClient.isInitialized() && !this.all2) {
         closeHandledScreenEvent.runVoid();
         this.defer(this::getAll);
      }
   }

   private void getAll2() {
      for (KeyBinding keyBinding : this.class_3041) {
         keyBinding.setPressed(InputUtil.isKeyPressed(mc.getWindow(), Utils9.withClass_3046(keyBinding).getCode()));
      }

      if (!ToggleableUtils.autoSprint.isEnabled()) {
         mc.options.sprintKey.setPressed(InputUtil.isKeyPressed(mc.getWindow(), Utils9.withClass_3046(mc.options.sprintKey).getCode()));
      }
   }

   @Api8(event = ClickSlotEvent.class)
   public void onClickSlot(ClickSlotEvent clickSlotEvent) {
      if (!BThackClient.isInitialized()) {
         clickSlotEvent.runVoid();
         this.defer(() -> GuiMove.onClickSlot2(clickSlotEvent));
      }
   }

   private Boolean isAll() {
      return !(Boolean)this.all.getValue();
   }

   public void defer(Runnable runnable) {
      if (InputUtils.isClass_7443(mc.player.input) && (Boolean)this.grim.getValue()) {
         this.int1 = 1;
         AtomicReference<Helper8<MinecraftClientTickEvent>> atomicReference = new AtomicReference<>();
         atomicReference.set(new Helper8<>(MinecraftClientTickEvent.class, (obj) -> this.handleRunnable(runnable, atomicReference, obj)));
         BThackClient.getInstance().getEventBus().subscribe(atomicReference.get());
      } else {
         runnable.run();
      }
   }

   private static void onClickSlot2(ClickSlotEvent clickSlotEvent) {
      Utils9.handleClass_63613(
         mc.interactionManager, clickSlotEvent.int1, clickSlotEvent.int2, clickSlotEvent.x, clickSlotEvent.slotActionType, clickSlotEvent.playerEntity
      );
   }

   @Api8(event = MinecraftClientTickEvent.class)
   public void onTick2() {
      if ((Boolean)this.all.getValue() || this.isMc() || this.isChat() || this.isUi() || this.isSettings()) {
         this.getAll2();
      }
   }

   private boolean isSettings() {
      return (Boolean)this.settings.getValue()
         && (
            mc.currentScreen instanceof GameOptionsScreen
               || mc.currentScreen instanceof ChatOptionsScreen
               || mc.currentScreen instanceof ControlsOptionsScreen
               || mc.currentScreen instanceof CreditsAndAttributionScreen
               || mc.currentScreen instanceof FontOptionsScreen
               || mc.currentScreen instanceof LanguageOptionsScreen
               || mc.currentScreen instanceof OnlineOptionsScreen
               || mc.currentScreen instanceof OptionsScreen
               || mc.currentScreen instanceof SkinOptionsScreen
               || mc.currentScreen instanceof SoundOptionsScreen
               || mc.currentScreen instanceof TelemetryInfoScreen
               || mc.currentScreen instanceof VideoOptionsScreen
               || mc.currentScreen instanceof GameMenuScreen
         );
   }

   private boolean isUi() {
      return (Boolean)this.ui.getValue() && mc.currentScreen instanceof ScreenHelperSub1;
   }

   private void getAll() {
      this.all2 = true;
      mc.player.closeHandledScreen();
      this.all2 = false;
   }

   private Boolean isAll2() {
      return !(Boolean)this.all.getValue();
   }

   @Api8(event = TickMovementEvent.class)
   public void onTickMovement() {
      if (this.int1 > 0) {
         InputUtils.withClass_7441(mc.player.input, 0.0F, 0.0F);
         mc.player.input.playerInput = PlayerInput.DEFAULT;
         this.int1--;
      }
   }

   private void handleRunnable(Runnable runnable, AtomicReference atomicReference, MinecraftClientTickEvent obj) {
      if (this.int1 < 1) {
         runnable.run();
         BThackClient.getInstance().getEventBus().unsubscribe(atomicReference.get());
      }
   }
}
