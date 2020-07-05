package net.cursedCraft.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CursedRegistry {

    /**
     * Create Block instances with FabricBlockSettings instead of Settings to make use of the API.
     * the field name is all caps snake case because it is a final field/constant.
     * all added objects must be final.
     */

    public static final Block UPCYCLING_MACHINE =  new Block(FabricBlockSettings.of(Material.WOOD));


    public static final Item UPCYCLING_MACHINE_BLOCK= new BlockItem(UPCYCLING_MACHINE, new Item.Settings().group(ItemGroup.TOOLS));

    public static void register(){
        /**
         * namespace is the namespace to look for resources in. It cannot have spaces or capital letters.
         *
         * path also uses snake case(no caps, underscores to separate words).
         * MC will apply resource jsons with matching path starting in the appropriate folder to this
         * (e.g. a texture in resources/assets/namespace/textures/item/ might be item_name.png, and applied to item_name).
         */
        Registry.register(Registry.BLOCK, new Identifier("cursedcraft", "upcycling_machine"), UPCYCLING_MACHINE);

        /**
         * BlockItem is a subclass of Item specifically for placing a block.
         *
         */
        Registry.register(Registry.ITEM, new Identifier("cursedcraft", "upcycling_machine"), new BlockItem(UPCYCLING_MACHINE, new Item.Settings().group(ItemGroup.TOOLS)));
    };
}
