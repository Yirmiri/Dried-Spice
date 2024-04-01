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

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(level.damageSources().hotFloor(), 2);
        }

        super.stepOn(level, pos, state, entity);
    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(DSItems.SOUL_PEPPER_SEEDS.get());
    }
}
