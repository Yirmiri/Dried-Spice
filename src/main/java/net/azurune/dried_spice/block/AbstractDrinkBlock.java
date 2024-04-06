package net.azurune.dried_spice.block;

import net.azurune.dried_spice.util.DSStates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractDrinkBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty DECORATIVE_BOTTLES = DSStates.DECORATIVE_BOTTLES;
    public static final IntegerProperty DRANK = DSStates.DRANK;

    private static final VoxelShape AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);

    public AbstractDrinkBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
        this.registerDefaultState(this.stateDefinition.any().setValue(DRANK, Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos pos, CollisionContext context) {
        switch (blockState.getValue(DECORATIVE_BOTTLES)) {
            case 1:
            default:
                return AABB;
            case 2:
                return AABB;
            case 3:
                return AABB;
            case 4:
                return AABB; //TO-DO: horizontal facing state with unique hitboxes based on facing direction
        }
    }

    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext ctx) {
        return !ctx.isSecondaryUseActive() && ctx.getItemInHand().getItem() == this.asItem() && blockState.getValue(DECORATIVE_BOTTLES) < 4 || super.canBeReplaced(blockState, ctx);
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockstate = ctx.getLevel().getBlockState(ctx.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.cycle(DECORATIVE_BOTTLES);
        } else {
            FluidState fluidstate = ctx.getLevel().getFluidState(ctx.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(ctx).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (state.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return !state.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() :
                super.updateShape(state, direction, blockState, levelAccessor, blockPos, blockPos1);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, DECORATIVE_BOTTLES, DRANK);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter getter, BlockPos pos, PathComputationType computationType) {
        return false;
    }
}
