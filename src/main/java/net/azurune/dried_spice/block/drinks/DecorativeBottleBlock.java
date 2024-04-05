package net.azurune.dried_spice.block.drinks;

import net.azurune.dried_spice.block.AbstractDrinkBlock;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.uti.DSStates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class DecorativeBottleBlock extends AbstractDrinkBlock {
    public static final IntegerProperty DECORATIVE_BOTTLES = DSStates.DECORATIVE_BOTTLES;

    public DecorativeBottleBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    //say hello to this shit :skull:
    public void tick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource source) {
        if (this.scanForWater(level, pos)) {
            if (blockState.getValue(DECORATIVE_BOTTLES) == 1) level.setBlock(pos, DSBlocks.DECORATIVE_WATER_BOTTLE.get().defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE), 2);
            if (blockState.getValue(DECORATIVE_BOTTLES) == 2) level.setBlock(pos, DSBlocks.DECORATIVE_WATER_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, Integer.valueOf(2)).setValue(WATERLOGGED, Boolean.FALSE), 2);
            if (blockState.getValue(DECORATIVE_BOTTLES) == 3) level.setBlock(pos, DSBlocks.DECORATIVE_WATER_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, Integer.valueOf(3)).setValue(WATERLOGGED, Boolean.FALSE), 2);
            if (blockState.getValue(DECORATIVE_BOTTLES) == 4) level.setBlock(pos, DSBlocks.DECORATIVE_WATER_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, Integer.valueOf(4)).setValue(WATERLOGGED, Boolean.FALSE), 2);
        }
        level.playSound((Player)null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        if (this.scanForWater(accessor, pos)) {
            accessor.scheduleTick(pos, this, 60 + accessor.getRandom().nextInt(40));

        } else if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }

        return !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() :
                super.updateShape(state, direction, state, accessor, pos, pos1);
    }

    protected boolean scanForWater(BlockGetter getter, BlockPos pos) {
        BlockState state = getter.getBlockState(pos);
        for (Direction direction : Direction.values()) {
            FluidState fluidstate = getter.getFluidState(pos.relative(direction));
            if (state.canBeHydrated(getter, pos, fluidstate, pos.relative(direction))) {
                return true;
            }
        }

        return false;
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockstate = ctx.getLevel().getBlockState(ctx.getClickedPos());
        if (this.scanForWater(ctx.getLevel(), ctx.getClickedPos())) {
            ctx.getLevel().scheduleTick(ctx.getClickedPos(), this, 60 + ctx.getLevel().getRandom().nextInt(40));
        }

        if (blockstate.is(this)) {
            return blockstate.cycle(DECORATIVE_BOTTLES);
        } else {
            FluidState fluidstate = ctx.getLevel().getFluidState(ctx.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(ctx).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }
}
