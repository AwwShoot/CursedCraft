package net.cursedCraft.common;

import net.cursedCraft.common.blocks.GrayGooBlock;
import net.cursedCraft.common.blocks.GreyGooBlock;
import net.cursedCraft.common.blocks.UpcyclingBlock;
import net.cursedCraft.common.items.FloatyStick;
import net.cursedCraft.common.items.GoldBludgeon;
import net.cursedCraft.common.materials.GoldBludgeonMaterial;
import net.cursedCraft.common.screen.UpcyclingScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
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


    public static final Material GOO= (new FabricMaterialBuilder(MaterialColor.GRAY).burnable().build());

    public static final ToolMaterial GOLD_BLUDGEON_MATERIAL= new GoldBludgeonMaterial(1, 420, 1.3F, 6.0F, 22, () -> {
        return Ingredient.ofItems(Items.GOLD_INGOT);
    });
    /**
     * Create Block instances with FabricBlockSettings instead of Settings to make use of the API.
     * the field name is all caps snake case because it is a final field/constant.
     * all added objects must be final.
     */
    public static final Block UPCYCLING_MACHINE =  new UpcyclingBlock(FabricBlockSettings.of(Material.WOOD));

    public static final Block GREY_GOO =new GreyGooBlock(FabricBlockSettings.of(GOO));
    public static final Block GRAY_GOO =new GrayGooBlock(FabricBlockSettings.of(GOO));

    public static final Block TBPI = new Block(FabricBlockSettings.of(Material.STONE));

    /**
     * BlockItem is a subclass of Item specifically for placing a block.
     */
    public static final Item UPCYCLING_MACHINE_ITEM= new BlockItem(UPCYCLING_MACHINE, new Item.Settings().group(ItemGroup.TOOLS));

    public static final Item GREY_GOO_ITEM=new BlockItem(GREY_GOO, new Item.Settings().group(ItemGroup.MISC));
    public static final Item GRAY_GOO_ITEM=new BlockItem(GRAY_GOO, new Item.Settings().group(ItemGroup.MISC));

    public static final Item AYBERKSTICK= new FloatyStick(new Item.Settings().maxDamage(255).group(ItemGroup.TOOLS));
    public static final Item ANCIENT_SHULKER_RESIDUE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final GoldBludgeon GOLD_BLUDGEON = new GoldBludgeon(GOLD_BLUDGEON_MATERIAL, 0,-3.2F, new Item.Settings().group(ItemGroup.COMBAT));

    public static final Item TBPI_ITEM=new BlockItem(TBPI, new Item.Settings().group(ItemGroup.MISC));

    /**
     * BlockEntityTypes link a BlockEntity to a Block
     * between <> will be the class of the BlockEntity
     */


    /**
     * make screenhandlertypes not final and assign them in the registry
     *
     */
    public static ScreenHandlerType<UpcyclingScreenHandler> UPCYCLING_SCREEN_HANDLER;

    public static void register(){

        Registry.register(Registry.BLOCK, getID("upcycling_machine"), UPCYCLING_MACHINE);
        Registry.register(Registry.BLOCK, getID("grey_goo"), GREY_GOO);
        Registry.register(Registry.BLOCK, getID("gray_goo"), GRAY_GOO);
        Registry.register(Registry.BLOCK, getID("test_block_please_ignore"), TBPI);

        Registry.register(Registry.ITEM, getID("upcycling_machine"), UPCYCLING_MACHINE_ITEM);
        Registry.register(Registry.ITEM, getID("grey_goo"), GREY_GOO_ITEM);
        Registry.register(Registry.ITEM, getID("gray_goo"), GRAY_GOO_ITEM);
        Registry.register(Registry.ITEM, getID("test_block_please_ignore"), TBPI_ITEM);


        Registry.register(Registry.ITEM, getID("floaty_stick"), AYBERKSTICK);
        Registry.register(Registry.ITEM, getID("ancient_shulker_residue"), ANCIENT_SHULKER_RESIDUE);
        Registry.register(Registry.ITEM, getID("gold_bludgeon"), GOLD_BLUDGEON);
        /**
         * Screenhandlers are given the same path as their other related objects.
         *  Use the fabric class ScreenHandler Registry to register screens and pass in a lambda function creating the ScreenHandler in order to circumvent its privated status.
         */
        UPCYCLING_SCREEN_HANDLER= ScreenHandlerRegistry.registerSimple(getID("upcycling_machine"), (int syncId, PlayerInventory inventory) -> {
            return new UpcyclingScreenHandler(UPCYCLING_SCREEN_HANDLER, syncId, inventory, ScreenHandlerContext.EMPTY);
        });


    };

}
