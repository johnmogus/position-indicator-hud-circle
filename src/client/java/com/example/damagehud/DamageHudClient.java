package com.example.damagehud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DamageHudClient implements ClientModInitializer {
    public static final String MOD_ID = "damage-hud";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final Identifier SPRITE = Identifier.fromNamespaceAndPath("damage-hud", "textures/gui/footsteps_indicator.png");

	@Override
	public void onInitializeClient() {

        HudElementRegistry.addFirst(SPRITE, (guiGraphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();
            LocalPlayer player = client.player;
            if (player == null) return;

            List<Entity> nearbyEntities = getNearbyEntities(player, 20);

            for (Entity entity : nearbyEntities) {

                int u = 0, v = 0, width = 32, height = 8, textureWidth = 32, textureHeight = 8;

                Vec3 relativePos = entity.position().subtract(player.position());
                double angle = (Math.atan2(relativePos.z, relativePos.x) - player.getYRot() * Math.PI / 180) - Math.PI/2;

                // Convert angle to screen position (circle around crosshair)
                int cx = client.getWindow().getGuiScaledWidth() / 2;
                int cy = client.getWindow().getGuiScaledHeight() / 2;
                int radius = cy / 2;

                guiGraphics.pose().pushMatrix();
                guiGraphics.pose().rotateAbout((float) (angle), cx, cy);
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE, cx - width / 2, cy - radius - width / 2, u, v, width, height, textureWidth, textureHeight);
                guiGraphics.pose().popMatrix();

            }
        });

	}

    public static List<Entity> getNearbyEntities(Player player, double radius) {
        AABB box = player.getBoundingBox().inflate(radius);

        return player.level().getEntities(player, box, e -> true);
    }

}