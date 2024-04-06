package net.azurune.dried_spice.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LightweightEffect extends MobEffect {
    public LightweightEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "69941E3F-23D9-4F7A-9771-1C2FED6050BD", 1.0, AttributeModifier.Operation.ADDITION);
    }
}
