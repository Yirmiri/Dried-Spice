package net.azurune.dried_spice.screen.slot;

import net.azurune.dried_spice.item.TeaItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TeaKettleIngredientSlot extends SlotItemHandler {
    public TeaKettleIngredientSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }
//actually just the lazy way of doing recipes
    @Override
    public boolean mayPlace(ItemStack stack) {
        return !(stack.getItem() instanceof TeaItem);
    }

    public int getMaxStackSize() {
        return 64;
    }
}
