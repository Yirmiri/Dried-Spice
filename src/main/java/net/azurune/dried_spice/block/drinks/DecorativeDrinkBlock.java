package net.azurune.dried_spice.block.drinks;

import net.azurune.dried_spice.block.AbstractDrinkBlock;
import net.azurune.dried_spice.datagen.DSItemTagProvider;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.uti.DSStates;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class DecorativeDrinkBlock extends AbstractDrinkBlock {
    public static final IntegerProperty DECORATIVE_BOTTLES = DSStates.DECORATIVE_BOTTLES;

    public DecorativeDrinkBlock(BlockBehaviour.Properties properties) {
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
        accessor.gameEvent(player, GameEvent.DRINK, pos);
        accessor.playSound((Player) null, pos, SoundEvents.GENERIC_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (state.getValue(DECORATIVE_BOTTLES) == 1) {
            if (i < 2) {
                accessor.setBlock(pos, state.setValue(DRANK, i + 1), 3);
            } else {
                accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 1), 2);
            }
        } else {
            if (state.getValue(DECORATIVE_BOTTLES) == 2) {
                if (i < 5) {
                    accessor.setBlock(pos, state.setValue(DRANK, i + 1), 3);
                } else {
                    accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 2), 2);
                }
            } else {
                if (state.getValue(DECORATIVE_BOTTLES) == 3) {
                    if (i < 8) {
                        accessor.setBlock(pos, state.setValue(DRANK, i + 1), 3);
                    } else {
                        accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 3), 2);
                    }
                } else {
                    if (state.getValue(DECORATIVE_BOTTLES) == 4) {
                        if (i < 11) {
                            accessor.setBlock(pos, state.setValue(DRANK, i + 1), 3);
                        } else {
                            accessor.setBlock(pos, DSBlocks.DECORATIVE_BOTTLE.get().defaultBlockState().setValue(DECORATIVE_BOTTLES, 4), 2);
                        }
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
