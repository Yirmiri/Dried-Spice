package net.azurune.dried_spice.mixin;

import net.azurune.dried_spice.mixininterfaces.IMobEffectInstanceMixin;
import net.azurune.dried_spice.register.DSMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin implements IMobEffectInstanceMixin {

    @Shadow
    int duration;
    @Shadow @Final private MobEffect effect;
    @Unique
    public LivingEntity entity;

    @Inject(at = @At("HEAD"), method = "tickDownDuration", cancellable = true)
    public void tickDownDuration(CallbackInfoReturnable<Integer> cir) {
        if (entity != null) {
            if (this.effect != DSMobEffects.DROWSY.get() && entity.hasEffect(DSMobEffects.DROWSY.get())) {
                cir.setReturnValue(this.duration);
            }
        }
    }

    @Override
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
}
