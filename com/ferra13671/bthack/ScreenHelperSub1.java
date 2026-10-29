package com.ferra13671.bthack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;

public class ScreenHelperSub1 extends ScreenHelper {
   final transient ComponentHelper2 componentHelper2 = new ComponentHelper2(this.eventBus, this);
   final transient List<Component> screens = new CopyOnWriteArrayList();
   final transient ComponentHelper componentHelper = new ComponentHelper(this.eventBus);

   public boolean mouseReleased(Click click) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_11909(click));
      return false;
   }

   public boolean charTyped(CharInput charInput) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_1190517(charInput));
      return false;
   }

   private void handleClass_11909(Click click) {
      XYButton xYButton = new XYButton(Utils11.getMouseX(), Utils11.getMouseY(), click.button());
      this.componentHelper2.onMouseMove(xYButton);
      this.screens.forEach((component) -> ScreenHelperSub1.handleXYButton10(xYButton, component));
   }

   public void renderBackground(DrawContext drawContext, int num, int num2, float flt) {
   }

   public void mouseMoved(double dbl, double dbl2) {
      BThackClient.getInstance().getShutdownExecutor().execute(this::runVoid11);
   }

   @Api8(event = EventSub3.class)
   public void handleEventSub31(EventSub3 eventSub3) {
      this.componentHelper.handleString(null);
   }

   private void handleClass_119082(KeyInput keyInput) {
      this.componentHelper2.onKeyPress(keyInput.key());
      this.screens.forEach((component) -> ScreenHelperSub1.handleClass_119085(keyInput, component));
   }

   @Override
   public void setPosition(int num2, int num3) {
      this.componentHelper2.setPosition(num2, num3);
      if (!this.screens.isEmpty()) {
         boolean bool = false;

         for (int num = 0; num < this.screens.size(); num++) {
            Component component = (Component)this.screens.get(num);
            Component component2 = (Component)this.screens.get(this.screens.size() - 1 - num);
            component.setPosition(num2, num3);
            if (!bool) {
               if (component2.isHovered(num2, num3)) {
                  bool = true;
               }

               component2.getHelper35().setValue2(bool);
            } else {
               component2.getHelper35().setValue2(false);
            }
         }

         this.componentHelper2.getHelper35().setValue2(this.componentHelper2.isHovered(num2, num3) && !bool);
      } else {
         this.componentHelper2.getHelper35().setValue2(this.componentHelper2.isHovered(num2, num3));
      }

      ElementUtils.updateHover(this.componentHelper, null, num2, num3);
   }

   @Api8(event = EventSub15.class, priority = -1000)
   public void runVoid3() {
      this.screens.forEach(Component::render4);
      this.componentHelper.render4();
   }

   @Api8(event = EventSub1.class)
   public void handleEventSub14(EventSub1 eventSub1) {
      this.screens.addLast(eventSub1.component);
   }

   private static void handleClass_119085(KeyInput keyInput, Component component) {
      component.onKeyPress(keyInput.key());
   }

   public boolean mouseClicked(Click click, boolean bool) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_119099(click));
      return false;
   }

   private static void handleXYButton6(XYButton xYButton, Component component) {
      ElementUtils.handleClick(component, xYButton);
   }

   private static void handlePointAmount7(PointAmount pointAmount, Component component) {
      ElementUtils.handlePointClick(component, pointAmount);
   }

   @Api8(event = EventSub13.class)
   public void handleEventSub138(EventSub13 eventSub13) {
      this.screens.remove(eventSub13.component);
      eventSub13.component.close();
   }

   private void handleClass_119099(Click click) {
      XYButton xYButton = new XYButton(Utils11.getMouseX(), Utils11.getMouseY(), click.button());
      ElementUtils.handleClick(this.componentHelper2, xYButton);
      this.screens.forEach((component) -> ScreenHelperSub1.handleXYButton6(xYButton, component));
   }

   private static void handleXYButton10(XYButton xYButton, Component component) {
      component.onMouseMove(xYButton);
   }

   private void runVoid11() {
      int num = Utils11.getMouseX();
      int num2 = Utils11.getMouseY();
      this.componentHelper2.setSize(num, num2);
      this.screens.forEach((component) -> ScreenHelperSub1.handleInt(num, num2, component));
      this.componentHelper.setSize(num, num2);
   }

   private void handleDouble12(double dbl) {
      PointAmount pointAmount = new PointAmount(Utils11.getMouseX(), Utils11.getMouseY(), (float)dbl);
      ElementUtils.handlePointClick(this.componentHelper2, pointAmount);
      this.screens.forEach((component) -> ScreenHelperSub1.handlePointAmount7(pointAmount, component));
   }

   public boolean keyPressed(KeyInput keyInput) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleClass_119082(keyInput));
      return super.keyPressed(keyInput);
   }

   private static void handleClass_1190513(CharInput charInput, Component component) {
      component.onCharTyped(charInput.codepoint());
   }

   private static void handleInt(int num, int num2, Component component) {
      component.setSize(num, num2);
   }

   public boolean shouldPause() {
      return false;
   }

   public boolean mouseScrolled(double dbl, double dbl2, double dbl3, double dbl4) {
      BThackClient.getInstance().getShutdownExecutor().execute(() -> this.handleDouble12(dbl));
      return false;
   }

   public ComponentHelper getComponentHelper() {
      return this.componentHelper;
   }

   @Override
   public void close6() {
      this.componentHelper2.close6();
      if (!this.screens.isEmpty()) {
         for (Component component : this.screens) {
            component.close6();
         }
      }
   }

   @Api8(event = EventSub19.class)
   public void runVoid15() {
      this.screens.forEach(this::handleComponent18);
      this.componentHelper.handleString(null);
      ToggleableUtils.clickUI.boolean1 = true;
      ToggleableUtils.clickUI.setEnabled(false, true);
      ToggleableUtils.clickUI.boolean1 = false;
      BThackClient.getInstance().getClientPaths().save();
   }

   @Api8(event = EventSub21.class)
   public void handleEventSub2116(EventSub21 eventSub21) {
      this.componentHelper.handleString(BThackClient.getInstance().getTranslationManager().translate(eventSub21.setting.getListSetting3()));
   }

   private void handleClass_1190517(CharInput charInput) {
      this.componentHelper2.onCharTyped(charInput.codepoint());
      this.screens.forEach((component) -> ScreenHelperSub1.handleClass_1190513(charInput, component));
   }

   private void handleComponent18(Component component) {
      this.getEventBus().register(new EventSub13(component));
   }
}
