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
        Vec3 relativePosition = position.subtract(player.position());
        double distanceFromPlayer = relativePosition.distanceTo(Vec3.ZERO);

        double angle = (Math.atan2(relativePosition.z, relativePosition.x) - player.getYRot() * Math.PI / 180) - Math.PI/2;
        int alphaValue = (int) Math.clamp((-distanceFromPlayer) * 12.75 + 255, 0, 255);
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
