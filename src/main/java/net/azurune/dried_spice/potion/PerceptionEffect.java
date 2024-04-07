package net.azurune.dried_spice.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class PerceptionEffect extends MobEffect {
    public PerceptionEffect(MobEffectCategory category, int i) {
        super(category, i);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int pAmplifier) {
        List<LivingEntity> list = living.level().getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(12.0D));
        for (LivingEntity livingEntity : list) {
            if (living.isAlive()) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 0));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}