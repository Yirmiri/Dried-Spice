package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DSItemTagProvider extends ItemTagsProvider {
    public static final TagKey<Item> DECORATIVE_BOTTLES = tag("decorative_bottles");

    public DSItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> aSuper, CompletableFuture<TagLookup<Block>> future, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, aSuper, future, DriedSpice.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DSItemTagProvider.DECORATIVE_BOTTLES).add(
                DSBlocks.DECORATIVE_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_WATER_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_HONEY_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_MILK_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_DRAGONS_BREATH_BOTTLE.get().asItem()
        );
    }

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(new ResourceLocation(DriedSpice.MODID, name));
    }
}
