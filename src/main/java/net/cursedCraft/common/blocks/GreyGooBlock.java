package net.cursedCraft.common.blocks;

import net.cursedCraft.common.CursedRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class GreyGooBlock extends Block {
    public final boolean randomTicks =true;
    public GreyGooBlock(Settings settings) {
        super(settings);
    }

    public boolean hasRandomTicks(BlockState state) {
        return this.randomTicks;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random){


        for(int x = 0; x < 10; x++){
            BlockPos target = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
            if(!world.getBlockState(target).isOf(Blocks.AIR)&& !world.getBlockState(target).isOf(CursedRegistry.GREY_GOO)) {

                world.setBlockState(target, this.getDefaultState());
                return;
            }


        }
        world.removeBlock(pos, false);
    }
}
