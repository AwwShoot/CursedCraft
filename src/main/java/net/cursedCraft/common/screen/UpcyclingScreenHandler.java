package net.cursedCraft.common.screen;

import net.cursedCraft.common.CursedRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;

import static net.minecraft.item.Items.BREAD;

public class UpcyclingScreenHandler extends ScreenHandler {
    private final Inventory result;
    private final Inventory input;
    private final ScreenHandlerContext context;


    public UpcyclingScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
    this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public UpcyclingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(CursedRegistry.UPCYCLING_SCREEN_HANDLER, syncId);
        this.result = new CraftingResultInventory();
        this.input = new SimpleInventory(2);
        this.context = context;
        this.addSlot(new Slot(this.input, 0, 49, 19) {
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.input, 1, 49, 40) {
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.addSlot(new Slot(this.result, 2, 129, 34) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }

    }




    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return true;
    }


    protected ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        return new ItemStack(BREAD);
    }


    protected boolean canUse(BlockState state) {
        return true;
    }


    public void updateResult() {
        ItemStack itemStack = this.input.getStack(0);
        ItemStack itemStack2 = this.input.getStack(1);
        boolean oneEmpty = !itemStack.isEmpty() || !itemStack2.isEmpty();
        boolean bothEmpty = !itemStack.isEmpty() && !itemStack2.isEmpty();
        if (!oneEmpty) {
            this.result.setStack(0, ItemStack.EMPTY);
        }else {
            boolean bl3 = !itemStack.isEmpty() && itemStack.getItem() != Items.ENCHANTED_BOOK && !itemStack.hasEnchantments() || !itemStack2.isEmpty() && itemStack2.getItem() != Items.ENCHANTED_BOOK && !itemStack2.hasEnchantments();
            if (itemStack.getCount() > 1 || itemStack2.getCount() > 1 || !bothEmpty && bl3) {
                this.result.setStack(0, ItemStack.EMPTY);
                this.sendContentUpdates();
                return;
            }
            int i = 1;
            int m;
            ItemStack itemStack3;
            if (bothEmpty) {
                if (itemStack.getItem() != itemStack2.getItem()) {
                    this.result.setStack(0, ItemStack.EMPTY);
                    this.sendContentUpdates();
                    return;
                }

                Item item = itemStack.getItem();
                int j = item.getMaxDamage() - itemStack.getDamage();
                int k = item.getMaxDamage() - itemStack2.getDamage();
                int l = j + k + item.getMaxDamage() * 5 / 100;
                m = Math.max(item.getMaxDamage() - l, 0);

                    if (!ItemStack.areEqual(itemStack, itemStack2)) {
                        this.result.setStack(0, ItemStack.EMPTY);
                        this.sendContentUpdates();
                        return;
                    }

                    i = 2;
                }
            }

        this.sendContentUpdates();
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, world, this.input);
        });
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
