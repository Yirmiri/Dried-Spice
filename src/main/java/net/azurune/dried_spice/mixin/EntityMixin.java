package net.azurune.dried_spice.mixin;

import net.azurune.dried_spice.register.DSMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IForgeEntity {

    /**
     * Not using {@link net.minecraft.client.renderer.entity.LivingEntityRenderer} <br>
     * because it doesn't stop shadow rendering.
     */
    @Inject(at = @At("HEAD"), method = "shouldRender", cancellable = true)
    public void shouldRender(CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof LivingEntity living)
            if (living.hasEffect(DSMobEffects.TRUE_INVISIBILITY.get())) {
                cir.setReturnValue(false);
            }
    }

    @Inject(at = @At("HEAD"), method = "isColliding", cancellable = true)
    public void isColliding(BlockPos pPos, BlockState pState, CallbackInfoReturnable<Boolean> cir) {
        if (pState.is(Blocks.WATER))
            cir.setReturnValue(true);
    }
}
