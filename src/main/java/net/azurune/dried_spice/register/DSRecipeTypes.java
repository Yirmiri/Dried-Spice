package net.azurune.dried_spice.register;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.screen.recipe.TeaKettleRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DSRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, DriedSpice.MODID);

    public static final RegistryObject<RecipeType<TeaKettleRecipe>> BREWING = RECIPE_TYPES.register("brewing", () -> register("brewing"));

    public static <T extends Recipe<?>> RecipeType<T> register(final String identifier) {
        return new RecipeType<>() {
            public String toString() {
                return DriedSpice.MODID + ":" + identifier;
            }
        };
    }
}
