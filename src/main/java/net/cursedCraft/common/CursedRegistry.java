package net.cursedCraft.common;

import net.cursedCraft.CursedCore;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CursedRegistry {

    /**
     * name uses snake case(no caps, underscores to separate words).
     * MC will apply resource jsons with matching names to this (a texture might be item_name.png, and applied to item_name).
     * Create Block instances with FabricBlockSettings instead of Settings to make use of the API.
     */
    public static void register(){
        registerBlock("upcycling_machine", new Block(FabricBlockSettings.of(Material.WOOD)));
    }

    private static Block registerBlock(String name, Block block)
    {
        Registry.register(Registry.BLOCK, new Identifier("CursedCraft", name), block);
        registerItem(name, new BlockItem(block, new Item.Settings().group(ItemGroup.COMBAT)));
        return block;
    }

    public static Item registerItem(String name, Item item)
    {
        Registry.register(Registry.ITEM, new Identifier("CursedCraft", name), item);

        return item;
    }
}
