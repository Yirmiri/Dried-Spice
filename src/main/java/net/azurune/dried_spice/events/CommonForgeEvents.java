package net.azurune.dried_spice.events;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = DriedSpice.MODID)
public class CommonForgeEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void removeEffect(MobEffectEvent.Remove event) {
        if (event.getEffect() != DSMobEffects.DROWSY.get() && event.getEntity().hasEffect(DSMobEffects.DROWSY.get())) event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void heal(LivingHealEvent event) {
        if (event.getEntity().hasEffect(DSMobEffects.BLOOD_CLOT.get())) event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void interactEvent(PlayerInteractEvent event) {
        if (event.getEntity().hasEffect(DSMobEffects.BUILDING_FATIGUE.get())) event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void targetEntity(LivingChangeTargetEvent event) {
        if (event.getNewTarget() == null) return;
        if (event.getNewTarget().hasEffect(DSMobEffects.TRUE_INVISIBILITY.get()) || event.getNewTarget().hasEffect(DSMobEffects.ENIGMA.get())) event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        BlockPos pos = player.blockPosition();
        if (player.hasEffect(DSMobEffects.FIRE_WALKING.get()) && !player.level().getBlockState(pos.below()).isAir() && player.level().getBlockState(pos).isAir()) {
            player.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
        }
    }

    @SubscribeEvent
    public static void livingFallEvent(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        event.setDistance(handleFall(entity, event.getDistance()));
    }

    @SubscribeEvent
    public static void fall(PlayerFlyableFallEvent event) {
        event.setDistance(handleFall(event.getEntity(), event.getDistance()));
    }

    public static float handleFall(LivingEntity entity, float distance) {
        if (entity.hasEffect(DSMobEffects.HYPER_ELASTICITY.get()) && !entity.isCrouching()) {
            double y = distance / 5 / 3;
            entity.setDeltaMovement(entity.getDeltaMovement().x, y, entity.getDeltaMovement().z);
            return (distance / 3);
        }

        if (entity.hasEffect(DSMobEffects.STEEL_FEET.get())) {
            return 0;
        }

        return distance;
    }
}
