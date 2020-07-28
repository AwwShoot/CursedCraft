package net.cursedCraft.common.blocks;

import net.cursedCraft.common.screen.UpcyclingScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class UpcyclingBlock extends Block {
    public UpcyclingBlock(Settings settings) {
        super(settings);
    }
    private static final TranslatableText TITLE = new TranslatableText("piss");

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }


    //public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
    //    return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
   //         return new UpcyclingScreenHandler(i, playerInventory, ScreenHandlerContext.create(world, pos));
    //    }, TITLE);
  //  }
}
