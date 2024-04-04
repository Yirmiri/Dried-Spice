package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class DSRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public DSRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.COPPER_TEA_KETTLE.get(), 1)
                .define('#', Items.COPPER_INGOT).define('@', Items.BUCKET)
                .pattern(" # ")
                .pattern("#@#")
                .pattern("###").unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.STOVE.get(), 1)
                .define('#', Items.IRON_INGOT).define('@', Blocks.MUD_BRICKS).define('&', Blocks.MAGMA_BLOCK)
                .pattern("#&#")
                .pattern("@ @")
                .pattern("###").unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Blocks.FLOWERING_AZALEA_LEAVES, 1).requires(DSBlocks.AZALEA_FLOWER.get(), 1).requires(Blocks.AZALEA_LEAVES)
                .unlockedBy(getHasName(DSBlocks.AZALEA_FLOWER.get()), has(DSBlocks.AZALEA_FLOWER.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Blocks.FLOWERING_AZALEA, 1).requires(DSBlocks.AZALEA_FLOWER.get(), 1).requires(Blocks.AZALEA)
                .unlockedBy(getHasName(DSBlocks.AZALEA_FLOWER.get()), has(DSBlocks.AZALEA_FLOWER.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DSItems.PEPPER_SEEDS.get(), 2).requires(DSItems.PEPPER.get())
                .unlockedBy(getHasName(DSItems.PEPPER.get()), has(DSItems.PEPPER.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DSItems.SOUL_PEPPER_SEEDS.get(), 2).requires(DSItems.SOUL_PEPPER.get())
                .unlockedBy(getHasName(DSItems.SOUL_PEPPER.get()), has(DSItems.SOUL_PEPPER.get())).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.DECORATIVE_BOTTLE.get(), 4)
                .define('#', Items.GLASS)
                .pattern(" # ")
                .pattern("# #")
                .pattern("###").unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.DECORATIVE_WATER_BOTTLE.get(), 8)
                .define('#', DSBlocks.DECORATIVE_BOTTLE.get()).define('@', Items.WATER_BUCKET)
                .pattern("###")
                .pattern("#@#")
                .pattern("###").unlockedBy(getHasName(DSBlocks.DECORATIVE_BOTTLE.get()), has(DSBlocks.DECORATIVE_BOTTLE.get())).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.DECORATIVE_DRAGONS_BREATH_BOTTLE.get(), 8)
                .define('#', DSBlocks.DECORATIVE_BOTTLE.get()).define('@', Items.DRAGON_BREATH)
                .pattern("###")
                .pattern("#@#")
                .pattern("###").unlockedBy(getHasName(DSBlocks.DECORATIVE_BOTTLE.get()), has(DSBlocks.DECORATIVE_BOTTLE.get())).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.DECORATIVE_HONEY_BOTTLE.get(), 8)
                .define('#', DSBlocks.DECORATIVE_BOTTLE.get()).define('@', Items.HONEY_BLOCK)
                .pattern("###")
                .pattern("#@#")
                .pattern("###").unlockedBy(getHasName(DSBlocks.DECORATIVE_BOTTLE.get()), has(DSBlocks.DECORATIVE_BOTTLE.get())).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSBlocks.DECORATIVE_MILK_BOTTLE.get(), 8)
                .define('#', DSBlocks.DECORATIVE_BOTTLE.get()).define('@', Items.MILK_BUCKET)
                .pattern("###")
                .pattern("#@#")
                .pattern("###").unlockedBy(getHasName(DSBlocks.DECORATIVE_BOTTLE.get()), has(DSBlocks.DECORATIVE_BOTTLE.get())).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DSItems.MUD_CUP.get(), 3)
                .define('#', Blocks.PACKED_MUD)
                .pattern("# #")
                .pattern(" # ").unlockedBy(getHasName(Blocks.PACKED_MUD), has(Blocks.PACKED_MUD)).save(output);
    }

    protected static RecipeBuilder fourforfourBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike, 4)
                .define('#', ingredient)
                .pattern("##")
                .pattern("##");
    }
}