package com.example.damagehud.mixin.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientLevel.class)
public class FootstepCheckMixin {
//    @Inject(at = @At("HEAD"), method = "playSound")
//    private void onPlay(double d, double e, double f, SoundEvent soundEvent, SoundSource soundSource, float g, float h, boolean bl, long l, CallbackInfo ci) {
//        ResourceKey<SoundEvent> key = BuiltInRegistries.SOUND_EVENT.getResourceKey(soundEvent).orElse(null);
//        if (key != null && key.registry().getPath().contains("step")) {
//            Vec3 pos = new Vec3(d, e, f);
//        }
//    }
}