package com.example.damagehud;

import com.example.damagehud.hud.PointerDrawer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
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

    public static List<Entity> nearbyEntities;

	@Override
	public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            LocalPlayer player = client.player;
            if (player == null) return;
            nearbyEntities = getNearbyEntities(player, 20);

        });

        HudElementRegistry.addFirst(SPRITE, (guiGraphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();
            LocalPlayer player = client.player;
            if (player == null) return;

            for (Entity entity : nearbyEntities) {
                int u = 0, v = 0, width = 32, height = 8, textureWidth = 32, textureHeight = 8;
                PointerDrawer.drawPointerTowardsPosition(
                        guiGraphics,
                        player,
                        entity.position(),
                        SPRITE,
                        u,
                        v,
                        width,
                        height,
                        textureWidth,
                        textureHeight,
                        0x00FFFFFF);

            }
        });

	}

    public List<Entity> getNearbyEntities(Player player, double radius) {
        AABB box = player.getBoundingBox().inflate(radius);
        return player.level().getEntities(player, box, e -> true);
    }

}