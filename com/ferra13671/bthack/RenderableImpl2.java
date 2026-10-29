package com.ferra13671.bthack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.util.TriConsumer;

public abstract class RenderableImpl2 extends Element implements Renderable {
   final transient SearchComponent searchComponent;
   transient Float scrollY = 0.0F;
   transient float float1 = 0.0F;
   final transient List<ElementSub2> children = new CopyOnWriteArrayList();
   final transient Api2Impl api2Impl = new Api2Impl(this::handleApi2Impl8);
   transient String string1 = "";

   private void handleInt(int num, int num2, ElementSub2 elementSub2) {
      ElementUtils.updateHover(elementSub2, this, num, num2 - this.scrollY.intValue());
   }

   private static void handleXYButton1(XYButton xYButton, ElementSub2 elementSub2) {
      ElementUtils.handleClick(elementSub2, xYButton);
   }

   public SearchComponent getSearchComponent() {
      return this.searchComponent;
   }

   private void handleNamedObject2(NamedObject namedObject, Integer integer, Integer integer2) {
      this.children.add(new ElementSub2(this.eventBus, integer.intValue(), integer2.intValue(), this, namedObject));
   }

   @Override
   public void close() {
      super.close();
      this.api2Impl.close();
      this.children.forEach(ElementSub2::close);
   }

   public abstract void handleXYButton3(XYButton var1, NamedObject var2);

   protected void handleElementHelper4(ElementHelper elementHelper) {
      this.string1 = elementHelper.getStringBuilder();
      this.render4();
      this.scrollY = EntityUtils.getFloat6(this.scrollY, this.float1, this.heightOffset);
   }

   public abstract List<NamedObject> applyList5();

   @Override
   public void handlePointAmount(PointAmount pointAmount) {
      this.scrollY = this.scrollY + pointAmount.getAmount() * 30.0F;
      this.scrollY = EntityUtils.getFloat6(this.scrollY, this.float1, this.heightOffset);
   }

   @Override
   public void onClick(XYButton xYButton) {
      XYButton xYButton2 = EntityUtils.relativize(xYButton, (int)this.getWidth(), (int)this.getHeight() - this.scrollY.intValue());
      this.children.forEach((elementSub2) -> RenderableImpl2.handleXYButton1(xYButton, elementSub2));
   }

   private static void handleApi2Impl6(Api2Impl api2Impl, ElementSub2 elementSub2) {
      elementSub2.handleApi2Impl(api2Impl);
   }

   public Float getScrollY() {
      return this.scrollY;
   }

   public RenderableImpl2(EventBus eventBus, float flt, float flt2, SearchComponent searchComponent) {
      super(eventBus, flt, flt2, Utils4.vector2i23.x, Utils4.vector2i23.y - 3 - 56 - 46);
      this.searchComponent = searchComponent;
      this.runVoid7();
      this.api2Impl.render4();
   }

   @Override
   public void setPosition(int num, int num2) {
      int num3 = num - (int)this.getWidth();
      int num4 = num2 - (int)this.getHeight();
      super.setPosition(num3, num4);
      RendererInit.handleInt1(
         (int)(this.searchComponent.getWidth() + this.getWidth()),
         (int)(this.searchComponent.getHeight() + this.getHeight()),
         (int)this.getWidthOffset(),
         (int)this.getHeightOffset()
      );
      this.applyTransform(0.0F, this.scrollY);
      this.api2Impl.render4();
      this.children.forEach((elementSub2) -> this.handleInt(num, num2, elementSub2));
      this.api2Impl.close2();
      this.popMatrix();
      RendererInit.runVoid8();
   }

   private void setFloat(List<NamedObject> list, TriConsumer<NamedObject, Integer, Integer> triConsumer) {
      int num = 10;
      int num2 = 10;
      float flt = 0.0F;

      for (NamedObject namedObject : list) {
         triConsumer.accept(namedObject, num, num2);
         if (flt < num2) {
            flt = num2;
         }

         num += 42;
         if (num + 10 >= this.getWidthOffset()) {
            num = 10;
            num2 += 42;
         }
      }

      this.float1 = flt + 38.0F + 10.0F;
   }

   private void runVoid7() {
      this.searchComponent.getComponentHelper().handleString(null);
      this.children.forEach(ElementSub2::close);
      this.children.clear();
      this.setFloat(this.applyList5().stream().filter(this::isNamedObject9).toList(), this::handleNamedObject2);
   }

   @Override
   public void render4() {
      this.runVoid7();
      this.api2Impl.render4();
   }

   private void handleApi2Impl8(Api2Impl api2Impl) {
      this.children.forEach((elementSub2) -> RenderableImpl2.handleApi2Impl6(api2Impl, elementSub2));
   }

   private boolean isNamedObject9(NamedObject namedObject) {
      return this.string1.isEmpty()
         || namedObject.getName().toLowerCase().contains(this.string1.toLowerCase())
         || namedObject.getString1().toLowerCase().contains(this.string1.toLowerCase());
   }
}
