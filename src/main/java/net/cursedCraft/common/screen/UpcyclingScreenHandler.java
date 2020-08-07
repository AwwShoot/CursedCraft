package net.cursedCraft.common.screen;

import net.cursedCraft.common.CursedRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
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

import static net.minecraft.item.Items.*;

public class UpcyclingScreenHandler extends ScreenHandler {
    private final Inventory result;
    private final Inventory input;
    private final ScreenHandlerContext context;

    // most of this is copy/pasted from the GrindstoneScreenHandler and then edited around.
    public UpcyclingScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
    this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public UpcyclingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(CursedRegistry.UPCYCLING_SCREEN_HANDLER, syncId);
        this.result = new CraftingResultInventory();
        this.input = new SimpleInventory(2){
            // I am not fully certain what this method does, however it is necessary in order for the screen to update properly because you tack on the onContentChanged function when implementing it
            public void markDirty() {
                super.markDirty();
                UpcyclingScreenHandler.this.onContentChanged(this);
            }
        };
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
            // you have to mually implement this method, otherwise nothing happens when you take an item from the output (other than taking the item)
            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                context.run((world, blockPos) -> {

                    world.syncWorldEvent(1042, blockPos, 0);
                });
                UpcyclingScreenHandler.this.input.setStack(0, ItemStack.EMPTY);
                UpcyclingScreenHandler.this.input.setStack(1, ItemStack.EMPTY);
                return stack;
            }
        });
        //This is creating your inventory slots on the screen.
        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        System.out.println("Made a screen :)");
    }

    /**
     * I have placed a number of print statements here to check and test things. It seems to be very redundant, calling this method 3-4 times when something changes on the screen.
     * I have renamed a few instance variables for conveniency. Namely the inputs and the two booleans created at the beginning.
     * For now, nonsensical dummy outputs exist to let me know in game, what the screen is detecting.
     */
    public void updateResult() {
        System.out.println("Got called");
        ItemStack inputOne = this.input.getStack(0);
        ItemStack InputTwo = this.input.getStack(1);
        boolean objectInInput = !inputOne.isEmpty() || !InputTwo.isEmpty();
        boolean bothFull = !inputOne.isEmpty() && !InputTwo.isEmpty();
        if (!objectInInput) {
            System.out.println("empty");
            this.result.setStack(0, new ItemStack(IRON_BARS));
        }else {

            if (inputOne.getCount() > 1 || InputTwo.getCount() > 1 || !bothFull ) {
                this.result.setStack(0, new ItemStack(LEATHER_BOOTS));

                return;
            }



            if (bothFull) {
                System.out.println("Always true my ass");
                if (inputOne.getItem() != InputTwo.getItem()) {
                    this.result.setStack(0, new ItemStack(BREAD));

                    return;
                }



            }
        }


    }

    public void onContentChanged(Inventory inventory) {
        System.out.println("Something changed");
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }

    }
    //If you do not implement this, shift clicking will softlock the game. IMPLEMENT IT.
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            ItemStack itemStack3 = this.input.getStack(0);
            ItemStack itemStack4 = this.input.getStack(1);
            if (index == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return new ItemStack(ICE);
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (index != 0 && index != 1) {
                if (!itemStack3.isEmpty() && !itemStack4.isEmpty()) {
                    if (index >= 3 && index < 30) {
                        if (!this.insertItem(itemStack2, 30, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= 30 && index < 39 && !this.insertItem(itemStack2, 3, 30, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.insertItem(itemStack2, 0, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
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




    public void close(PlayerEntity player) {
        System.out.println("hah");
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, world, this.input);
        });
    }



    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
