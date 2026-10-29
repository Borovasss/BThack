package com.ferra13671.bthack;

public final class ElementUtils {
   ElementUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static void handleClick(Element element, XYButton xYButton) {
      element.onMouseRelease(xYButton);
      if ((Boolean)element.getHelper35().getValue() && element.isHovered(xYButton.getX2(), xYButton.getX())) {
         element.onClick(xYButton);
      }
   }

   public static void handlePointClick(Element element, PointAmount pointAmount) {
      if ((Boolean)element.getHelper35().getValue() && element.isHovered(pointAmount.getX2(), pointAmount.getX())) {
         element.handlePointAmount(pointAmount);
      }
   }

   public static void updateHover(Element element, Element element2, int num, int num2) {
      element.getHelper35().setValue2((element2 == null || (Boolean)element2.getHelper35().getValue()) && element.isHovered(num, num2));
      element.setPosition(num, num2);
   }
}
