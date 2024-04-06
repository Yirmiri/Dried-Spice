package net.azurune.dried_spice.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DensityEffect extends MobEffect {
    public DensityEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "6C3A159B-905D-4EF8-BF87-BC4DADBE19AB", -1.0, AttributeModifier.Operation.ADDITION);
    }
}
