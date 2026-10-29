package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerHelper extends Manager implements MinecraftInstance {
   final transient List<Hud> elements = new ArrayList();
   final transient Api2Impl renderContext = new Api2Impl(this::closeElement);

   @Api8(event = OnResizedEvent.class)
   public void onResize(OnResizedEvent onResizedEvent) {
      this.render4();
   }

   public void render4() {
      this.renderContext.render4();
   }

   private void addHudElement(Hud hud, Boolean boolean_) {
      this.render4();
   }

   @Api8(event = EventSub18.class, priority = Integer.MIN_VALUE)
   public void init() {
      if (!(mc.currentScreen instanceof ScreenHelperSub2)) {
         this.close2();
      }
   }

   public List<Hud> getElements() {
      return Collections.unmodifiableList(this.elements);
   }

   public ManagerHelper() {
      HudUtils.getList().forEach(this::removeHudElement);
      this.renderContext.render4();
      BThackClient.getInstance().getEventBus().subscribe(this);
      BThackClient.getInstance().getAtlasManager().handleGLTexture1(RendererInit.config.gLTexture5);
   }

   public void removeHudElement(Hud hud) {
      this.getAtlas3();
      this.elements.add(hud);
      hud.handleBiConsumer(this::addHudElement);
   }

   private void closeElement(Api2Impl api2Impl) {
      for (Hud hud : this.elements) {
         if (hud.isEnabled()) {
            hud.render(api2Impl);
         }
      }
   }

   public void close2() {
      this.renderContext.close2();

      for (Hud hud : this.elements) {
         if (hud.isEnabled()) {
            hud.getApi2Impl2();
         }
      }
   }
}
