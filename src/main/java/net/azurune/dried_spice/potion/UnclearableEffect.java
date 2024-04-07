package net.azurune.dried_spice.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UnclearableEffect extends MobEffect {
    public UnclearableEffect(MobEffectCategory category, int i) {
        super(category, i);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(ItemStack.EMPTY);
        return ret;
    }
}
