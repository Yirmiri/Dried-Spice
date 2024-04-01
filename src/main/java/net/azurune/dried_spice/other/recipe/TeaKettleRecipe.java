package net.azurune.dried_spice.other.recipe;

import com.google.gson.JsonParseException;
import net.azurune.dried_spice.DriedSpice;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TeaKettleRecipe implements Recipe<SimpleContainer> {
    public static final int INGREDIENTS = 5;
    private final NonNullList<Ingredient> ingredient;
    private final ItemStack output;
    private final ResourceLocation id;

    public TeaKettleRecipe(NonNullList<Ingredient> ingredient, ItemStack output, ResourceLocation id) {
        this.ingredient = ingredient;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer inv, Level level) {
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < INGREDIENTS; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        return i == this.ingredient.size() && net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.ingredient) != null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredient;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.BREWING;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.BREWING;
    }

    public static class Type implements RecipeType<TeaKettleRecipe> {
        public static final Type BREWING = new Type();
        public static final String ID = "brewing";
    }

    public static class Serializer implements RecipeSerializer<TeaKettleRecipe> {
        public static final Serializer BREWING = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(DriedSpice.MODID, "brewing");

        @Override
        public TeaKettleRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            final NonNullList<Ingredient> ingredients = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for this recipe");
            } else if (ingredients.size() > TeaKettleRecipe.INGREDIENTS) {
                throw new JsonParseException("The max amount of ingredients allowed in this recipe is " + TeaKettleRecipe.INGREDIENTS);
            } else {
                final ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
                return new TeaKettleRecipe(ingredients, output, recipeId);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Override
        public @Nullable TeaKettleRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf byteBuf) {
            int i = byteBuf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < ingredients.size(); ++j) {
                ingredients.set(j, Ingredient.fromNetwork(byteBuf));
            }

            ItemStack output = byteBuf.readItem();
            return new TeaKettleRecipe(ingredients, output, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf byteBuf, TeaKettleRecipe recipe) {
            byteBuf.writeInt(recipe.ingredient.size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(byteBuf);
            }

            byteBuf.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}
