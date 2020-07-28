package net.cursedCraft.common.screen;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class UpcyclingScreenHandler extends ForgingScreenHandler {
    public UpcyclingScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }
    //public static final ScreenHandlerType<AnvilScreenHandler> UPCYCLING = register("anvil", AnvilScreenHandler::new);

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return false;
    }

    @Override
    protected ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        return null;
    }

    @Override
    protected boolean canUse(BlockState state) {
        return false;
    }

    @Override
    public void updateResult() {

    }
}
