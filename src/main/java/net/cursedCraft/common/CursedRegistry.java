package net.cursedCraft.common;

import net.cursedCraft.common.blockentities.UpcyclingBlockEntity;
import net.cursedCraft.common.blocks.UpcyclingBlock;
import net.cursedCraft.common.items.FloatyStick;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CursedRegistry {

    /**
     * namespace is the namespace to look for resources in. It cannot have spaces or capital letters.
     *
     * path also uses snake case(no caps, underscores to separate words).
     * MC will apply resource jsons with matching path starting in the appropriate folder to this
     * (e.g. a texture in resources/assets/namespace/textures/item/ might be item_name.png, and applied to item_name).
     */
    public static Identifier getID(String path){
        return new Identifier("cursedcraft", path);
    }

    /**
     * Create Block instances with FabricBlockSettings instead of Settings to make use of the API.
     * the field name is all caps snake case because it is a final field/constant.
     * all added objects must be final.
     */
    public static final Block UPCYCLING_MACHINE =  new UpcyclingBlock(FabricBlockSettings.of(Material.WOOD));

    /**
     * BlockItem is a subclass of Item specifically for placing a block.
     */
    public static final Item UPCYCLING_MACHINE_ITEM= new BlockItem(UPCYCLING_MACHINE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item AYBERKSTICK= new FloatyStick(new Item.Settings().group(ItemGroup.TOOLS));

    /**
     * BlockEntityTypes link a BlockEntity to a Block
     * between <> will be the class of the BlockEntity
     */
    public static BlockEntityType<UpcyclingBlockEntity> UPCYCLING_MACHINE_BLOCKENTITY;// Not registered because it's confusing. Looking for a way around using BlockEntity for this.

    public static void register(){

        Registry.register(Registry.BLOCK, getID("upcycling_machine"), UPCYCLING_MACHINE);

        Registry.register(Registry.ITEM, getID("upcycling_machine"), UPCYCLING_MACHINE_ITEM);

        Registry.register(Registry.ITEM, getID("floaty_stick"), AYBERKSTICK);
    };

}
