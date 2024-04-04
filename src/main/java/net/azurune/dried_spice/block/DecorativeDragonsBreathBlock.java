package net.azurune.dried_spice.block;

import net.azurune.dried_spice.datagen.DSItemTagProvider;
import net.azurune.dried_spice.register.DSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.ToIntFunction;

public class DecorativeDragonsBreathBlock extends AbstractDrinkBlock {
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (state) -> {
        return 3 * state.getValue(DECORATIVE_BOTTLES);
    };

    public DecorativeDragonsBreathBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide && !player.getMainHandItem().is(DSItemTagProvider.DECORATIVE_BOTTLES)) {
            if (drink(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }
        }

        return drink(level, pos, state, player);
    }

    protected static InteractionResult drink(LevelAccessor accessor, BlockPos pos, BlockState state, Player player) {
        int i = state.getValue(DRANK);
        player.hurt(player.damageSources().dragonBreath(), 69.0F);
        accessor.gameEvent(player, GameEvent.DRINK, pos);
        accessor.playSound((Player) null, pos, SoundEvents.GENERIC_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (state.getValue(DECORATIVE_BOTTLES) == 1) {
            accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 1), 2);
        } else {
            if (state.getValue(DECORATIVE_BOTTLES) == 2) {
                accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 2), 2);
            } else {
                if (state.getValue(DECORATIVE_BOTTLES) == 3) {
                    accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 3), 2);
                } else {
                    if (state.getValue(DECORATIVE_BOTTLES) == 4) {
                        accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 4), 2);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
