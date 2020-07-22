package net.cursedCraft.common.blockentities;

import net.cursedCraft.common.CursedRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

public class UpcyclingBlockEntity extends BlockEntity {
    public UpcyclingBlockEntity(BlockEntityType<?> type) {
        super(CursedRegistry.UPCYCLING_MACHINE_BLOCKENTITY);
    }
}
