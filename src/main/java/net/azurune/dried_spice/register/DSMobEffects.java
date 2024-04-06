package net.azurune.dried_spice.register;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.potion.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class DSMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DriedSpice.MODID);

    public static final RegistryObject<MobEffect> VULNERABILITY = MOB_EFFECTS.register("vulnerability", () -> new VulnerabilityEffect(MobEffectCategory.HARMFUL, 0x74534f).addAttributeModifier(Attributes.ARMOR, "25A87ACE-6185-486B-842B-D3D6A05f071C", -2.0,AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> HEALTH_REDUCTION = MOB_EFFECTS.register("health_reduction", () -> new HealthReductionEffect(MobEffectCategory.HARMFUL, 0x3b0402).addAttributeModifier(Attributes.MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -2.0,AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> DENSITY = MOB_EFFECTS.register("density", () -> new DensityEffect(MobEffectCategory.NEUTRAL, 0x3b0402).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "6C3A159B-905D-4EF8-BF87-BC4DADBE19AB", -1.0,AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> LIGHTWEIGHT = MOB_EFFECTS.register("lightweight", () -> new LightweightEffect(MobEffectCategory.NEUTRAL, 0x3b0402).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "69941E3F-23D9-4F7A-9771-1C2FED6050BD", 1.0,AttributeModifier.Operation.ADDITION));

    //Effects created by Zeus
    public static final RegistryObject<MobEffect> FAST_FALLING = MOB_EFFECTS.register("fast_falling", () -> new FastFallingEffect(MobEffectCategory.HARMFUL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<MobEffect> DROWSY = MOB_EFFECTS.register("drowsy", () -> new NoSpecialEffect(MobEffectCategory.HARMFUL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> BLOOD_CLOT = MOB_EFFECTS.register("blood_clot", () -> new NoSpecialEffect(MobEffectCategory.HARMFUL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> BUILDING_FATIGUE = MOB_EFFECTS.register("building_fatigue", () -> new NoSpecialEffect(MobEffectCategory.HARMFUL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> TRUE_INVISIBILITY = MOB_EFFECTS.register("true_invisibility", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(0, 0, 0, 0).getRGB()));
    public static final RegistryObject<NoSpecialEffect> FIRE_SKIN = MOB_EFFECTS.register("fire_skin", () -> new NoSpecialEffect(MobEffectCategory.HARMFUL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> FIRE_WALKING = MOB_EFFECTS.register("fire_walking", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> PYROMANIAC = MOB_EFFECTS.register("pyromaniac", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> HYPER_ELASTICITY = MOB_EFFECTS.register("hyper_elasticity", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> STEEL_FEET = MOB_EFFECTS.register("steel_feet", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> ENIGMA = MOB_EFFECTS.register("enigma", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(255, 100, 255).getRGB()));
    public static final RegistryObject<NoSpecialEffect> WATER_WALKING = MOB_EFFECTS.register("water_walking", () -> new NoSpecialEffect(MobEffectCategory.BENEFICIAL, new Color(255, 100, 255).getRGB()));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
