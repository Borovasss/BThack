package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.minecraft.RectColors;
import com.ferra13671.cometrenderer.minecraft.RenderColor;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedBlurBatch;
import com.ferra13671.cometrenderer.minecraft.batch.impl.RoundedRectBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class HudElement implements Hud {
   final transient HudInfo hudInfo = this.getClass().getAnnotation(HudInfo.class);
   final transient String name = this.hudInfo.name();
   final transient String getname = this.name.toLowerCase().replace(" ", "-");
   protected transient float width = this.hudInfo.x();
   protected transient float height = this.hudInfo.y();
   protected transient float widthOffset = this.hudInfo.width();
   protected transient float heightOffset = this.hudInfo.height();
   transient Api1Impl moduleState;
   transient List settings = null;
   transient boolean enabled = this.hudInfo.enabled();
   transient BiConsumer biConsumer1;

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public String getGetname() {
      return this.getname;
   }

   @Override
   public void setEnabled(boolean bool, boolean bool2) {
      if (this.isEnabled() && !bool) {
         this.enabled = false;
         this.onDisable2();
         if (this.biConsumer1 != null) {
            this.biConsumer1.accept(this, false);
         }
      }

      if (!this.isEnabled() && bool) {
         this.enabled = true;
         this.onEnable3();
         if (this.biConsumer1 != null) {
            this.biConsumer1.accept(this, true);
         }
      }
   }

   @Override
   public void getEnabled() {
   }

   protected void onDisable2() {
      BThackClient.getInstance().getEventBus().unsubscribe(this);
   }

   @Override
   public void render(Api2Impl api2Impl) {
   }

   @Override
   public List getSettings() {
      if (this.settings == null) {
         this.settings = this.buildSettings();
      }

      return this.settings;
   }

   @Override
   public float getWidthOffset() {
      return this.widthOffset;
   }

   private List buildSettings() {
      List<Setting<?>> list = new ArrayList<>();
      Utils.getSettings(this.getClass(), this, list);
      list.forEach(this::handleSetting);
      return list;
   }

   private void handleSetting(Setting setting) {
      setting.setString("hud-element.".concat(this.getGetname()));
   }

   @Override
   public CategoryInfoApi getCategoryInfoApi() {
      return CategoryUtils.categoryInfoApi;
   }

   @Override
   public float getHeight() {
      return this.height;
   }

   protected void onEnable3() {
      BThackClient.getInstance().getEventBus().subscribe(this);
   }

   @Override
   public float getHeightOffset() {
      return this.heightOffset;
   }

   @Override
   public String getName() {
      return this.name;
   }

   protected void renderBackground(Api2Impl api2Impl) {
      this.renderBackground(api2Impl, this.getWidth(), this.getHeight(), this.getWidthOffset(), this.getHeightOffset());
   }

   @Override
   public void handleBiConsumer(BiConsumer biConsumer) {
      this.biConsumer1 = biConsumer;
   }

   @Override
   public void setX(float flt) {
      this.width = flt;
   }

   protected void renderBackground(Api2Impl api2Impl, float flt, float flt2, float flt3, float flt4, float flt5) {
      ((RoundedBlurBatch)api2Impl.getBatch(HudTextures.nameBatchCreator1)).rectSized(flt, flt2, flt3, flt4, flt5);
      ((RoundedRectBatch)api2Impl.getBatch(HudTextures.nameBatchCreator2)).rectSized(flt, flt2, flt3, flt4, flt5, RectColors.oneColor(Utils4.renderColor1));
      ((AbstractPrimitiveBatchHelper)api2Impl.getBatch(HudTextures.nameBatchCreator3))
         .withFloat2(flt, flt2, flt3, flt4, flt5, 2.0F, RectColors.oneColor(RenderColor.TRANSLUCENT), RectColors.oneColor(Utils4.api2Impl2));
   }

   @Override
   public void setY(float flt) {
      this.height = flt;
   }

   @Override
   public void setHeight(float flt) {
      this.heightOffset = flt;
   }

   protected void renderBackground(Api2Impl api2Impl, float flt, float flt2, float flt3, float flt4) {
      this.renderBackground(api2Impl, flt, flt2, flt3, flt4, Math.min(20.0F, flt4 / 2.0F));
   }

   @Override
   public float getWidth() {
      return this.width;
   }

   @Override
   public Api1Impl getModuleState() {
      if (this.moduleState == null) {
         this.moduleState = new WidgetState(this);
      }

      return this.moduleState;
   }

   @Override
   public void setWidth(float flt) {
      this.widthOffset = flt;
   }

   @Override
   public void getApi2Impl2() {
   }
}
