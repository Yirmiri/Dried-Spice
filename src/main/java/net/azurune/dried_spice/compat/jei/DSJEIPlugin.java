package net.azurune.dried_spice.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSRecipeTypes;
import net.azurune.dried_spice.screen.TeaKettleScreen;
import net.azurune.dried_spice.screen.recipe.TeaKettleRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@JeiPlugin
@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class DSJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(DriedSpice.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TeaKettleCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<TeaKettleRecipe> brewingRecipes = recipeManager.getAllRecipesFor(DSRecipeTypes.BREWING.get());
        registration.addRecipes(TeaKettleCategory.BREWING, brewingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(TeaKettleScreen.class, 82, 50, 16, 16, TeaKettleCategory.BREWING);
    }
}
