package net.azurune.dried_spice.uti;

import net.azurune.dried_spice.block.SoulPepperBlock;
import net.azurune.dried_spice.block.StoveBlock;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class DSProperties {

    public class BlockProperties {
        //MISC
        public static final BlockBehaviour.Properties COPPER_TEA_KETTLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 2.0F).sound(SoundType.COPPER);
        public static final BlockBehaviour.Properties IRON_TEA_KETTLE = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASS).strength(2.0F, 2.0F).sound(SoundType.NETHERITE_BLOCK);
        public static final BlockBehaviour.Properties STOVE = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASS).strength(2.0F, 4.0F).sound(SoundType.MUD_BRICKS).lightLevel((blockState) -> { return StoveBlock.burntOut(blockState) ? 3 : 15; });

        //NATURAL
        public static final BlockBehaviour.Properties AZALEA_FLOWER = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.WET_GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY);
        public static final BlockBehaviour.Properties PEPPER_PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().instabreak().sound(SoundType.NETHER_WART).pushReaction(PushReaction.DESTROY);
        public static final BlockBehaviour.Properties SOUL_PEPPER_PLANT = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).noCollission().instabreak().sound(SoundType.NETHER_WART).pushReaction(PushReaction.DESTROY).lightLevel((blockState) -> { return SoulPepperBlock.glowing(blockState) ? 0 : 5; });

        //COMPAT
        public static final BlockBehaviour.Properties SOUL_STOVE = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASS).strength(2.0F, 4.0F).sound(SoundType.MUD_BRICKS).lightLevel((blockState) -> { return StoveBlock.burntOut(blockState) ? 3 : 7; });
    }

    public class Foods {
        //MISC FOODS
        public static final FoodProperties TEA_LEAVES = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().build();
        public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1.0F).alwaysEat().fast().build();
        public static final FoodProperties SOUL_PEPPER = new FoodProperties.Builder().nutrition(4).saturationMod(0.2f).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0), 1.0F).alwaysEat().fast().build();
    }
}
