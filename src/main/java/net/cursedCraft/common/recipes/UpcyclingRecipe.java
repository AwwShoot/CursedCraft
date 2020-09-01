package net.cursedCraft.common.recipes;

import com.google.gson.JsonObject;
import net.cursedCraft.common.CursedRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class UpcyclingRecipe implements Recipe<Inventory> {
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;
    private final Identifier id;

    public UpcyclingRecipe(Identifier id, Ingredient base, Ingredient addition, ItemStack result) {
        this.id = id;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public boolean matches(Inventory inv, World world) {
        return this.base.test(inv.getStack(0)) && this.addition.test(inv.getStack(1));
    }

    public ItemStack craft(Inventory inv) {
        ItemStack itemStack = this.result.copy();
        System.out.println("you did it!");

        return itemStack;
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }


    public ItemStack getOutput() {
        return this.result;
    }

    public Identifier getId() {
        return this.id;
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(CursedRegistry.UPCYCLING_MACHINE);
    }

    public RecipeSerializer<?> getSerializer() {
        System.out.println("it checked the serializer");
        return CursedRegistry.UPCYLING_SERIALIZER;
    }

    public RecipeType<?> getType() {
        return CursedRegistry.UPCYCLING;
    }

    public static class Serializer implements RecipeSerializer<UpcyclingRecipe> {
        public UpcyclingRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new UpcyclingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        public UpcyclingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new UpcyclingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        public void write(PacketByteBuf packetByteBuf, UpcyclingRecipe upcyclingRecipe) {
            upcyclingRecipe.base.write(packetByteBuf);
            upcyclingRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(upcyclingRecipe.result);
        }
    }


}
