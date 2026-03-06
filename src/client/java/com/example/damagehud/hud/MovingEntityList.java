package com.example.damagehud.hud;

import net.minecraft.world.entity.Entity;

import java.util.List;

public class MovingEntityList {
    private static List<MovingEntity> movingEntities;

    public void addEntities(List<Entity> movingEntities) {
        this.movingEntities.addAll();
    }

    public static List<MovingEntity>

    public static List<Entity> getMovingEntities() {
        return movingEntities;
    }
}
