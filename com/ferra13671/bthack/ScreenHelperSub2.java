package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;

public class ScreenHelperSub2 extends ScreenHelper {
   final transient List<BooleanSetting> list1 = new ArrayList(BThackClient.getInstance().getManagerHelper().getElements().size());
   final transient Map<Setting, Hud> map1 = new HashMap(BThackClient.getInstance().getManagerHelper().getElements().size());
   final transient List<ElementSub5> list2 = new ArrayList(BThackClient.getInstance().getManagerHelper().getElements().size());
   final transient ListSettingApi listSettingApi = this::getList;
   protected transient ComponentSub4 componentSub4;

   private static void handleClass_11908(KeyInput keyInput, ElementSub5 elementSub5) {
      elementSub5.onKeyPress(keyInput.key());
   }

   private void setComponentSub4(Click click) {
      XYButton xYButton = new XYButton(Utils11.getMouseX(), Utils11.getMouseY(), click.button());
      if (click.button() == 1 && (this.componentSub4 == null || !(Boolean)this.componentSub4.getHelper35().getValue())) {
         if (this.componentSub4 != null) {
            this.componentSub4.close();
         }

         this.componentSub4 = new ComponentSub4(this.eventBus, this.listSettingApi, xYButton.getX2(), xYButton.getX());
      } else if (this.componentSub4 != null) {
         ElementUtils.handleClick(this.componentSub4, xYButton);
         if (!(Boolean)this.componentSub4.getHelper35().getValue()) {
            this.componentSub4.close();
            this.componentSub4 = null;
         }
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handleXYButton(xYButton, elementSub5));
   }

   private static void handleSetting1(Setting setting, Hud hud) {
      ((BooleanSetting)setting).setValue2(hud.isEnabled());
   }

   protected void init() {
      this.map1.forEach(ScreenHelperSub2::handleSetting1);
   }

   private void handleClass_119092(Click click) {
      XYButton xYButton = new XYButton(Utils11.getMouseX(), Utils11.getMouseY(), click.button());
      if (this.componentSub4 != null) {
         this.componentSub4.onMouseMove(xYButton);
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handleXYButton9(xYButton, elementSub5));
   }

   private static void handleInt3(int num, int num2, ElementSub5 elementSub5) {
      elementSub5.setSize(num, num2);
   }

   public ScreenHelperSub2() {
      for (Hud hud : BThackClient.getInstance().getManagerHelper().getElements()) {
         BooleanSetting booleanSetting = ((BooleanSetting.BooleanSettingBuilder)((BooleanSetting.BooleanSettingBuilder)BooleanSetting.builder()
                  .name(hud.getName()))
               .value(hud.isEnabled()))
            .build();
         this.list1.add(booleanSetting);
         this.map1.put(booleanSetting, hud);
         this.list2.add(new ElementSub5(this.eventBus, hud, this));
      }

      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   private List getList() {
      return this.list1;
   }

   @Api8(event = EventSub19.class)
   public void setComponentSub42() {
      if (this.componentSub4 != null) {
         this.componentSub4.close();
         this.componentSub4 = null;
      }
   }

   public boolean charTyped(CharInput charInput) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_119056(charInput));
      return false;
   }

   @Override
   public void setPosition(int num, int num2) {
      BThackClient.getInstance().getManagerHelper().close2();
      if (this.componentSub4 != null) {
         ElementUtils.updateHover(this.componentSub4, null, num, num2);
         if ((Boolean)this.componentSub4.getHelper35().getValue()) {
            return;
         }
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handleInt12(num, num2, elementSub5));
   }

   public boolean keyPressed(KeyInput keyInput) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_119087(keyInput));
      return super.keyPressed(keyInput);
   }

   public boolean mouseReleased(Click click) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_119092(click));
      return false;
   }

   public boolean shouldPause() {
      return false;
   }

   private void runVoid4() {
      int num = Utils11.getMouseX();
      int num2 = Utils11.getMouseY();
      if (this.componentSub4 != null) {
         this.componentSub4.setSize(num, num2);
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handleInt3(num, num2, elementSub5));
   }

   private static void handlePointAmount5(PointAmount pointAmount, ElementSub5 elementSub5) {
      ElementUtils.handlePointClick(elementSub5, pointAmount);
   }

   private void handleClass_119056(CharInput charInput) {
      if (this.componentSub4 != null) {
         this.componentSub4.onCharTyped(charInput.codepoint());
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handleClass_1190510(charInput, elementSub5));
   }

   private void handleClass_119087(KeyInput keyInput) {
      if (this.componentSub4 != null) {
         this.componentSub4.onKeyPress(keyInput.key());
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handleClass_11908(keyInput, elementSub5));
   }

   public boolean mouseScrolled(double dbl, double dbl2, double dbl3, double dbl4) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleDouble8(dbl));
      return false;
   }

   private void handleDouble8(double dbl) {
      PointAmount pointAmount = new PointAmount(Utils11.getMouseX(), Utils11.getMouseY(), (float)dbl);
      if (this.componentSub4 != null) {
         ElementUtils.handlePointClick(this.componentSub4, pointAmount);
      }

      this.list2.forEach((elementSub5) -> ScreenHelperSub2.handlePointAmount5(pointAmount, elementSub5));
   }

   private static void handleXYButton9(XYButton xYButton, ElementSub5 elementSub5) {
      elementSub5.onMouseMove(xYButton);
   }

   public void renderBackground(DrawContext drawContext, int num, int num2, float flt) {
   }

   @Override
   public void close6() {
      this.list2.forEach(Helper20::close6);
      if (this.componentSub4 != null) {
         this.componentSub4.close6();
      }
   }

   private static void handleClass_1190510(CharInput charInput, ElementSub5 elementSub5) {
      elementSub5.onCharTyped(charInput.codepoint());
   }

   private static void handleXYButton(XYButton xYButton, ElementSub5 elementSub5) {
      ElementUtils.handleClick(elementSub5, xYButton);
   }

   private static void handleInt12(int num, int num2, ElementSub5 elementSub5) {
      elementSub5.getHelper35().setValue2(elementSub5.isHovered(num, num2));
   }

   public boolean mouseClicked(Click click, boolean bool) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.setComponentSub4(click));
      return false;
   }

   @Api8(event = EventSub12.class)
   public void handleEventSub1213(EventSub12 eventSub12) {
      if (this.map1.containsKey(eventSub12.setting)) {
         ((Hud)this.map1.get(eventSub12.setting)).setEnabled((Boolean)((BooleanSetting)eventSub12.setting).getValue(), false);
      }
   }

   public void mouseMoved(double dbl, double dbl2) {
      BThackClient.getInstance().getShutdownExecutor().execute(this::runVoid4);
   }
}
