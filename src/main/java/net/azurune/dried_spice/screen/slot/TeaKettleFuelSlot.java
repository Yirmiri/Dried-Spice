package net.azurune.dried_spice.screen.slot;

import net.azurune.dried_spice.item.GenericDrinkItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TeaKettleFuelSlot extends SlotItemHandler {
    public TeaKettleFuelSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof GenericDrinkItem;
    }
}
