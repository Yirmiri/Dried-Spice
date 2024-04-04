package net.azurune.dried_spice.mixin;

import com.mojang.authlib.GameProfile;
import net.azurune.dried_spice.DriedSpice;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level level, BlockPos pos, float v, GameProfile profile) {
        super(level, pos, v, profile);
    }

    @Inject(method = "getCloakTextureLocation", at = @At(value = "HEAD"), cancellable = true)
    public void getCloakTextureLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        String username = this.getDisplayName().getString();
        if (DriedSpice.AZURUNE.contains(username)) cir.setReturnValue(new ResourceLocation(DriedSpice.MODID, "textures/capes/azurune.png"));
        if (DriedSpice.COTTON_CANDY.contains(username)) cir.setReturnValue(new ResourceLocation(DriedSpice.MODID, "textures/capes/cotton_candy_light.png"));
        if (DriedSpice.HEX.contains(username)) cir.setReturnValue(new ResourceLocation(DriedSpice.MODID, "textures/capes/hex.png"));
        if (DriedSpice.SUPPORTERS.contains(username)) cir.setReturnValue(new ResourceLocation(DriedSpice.MODID, "textures/capes/ds_supporters.png"));
    }
}
