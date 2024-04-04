package net.azurune.dried_spice.register;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.screen.recipe.TeaKettleRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DSRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DriedSpice.MODID);

    public static final RegistryObject<RecipeSerializer<TeaKettleRecipe>> BREWING_SERIALIZER = RECIPE_SERIALIZERS.register("brewing", TeaKettleRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}