package net.azurune.dried_spice.screen;

import net.azurune.dried_spice.block.entity.TeaKettleBlockEntity;
import net.azurune.dried_spice.screen.slot.TeaKettleCupSlot;
import net.azurune.dried_spice.screen.slot.SimpleOutputSlot;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSMenuTypes;
import net.azurune.dried_spice.screen.slot.TeaKettleIngredientSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class TeaKettleMenu extends AbstractContainerMenu {
    public final TeaKettleBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public TeaKettleMenu(int container, Inventory inventory, FriendlyByteBuf byteBuf) {
        this(container, inventory, inventory.player.level().getBlockEntity(byteBuf.readBlockPos()), new SimpleContainerData(6));
    }

    public TeaKettleMenu(int container, Inventory inv, BlockEntity entity, ContainerData data) {
        super(DSMenuTypes.TEA_KETTLE_MENU.get(), container);
        checkContainerSize(inv, 6);
        blockEntity = ((TeaKettleBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addDataSlots(data);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
            this.addSlot(new TeaKettleIngredientSlot(itemHandler, 1, 18, 13));
            this.addSlot(new TeaKettleIngredientSlot(itemHandler, 2, 36, 13));
            this.addSlot(new TeaKettleIngredientSlot(itemHandler, 3, 18, 31));
            this.addSlot(new TeaKettleIngredientSlot(itemHandler, 4, 36, 31));
            this.addSlot(new TeaKettleCupSlot(itemHandler, 5, 27, 55));
            this.addSlot(new SimpleOutputSlot(itemHandler, 0, 120, 35));
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, DSBlocks.COPPER_TEA_KETTLE.get());
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressBarSize = 12;

        return maxProgress != 0 && progress != 0 ? progress * progressBarSize / maxProgress : 0;
    }

    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int INVENTORY_SLOT_COUNT = 6;
    private static final int INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, INVENTORY_FIRST_SLOT_INDEX, INVENTORY_FIRST_SLOT_INDEX + INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < INVENTORY_FIRST_SLOT_INDEX + INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex" + index);
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
}
