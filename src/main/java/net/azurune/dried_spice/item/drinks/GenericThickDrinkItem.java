package net.azurune.dried_spice.item.drinks;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class GenericThickDrinkItem extends GenericDrinkItem {
    public GenericThickDrinkItem(Properties properties, int speed) {
        super(properties, speed);
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
