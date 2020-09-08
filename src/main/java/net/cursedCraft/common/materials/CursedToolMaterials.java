package net.cursedCraft.common.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum CursedToolMaterials implements ToolMaterial {
    IRON_TIPPED(2, 163, 5.0F, 1.5F, 5, () -> {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }),
    DIAMOND_TIPPED(3, 800, 7.0F, 2.5F, 14, () -> {
        return Ingredient.ofItems(Items.DIAMOND);
    }),
    NETHERITE_TIPPED(4, 1500, 9.0F, 4.0F, 15, () -> {
        return Ingredient.ofItems(Items.NETHERITE_INGOT);
    });


    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;



    private CursedToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantibility, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantibility;
        this.repairIngredient = new Lazy(repairIngredient);
    }
    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
