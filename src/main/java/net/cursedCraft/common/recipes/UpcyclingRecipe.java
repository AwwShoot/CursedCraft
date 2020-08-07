package net.cursedCraft.common.recipes;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class UpcyclingRecipe implements Recipe<Inventory> {

    public boolean matches(Inventory inv, World world) {
        return false;
    }


    public ItemStack craft(Inventory inv) {
        return null;
    }


    public boolean fits(int width, int height) {
        return false;
    }


    public ItemStack getOutput() {
        return null;
    }

    public Identifier getId() {
        return null;
    }


    public RecipeSerializer<?> getSerializer() {
        return null;
    }


    public RecipeType<?> getType() {
        return null;
    }
}
