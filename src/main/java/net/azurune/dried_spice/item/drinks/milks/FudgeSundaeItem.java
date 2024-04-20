package net.azurune.dried_spice.item.drinks.milks;

import net.azurune.dried_spice.item.drinks.GenericThickDrinkItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FudgeSundaeItem extends GenericThickDrinkItem {
    public FudgeSundaeItem(Properties properties, int speed) {
        super(properties, speed);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.translatable("tooltip.fudge_sundae.drink").withStyle());
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("desc.fudge_sundae.drink").withStyle());
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
