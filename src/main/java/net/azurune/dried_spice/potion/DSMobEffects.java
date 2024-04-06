package net.azurune.dried_spice.potion;

import net.azurune.dried_spice.DriedSpice;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DSMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DriedSpice.MODID);

    public static final RegistryObject<MobEffect> VULNERABILITY = MOB_EFFECTS.register("vulnerability", () -> new VulnerabilityEffect(MobEffectCategory.HARMFUL, 0x74534f).addAttributeModifier(Attributes.ARMOR, "25A87ACE-6185-486B-842B-D3D6A05f071C", -1.0,AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> HEALTH_REDUCTION = MOB_EFFECTS.register("health_reduction", () -> new HealthReductionEffect(MobEffectCategory.HARMFUL, 0x3b0402).addAttributeModifier(Attributes.MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -2.0,AttributeModifier.Operation.ADDITION));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
