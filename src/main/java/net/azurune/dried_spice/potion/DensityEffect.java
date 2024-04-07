package net.azurune.dried_spice.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DensityEffect extends MobEffect {
    public DensityEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "9ecc9f09-f331-4ee5-87ad-d1e71b0f8237", 1.0, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
