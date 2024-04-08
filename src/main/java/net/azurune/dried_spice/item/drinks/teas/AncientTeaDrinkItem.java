package net.azurune.dried_spice.item.drinks.teas;

import net.azurune.dried_spice.item.drinks.GenericDrinkItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AncientTeaDrinkItem extends GenericDrinkItem {
    public AncientTeaDrinkItem(Properties properties, int speed) {
        super(properties, speed);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.ancient_tea.drink").withStyle());
        } else {
            components.add(Component.translatable("tooltip.shift.information").withStyle());
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("desc.ancient_tea.drink").withStyle());
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
