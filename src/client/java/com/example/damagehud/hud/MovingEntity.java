package com.example.damagehud.hud;

import net.minecraft.world.phys.Vec3;

public class MovingEntity {
    private final Vec3 position;
    private final double INITIAL_TIME = 40;
    private double timer = INITIAL_TIME;

    public MovingEntity(Vec3 position) {
        this.position = position;
    }

    public Vec3 getPosition() {return position;}
    public void setTime(double time) {this.timer = time;}
    public void subtractTime(double time) {this.timer -= time;}
    public double getTime() {return timer;}

}
