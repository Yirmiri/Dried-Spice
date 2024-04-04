package net.azurune.dried_spice.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSRecipeTypes;
import net.azurune.dried_spice.screen.recipe.TeaKettleRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEICompat implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(DriedSpice.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TeaKettleJEICategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<TeaKettleRecipe> kettleRecipes = recipeManager.getAllRecipesFor(DSRecipeTypes.BREWING.get());
        registration.addRecipes(TeaKettleJEICategory.TEA_KETTLE, kettleRecipes);
    }
}
