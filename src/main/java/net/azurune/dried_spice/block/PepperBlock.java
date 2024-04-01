package net.azurune.dried_spice.block;

import net.azurune.dried_spice.register.DSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PepperBlock extends BushBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)};

    public PepperBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(Blocks.SOUL_SAND);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        int i = state.getValue(AGE);
        if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, source.nextInt(10) == 0)) {
            state = state.setValue(AGE, Integer.valueOf(i + 1));
            level.setBlock(pos, state, 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
        }

    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(DSItems.PEPPER_SEEDS.get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
