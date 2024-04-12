package net.azurune.dried_spice.util;

import net.azurune.dried_spice.DriedSpice;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class DSDamageTypes {
    public static final ResourceKey<DamageType> COFFEE_BUSH = create("coffee_bush");
    public static final ResourceKey<DamageType> STOVE = create("stove");
    public static final ResourceKey<DamageType> SOUL_PEPPER = create("soul_pepper");

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(COFFEE_BUSH, new DamageType("dried_spice.coffee_bush", 0.1F, DamageEffects.POKING));
        context.register(STOVE, new DamageType("dried_spice.stove", 0.1F, DamageEffects.BURNING));
        context.register(SOUL_PEPPER, new DamageType("dried_spice.soul_pepper", 0.1F, DamageEffects.BURNING));
    }

    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(DriedSpice.MODID, name));
    }

    public static DamageSource damageSource(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }
}
