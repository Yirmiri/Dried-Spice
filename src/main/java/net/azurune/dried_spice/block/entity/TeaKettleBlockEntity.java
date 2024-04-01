package net.azurune.dried_spice.block.entity;

import net.azurune.dried_spice.block.TeaKettleBlock;
import net.azurune.dried_spice.datagen.DSBlockTagProvider;
import net.azurune.dried_spice.other.screen.TeaKettleMenu;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.azurune.dried_spice.other.recipe.TeaKettleRecipe;
import net.azurune.dried_spice.register.DSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Optional;

public class TeaKettleBlockEntity extends BlockEntity implements MenuProvider {
    private int brewProgress = 0;
    private int maxBrewProgress = 200;
    private static final int INPUT_SLOT_1 = 1;
    private static final int INPUT_SLOT_2 = 2;
    private static final int INPUT_SLOT_3 = 3;
    private static final int INPUT_SLOT_4 = 4;
    private static final int CUP_SLOT = 5;
    private static final int OUTPUT_SLOT = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public TeaKettleBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(DSBlockEntities.TEA_KETTLE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TeaKettleBlockEntity.this.brewProgress;
                    case 1 -> TeaKettleBlockEntity.this.maxBrewProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TeaKettleBlockEntity.this.brewProgress = value;
                    case 1 -> TeaKettleBlockEntity.this.maxBrewProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.dried_spice.tea_kettle");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("tea_kettle.progress", brewProgress);

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        brewProgress = tag.getInt("tea_kettle.progress");
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        BlockState blockState = level.getBlockState(pos.below());
        if(hasRecipe()) {
            if (blockState.is(DSBlockTagProvider.HEAT_BLOCKS)) {
                increaseBrewingProgress();
                setChanged(level, pos, blockState);

                if (hasProgressFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        }
    }

    private void resetProgress() {
        brewProgress = 0;
    }

    private void craftItem() {
        Optional<TeaKettleRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_2, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_3, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_4, 1, false);
        this.itemHandler.extractItem(CUP_SLOT, 1, false);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(), this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        java.util.Optional<TeaKettleRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private java.util.Optional<TeaKettleRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());

        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(TeaKettleRecipe.Type.BREWING, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return brewProgress >= maxBrewProgress;
    }

    private void increaseBrewingProgress() {
        brewProgress++;
    }

    @Nullable @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new TeaKettleMenu(id, inventory, this, this.data);
    }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            TeaKettleBlockEntity.this.playSound(state, SoundEvents.IRON_DOOR_OPEN);
            TeaKettleBlockEntity.this.updateBlockState(state, true);
        }

        protected void onClose(Level level, BlockPos pos, BlockState state) {
            TeaKettleBlockEntity.this.playSound(state, SoundEvents.IRON_DOOR_CLOSE);
            TeaKettleBlockEntity.this.updateBlockState(state, false);
        }

        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int i, int i1) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof TeaKettleMenu;
        }
    };

    void updateBlockState(BlockState state, boolean b) {
        this.level.setBlock(this.getBlockPos(), state.setValue(TeaKettleBlock.OPEN, Boolean.valueOf(b)), 3);
    }

    void playSound(BlockState state, SoundEvent soundEvent) {
        Vec3i vec3i = state.getValue(TeaKettleBlock.FACING).getNormal();
        double x = (double)this.worldPosition.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
        double y = (double)this.worldPosition.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
        double z = (double)this.worldPosition.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
        this.level.playSound((Player)null, x, y, z, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
