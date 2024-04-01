package net.azurune.dried_spice.block;

import net.azurune.dried_spice.register.DSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SoulPepperBlock extends PepperBlock {
    public SoulPepperBlock(Properties properties) {
        super(properties);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        int i = state.getValue(AGE);
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity) && i < 2) {
            entity.hurt(level.damageSources().hotFloor(), 2);
        }

        super.entityInside(state, level, pos, entity);
    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(DSItems.SOUL_PEPPER_SEEDS.get());
    }

    public static boolean glowing(BlockState state) {
        int i = state.getValue(AGE);
        return i < 2;
    }
}
