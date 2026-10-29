package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public final class HudUtils {
   static final transient List<Hud> list1 = new ArrayList();
   public static final transient FPSInfo fPSInfo = (FPSInfo)subscribe(new FPSInfo());
   public static final transient InventoryInfo inventoryInfo = (InventoryInfo)subscribe(new InventoryInfo());
   public static final transient PingInfo pingInfo = (PingInfo)subscribe(new PingInfo());
   public static final transient PlayersInfo playersInfo = (PlayersInfo)subscribe(new PlayersInfo());
   public static final transient PositionInfo positionInfo = (PositionInfo)subscribe(new PositionInfo());
   public static final transient RotationInfo rotationInfo = (RotationInfo)subscribe(new RotationInfo());
   public static final transient SpeedInfo speedInfo = (SpeedInfo)subscribe(new SpeedInfo());
   public static final transient TPSInfo tPSInfo = (TPSInfo)subscribe(new TPSInfo());
   public static final transient WatermarkInfo watermarkInfo = (WatermarkInfo)subscribe(new WatermarkInfo());

   HudUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private static Hud subscribe(Hud hud) {
      list1.addLast(hud);
      return hud;
   }

   public static List<Hud> getList() {
      return list1;
   }

   static {
      list1.forEach(Api4::getEnabled);
   }
}
