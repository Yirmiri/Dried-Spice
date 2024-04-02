package net.azurune.dried_spice.other.screen.slot;

import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TeaKettleCupSlot extends SlotItemHandler {
    public TeaKettleCupSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof BottleItem;
    }
}
