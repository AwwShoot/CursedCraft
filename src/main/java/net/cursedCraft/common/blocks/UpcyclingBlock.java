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

import static net.cursedCraft.common.CursedRegistry.UPCYCLING_SCREEN_HANDLER;
import static net.minecraft.screen.ScreenHandlerType.ANVIL;
import static net.minecraft.screen.ScreenHandlerType.GENERIC_9X1;


public class UpcyclingBlock extends Block {
    public UpcyclingBlock(Settings settings) {
        super(settings);
    }

    private static final TranslatableText TITLE = new TranslatableText("piss");

    /**
     * Says it's a deprecated method but it works just fine. This is what runs when the block is right clicked.
     * The returned ActionResult tells the server what you have done.
     */
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            player.incrementStat(Stats.INTERACT_WITH_GRINDSTONE);
            return ActionResult.CONSUME;
        }
    }


    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
            return new UpcyclingScreenHandler(UPCYCLING_SCREEN_HANDLER,i, playerInventory, ScreenHandlerContext.create(world, pos));
        }, TITLE);
    }
}
