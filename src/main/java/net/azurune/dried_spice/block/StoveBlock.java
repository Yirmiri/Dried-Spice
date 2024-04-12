package net.azurune.dried_spice.block;

import net.azurune.dried_spice.util.DSDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;

public class StoveBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private final int fireDamage;

    public StoveBlock(int i, BlockBehaviour.Properties properties) {
        super(properties);
        this.fireDamage = i;
        registerDefaultState(defaultBlockState().setValue(LIT, false));
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource source) {
        if (state.getValue(LIT)) {
            double x = (double)pos.getX() + 0.5D;
            double y = pos.getY();
            double z = (double)pos.getZ() + 0.5D;
            if (source.nextDouble() < 0.1D) {
                level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            level.addParticle(ParticleTypes.SMOKE, x, y + 1.1D, z, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldStack = player.getItemInHand(hand);
        Item heldItem = heldStack.getItem();
        if (state.getValue(LIT)) {
            if (heldStack.canPerformAction(ToolActions.SHOVEL_DIG)) {
                extinguish(state, level, pos);
                heldStack.hurtAndBreak(1, player, action -> action.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            } else if (heldItem == Items.WATER_BUCKET) {
                if (!level.isClientSide()) {
                    level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                extinguish(state, level, pos);
                if (!player.isCreative()) {
                    player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            if (heldItem instanceof FlintAndSteelItem) {
                level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                heldStack.hurtAndBreak(1, player, action -> action.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            } else if (heldItem instanceof FireChargeItem) {
                level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                if (!player.isCreative()) {
                    heldStack.shrink(1);
                }
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    public void extinguish(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos, state.setValue(LIT, false), 2);
        double x = (double) pos.getX() + 0.5D;
        double y = pos.getY();
        double z = (double) pos.getZ() + 0.5D;
        level.playLocalSound(x, y, z, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F, false);
    }

    public static boolean burntOut(BlockState blockState) {
        return !blockState.getValue(LIT);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(DSDamageTypes.damageSource(level, DSDamageTypes.STOVE), this.fireDamage);
        }

        super.stepOn(level, pos, state, entity);
    }

    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        BlockPos blockpos = result.getBlockPos();
        if (!level.isClientSide && projectile.isOnFire() && projectile.mayInteract(level, blockpos) && !state.getValue(LIT)) {
            level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
        }
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(LIT, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite()).setValue(LIT, false);
    }

    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
        return false;
    }
}
