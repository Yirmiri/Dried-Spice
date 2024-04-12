package net.azurune.dried_spice.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.screen.recipe.TeaKettleRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class TeaKettleCategory implements IRecipeCategory<TeaKettleRecipe> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(DriedSpice.MODID, "textures/gui/tea_kettle_jei_TEMPFIX.png");
    public static final ResourceLocation UID = new ResourceLocation(DriedSpice.MODID, "brewing");
    public static final RecipeType<TeaKettleRecipe> BREWING = new RecipeType<>(UID, TeaKettleRecipe.class);
    private final IDrawable icon;
    private final IDrawable background;

    public TeaKettleCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 92);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DSBlocks.COPPER_TEA_KETTLE.get()));
    }

    @Override
    public RecipeType<TeaKettleRecipe> getRecipeType() {
        return BREWING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.dried_spice.tea_kettle");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, @NotNull TeaKettleRecipe recipe, IFocusGroup focuses) {
        int borderSlotSize = 18;
        for (int row = 0; row < 2; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipe.getIngredients().size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, (column * borderSlotSize) + 1, (row * borderSlotSize) + 1).addItemStacks(Arrays.asList(recipe.getIngredients().get(inputIndex).getItems()));
                }
            }
        }

        //builder.addSlot(RecipeIngredientRole.INPUT, 27, 55).addIngredients(recipe.getIngredients().get(0));
        //builder.addSlot(RecipeIngredientRole.INPUT, 18, 13).addIngredients(recipe.getIngredients().get(1));
        //builder.addSlot(RecipeIngredientRole.INPUT, 36, 13).addIngredients(recipe.getIngredients().get(2));
        //builder.addSlot(RecipeIngredientRole.INPUT, 18, 31).addIngredients(recipe.getIngredients().get(3));
        //builder.addSlot(RecipeIngredientRole.INPUT, 36, 31).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 35).addItemStack(recipe.getResultItem(null));
    }
}
