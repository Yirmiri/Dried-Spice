package net.azurune.dried_spice.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HealthReductionEffect extends MobEffect {
    public HealthReductionEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -2.0, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(ItemStack.EMPTY);
        return ret;
    }
}

