package net.azurune.dried_spice.item.drinks;

import net.azurune.dried_spice.item.GenericDrinkItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class MilkDrinkItem extends GenericDrinkItem {
    public MilkDrinkItem(Properties properties, int speed) {
        super(properties, speed);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.translatable("desc.milk.drink").withStyle());
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
