package net.azurune.dried_spice.item;

import net.azurune.dried_spice.register.DSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class CupItem extends Item {
    public CupItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
            BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
            if (blockhitresult.getType() == HitResult.Type.MISS) {
                return InteractionResultHolder.pass(itemstack);
            } else {
                if (blockhitresult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockpos = blockhitresult.getBlockPos();
                    if (!level.mayInteract(player, blockpos)) {
                        return InteractionResultHolder.pass(itemstack);
                    }

                    if (level.getFluidState(blockpos).is(FluidTags.WATER)) {
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
                        return InteractionResultHolder.sidedSuccess(this.turnCupIntoItem(itemstack, player, PotionUtils.setPotion(new ItemStack(DSItems.MUD_CUP_OF_WATER.get()), Potions.WATER)), level.isClientSide());
                    }
                }

                return InteractionResultHolder.pass(itemstack);
            }
        }

    protected ItemStack turnCupIntoItem(ItemStack stack, Player player, ItemStack itemStack) {
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(stack, player, itemStack);
    }
}
