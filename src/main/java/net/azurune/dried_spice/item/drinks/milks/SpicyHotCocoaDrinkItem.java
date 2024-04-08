package net.azurune.dried_spice.item.drinks.milks;

import net.azurune.dried_spice.item.drinks.GenericDrinkItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SpicyHotCocoaDrinkItem extends GenericDrinkItem {
    public SpicyHotCocoaDrinkItem(Properties properties, int speed) {
        super(properties, speed);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.spicy_hot_cocoa.drink").withStyle());
        } else {
            components.add(Component.translatable("tooltip.shift.information").withStyle());
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("desc.spicy_hot_cocoa.drink").withStyle());
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
