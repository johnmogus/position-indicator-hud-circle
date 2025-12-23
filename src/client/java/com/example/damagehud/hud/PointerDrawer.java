package com.example.damagehud.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class PointerDrawer {
    public static void drawPointerTowardsPosition(
            GuiGraphics guiGraphics,
            Player player,
            Vec3 position,
            Identifier sprite,
            int u,
            int v,
            int width,
            int height,
            int textureWidth,
            int textureHeight,
            int baseColor)
    {
        Vec3 relativePos = position.subtract(player.position());
        double angle = (Math.atan2(relativePos.z, relativePos.x) - player.getYRot() * Math.PI / 180) - Math.PI/2;
        double distanceFromPlayer = player.position().distanceTo(position);
        int alphaValue = (int) Math.clamp(distanceFromPlayer, 0, 255); //TODO: FIX THIS CRAP
        int color = (alphaValue << 24) | baseColor;

        Minecraft client = Minecraft.getInstance();
        int cx = client.getWindow().getGuiScaledWidth() / 2;
        int cy = client.getWindow().getGuiScaledHeight() / 2;
        int radius = cy / 2;

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().rotateAbout((float) (angle), cx, cy);
        guiGraphics.blit(
                RenderPipelines.GUI_TEXTURED,
                sprite,
                cx - width / 2,
                cy - radius - width / 2,
                u,
                v,
                width,
                height,
                textureWidth,
                textureHeight,
                color);
        guiGraphics.pose().popMatrix();

    }

}
