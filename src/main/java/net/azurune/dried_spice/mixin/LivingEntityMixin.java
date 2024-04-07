package net.azurune.dried_spice.mixin;

import net.azurune.dried_spice.mixininterfaces.IMobEffectInstanceMixin;
import net.azurune.dried_spice.register.DSMobEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Shadow @Final
    private Map<MobEffect, MobEffectInstance> activeEffects;

    @Inject(at = @At("HEAD"), method = "tickEffects")
    public void tickEffects(CallbackInfo ci) {
        for (MobEffectInstance mobEffect : this.activeEffects.values()) {
            if (mobEffect instanceof IMobEffectInstanceMixin effect) {
                effect.setEntity((LivingEntity) (Object) this);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void hurt(DamageSource source, float pAmount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (source.is(DamageTypeTags.IS_FIRE) && livingEntity.hasEffect(DSMobEffects.PYROMANIAC.get())) {
            if (livingEntity.tickCount % 20 == 0) {
                if (livingEntity.getHealth() < livingEntity.getMaxHealth()) {
                    livingEntity.heal(1.0F);
                }
            }
            cir.setReturnValue(false);
        }

        if (livingEntity.hasEffect(DSMobEffects.TRAIL_BLAZING.get()) && source.is(DamageTypeTags.IS_FIRE))
            cir.setReturnValue(false);

        if (livingEntity.hasEffect(DSMobEffects.FIRE_SKIN.get())) {
            Entity entity = source.getEntity();
            if (entity != null) entity.setSecondsOnFire(5);
        }
    }
}
