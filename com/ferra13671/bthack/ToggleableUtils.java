package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public final class ToggleableUtils {
   static final transient List<Toggleable> list1 = new ArrayList();
   public static final transient ClickUI clickUI = (ClickUI)subscribe(new ClickUI());
   public static final transient AutoSave autoSave = (AutoSave)subscribe(new AutoSave());
   public static final transient Aura aura = (Aura)subscribe(new Aura());
   public static final transient AutoArmor autoArmor = (AutoArmor)subscribe(new AutoArmor());
   public static final transient AutoOffhand autoOffhand = (AutoOffhand)subscribe(new AutoOffhand());
   public static final transient NoFriendDamage noFriendDamage = (NoFriendDamage)subscribe(new NoFriendDamage());
   public static final transient Auto2FA auto2FA = (Auto2FA)subscribe(new Auto2FA());
   public static final transient AutoAuth autoAuth = (AutoAuth)subscribe(new AutoAuth());
   public static final transient AutoSign autoSign = (AutoSign)subscribe(new AutoSign());
   public static final transient ElytraSwap elytraSwap = (ElytraSwap)subscribe(new ElytraSwap());
   public static final transient FastUse fastUse = (FastUse)subscribe(new FastUse());
   public static final transient FreeCamera freeCamera = (FreeCamera)subscribe(new FreeCamera());
   public static final transient InventoryTweaks inventoryTweaks = (InventoryTweaks)subscribe(new InventoryTweaks());
   public static final transient MultiTask multiTask = (MultiTask)subscribe(new MultiTask());
   public static final transient XCarry xCarry = (XCarry)subscribe(new XCarry());
   public static final transient AutoSprint autoSprint = (AutoSprint)subscribe(new AutoSprint());
   public static final transient Blink blink = (Blink)subscribe(new Blink());
   public static final transient ElytraBoost elytraBoost = (ElytraBoost)subscribe(new ElytraBoost());
   public static final transient ElytraRecast elytraRecast = (ElytraRecast)subscribe(new ElytraRecast());
   public static final transient Freeze freeze = (Freeze)subscribe(new Freeze());
   public static final transient GuiMove guiMove = (GuiMove)subscribe(new GuiMove());
   public static final transient JumpDelay jumpDelay = (JumpDelay)subscribe(new JumpDelay());
   public static final transient SafeWalk safeWalk = (SafeWalk)subscribe(new SafeWalk());
   public static final transient Timer timer = (Timer)subscribe(new Timer());
   public static final transient Ambience ambience = (Ambience)subscribe(new Ambience());
   public static final transient Arrows arrows = (Arrows)subscribe(new Arrows());
   public static final transient BlockOutline blockOutline = (BlockOutline)subscribe(new BlockOutline());
   public static final transient Breadcrumbs breadcrumbs = (Breadcrumbs)subscribe(new Breadcrumbs());
   public static final transient ContainerESP containerESP = (ContainerESP)subscribe(new ContainerESP());
   public static final transient FullBright fullBright = (FullBright)subscribe(new FullBright());
   public static final transient HandView handView = (HandView)subscribe(new HandView());
   public static final transient Nametags nametags = (Nametags)subscribe(new Nametags());
   public static final transient NoRender noRender = (NoRender)subscribe(new NoRender());
   public static final transient PlayerESP playerESP = (PlayerESP)subscribe(new PlayerESP());
   public static final transient Tooltips tooltips = (Tooltips)subscribe(new Tooltips());
   public static final transient Tracers tracers = (Tracers)subscribe(new Tracers());
   public static final transient Xray xray = (Xray)subscribe(new Xray());
   public static final transient Zoom zoom = (Zoom)subscribe(new Zoom());

   public static List<Toggleable> getList() {
      return list1;
   }

   ToggleableUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      list1.forEach(Api4::getEnabled);
   }

   private static Toggleable subscribe(Toggleable toggleable) {
      list1.addLast(toggleable);
      return toggleable;
   }
}
