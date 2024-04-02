package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DSBlockTagProvider extends BlockTagsProvider {
    public static final TagKey<Block> HEAT_BLOCKS = tag("heat_sources");

    public DSBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DriedSpice.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(DSBlockTagProvider.HEAT_BLOCKS).add(
                DSBlocks.STOVE.get(),
                DSBlocks.SOUL_PEPPER_CROP.get(),
                Blocks.MAGMA_BLOCK,
                Blocks.CAMPFIRE,
                Blocks.SOUL_CAMPFIRE,
                Blocks.FIRE,
                Blocks.SOUL_FIRE
        );
    }

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(DriedSpice.MODID, name));
    }
}
