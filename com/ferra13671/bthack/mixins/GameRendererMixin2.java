package com.ferra13671.bthack.mixins;

import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameRenderer.class)
public interface GameRendererMixin2 {
   @Accessor("guiRenderer")
   GuiRenderer applyClass_11228();
}
