package net.azurune.dried_spice.mixin;

import net.azurune.dried_spice.register.DSMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EntityCollisionContext.class)
public class EntityCollisionContextMixin {
    @Shadow @Final @Nullable
    private Entity entity;

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    public void can(FluidState fluidState, FluidState fluidState1, CallbackInfoReturnable<Boolean> cir) {
        if (fluidState.getType() == Fluids.WATER || fluidState1.getType() == Fluids.WATER || fluidState1.getType() == Fluids.FLOWING_WATER || fluidState.getType() == Fluids.FLOWING_WATER)
            if (this.entity instanceof LivingEntity living)
                if (living.hasEffect(DSMobEffects.WATER_WALKING.get())) cir.setReturnValue(true);
    }
}
